package org.codekart.graph;

public class Disjointset {
    // concept:
    // Union-Find
    // Combine two sets
    // Find if two elements are in the same set or not
    // Disjoint set -> no common elements
    // Find the number of connected components
    // Find the size of the connected component
    // Find the number of elements in the connected component

    public static void main(String[] args) {
        int[] parent = new int[10];
        for (int i = 0; i < 10; i++) {
            parent[i] = i;
        }
        union(parent, 1, 2);
        union(parent, 2, 3);
        union(parent, 4, 5);
        union(parent, 6, 7);
        union(parent, 8, 9);
        System.out.println(find(parent, 1));
        System.out.println(find(parent, 1));

    }

    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent, parent[x]);
    }

    private static void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[xRoot] = yRoot;
    }

    // Rank and Path Compression
    protected static int findWithPathCompression(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findWithPathCompression(parent, parent[x]);
    }

    protected static void unionWithRank(int[] parent, int[] rank, int x, int y) {
        int xRoot = findWithPathCompression(parent, x);
        int yRoot = findWithPathCompression(parent, y);
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    // DSU by size
    protected static void unionBySize(int[] parent, int[] size, int x, int y) {
        int xRoot = findWithPathCompression(parent, x);
        int yRoot = findWithPathCompression(parent, y);
        if (size[xRoot] < size[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        } else {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
        }
    }
    

    private static boolean detectCycleInUndirectedGraphUsingDisjointSet(int[][] graph) {
        int[] parent = new int[graph.length];
        int[] rank = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            parent[i] = i;
        }

        for (int u = 0; u < graph.length; u++) {
            for (int v : graph[u]) {
                if (u < v) {
                    int uRoot = findWithPathCompression(parent, u);
                    int vRoot = findWithPathCompression(parent, v);
                    if (uRoot == vRoot) {
                        return true;
                    }
                    unionWithRank(parent, rank, uRoot, vRoot);
                }
            }
        }
        return false;

    }


    public static class SatisfibilityOfGivenEquations {

        public static void main(String[] args) {
            String[] equations = { "a==b", "b!=c", "c==a" };
            System.out.println(isSatisfiable(equations));
        }

        private static boolean isSatisfiable(String[] equations) {
            int[] parent = new int[26];
            int[] rank = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }

            // First process all equality equations
            for (String equation : equations) {
                if (equation.charAt(1) == '=') {
                    unionWithRank(parent, rank, equation.charAt(0) - 'a', equation.charAt(3) - 'a');
                }
            }

            // Then check inequality equations
            for (String equation : equations) {
                if (equation.charAt(1) == '!') {
                    if (find(parent, equation.charAt(0) - 'a') == find(parent, equation.charAt(3) - 'a')) {
                        return false;
                    }
                }
            }

            return true;
        }
    }


    public static class NumberOfOperationsToMakeNetworkConnected {

        public static void main(String[] args) {
            int[][] connections = {{0,1},{0,2},{1,2}};
            System.out.println(makeConnected(4, connections));
        }

        private static int makeConnected(int n, int[][] connections) {
            int[] parent = new int[n];
            int[] rank = new int[n];
            int components = n;
            for(int i=0;i<n;i++) {
                parent[i] = i;
            }
            for(int[] connection : connections) {
                int u = connection[0];
                int v = connection[1];
                int uRoot = findWithPathCompression(parent, u);
                int vRoot = findWithPathCompression(parent, v);

                if(uRoot != vRoot) {
                    unionWithRank(parent, rank, uRoot, vRoot);
                    components--;
                }
            }
            return components - 1;           
        }
    }

    public static class NumberOfProvinces {

        public static void main(String[] args) {
            int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
            System.out.println(findCircleNum(isConnected));
        }

        private static int findCircleNum(int[][] isConnected) {
            int[] parent = new int[isConnected.length];
            int[] rank = new int[isConnected.length];
            for(int i=0;i<isConnected.length;i++) {
                parent[i] = i;
            }

            for(int i=0;i<isConnected.length;i++) {
                for(int j=0;j<isConnected[i].length;j++) {
                    if(isConnected[i][j] == 1) {
                        unionWithRank(parent, rank, i, j);
                    }
                }
            }

            int components = 0;
            for(int i=0;i<isConnected.length;i++) {
                if(parent[i] == i) {
                    components++;
                }
            }
            return components;
        }
    }

    public static class NumberOfConnectedComponentsInAnUndirectedGraph {

        public static void main(String[] args) {
            int[][] edges = {{0,1},{1,2},{3,4}};
            System.out.println(countComponents(5, edges));
        }

        private static int countComponents(int n, int[][] edges) {
            int[] parent = new int[n];
            int[] rank = new int[n];
            for(int i=0;i<n;i++) {
                parent[i] = i;
            }
            for(int[] edge : edges) {
                unionWithRank(parent, rank, edge[0], edge[1]);
            }
            
            int components = 0;
            for(int i=0;i<n;i++) {
                if(parent[i] == i) {
                    components++;
                }
            }
            return components;
        }
    }
}
