package grant.guo.leetcode.stack;


import scala.Char;
import scala.math.Ordering;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class leetcode394_Decode_String {
    private class Ele {
        public int times;
        public StringBuffer pattern = new StringBuffer();
        public Ele(int times) {
            this.times = times;
        }
    }
    public String decodeString(String s) {
        StringBuffer ret = new StringBuffer();
        Stack<Ele> stack = new Stack<>();
        int pos = 0;
        while(pos < s.length()) {
            char curr = s.charAt(pos);
            if (curr == ']') {
                Ele top = stack.pop();
                int times = top.times;
                StringBuffer pattern = new StringBuffer();
                while(times > 0) {
                    pattern.append(top.pattern.toString());
                    times--;
                }
                if(!stack.isEmpty()) {
                    stack.peek().pattern.append(pattern);
                } else {
                    ret.append(pattern);
                }
                pos++;
            }
            else if(Character.isLetter(curr) && stack.isEmpty()){
                ret.append(curr);
                pos++;
            } else if (Character.isLetter(curr) && !stack.isEmpty()) {
                stack.peek().pattern.append(curr);
                pos++;
            } else if (Character.isDigit(curr)) {
                Stack<Character> buffer = new Stack<>();
                buffer.push(curr);
                pos++;
                while(Character.isDigit(s.charAt(pos))) {
                    buffer.push(s.charAt(pos));
                    pos++;
                }
                pos++;

                int base = 1;
                int value = 0;
                while(!buffer.isEmpty()) {
                    value += (Integer.parseInt(Character.toString(buffer.pop())) * base);
                    base *= 10;
                }
                stack.push(new Ele(value));
            }
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        leetcode394_Decode_String inst = new leetcode394_Decode_String();
        String s = "3[a]2[bc]";
//        String s = "3[a2[c]]";
//        String s = "2[abc]3[cd]ef";
//        String s = "100[leetcode]";
        System.out.println(inst.decodeString(s));
    }
}
