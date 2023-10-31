import java.util.HashMap;
import java.util.Map;

public class Node {

    private int id;
    private final String name;
    private final Map<Node, Integer> neighbours;
    private final int height;
    private int pathValue;
    private Node previous;

    private int row;
    private int column;


    public Node(String name, int height){
        this.name = name;
        this.neighbours = new HashMap<>();
        this.pathValue = Integer.MAX_VALUE;
        this.height = height;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void setRowColumn(int row, int column){
        this.row = row;
        this.column = column;
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

    public int getHeight(){
        return this.height;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}