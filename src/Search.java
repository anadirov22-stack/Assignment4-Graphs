import java.util.*;

public class Search<V> {

    public List<V> breadthFirstSearch(
            WeightedGraph<V> graph,
            Vertex<V> start,
            Vertex<V> goal
    ) {

        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {

            Vertex<V> current = queue.poll();

            if (current.equals(goal)) {
                return reconstructPath(parentMap, goal);
            }

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<V> reconstructPath(
            Map<Vertex<V>, Vertex<V>> parentMap,
            Vertex<V> goal
    ) {

        List<V> path = new ArrayList<>();
        Vertex<V> current = goal;

        while (current != null) {
            path.add(current.getData());
            current = parentMap.get(current);
        }

        Collections.reverse(path);

        return path;
    }
}



