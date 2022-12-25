package org.example;
import com.mathsystem.graphapi.GraphFactory;
import com.mathsystem.graphapi.undirected.UndirectedGraph;
import java.io.File;

public class main {
    public main() {
    }

    public static void main(String[] args) {
        File file = new File("D:\\graph.txt");
        UndirectedGraph undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(file);
        lax_order rename_test = new lax_order();
        System.out.print(rename_test.execute(undirectedGraph));
    }
}
