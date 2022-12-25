import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsAllBridges implements GraphProperty {
    @Override
    public boolean execute(Graph abstractGraph) {
        Map<UUID, Set<UUID>> adjacencyList = new HashMap<>();
        createAdjacencyList(abstractGraph, adjacencyList);
        Set<Set<UUID>> markedEdges = new HashSet<>();

        for (Edge edge: abstractGraph.getEdges()) {
            if (edge.getColor() != Color.gray) {
                Set<UUID> vertices = new HashSet<>();
                vertices.add(edge.getFromV());
                vertices.add(edge.getToV());
                markedEdges.add(vertices);
            }
        }

        Map<UUID, Boolean> visited = new HashMap<>();

        for (UUID vertex : abstractGraph.getVertices().keySet()) {
            visited.put(vertex, false);
        }

        Map<Integer, UUID> L = new HashMap<>();
        Map<UUID, Integer> C = new HashMap<>();

        //for (UUID i : adjacencyList.keySet()) {
        //    System.out.print(i + " ");
        //    System.out.println(adjacencyList.get(i));
        //}
        //System.out.println("BREAK");

        for (UUID vertex : visited.keySet()) {
            if (!visited.get(vertex)) {
                DFS_First(L, C, adjacencyList, visited, vertex);
            }
        }
        //System.out.println("BREAK");

        //for (UUID i : adjacencyList.keySet()) {
        //    System.out.print(i + " ");
        //    System.out.println(adjacencyList.get(i));
        //}
        //System.out.println("BREAK");

        //for (Integer i : L.keySet()) {
        //    System.out.print(i + " ");
        //    System.out.println(L.get(i));
        //}
        //System.out.println("BREAK");

        for (UUID vertex : abstractGraph.getVertices().keySet()) {
            visited.put(vertex, false);
        }

        int c = 0;
        for (int i = 0; i < L.size(); i++){
            if (!visited.get(L.get(i))) {
                c++;
                DFS_Second(C, adjacencyList, visited, L.get(i), c);
            }
        }

        //System.out.print(C.values());

        for (Edge edge: abstractGraph.getEdges()) {
            if (!C.get(edge.getFromV()).equals(C.get(edge.getToV()))) {
                Set<UUID> vertices = new HashSet<>();
                vertices.add(edge.getFromV());
                vertices.add(edge.getToV());
                if (markedEdges.contains(vertices)) {
                    markedEdges.remove(vertices);
                }
                else return false;
            }
        }

        return markedEdges.isEmpty();

    }

    private void DFS_First(Map<Integer, UUID> L, Map<UUID, Integer> C, Map<UUID, Set<UUID>> adjacencyList,
                           Map<UUID, Boolean> visited, UUID curr_vertex) {

        Set <UUID> temp = new HashSet<>();
        visited.put(curr_vertex, true);
        L.put(L.size(), curr_vertex);
        C.put(curr_vertex, 0);
        for (UUID vertex : adjacencyList.get(curr_vertex)) {
            if (!visited.get(vertex)) {
                DFS_First(L, C, adjacencyList, visited, vertex);
                temp.add(vertex);
            }
        }
        for (UUID vertex : temp) {
            //System.out.println(curr_vertex + " " + vertex);
            adjacencyList.get(curr_vertex).remove(vertex);
        }

    }

    private void DFS_Second(Map<UUID, Integer> C, Map<UUID, Set<UUID>> adjacencyList,
                            Map<UUID, Boolean> visited, UUID curr_vertex, int c) {

        visited.put(curr_vertex, true);
        C.put(curr_vertex, c);
        for (UUID vertex : adjacencyList.get(curr_vertex)) {
            if (!visited.get(vertex)) {
                DFS_Second(C, adjacencyList, visited, vertex, c);
            }
        }

    }

    private void createAdjacencyList(Graph graph, Map<UUID, Set<UUID>> adjacencyList) {
        for (UUID vertex : graph.getVertices().keySet()) {
            Set<UUID> array = new HashSet<>();
            adjacencyList.put(vertex, array);
        }

        for (Edge edge : graph.getEdges()) {
            UUID vertex1 = edge.getFromV();
            UUID vertex2 = edge.getToV();

            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);

        }
    }
}
