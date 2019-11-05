package grant.guo.leetcode.stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode385_Mini_Parser {

    private class Pair{
        public NestedInteger instance;
        public boolean isClose;
        public Pair(NestedInteger ni, boolean close) {
            this.instance = ni;
            this.isClose = close;
        }
    }

    public NestedInteger deserialize(String s) {
        Stack<Pair> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for(int i = 0;i < s.length();i++) {
            char cur = s.charAt(i);
            if(cur == '[') {
                stack1.push( new Pair(createNewNestedInteger(false, null), false));
            } else if (cur == ']') {
                if(!stack2.isEmpty()) {
                    stack1.peek().instance.add(generateInteger(stack2));
                    stack1.peek().isClose = true;
                } else {
                    if(stack1.peek().isClose == true) {
                        NestedInteger top = stack1.pop().instance;
                        stack1.peek().instance.add(top);
                        stack1.peek().isClose = true;
                    } else {
                        stack1.peek().isClose = true;
                    }
                }
            } else if (cur == ',') {
                if(!stack2.isEmpty()) {
                    stack1.peek().instance.add(generateInteger(stack2));
                } else {
                    NestedInteger top = stack1.pop().instance;
                    stack1.peek().instance.add(top);
                }
            } else {
                // digit and minus sysmbol
                stack2.push(cur);
            }
        }
        if(!stack2.isEmpty()) {
            return generateInteger(stack2);
        }
        return stack1.pop().instance;
    }

    public NestedInteger generateInteger(Stack<Character> stack) {
        int base = 1;
        int value = 0;
        while(!stack.isEmpty()) {
            Character top = stack.pop();
            if(top == '-') {
                value = value * (-1);
            } else {
                value += Integer.parseInt(top.toString()) * base;
                base *= 10;
            }
        }
        return createNewNestedInteger(true, value);
    }


    public NestedInteger createNewNestedInteger(boolean isInteger, Integer value) {
        NestedInteger ni = new NestedIntegerImpl();
        if(isInteger && value != null)
            ni.setInteger(value);
        return ni;
    }

    private static class NestedIntegerImpl implements NestedInteger{
        private Integer value = null;
        private List<NestedInteger> children = new ArrayList<>();
        @Override
        public boolean isInteger() {
            return value != null;
        }

        @Override
        public Integer getInteger() {
            return value;
        }

        @Override
        public void setInteger(int value) {
            this.value = value;

        }

        @Override
        public void add(NestedInteger ni) {
            children.add(ni);
        }

        @Override
        public List<NestedInteger> getList() {
            return this.children;
        }
    }

    public static void main(String[] args) {
        leetcode385_Mini_Parser inst = new leetcode385_Mini_Parser();
        String s = "[[],[[[[]],-4],[[[]],[0],[[]]]]]";
//        String s = "123";
//        String s = "-3";
//        String s = "[]";
//        String s = "[[]]";
//        String s = "[123,[456,[789]]]";
        NestedInteger ret = inst.deserialize(s);
        System.out.println(ret);

    }
}

