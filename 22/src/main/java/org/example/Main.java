package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Graph gr;
        GraphCut t = new GraphCut();
        try {
            gr = GraphFactory.loadGraphFromFile(new File("src\\main\\resources\\graph (6).txt")); //считываем граф из файла
            System.out.println(t.execute(gr));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}