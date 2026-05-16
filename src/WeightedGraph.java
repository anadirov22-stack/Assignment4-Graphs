import java.util.ArrayList;
import java.util.List;

public class WeightedGraph<V> {

    private List<Vertex<V>> vertices;

    public WeightedGraph() {
        vertices = new ArrayList<>();
    }

    public void addVertex(Vertex<V> vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {

        if (!vertices.contains(source)) {
            addVertex(source);
        }

        if (!vertices.contains(dest)) {
            addVertex(dest);
        }

        // Undirected graph
        source.addAdjacentVertex(dest, weight);
        dest.addAdjacentVertex(source, weight);
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }
}


