//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {

    private final String name;
    private final Map<Node, Integer> neighbours;


    public Node(String name){
        this.name = name;
        neighbours = new HashMap<>();
    }

    public void addNeighbour(Node node, int weight){
        if (!neighbours.containsKey(node)) {
            neighbours.put(node, weight);
        }
    }

    public String getName(){
        return this.name;
    }

    public Map<Node, Integer> getNeighbours(){
        return neighbours;
    }
}