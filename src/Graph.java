import java.util.ArrayList;
import java.util.Stack;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private final ArrayList<ArrayList<Node>> allNodes;
    private final ArrayList<Node> nodes;
    private int numberOfNodes;

    public Graph(){
        this.nodes = new ArrayList<>();
        this.allNodes = new ArrayList<>();
    }

    public ArrayList<ArrayList<Node>> getAllNodes(){
        return this.allNodes;
    }

    public ArrayList<Node> getNodes(){
        return this.nodes;
    }

    public void addNode(Node node){
        node.setId(numberOfNodes);
        nodes.add(node);
        numberOfNodes++;
    }

    public void addEdge(Node node1, Node node2, int weight){
        node1.addNeighbour(node2, weight);
        node2.addNeighbour(node1, weight);
    }

    public void addEdge(String nodeS1, String nodeS2, int weight){
        Node node1 = null;
        Node node2 = null;

        for (Node node: nodes){
            String currentNodeName = node.getName();
            if (currentNodeName.equals(nodeS1)){
                node1 = node;
            } else if (currentNodeName.equals(nodeS2)){
                node2 = node;
            }
        }

        if (node1 != null && node2 != null){
            addEdge(node1, node2, weight);
        } else {
            System.out.println("Invalid nodes, can't add edge");
        }
    }

    public Node getNodeByName(String name){
            for (Node node: nodes){
                if (node.getName().equals(name)){
                   return node;
                }
            }
        return null;
    }

    public void resetGraph(){
        for (Node node: nodes){
            node.setPrevious(null);
            node.setPathValue(Integer.MAX_VALUE);
            node.clearNeighbours();
        }
    }

    public void findAndSetNeighbours(Node node){
        int northRow;
        int southRow;
        int westCol;
        int eastCol;
        int listLength = allNodes.size();
        int row = node.getRow();
        int col  = node.getColumn();
        ArrayList<Node> currentRow = allNodes.get(0);


        if (row == 0){
            northRow = 0;
            southRow = 1;
        } else if (row == listLength-1) {
            northRow = row-1;
            southRow = row;
        } else {
            northRow = row-1;
            southRow = row+1;
        }

        if (col == 0){
            westCol = 0;
            eastCol = 1;
        } else if (col == currentRow.size()-1){
            westCol = col-1;
            eastCol = col;
        } else {
            westCol = col-1;
            eastCol = col+1;
        }

        ArrayList<Node> neighbours = new ArrayList<>();
        Node[] neighbourNodes = new Node[4];
        // n, s, e, w
        neighbourNodes[0] = allNodes.get(northRow).get(col);
        neighbourNodes[1] = allNodes.get(southRow).get(col);
        neighbourNodes[2] = allNodes.get(row).get(eastCol);
        neighbourNodes[3] = allNodes.get(row).get(westCol);

        for (Node neighbour: neighbourNodes){
            int nodeHeight = node.getHeight();
            int neighbourHeight = neighbour.getHeight();
            if (!(neighbourHeight - nodeHeight > 1)){ // forward
            //if (!(nodeHeight - neighbourHeight > 1)){ // reverse
                if (neighbour.getId() != node.getId()) {
                    neighbours.add(neighbour);
                }
            }
        }

        for (Node neighbour: neighbours){
            node.addNeighbour(neighbour, 1);
        }
    }


    public ArrayList<Node> dijkstraPath(Node startNode, String _endNode){
        resetGraph();
        ArrayList<Node> path = new ArrayList<>();

        ArrayList<Node> closed = new ArrayList<>();
        ArrayList<Node> open = new ArrayList<>(nodes);
        Node endNode = getNodeByName(_endNode);
        Node currentNode = null;

        startNode.setPathValue(0);
        int currentPathValue;
        int minPathValue;

        while (!closed.contains(endNode)){
            minPathValue = Integer.MAX_VALUE;
            for (Node node: open){
                currentPathValue = node.getPathValue();
                if (currentPathValue < minPathValue){
                    currentNode = node;
                    minPathValue = currentPathValue;
                }
            }

            //System.out.println("Current Node: " + currentNode.getName() + ", ID: " + currentNode.getId());
            System.out.println(open.size());

            open.remove(currentNode);
            closed.add(currentNode);

            int combinedPathValue;
            if (currentNode != endNode){
                findAndSetNeighbours(currentNode);
                for (Map.Entry<Node, Integer> neighbour: currentNode.getNeighbours().entrySet()){
                    if (open.contains(neighbour.getKey())){
                        Node neighbourNode = neighbour.getKey();
                        int edgeWeight = neighbour.getValue();
                        combinedPathValue = currentNode.getPathValue() + edgeWeight;
                        if (combinedPathValue < neighbourNode.getPathValue()){
                            neighbourNode.setPathValue(combinedPathValue);
                            neighbourNode.setPrevious(currentNode);
                        }
                    }
                }
            }
        }

        currentNode = endNode;
        while (currentNode != null){
            path.add(currentNode);
            currentNode = currentNode.getPrevious();
        }

        Collections.reverse(path);
        return path;
    }

    /**
     *
     * @param node the starting node
     * @return list of nodes in the search
     */
    public ArrayList<Node> breadthFirstSearch(Node node){
        resetGraph();
        ArrayList<Node> results = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()){
            Node currentNode = queue.poll();
            if (!results.contains(currentNode)){
                findAndSetNeighbours(currentNode);
                results.add(currentNode);
                queue.addAll(currentNode.getNeighbours().keySet());
            }
        }
        return results;
    }

    public ArrayList<Node> depthFirstSearch(){
        resetGraph();
        ArrayList<Node> results = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(0));

        while (!stack.empty()){
            Node currentNode = stack.pop();
            if (!results.contains(currentNode)){
                findAndSetNeighbours(currentNode);
                results.add(currentNode);
                for (Node neighbour: currentNode.getNeighbours().keySet()){
                    if (!results.contains(neighbour)){
                        stack.push(neighbour);
                    }
                }
            }
         }
        return results;
    }

    public void displayGraph(){
        for (Node node: nodes){
            StringBuilder nodeDetails = new StringBuilder(node.getName() + ": ");
            for (Node neighbour: node.getNeighbours().keySet()){
                nodeDetails.append(neighbour.getName()).append(", ");
            }
            System.out.println(nodeDetails);
        }
        System.out.println();
    }
}