package grant.guo.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 */
public class leetcode150_Evaluate_Reverse_Polish_Notation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<tokens.length;i++) {
            String curr = tokens[i];
            try {
                Integer operand = Integer.parseInt(curr);
                stack.push(operand);
            } catch (Exception e) {
                Integer r_operand = stack.pop();
                Integer l_operand = stack.pop();
                switch(curr) {
                    case "+" : {
                        stack.push(r_operand + l_operand);
                        break;
                    }
                    case "-": {
                        stack.push(l_operand - r_operand);
                        break;
                    }
                    case "*": {
                        stack.push(l_operand*r_operand);
                        break;
                    }
                    case "/": {
                        stack.push(l_operand/r_operand);
                        break;
                    }
                }
            }
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        leetcode150_Evaluate_Reverse_Polish_Notation inst = new leetcode150_Evaluate_Reverse_Polish_Notation();
//        String[] tokens = {"2", "1", "+", "3", "*"};
//        String[] tokens = {"4", "13", "5", "/", "+"};
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int ret = inst.evalRPN(tokens);
        System.out.println(ret);
    }
}
