package org.codekart.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExit {

    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) {
        char[][] maze = {{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        int[] entrance = {1, 2};
        System.out.println(nearestExit(maze, entrance));
       
    }

    

    public static int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
  
        Queue<int[]> queue = new LinkedList<>();
        maze[entrance[0]][entrance[1]] = '+';
        queue.add(entrance);
        int steps = 0;

        while(!queue.isEmpty()) {
          
            int qsize = queue.size();

            for(int i = 0 ; i < qsize; i++ ) {

                int[] cell = queue.poll();

                int r = cell[0];
                int c = cell[1];
    
                if((r == 0 || r == rows - 1 || c == 0 || c == cols - 1) && !(r == entrance[0] && c == entrance[1])) {
                    return steps;
                }

                for(int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if( nr < 0 || nc < 0 || nr >= rows || nc >= cols || maze[nr][nc] == '+') {
                        continue;
                    }

                    maze[r][c] = '+';
                    queue.add(new int[] {nr, nc});
                }

            }
            steps++;
        }

        return -1;  // Return -1 if no exit is found
    }

    

}
