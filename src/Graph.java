import java.util.ArrayList;
import java.util.Stack;

public class Graph {

    private final ArrayList<Node> nodes;

    public Graph(){
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2){
        node1.addNeighbour(node2);
        node2.addNeighbour(node1);
    }

    public void addEdge(String nodeS1, String nodeS2){
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
            addEdge(node1, node2);
        } else {
            System.out.println("Invalid nodes, can't add edge");
        }
    }

    public ArrayList<Node> getNeighbours(Node node){
        return node.getNeighbours();
    }

    public ArrayList<Node> depthFirstSearch(){
        ArrayList<Node> results = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(7));

        while (!stack.empty()){
            Node currentNode = stack.pop();
            if (!results.contains(currentNode)){
                results.add(currentNode);
                for (Node neighbour: currentNode.getNeighbours()){
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
            for (Node neighbour: node.getNeighbours()){
                nodeDetails.append(neighbour.getName()).append(", ");
            }
            System.out.println(nodeDetails);
        }
        System.out.println();
    }
}