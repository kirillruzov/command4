package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class GraphCut implements GraphProperty {
    private Map<UUID, List<UUID>> adjLists;
    private Map<UUID, Integer> visited;
    private Map<UUID, List<UUID>> allAdjLists;
    private Map<UUID, Integer> allVisited;
    private Map<UUID, List<UUID>> xAdjLists;
    private Map<UUID, Integer> xVisited;
    private boolean result = true;

    @Override
    public boolean execute(Graph graph) {
        visited = new HashMap<UUID, Integer>();
        adjLists = new HashMap<UUID, List<UUID>>();
        allVisited = new HashMap<UUID, Integer>();
        allAdjLists = new HashMap<UUID, List<UUID>>();
        xVisited = new HashMap<UUID, Integer>();
        xAdjLists = new HashMap<UUID, List<UUID>>();
        for (UUID v : graph.getVertices().keySet()) {
            visited.put(v, 0);
            allVisited.put(v, 0);
            xVisited.put(v, 0);
        }
        List<Edge> edges = graph.getEdges();
        for (Edge edge: edges) {
            if (!allAdjLists.containsKey(edge.getFromV())) {
                allAdjLists.put(edge.getFromV(), new ArrayList<UUID>());
            }
            if (!allAdjLists.containsKey(edge.getToV())) {
                allAdjLists.put(edge.getToV(), new ArrayList<UUID>());
            }
            allAdjLists.get(edge.getFromV()).add(edge.getToV());
            allAdjLists.get(edge.getToV()).add(edge.getFromV());

            if (edge.getColor() == Color.red) {
                if (!adjLists.containsKey(edge.getFromV())) {
                    adjLists.put(edge.getFromV(), new ArrayList<UUID>());
                }
                if (!adjLists.containsKey(edge.getToV())) {
                    adjLists.put(edge.getToV(), new ArrayList<UUID>());
                }
                adjLists.get(edge.getFromV()).add(edge.getToV());
                adjLists.get(edge.getToV()).add(edge.getFromV());
            }
           else  {
                if (!xAdjLists.containsKey(edge.getFromV())) {
                    xAdjLists.put(edge.getFromV(), new ArrayList<UUID>());
                }
                if (!xAdjLists.containsKey(edge.getToV())) {
                    xAdjLists.put(edge.getToV(), new ArrayList<UUID>());
                }
                xAdjLists.get(edge.getFromV()).add(edge.getToV());
                xAdjLists.get(edge.getToV()).add(edge.getFromV());
            }
        }
        return isBipartite();
    }

    private boolean isBipartite() {
        int number2 = 0; // число компонент связности всего графа
        int number3 = 0;
        for (UUID v : allAdjLists.keySet()) {
            if (allVisited.get(v) == 0){
                DFS2(v);
                number2++;
            }
        }
        for (UUID v : xAdjLists.keySet()) {
            if (xVisited.get(v) == 0){
                DFS3(v);
                number3++;
            }
        }
        for (UUID v : adjLists.keySet()) {
            if (visited.get(v) == 0)
                DFS(v, 1);
        }
        return result && (number3 > number2);
    }

    private void DFS(UUID vertex, int color) {
        visited.put(vertex, color);
        if (adjLists.containsKey(vertex)) {
            for (UUID adj : adjLists.get(vertex)) {
                if (visited.get(adj) == 0)
                    DFS(adj, color * (-1));
                else if (Objects.equals(visited.get(adj), visited.get(vertex))) {
                    result = false;
                }
            }
        }
    }

    private void DFS2(UUID vertex) {
        allVisited.put(vertex, 1);
        if (allAdjLists.containsKey(vertex)) {
            for (UUID adj : allAdjLists.get(vertex)) {
                if (allVisited.get(adj) == 0)
                    DFS2(adj);
            }
        }
    }

    private void DFS3(UUID vertex) {
        xVisited.put(vertex, 1);
        if (xAdjLists.containsKey(vertex)) {
            for (UUID adj : xAdjLists.get(vertex)) {
                if (xVisited.get(adj) == 0)
                    DFS3(adj);
            }
        }
    }
}
