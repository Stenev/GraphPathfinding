
public class Pathfinding {

    public static void main(String[] args){
        String[][] stations = {
                {"Oxford", "150", "15"},
                {"London", "240", "100"},
                {"Bournemouth", "130", "230"},
                {"Plymouth", "10", "250"},
                {"Reading", "170", "100"},
                {"Swindon", "130", "90"},
                {"Bristol", "80", "110"},
                {"Bath", "95", "115"}
        };

        Graph graph = new Graph();

        for (String[] stationDetails: stations){
            String name = stationDetails[0];
            int x = Integer.parseInt(stationDetails[1]);
            int y = Integer.parseInt(stationDetails[2]);

            graph.addNode(new Node(name, x, y));
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

        for (Node node: graph.aStarSearch("Oxford", "Plymouth")){
            System.out.println(node.getName());
        }

    }
}
