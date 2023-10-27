import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private final String name;
    private ArrayList<Node> neighbours;


    public Node(String name){
        this.name = name;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour(Node node){ //, int weight){
        if (!neighbours.contains(node)) {
            neighbours.add(node); //, weight);
        }
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Node> getNeighbours(){
        return neighbours;
    }

}
