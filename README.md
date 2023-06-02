# lab6
Assignment work for Algorithm and Data Structures course. Astana It University. Software Engineering. Course 1
## MyGraph Class
```java
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

    public void BFS(Vertex start){
        ArrayList<Vertex> queue = new ArrayList<>();
        queue.add(start);
        Map<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex vertex:list.keySet()){
            visited.put(vertex, false);
        }
        System.out.println();
        System.out.println(BFSHelper(start,visited, queue));
    }

    private ArrayList<Vertex> BFSHelper(Vertex vertex, Map<Vertex, Boolean> visited, ArrayList<Vertex> queue) {
        visited.put(vertex, true);
        for (Vertex neighbor : getNeighbors(vertex)) {
            if (!visited.get(neighbor)) {
                BFSHelper(neighbor, visited, queue);
                queue.add(neighbor);
            }
        }
        return queue;
    }


}
```
