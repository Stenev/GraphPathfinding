import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;


public class Pathfinding {

    // Advent of Code 2022 - Day 12 - Hill Climbing Algorithm
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Dev\\Java\\Text_files\\input2.txt";
        File file = new File(fileName);
        ArrayList<String> inputLines = new ArrayList<>();

        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(inputLines::add);
        }

        for (String line: inputLines){
            System.out.println("Current line: " + line);
        }


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
            graph.addNode(new Node(station));
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
