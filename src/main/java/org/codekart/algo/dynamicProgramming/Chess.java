package org.codekart.algo.dynamicProgramming;

import java.util.LinkedList;
import java.util.Queue;

public class Chess {

    public int[][] moves = {
            { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
            { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }
    };

    // maximum no of moves to kill all pawns
    public int maxMoves(int kx, int ky, int[][] positions) {
        int n = positions.length;
        int[][] pos = new int[n + 1][2];
        pos[0] = new int[] { kx, ky };
        for (int i = 0; i < n; i++) {
            pos[i + 1] = positions[i];
        }
        // find max moves
        int maxMoves = 0;
        // let's create a dp array to store minDistance[i][j] from any i position to j
        // position
        int[][] minDistance = new int[n + 1][n + 1];
        for (int index = 0; index <= n; index++) {
            int x = pos[index][0];
            int y = pos[index][1];
            bfs(x, y, index, minDistance, pos, n);

        }
        // Alice starts first , minDistance[i][j] cells at index i in positions array to
        // cell at index j in positions array
        boolean aliceTurn = true;

        Integer[][] memo = new Integer[n+1][1 << 15];

        maxMoves = solve(minDistance, 0, aliceTurn, pos, (1 << (n)) - 1, memo);
        return maxMoves;

    }

    public int solve(int[][] minDistance, int index, boolean aliceTurn, int[][] positions, int mask, Integer[][] memo) {
        if (mask == 0) {
            return 0;
        }
        if (memo[index][mask] != null) {
            return memo[index][mask];
        }

        int result = aliceTurn ? -10 : (int) 1e9;
        for (int i = 1; i <= positions.length; i++) {
            if ((mask & (1 << i - 1)) != 0) {
                int moves = minDistance[index][i];
                result = aliceTurn
                        ? Math.max(result, moves + solve(minDistance, i, !aliceTurn, positions, mask ^ (1 << i - 1), memo))
                        : Math.min(result, moves + solve(minDistance, i, !aliceTurn, positions, mask ^ (1 << i - 1), memo));
            }
        }
        return memo[index][mask] = result;
    }

    public void bfs(int x, int y, int index, int[][] minDistance, int[][] positions, int n) {
        // let's define a new array which store minimum distance of index position to other positions
        // t[i][j] = minimum distance of (i,j) position from (x,y) position
        int[][] t = new int[51][51];
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                t[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        t[x][y] = 0;
        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int currentX = currentPosition[0];
            int currentY = currentPosition[1];
            for (int[] move : moves) {
                int nextX = currentX + move[0];
                int nextY = currentY + move[1];
                if (isSafe(nextX, nextY) && t[nextX][nextY] == Integer.MAX_VALUE) {
                    t[nextX][nextY] = t[currentX][currentY] + 1;
                    queue.add(new int[] { nextX, nextY });
                }
            }
        }

        for (int i = 0; i < positions.length; i++) {
            int x1 = positions[i][0];
            int y1 = positions[i][1];
            if (t[x1][y1] == Integer.MAX_VALUE) {
                minDistance[index][i] = (int) 1e9;
            } else {
                minDistance[index][i] = t[x1][y1];
            }
        }
    }

    // board is 50*50
    public boolean isSafe(int x, int y) {
        return x >= 0 && x < 51 && y >= 0 && y < 51;
    }

}
