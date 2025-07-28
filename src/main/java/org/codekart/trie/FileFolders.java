package org.codekart.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileFolders {

    class FileTrieNode {
        Map<String, FileTrieNode> children;
        boolean isFolder;
        String folderPath;

        public FileTrieNode() {
            children = new HashMap<>();
            isFolder = false;
        }

        public FileTrieNode(String path) {
            this();
            this.folderPath = path;
        }

    }

    class FileTrie {
        FileTrieNode root;

        public FileTrie() {
            root = new FileTrieNode();
        }

        public void insert(String folder) {
            FileTrieNode current = root;
            String relativePath = "";
            for (String f : folder.split("/")) {
                if (f.isEmpty()) {
                    continue;
                }

                relativePath += "/" + f;

                if (!current.children.containsKey(f)) {
                    current.children.put(f, new FileTrieNode(relativePath));
                }
                current = current.children.get(f);
            }
            current.isFolder = true;
            current.folderPath = folder;
        }

        public List<String> getFolders() {
            List<String> result = new ArrayList<>();
            getFolders(root, result);
            return result;
        }

        private void getFolders(FileTrieNode node, List<String> result) {
            if (node.isFolder) {
                result.add(node.folderPath);
            }
            for (FileTrieNode child : node.children.values()) {
                getFolders(child, result);
            }
        }

        // Approach 2: Sorting and checking parent folders
        public List<String> removeSubfolders(String[] folder) {
            Arrays.sort(folder);
            List<String> result = new ArrayList<>();
            result.add(folder[0]);
            for (int i = 1; i < folder.length; i++) {
                String current = folder[i];
                String parent = result.get(result.size() - 1) + "/";
                if (!current.startsWith(parent)) {
                    result.add(current);
                }
            }
            return result;
        }
    }

    // leetcode 1233 - Remove Sub-Folders from the Filesystem
    public List<String> removeSubfoldersTrie(String[] folder) {
        FileTrie fileTrie = new FileTrie();
        for (String f : folder) {
            fileTrie.insert(f);
        }
        return fileTrie.removeSubfolders(fileTrie.getFolders().toArray(new String[0]));
    }

    // main method
    public static void main(String[] args) {
        FileFolders fileFolders = new FileFolders();
        String[] folder = { "/a", "/a/b", "/c/d", "/c/d/e", "/c/f" };
        System.out.println(fileFolders.removeSubfoldersTrie(folder));
    }
}
