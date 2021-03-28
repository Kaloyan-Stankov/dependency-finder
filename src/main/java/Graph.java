import java.util.*;

public class Graph {
    Map<Node, LinkedList<Node>> dependencyList;

    public Graph() {
        dependencyList = new LinkedHashMap<>();
    }

    public boolean hasEdge(Node source, Node destination) {
        return dependencyList.containsKey(source) && dependencyList.get(source).contains(destination);
    }

    public void addEdge(Node source, Node destination){

        if(!dependencyList.containsKey(source)) {
            dependencyList.put(source, new LinkedList<>());
        }

        if(!dependencyList.containsKey(destination)) {
            dependencyList.put(destination, new LinkedList<>());
        }

        if (!hasEdge(source, destination)){
            dependencyList.get(source).addLast(destination);
        }
    }

    // Solution 1 DFS
    public void depthFirstSearch(Node node) {
        node.visit();
        System.out.print(node.name + " ");

        LinkedList<Node> allNeighbors = dependencyList.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                depthFirstSearch(neighbor);
        }
    }

    public void resetVisitedNodes(){
        for(Node node : dependencyList.keySet()){
            node.unvisit();
        }
    }

    // Solution 2 BFS
    void breadthFirstSearch(Node node) {

        if (node == null)
            return;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();

            if (currentFirst.isVisited())
                continue;

            // Mark the node as visited
            currentFirst.visit();
            System.out.print(currentFirst.name + " ");

            LinkedList<Node> allNeighbors = dependencyList.get(currentFirst);

            if (allNeighbors == null)
                continue;

            for (Node neighbor : allNeighbors) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    void reversalSearch(Node node) {
        node.visit();
        System.out.print(node.name + " ");

        LinkedList<Node> allNeighbors = dependencyList.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                depthFirstSearch(neighbor);
        }
    }
}