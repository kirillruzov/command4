package org.example;

import java.io.*;
import java.util.*;
import com.mathsystem.entity.graph.*;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.GraphFactory;
import com.mathsystem.graphapi.undirected.*;
import com.mathsystem.graphapi.directed.*;
import static com.mathsystem.entity.graph.Color.*;

public class Main {
    public static List<Vertex> GenList(int size){
        List<Vertex> Vertices = new ArrayList<Vertex>();
        long num = 0L;
        for(int i =0;i<size;i++){
            var vertex1 = new Vertex();
            vertex1.setId(num);
            vertex1.setName(String.valueOf(num));
            num++;
            Vertices.add(vertex1);
        }
        return Vertices;
    }
    public static Edge GenEdge(long id,String Name,Vertex verIn,Vertex verOut){
        Edge edge1 = new Edge();
        edge1.setId(id);
        edge1.setName(Name);
        edge1.setFromVertex(verIn);
        edge1.setToVertex(verOut);
        edge1.setFromV(verIn.getName());
        edge1.setToV(verOut.getName());
        return edge1;
    }
    public static void main (String[] args) {
        //var file = new File("G:\\planar\\graph(3).txt");
        //var undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(file);
        //var rename_test = new Bridges();
        //public UndirectedGraph(int vertexCount, int edgeCount, List<Edge> edges, List<Vertex> vertices) {
        List<Vertex> Vertices = GenList(5);
        List<Edge> Edges = new ArrayList<Edge>();
        Edges.add(GenEdge(0,"0",Vertices.get(0),Vertices.get(1)));
        Edges.add(GenEdge(1,"1",Vertices.get(0),Vertices.get(2)));
        Edges.add(GenEdge(3,"3",Vertices.get(0),Vertices.get(3)));
        Edges.add(GenEdge(4,"4",Vertices.get(1),Vertices.get(2)));
        Edges.add(GenEdge(5,"5",Vertices.get(3),Vertices.get(4)));
        Edges.get(2).setColor(red);
        Edges.get(4).setColor(red);
        BridgeGraphs test = new BridgeGraphs();
        var undirectedGraph = new UndirectedGraph(Vertices.size(),Edges.size(),Edges,Vertices);
        System.out.print(test.execute(undirectedGraph));
        Vertices = GenList(7);
        Edges = new ArrayList<Edge>();
        Edges.add(GenEdge(0,"0",Vertices.get(0),Vertices.get(1)));
        Edges.add(GenEdge(1,"1",Vertices.get(0),Vertices.get(2)));
        Edges.add(GenEdge(2,"2",Vertices.get(1),Vertices.get(6)));
        Edges.add(GenEdge(3,"3",Vertices.get(1),Vertices.get(3)));
        Edges.add(GenEdge(4,"4",Vertices.get(1),Vertices.get(4)));
        Edges.add(GenEdge(5,"5",Vertices.get(2),Vertices.get(1)));
        Edges.add(GenEdge(6,"6",Vertices.get(3),Vertices.get(5)));
        Edges.add(GenEdge(7,"7",Vertices.get(4),Vertices.get(5)));
        Edges.get(2).setColor(red);
        Edges.get(4).setColor(red);
        /*Bridges b = new Bridge();
        Graph gr;
        try {
            File file = new File()
            gr = GraphFactory.loadGraphFromFile(new File("src\\main\\resources\\graph.txt"));
            System.out.println(b.execute(gr));
        }*/
        //System.out.println(Vertices.size());
        test = new BridgeGraphs();
        undirectedGraph = new UndirectedGraph(Vertices.size(),Edges.size(),Edges,Vertices);
        System.out.print(test.execute(undirectedGraph));
    }
}
