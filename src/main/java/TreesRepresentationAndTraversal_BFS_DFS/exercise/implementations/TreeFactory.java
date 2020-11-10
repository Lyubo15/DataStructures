package TreesRepresentationAndTraversal_BFS_DFS.exercise.implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String params : input) {
            int[] keys = Arrays.stream(params.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int parentKey = keys[0];
            int childKey = keys[1];

            addEdge(parentKey, childKey);
        }

        return this.getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Tree<Integer> value : this.nodesByKeys.values()) {
            if (value.getParent() == null){
                return value;
            }
        }
        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentNode = createNodeByKey(parent);
        Tree<Integer> childNode = createNodeByKey(child);

        parentNode.addChild(childNode);
        childNode.setParent(parentNode);
    }
}



