package com.drainshawty.lab1;

import com.drainshawty.lab1.task2.Graph;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask2 {

    @Test
    void testAddEdge() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
    }

    @Test
    void testCreatingGraphWithOutOfRangeNumberOfVertexes() {
        assertThrows(IllegalArgumentException.class, () -> new Graph(0));
        assertThrows(IllegalArgumentException.class, () -> new Graph(-5));
    }

    @Test
    void testAddEdgeOutsideOfRange() {
        Graph graph = new Graph(2);
        assertThrows(IndexOutOfBoundsException.class, () -> graph.addEdge(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.addEdge(0, 5));
    }

    @Test
    void testAddEdgeThatAlreadyExists() {
        Graph graph = new Graph(2);
        graph.addEdge(0, 1);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 0));
    }

    @Test
    void testBFS() {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        List<Integer> bfsOrder = graph.BFS(0);
        assertEquals(List.of(0, 1, 2, 3, 4, 5), bfsOrder);

        bfsOrder = graph.BFS(2);
        assertEquals(List.of(2, 0, 4, 5, 1, 3), bfsOrder);

        bfsOrder = graph.BFS(6);
        assertEquals(List.of(6), bfsOrder);
    }
}
