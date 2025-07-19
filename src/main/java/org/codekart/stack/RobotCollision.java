package org.codekart.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class RobotCollision {
    // leetcode 2751

    class Robot {
        int position;
        int direction;
        int health;
        int originalIndex;

        Robot(int position, int direction, int health, int originalIndex) {
            this.position = position;
            this.direction = direction;
            this.health = health;
            this.originalIndex = originalIndex;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], directions.charAt(i) == 'R' ? 1 : -1, healths[i], i);
        }

        Arrays.sort(robots, (a, b) -> a.position - b.position);

        Stack<Robot> stack = new Stack<>();

        for (Robot robot : robots) {
            if (robot.direction == 1) {
                stack.push(robot);
            } else {
                while (!stack.isEmpty() && robot.health > 0) {
                    Robot top = stack.pop();
                    if (top.health > robot.health) {
                        top.health--;
                        robot.health = 0;
                        stack.push(top);
                    } else if (top.health < robot.health) {
                        robot.health--;
                        top.health = 0;
                    } else {
                        robot.health = 0;
                        top.health = 0;
                    }
                }
            }
        }

        Arrays.sort(robots, (a, b) -> a.originalIndex - b.originalIndex);

        List<Integer> result = new ArrayList<>();
        for (Robot robot : robots) {
            if (robot.health > 0) {
                result.add(robot.health);
            }
        }
        return result;
    }

    // main method
    public static void main(String[] args) {
        RobotCollision robotCollision = new RobotCollision();
        int[] positions = { 5, 46, 12 };
        int[] healths = { 3, 27, 43 };
        String directions = "RLL";
        System.out.println(robotCollision.survivedRobotsHealths(positions, healths, directions));
    }
}
