
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();
        HashMap<String, Node> createdNodes = new HashMap<>();

        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/input.txt"))) {
            while (scanner.hasNext()) {
                String[] inputLine = scanner.nextLine().split(" ");
                for (int i=1; i < inputLine.length; i++) {
                    String firstElement = inputLine[0];
                    String currentElement = inputLine[i];

                    Node firstNode = new Node(firstElement);
                    Node currentNode = new Node(currentElement);

                    if( !createdNodes.containsKey(firstElement)) {
                        createdNodes.put(firstElement, firstNode);
                    }

                    if (!createdNodes.containsKey(currentElement)) {
                        createdNodes.put(currentElement, currentNode);
                    }

                    graph.addEdge(createdNodes.get(firstElement), createdNodes.get(currentElement));
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        System.out.println("Solution 1 DFS ---------------");
        for (Node node: graph.dependencyList.entrySet()
                .stream()
                .filter(es -> es.getValue().size() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())) {
            graph.breadthFirstSearch(node);
            graph.resetVisitedNodes();
        }

        System.out.println("Solution 2 BFS ---------------");
        for (Node node: graph.dependencyList.entrySet()
                .stream()
                .filter(es -> es.getValue().size() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())) {
            graph.depthFirstSearch(node);
            System.out.println();
            graph.resetVisitedNodes();
        }
    }
}
