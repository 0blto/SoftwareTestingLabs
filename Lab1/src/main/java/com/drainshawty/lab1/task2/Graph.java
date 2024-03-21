package com.drainshawty.lab1.task2;

import java.util.*;

public class Graph {
    private final int V;
    private final LinkedList<LinkedList<Integer>> adj;

    public Graph(int v) {
        if (v <= 0) throw new IllegalArgumentException("Number of vertexes should be int in range [0; 2 147 483 647]");
        this.V = v;
        this.adj = new LinkedList<>();
        for(int i = 0; i < v; i++) this.adj.add(new LinkedList<>());
    }

    public void addEdge(int v, int w) {
        if (this.adj.get(v).contains(w) || this.adj.get(w).contains(v)) throw new IllegalArgumentException("This edge already exists");
        this.adj.get(v).add(w);
        this.adj.get(w).add(v);
    }

    public List<Integer> BFS(int vertex) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] nodes = new boolean[this.V];
        nodes[vertex] = true;
        queue.add(vertex);

        while(!queue.isEmpty()) {
            vertex = queue.poll();
            order.add(vertex);
            for(int i = 0; i < this.adj.get(vertex).size(); ++i) {
                int a = this.adj.get(vertex).get(i);
                if (!nodes[a]) {nodes[a] = true;queue.add(a);}
            }
        }
        return order;
    }
}
