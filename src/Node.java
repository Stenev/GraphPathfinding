import java.util.HashMap;
import java.util.Map;

public class Node {

    private final String name;
    private final Map<Node, Integer> neighbours;
    private Integer pathValue;
    private Node previous;


    public Node(String name){
        this.name = name;
        this.neighbours = new HashMap<>();
        this.pathValue = Integer.MAX_VALUE;
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
        return this.neighbours;
    }

    public Node getPrevious(){
        return this.previous;
    }

    public void setPrevious(Node previous){
        this.previous = previous;
    }

    public Integer getPathValue(){
        return this.pathValue;
    }

    public void setPathValue(Integer pathValue){
        this.pathValue = pathValue;
    }
}