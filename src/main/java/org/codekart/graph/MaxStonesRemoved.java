package org.codekart.graph;

public class MaxStonesRemoved {
    public static void main(String[] args) {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println(maxStonesRemoved(stones));
    }

    public static int maxStonesRemoved(int[][] stones) {
        int n = stones.length;
        int group = 0;
        // solve using dfs
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(stones, i, visited);
                group++;
            }
        }
        return n - group;
    }

    public static void dfs(int[][] stones, int index, boolean[] visited) {
       visited[index] = true;
       for(int i =0 ; i < stones.length; i++) {
            if(!visited[i] && (stones[index][0] == stones[i][0] || stones[index][1] == stones[i][1])) {
                dfs(stones, i, visited);
            }
            
       }
       return;
    }

}
