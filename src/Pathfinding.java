import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;


public class Pathfinding {

    private static final ArrayList<ArrayList<Node>> allNodes = new ArrayList<>();

    // Advent of Code 2022 - Day 12 - Hill Climbing Algorithm
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Dev\\Java\\Text_files\\input2.txt";
        File file = new File(fileName);
        ArrayList<String> inputLines = new ArrayList<>();

        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(inputLines::add);
        }

//        for (String line: inputLines){
//            System.out.println("Current line: " + line);
//        }

        Graph graph = new Graph();

        for (String line: inputLines){
            char[] lineCharacters = line.toCharArray();
            ArrayList<Node> currentNodes = new ArrayList<>();

            for (char c: lineCharacters){
                int charValue = getAsciiValue(c);
                Node newNode = new Node(String.valueOf(c), charValue);

                currentNodes.add(newNode);
                graph.addNode(newNode);
            }
            allNodes.add(currentNodes);
        }

//        System.out.println(allNodes.get(0).size());
//
//        for (Node node: allNodes.get(0)){
//            System.out.println("Name: " + node.getName() + " Value: " + node.getHeight());
//        }


        int northRow;
        int southRow;
        int westCol;
        int eastCol;
        int listLength = allNodes.size();

        for (int row=0; row<listLength; row++){
            ArrayList<Node> currentRow = allNodes.get(row);

            for (int col=0; col<currentRow.size(); col++){
                Node currentNode = allNodes.get(row).get(col);

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

                int[] rowColDetails = {row, col, northRow, southRow, eastCol, westCol};
                ArrayList<Node> neighbours = findNeighbours(currentNode, rowColDetails);
                for (Node neighbour: neighbours){
                    graph.addEdge(currentNode, neighbour, 1);
                }

            }
        }

        graph.displayGraph();
        ArrayList<Node> path = graph.dijkstraPath("S", "E");
        System.out.println(path.size()-1);
    }

    public static ArrayList<Node> findNeighbours(Node node, int[] rowColDetails){
        int currentRow = rowColDetails[0];
        int currentCol = rowColDetails[1];
        int northRow = rowColDetails[2];
        int southRow = rowColDetails[3];
        int eastCol = rowColDetails[4];
        int westCol = rowColDetails[5];

        Node[] neighbourNodes = new Node[4];
        // n, s, e, w
        neighbourNodes[0] = allNodes.get(northRow).get(currentCol);
        neighbourNodes[1] = allNodes.get(southRow).get(currentCol);
        neighbourNodes[2] = allNodes.get(currentRow).get(eastCol);
        neighbourNodes[3] = allNodes.get(currentRow).get(westCol);

        ArrayList<Node> neighbours = new ArrayList<>();
        for (Node neighbour: neighbourNodes){
            int nodeHeight = node.getHeight();
            int neighbourHeight = neighbour.getHeight();
            if (validHeight(nodeHeight, neighbourHeight)){
                if (neighbour.getId() != node.getId()) {
                    neighbours.add(neighbour);
                }
            }

        }
        return neighbours;
    }

    public static boolean validHeight(int nodeHeight, int neighbourHeight){
        boolean tooHigh = neighbourHeight - nodeHeight > 1;
        boolean tooLow = nodeHeight - neighbourHeight > 1;
        return !(tooHigh || tooLow);
    }

    public static int getAsciiValue(char character){
        int value = (int) character;

        if (character == 'S'){
            value = 0;
        } else if (character == 'E'){
            value = 27;
        } else {
            value -= 96;
        }

        return value;
    }



    public static void graphDemo(String[] args){
        String[] stations = {
                "Oxford",
                "London",
                "Bournemouth",
                "Plymouth",
                "Reading",
                "Swindon",
                "Bristol",
                "Bath"
        };

//        Graph graph = new Graph();
//
//        for (String station: stations){
//            graph.addNode(new Node(station, 0));
//        }
//
//        graph.addEdge("Oxford", "London", 60);
//        graph.addEdge("Oxford", "Reading", 25);
//        graph.addEdge("Oxford", "Bath", 75);
//        graph.addEdge("London", "Reading", 40);
//        graph.addEdge("London", "Bournemouth", 110);
//        graph.addEdge("Bournemouth", "Plymouth", 95);
//        graph.addEdge("Bournemouth", "Reading", 95);
//        graph.addEdge("Plymouth", "Bristol", 50);
//        graph.addEdge("Bristol", "Bath", 12);
//        graph.addEdge("Bath", "Swindon", 35);
//        graph.addEdge("Bath", "Bournemouth", 50);
//        graph.addEdge("Bath", "Reading", 65);
//
//        graph.displayGraph();
//
//        for (Node node: graph.breadthFirstSearch()){
//            System.out.println(node.getName());
//        }
//        System.out.println("\n\n");
//
//        for (Node node: graph.dijkstraPath("Oxford", "Plymouth")){
//            System.out.println(node.getName());
//        }

    }
}
