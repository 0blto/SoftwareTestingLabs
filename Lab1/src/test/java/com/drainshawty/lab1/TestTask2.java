package com.drainshawty.lab1;

import com.drainshawty.lab1.task2.Graph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTask2 {

    static class BFSParams {
        public int vertex;
        public List<Integer> order;

        BFSParams(int vertex, List<Integer> order) {
            this.vertex = vertex;
            this.order = order;
        }
    }

    @Test
    void testAddEdge() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -5})
    void testCreatingGraphWithOutOfRangeNumberOfVertexes(int n) {assertThrows(IllegalArgumentException.class, () -> new Graph(n));}

    @ParameterizedTest
    @CsvSource({"-1, 0", "0, 5"})
    void testAddEdgeOutsideOfRange(int v, int w) {assertThrows(IndexOutOfBoundsException.class, () -> (new Graph(2)).addEdge(v, w));}

    @Test
    void testAddEdgeThatAlreadyExists() {
        Graph graph = new Graph(2);
        graph.addEdge(0, 1);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 0));
    }

    @ParameterizedTest
    @MethodSource("answersProvider")
    void testBFS(BFSParams params) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        List<Integer> bfsOrder = graph.BFS(params.vertex);
        assertEquals(params.order, bfsOrder);
    }

    private static Stream<BFSParams> answersProvider() {
        return Stream.of(
                new BFSParams(0, List.of(0, 1, 2, 3, 4, 5)),
                new BFSParams(2, List.of(2, 0, 4, 5, 1, 3)),
                new BFSParams(6, List.of(6))
        );
    }
}
