import java.util.*;

public class DijkstraSearch<V> {

    private Map<Vertex<V>, Double> distances;
    private Map<Vertex<V>, Vertex<V>> parentMap;

    public DijkstraSearch() {
        distances = new HashMap<>();
        parentMap = new HashMap<>();
    }

    public void dijkstra(WeightedGraph<V> graph, Vertex<V> start) {

        PriorityQueue<VertexDistance<V>> pq = new PriorityQueue<>();
        Set<Vertex<V>> visited = new HashSet<>();

        distances.clear();
        parentMap.clear();

        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);

        pq.add(new VertexDistance<>(start, 0.0));

        while (!pq.isEmpty()) {

            VertexDistance<V> current = pq.poll();
            Vertex<V> currentVertex = current.vertex;

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            for (Map.Entry<Vertex<V>, Double> entry :
                    currentVertex.getAdjacentVertices().entrySet()) {

                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();

                double newDistance =
                        distances.get(currentVertex) + weight;

                if (newDistance < distances.get(neighbor)) {

                    distances.put(neighbor, newDistance);
                    parentMap.put(neighbor, currentVertex);

                    pq.add(
                            new VertexDistance<>(neighbor, newDistance)
                    );
                }
            }
        }
    }

    public double getDistance(Vertex<V> vertex) {
        return distances.getOrDefault(
                vertex,
                Double.POSITIVE_INFINITY
        );
    }

    public List<V> getShortestPath(Vertex<V> start, Vertex<V> goal) {

        List<V> path = new ArrayList<>();

        if (!parentMap.containsKey(goal) && !start.equals(goal)) {
            return path;
        }

        Vertex<V> current = goal;

        while (current != null) {
            path.add(current.getData());
            current = parentMap.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    private static class VertexDistance<V>
            implements Comparable<VertexDistance<V>> {

        Vertex<V> vertex;
        double distance;

        public VertexDistance(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistance<V> other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}


