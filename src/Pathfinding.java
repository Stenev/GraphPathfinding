import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;


public class Pathfinding {



    // Advent of Code 2022 - Day 12 - Hill Climbing Algorithm
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String fileName = "C:\\Dev\\Java\\Text_files\\input.txt";
        File file = new File(fileName);
        ArrayList<String> inputLines = new ArrayList<>();

        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(inputLines::add);
        }

        // Add nodes to graph
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
            graph.getAllNodes().add(currentNodes);
        }


        // Set node x, y and neighbours
        for (int row=0; row<graph.getAllNodes().size(); row++){
            for (int col=0; col<graph.getAllNodes().get(0).size(); col++){
                Node currentNode = graph.getAllNodes().get(row).get(col);
                currentNode.setRowColumn(row, col);
            }
        }

        //graph.displayGraph();


        ArrayList<Node> startPoints = new ArrayList<>();

        for (int row=0; row<graph.getAllNodes().size(); row++) {
            for (int col=0; col < graph.getAllNodes().get(0).size(); col++) {
                Node currentNode = graph.getAllNodes().get(row).get(col);
                if (currentNode.getName().equals("a") && validPath(graph, currentNode)){
                    startPoints.add(currentNode);
                }
            }
        }


        int pathLength;
        ArrayList<Node> shortestPath = new ArrayList<>();
        int minPathLength = Integer.MAX_VALUE;

        for (Node node: startPoints) {
            graph.resetGraph();
            ArrayList<Node> path = graph.dijkstraPath(node, "E");
            pathLength = path.size();
            if (pathLength <= minPathLength){
                minPathLength = pathLength;
                shortestPath = path;
            }
        }

        //shortestPath = graph.dijkstraPath(graph.getNodeByName("S"), "E");
        StringBuilder pathStr = new StringBuilder();
        for (Node node: shortestPath){
            pathStr.append(node.getName());
            pathStr.append(", ");
        }

        System.out.println(pathStr);
        System.out.println(shortestPath.size()-1);


        long endTime = System.currentTimeMillis();
        long timeSec = (long) ((endTime - startTime) / 1000F);
        long timeMin = (long) (timeSec / 60F);

        System.out.println("Total execution time (seconds): " + (endTime - startTime) / 1000F);
        System.out.println("Total execution time (minutes): " + ((endTime - startTime) / 1000F) / 60F);
    }


    public static boolean validPath(Graph graph, Node startPoint){
        ArrayList<Node> bfsPath = graph.breadthFirstSearch(startPoint);

        for (Node node: bfsPath){
            if (node.getName().equals("E")){
                return true;
            }
        }
        return false;
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

        Graph graph = new Graph();

        for (String station: stations){
            graph.addNode(new Node(station, 0));
        }

        graph.addEdge("Oxford", "London", 60);
        graph.addEdge("Oxford", "Reading", 25);
        graph.addEdge("Oxford", "Bath", 75);
        graph.addEdge("London", "Reading", 40);
        graph.addEdge("London", "Bournemouth", 110);
        graph.addEdge("Bournemouth", "Plymouth", 95);
        graph.addEdge("Bournemouth", "Reading", 95);
        graph.addEdge("Plymouth", "Bristol", 50);
        graph.addEdge("Bristol", "Bath", 12);
        graph.addEdge("Bath", "Swindon", 35);
        graph.addEdge("Bath", "Bournemouth", 50);
        graph.addEdge("Bath", "Reading", 65);

        graph.displayGraph();



        for (Node node: graph.breadthFirstSearch(graph.getNodes().get(0))){
            System.out.println(node.getName());
        }
        System.out.println("\n\n");

        for (Node node: graph.dijkstraPath(graph.getNodeByName("Oxford"), "Plymouth")){
            System.out.println(node.getName());
        }

    }
}
