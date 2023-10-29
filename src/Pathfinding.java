
public class Pathfinding {

    public static void main(String[] args){
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
