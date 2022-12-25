package org.example;

import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;

import java.util.Iterator;
import java.util.List;

public class lax_order implements GraphProperty {
    public boolean execute(AbstractGraph abstractGraph) {
        int ver = abstractGraph.getVertexCount();
        if (ver > 0) {
            List<Vertex> vertices = abstractGraph.getVertices();
            int[][] arr_ver = new int[ver][ver];

            int i;
            int j;
            for (i = 0; i < ver; ++i) {
                for (j = 0; j < ver; ++j) {
                    arr_ver[i][j] = 0;
                }
            }

            int last;
            for (i = 0; i < ver; ++i) {
                Vertex v = (Vertex) vertices.get(i);

                for (last = 0; last < ver; ++last) {
                    Vertex w = (Vertex) vertices.get(last);
                    List<AbstractEdge> edgesFromW = w.getEdgeList();
                    Iterator var10 = edgesFromW.iterator();

                    while (var10.hasNext()) {
                        AbstractEdge abstractEdge = (AbstractEdge) var10.next();
                        if (abstractEdge.other(w) == v) {
                            arr_ver[i][last] = 1;
                        }
                    }
                }
            }

            boolean Eflag = true;
            for (i = 0; i < ver; ++i) {
                if (arr_ver[i][i] == 0) {
                    Eflag = false;
                }
            }

            boolean Aflag = true;
            for (i = 0; i < ver; ++i) {
                for (j =0; j < ver; ++j) {
                    if (i == j) continue;
                    if ( arr_ver[i][j] == 1 & arr_ver[j][i] == 1) {
                        Aflag = false;
                    }
                }
            }

            boolean Tflag = true;

            int k;
            for (i = 1; i <= ver; i++)

                for (j = i + 1; j <= ver; j++)

                    for (k = 1; k <= ver; k++)

                    {

                        if (k == i || k == j) continue;
                        if ( arr_ver[i][k] == 1 & arr_ver[k][j] == 1 ) {
                            if ( arr_ver[i][j] == 0) Tflag = false;
                            break;
                        }
                    }

            return Aflag & Eflag & Tflag;
        }
        return false;
    }
}
