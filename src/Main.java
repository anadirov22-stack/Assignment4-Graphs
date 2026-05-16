import java.util.List;

public class Main {

    public static void main(String[] args) {

        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> astana = new Vertex<>("Astana");
        Vertex<String> karaganda = new Vertex<>("Karaganda");
        Vertex<String> kostanay = new Vertex<>("Kostanay");
        Vertex<String> aktobe = new Vertex<>("Aktobe");
        Vertex<String> aktau = new Vertex<>("Aktau");

        graph.addEdge(astana, karaganda, 200);
        graph.addEdge(astana, kostanay, 700);

        graph.addEdge(karaganda, aktobe, 800);
        graph.addEdge(kostanay, aktau, 1200);

        graph.addEdge(aktobe, aktau, 900);

        graph.addEdge(astana, aktobe, 1000);

        System.out.println("Breadth First Search ");

        Search<String> bfs = new Search<>();

        List<String> bfsPath =
                bfs.breadthFirstSearch(graph, astana, aktau);

        System.out.println("BFS path from Astana to Aktau: " + bfsPath);

        System.out.println("\nDijkstra Search");

        DijkstraSearch<String> dijkstra =
                new DijkstraSearch<>();

        dijkstra.dijkstra(graph, astana);

        System.out.println("Distance from Astana to Aktau: " +
                dijkstra.getDistance(aktau));

        List<String> shortestPath =
                dijkstra.getShortestPath(astana, aktau);

        System.out.println("Shortest path from Astana to Aktau: " + shortestPath);
    }
}
