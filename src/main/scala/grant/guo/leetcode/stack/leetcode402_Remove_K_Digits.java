package grant.guo.leetcode.stack;

import java.util.Stack;

public class leetcode402_Remove_K_Digits {
    public String removeKdigits(String num, int k) {
        if(num.length() == k)
            return "0";

        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        int index = 1;
        while(index < num.length() && k > 0) {
            char peek = stack.peek();
            char curr = num.charAt(index);
            if (peek > curr) {
                while(!stack.isEmpty() && k > 0)
                {
                    stack.pop();
                    k--;
                    if(!stack.isEmpty() && stack.peek() > curr)
                        continue;
                    else
                        break;
                }
            }
            stack.push(curr);
            index++;
        }

        String ret = num.substring(index);
        while(!stack.isEmpty()) {
            if(k == 0)
                ret = stack.pop() + ret;
            else {
                stack.pop();
                k--;
            }

        }

        index = 0;
        while(index < ret.length()-1 && ret.charAt(index) == '0')
            index++;

        return ret.substring(index);
    }
    public static void main(String[] args) {
        leetcode402_Remove_K_Digits inst = new leetcode402_Remove_K_Digits();
        String num = "17839";
        int k = 2;
        System.out.println(inst.removeKdigits(num, k));
    }
}
