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

        graph.addEdge("Oxford", "London");
        graph.addEdge("Oxford", "Reading");
        graph.addEdge("Oxford", "Bath");
        graph.addEdge("London", "Reading");
        graph.addEdge("London", "Bournemouth");
        graph.addEdge("Bournemouth", "Plymouth");
        //graph.addEdge("Bournemouth", "Reading");
        graph.addEdge("Plymouth", "Bristol");
        graph.addEdge("Bristol", "Bath");
        graph.addEdge("Bath", "Swindon");
        //graph.addEdge("Bath", "Bournemouth");
        //graph.addEdge("Bath", "Reading");

        graph.displayGraph();

        for (Node node: graph.depthFirstSearch()){
            System.out.println(node.getName());
        }



    }
}
