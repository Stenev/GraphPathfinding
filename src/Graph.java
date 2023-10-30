import java.util.ArrayList;
import java.util.Stack;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private final ArrayList<Node> nodes;

    public Graph(){
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node){
        nodes.add(node);
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

    public ArrayList<Node> dijkstraPath(String _startNode, String _endNode){
        ArrayList<Node> results = new ArrayList<>();

        ArrayList<Node> closed = new ArrayList<>();
        ArrayList<Node> open = new ArrayList<>(nodes);
        Node startNode = getNodeByName(_startNode);
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

            open.remove(currentNode);
            closed.add(currentNode);

            int combinedPathValue;
            if (currentNode != endNode){
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
            results.add(currentNode);
            currentNode = currentNode.getPrevious();
        }

        Collections.reverse(results);
        return results;
    }

    public ArrayList<Node> breadthFirstSearch(){
        ArrayList<Node> results = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes.get(0));

        while (!queue.isEmpty()){
            Node currentNode = queue.poll();
            if (!results.contains(currentNode)){
                results.add(currentNode);
                queue.addAll(currentNode.getNeighbours().keySet());
            }
        }
        return results;
    }

    public ArrayList<Node> depthFirstSearch(){
        ArrayList<Node> results = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(0));

        while (!stack.empty()){
            Node currentNode = stack.pop();
            if (!results.contains(currentNode)){
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