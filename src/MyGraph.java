import org.w3c.dom.Node;

import java.util.*;

public class MyGraph<Vertex> {
    private Map<Vertex, List<Vertex>> list;


    public MyGraph() {
        list = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        list.put(vertex, new LinkedList<>());
    }

    public void addEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        list.get(source).add(destination);
        list.get(destination).add(source);

    }

    private void validateVertex(Vertex index) {
        if (!list.containsKey(index)) {
            throw new IllegalArgumentException("Vertex " + index + " is out of the range");
        }
    }

    public void printGraph() {
        for (Map.Entry<Vertex, List<Vertex>> entry : list.entrySet()) {
            Vertex vertex = entry.getKey();
            List<Vertex> neighbors = entry.getValue();
            System.out.print("Vertex " + vertex + " is connected to: ");
            for (Vertex neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void removeEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Vertex> neighbors = list.get(source);
        if (neighbors!=null) {
            neighbors.remove(destination);
        }
        list.get(destination).remove(source);
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Vertex> neighbors = list.get(source);
        return neighbors != null && neighbors.contains(destination);
    }

    public List<Vertex> getNeighbors(Vertex vertex) {
        validateVertex(vertex);
        return list.getOrDefault(vertex, new LinkedList<>());
    }

    public void DFS(Vertex start) {
        validateVertex(start);
        Map<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex vertex:list.keySet()) {
            visited.put(vertex,false);
        }
        DFSHelper(start, visited);

    }


    private void DFSHelper(Vertex vertex, Map<Vertex, Boolean> visited) {
        visited.put(vertex, true);
        System.out.print(vertex + " ");
        for (Vertex neighbor : list.get(vertex)) {
            if (!visited.get(neighbor)) {
                DFSHelper(neighbor, visited);
            }
        }
    }

    public ArrayList<Vertex> BFS(Vertex start){
        ArrayList<Boolean> adj = new ArrayList<>();
        return BFSHelper((int) start, adj);
    }

    public static ArrayList<Integer> BFSHelper(int V, ArrayList<Boolean>[] adj){
        ArrayList<Boolean> visited = new ArrayList<>(V);
        ArrayList<Integer> bfs_traversal = new ArrayList<>();
        for (int i = 0; i < V; ++i) {
            // To check if already visited
            if (visited.get(i) == false) {
                Queue<Integer> q = new LinkedList<>();
                visited.set(i, true);
                q.add(i);

                // BFS starting from ith node
                while (!q.isEmpty()) {
                    int g_node = q.peek();
                    q.poll();
                    bfs_traversal.add(g_node);
                    for (int it = 0;
                         it < adj[g_node].toArray().length;
                         it++) {
                        if (adj[g_node].get(it) == true) {
                            if (visited.get(it) == false) {
                                visited.set(it, true);
                                q.add(it);
                            }
                        }
                    }
                }
            }
        }
        return bfs_traversal;
    }
}