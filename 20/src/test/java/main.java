import java.io.*;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

public class main {
    public static void main (String[] args) throws FileNotFoundException {
        File file = new File("C:\\Tests\\empty_graph.txt");
        Graph graph = GraphFactory.loadGraphFromFile(file);
        IsAllBridges test = new IsAllBridges();
        System.out.print(test.execute(graph));
    }
}