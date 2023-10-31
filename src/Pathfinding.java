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
            graph.getAllNodes().add(currentNodes);
        }

//        System.out.println(allNodes.get(0).size());
//
//        for (Node node: allNodes.get(0)){
//            System.out.println("Name: " + node.getName() + " Value: " + node.getHeight());
//        }


        for (int row=0; row<graph.getAllNodes().size(); row++){
            for (int col=0; col<graph.getAllNodes().get(0).size(); col++){
                graph.getAllNodes().get(row).get(col).setRowColumn(row, col);
            }
        }



        graph.displayGraph();
        ArrayList<Node> path = graph.dijkstraPath("S", "E");
        StringBuilder pathStr = new StringBuilder();
        for (Node node: path){
            pathStr.append(node.getName());
            pathStr.append(", ");
        }
        long endTime = System.currentTimeMillis();
        long timeSec = (long) ((endTime - startTime) / 1000F);
        long timeMin = (long) (timeSec / 60F);

        System.out.println("Total execution time (seconds): " + (endTime - startTime) / 1000F);
        System.out.println("Total execution time (minutes): " + ((endTime - startTime) / 1000F) / 60F);
        System.out.println(pathStr.toString());
        System.out.println(path.size()-1);
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

        for (Node node: graph.breadthFirstSearch()){
            System.out.println(node.getName());
        }
        System.out.println("\n\n");

        for (Node node: graph.dijkstraPath("Oxford", "Plymouth")){
            System.out.println(node.getName());
        }

    }
}
