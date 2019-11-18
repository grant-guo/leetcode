package grant.guo.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class leetcode735_Asteroid_Collision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack =new Stack<>();
        for(int num: asteroids) {
            if(!stack.isEmpty()) {
                while(!stack.isEmpty()) {
                    int peek = stack.peek();
                    if(peek * num > 0) {
                        stack.push(num);
                        break;
                    } else {
                        if (Math.abs(num) >= Math.abs(peek)) {
                            stack.pop();
                        }
                        break;
                    }
                }
            } else {
                stack.push(num);
            }
        }

        int[] rets = new int[stack.size()];
        while(stack.size() > 0) {
            rets[stack.size() - 1] = stack.pop();
        }

        return rets;
    }

    public static void main(String[] args) {
        leetcode735_Asteroid_Collision inst = new leetcode735_Asteroid_Collision();

        int[] asteroids = {5, 10, -5};
//        int[] asteroids = {8, -8};
//        int[] asteroids = {10,2, -5};
        int[] rets = inst.asteroidCollision(asteroids);

        for(int num: rets){
            System.out.print(num + ", ");
        }
    }
}
