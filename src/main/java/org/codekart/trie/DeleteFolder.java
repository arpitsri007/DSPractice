package org.codekart.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeleteFolder {
    class FolderNode {
        String folderName;
        String subFolder;
        Map<String, FolderNode> children;

        public FolderNode(String folderName) {
            this.folderName = folderName;
            this.children = new TreeMap<>();
        }
    }

    class FolderTrie {
        FolderNode root;

        public FolderTrie() {
            this.root = new FolderNode("/");
        }

        public void insert(List<String> folder) {
            FolderNode current = root;
            for (String folderName : folder) {
                if (!current.children.containsKey(folderName)) {
                    current.children.put(folderName, new FolderNode(folderName));
                }
                current = current.children.get(folderName);
            }
        }

        public String populateSubFolder(FolderNode current) {
            if (current.children.size() == 0) {
                current.subFolder = "";
                return "";
            }
            for (Map.Entry<String, FolderNode> entry : current.children.entrySet()) {
                String childFolderName = entry.getKey();
                FolderNode childFolder = entry.getValue();
                current.subFolder += "(" + childFolderName + populateSubFolder(childFolder) + ")";
                current.subFolder = current.subFolder.replace("()", "");
            }
            char[] subFolderChars = current.subFolder.toCharArray();
            Arrays.sort(subFolderChars);
            current.subFolder = new String(subFolderChars);
            return current.subFolder;
        }

        public void deleteDuplicateFolder(List<List<String>> paths) {
            FolderTrie folderTrie = new FolderTrie();
            for (List<String> path : paths) {
                folderTrie.insert(path);
            }

            Map<String, Integer> countMap = new HashMap<>();
            folderTrie.populateSubFolder(folderTrie.root);
            System.out.println(folderTrie.root.subFolder);
        }

    }

    /*
     * paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
     * output = [["d"],["d","a"]]
     */

}
