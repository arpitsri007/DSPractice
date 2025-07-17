package org.codekart.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RobotCollision {
    // leetcode 2751

    class Robot {
        int position;
        int direction;
        int health;

        Robot(int position, int direction, int health) {
            this.position = position;
            this.direction = direction;
            this.health = health;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], directions.charAt(i) == 'R' ? 1 : -1, healths[i]);
        }

        Stack<Robot> stack = new Stack<>();
        for (Robot robot : robots) {
            if (stack.isEmpty() || stack.peek().direction == robot.direction) {
                stack.push(robot);
            } else {
                if (!stack.isEmpty() && stack.peek().direction != robot.direction) {
                    Robot top = stack.pop();
                    if (top.health > robot.health) {
                        top.health--;
                        robot.health = 0;
                        stack.push(top);
                    } else if (top.health < robot.health) {
                        robot.health--;
                        stack.push(robot);
                    } else {
                        robot.health = 0;
                    }
                }
                if (robot.health > 0) {
                    stack.push(robot);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(0, stack.pop().health);
        }

        return result;
    }

    // main method
    public static void main(String[] args) {
        RobotCollision robotCollision = new RobotCollision();
        int[] positions = { 3, 5, 2, 6 };
        int[] healths = { 10, 10, 15, 12 };
        String directions = "RLRL";
        System.out.println(robotCollision.survivedRobotsHealths(positions, healths, directions));
    }
}
