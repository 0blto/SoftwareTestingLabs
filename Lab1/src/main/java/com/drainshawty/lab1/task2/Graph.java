package com.drainshawty.lab1.task2;

import java.util.*;

public class Graph {
    private final int V;
    private final LinkedList<LinkedList<Integer>> adj;

    public Graph(int v) {
        this.V = v;
        this.adj = new LinkedList<>();
        for(int i = 0; i < v; i++) this.adj.add(new LinkedList<>());
    }

    void addEdge(int v, int w) {
        if (!this.adj.get(v).contains(w) && !this.adj.get(w).contains(v)) {
            this.adj.get(v).add(w);
            this.adj.get(w).add(v);
        } else throw new IllegalArgumentException("This edge already exists");
    }

    List<Integer> BFS(int vertex) {
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
