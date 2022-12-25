package org.example;
import com.mathsystem.entity.graph.*;
import com.mathsystem.graphapi.*;
import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.plugin.GraphProperty;

import java.util.ArrayList;
import java.util.List;

public class BridgeGraphs implements GraphProperty {

    private int vertexCount;
    static int CountVisits(boolean[] visited){
        int count =0;
        for (int i =0; i< visited.length;i++){
            if (visited[i]){
                count++;
            }
        }
        return count;
    }
    static int[][] adj;
    static void addEdge(int x, int y)
    {
        adj[x][y] = 1;
        //adj[y][x] = 1;
    }
    static void RemoveEdge(int x,int y){
        adj[x][y] = 0;
        //adj[y][x] = 0;
    }
    static void checkForBridge(AbstractEdge abstractEdge,AbstractGraph abstractGraph){
        int before,after =0;
        adj = new int[abstractGraph.getVertexCount()][abstractGraph.getVertexCount()];
        boolean[] visited = new boolean[abstractGraph.getVertexCount()];
        GenADJMat(abstractGraph);

        dfs(0, visited);
        before = CountVisits(visited);
        RemoveEdge(abstractEdge.getV().getIndex(),abstractEdge.getW().getIndex());
        visited = new boolean[abstractGraph.getVertexCount()];
        System.out.println();
        dfs(0, visited);
        after = CountVisits(visited);
        if (before!=after){
            System.out.print(abstractEdge.toString());
            System.out.println(" IS A BRIDGE");
        }
        else{
            System.out.print(abstractEdge.toString());
            System.out.println(" IS NOT A BRIDGE");
        }
    }
    static List<AbstractEdge> GetAllColouredEdges(AbstractGraph abstractGraph){
        List<AbstractEdge> Edges = new ArrayList<AbstractEdge>();
        List<String> Names = new ArrayList<String>();
        List<Vertex> Vertices = new ArrayList<Vertex>();
        Vertices = abstractGraph.getVertices();
        for (int i =0;i<Vertices.size();i++){
            var w = Vertices.get(i);
            var edgesFromW = w.getEdgeList();
            for (AbstractEdge abstractEdge : edgesFromW) {
                if (abstractEdge.getColor()==Color.red && !Names.contains(abstractEdge.getName())){
                    Edges.add(abstractEdge);
                    Names.add(abstractEdge.getName());
                }
            }
        }

        return Edges;
    }
    @Override
    public boolean execute(AbstractGraph abstractGraph)
    {
        List<AbstractEdge> Edges = new ArrayList<AbstractEdge>();
        Edges = GetAllColouredEdges(abstractGraph);
        System.out.println("Size of Edges:: ");
        System.out.println(Edges.size());
        for (AbstractEdge Edge : Edges) {
            checkForBridge(Edge, abstractGraph);
        }
        return true;
    }
    static void GenADJMat(AbstractGraph abstractGraph){
        var Vertices = abstractGraph.getVertices();
        for (int i =0;i<Vertices.size();i++){
            var w = Vertices.get(i);
            var edgesFromW = w.getEdgeList();
            for (AbstractEdge abstractEdge : edgesFromW) {
                addEdge(i,abstractEdge.other(w).getIndex());
            }
        }
        for (int i=0;i<Vertices.size();i++) {
            for (int w = 0; w < Vertices.size(); w++) {
                System.out.print(adj[i][w]);
            }
            System.out.println();
        }
    }
    static void dfs(int start, boolean[] visited)
    {
// Print the current node
        System.out.print(start + " ");

// Set current node as visited
        visited[start] = true;

// For every node of the graph
        for (int i = 0; i < adj[start].length; i++) {

// If some node is adjacent to the current node
// and it has not already been visited
            if (adj[start][i] == 1 && (!visited[i])) {
                dfs(i, visited);

            }
        }
    }
}





