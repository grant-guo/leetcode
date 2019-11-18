package grant.guo.leetcode.stack;

import java.util.*;
import java.util.stream.Collectors;

public class leetcode636_Exclusive_Time_of_Functions {

    public int[] exclusiveTime(int n, List<String> logs) {

        List<int[]> list = logs.stream().map(log -> {
            int[] ret = new int[3];
            String[] parts = log.split(":");
            ret[0] = Integer.parseInt(parts[0]);
            ret[1] = 0;
            if(parts[1].equals("start"))
                ret[1] = 1;
            ret[2] = Integer.parseInt(parts[2]);
            return ret;
        }).sorted((a, b) -> {
            if(a[2] < b[2])
                return -1;
            else if (a[2] > b[2])
                return 1;
            else
                return 0;
        }).collect(Collectors.toList());

        Stack<int[]> stack = new Stack<>();
        List<int[]> rets = new LinkedList<>();
        for(int[] arr: list) {
            if(!stack.isEmpty() && (stack.peek())[0] == arr[0]) {
                int[] f = new int[2];
                f[0] = arr[0];
                f[1] = arr[2] - (stack.peek())[2] + 1;

                if(rets.size() != 0) {
                    f[1] = f[1] - (rets.get(0))[1];
                }
                rets.add(0, f);
                stack.pop();
            } else
                stack.push(arr);
        }

        return rets.stream().sorted((a, b) -> {
            if(a[0] < b[0])
                return -1;
            else if (a[0] > b[0])
                return 1;
            else
                return 0;
        }).mapToInt(arr -> arr[1]).toArray();
    }

    public static void main(String[] args) {
        leetcode636_Exclusive_Time_of_Functions inst = new leetcode636_Exclusive_Time_of_Functions();
        List<String> logs = new LinkedList<String>() {{
            this.add("0:start:0");
            this.add("1:start:2");
            this.add("1:end:5");
            this.add("0:end:6");
        }};
        int[] ret = inst.exclusiveTime(logs.size(), logs);
        for(int num: ret) {
            System.out.print(num + ", ");
        }
    }
}
