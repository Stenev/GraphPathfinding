import java.util.HashMap;
import java.util.Map;

public class Node {

    private final String name;
    private final Map<Node, Integer> neighbours;

    private final int x;
    private final int y;
    private int cumulativeCost;
    private double distanceToTarget;
    private double estimatedCost;
    private Node previous;


    public Node(String name, int x, int y){
        this.name = name;
        this.neighbours = new HashMap<>();
        this.x = x;
        this.y = y;
    }

    public void calcDistanceToTarget(int targetX, int targetY){
        // 1 = source, 2 = destination
        // A* euclidean distance = Square root of ((x2 - x1)^2 + (y2 - y1)^2)
        double distanceX = Math.pow((targetX - this.x), 2);
        double distanceY = Math.pow((targetY - this.y), 2);
        distanceToTarget = Math.sqrt((distanceX + distanceY));
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

    public Integer getCumulativeCost(){
        return this.cumulativeCost;
    }

    public void setCumulativeCost(int cumulativeCost){
        this.cumulativeCost = cumulativeCost;
    }

    public void setDistanceToTarget(double distanceToTarget){
        this.distanceToTarget = distanceToTarget;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getDistanceToTarget() {
        return this.distanceToTarget;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public void setEstimatedCost(){
        this.estimatedCost = cumulativeCost + distanceToTarget;
    }

}