package grant.guo.leetcode.dp;

public class interview_questions_subsequence {

    /**
     * input1 ABC
     * input2 ABCBABC
     *
     * Find number of times a string occurs as a subsequence in given string
     *
     *
     */

    public int count(String source, int s_length, String target, int t_length) {

        if(s_length == 0)
            return 1;
        if(t_length == 0)
            return 0;

        if (source.charAt(s_length - 1) == target.charAt(t_length-1)) {
            return count(source, s_length -1, target, t_length - 1) + count(source, s_length, target, t_length-1);
        } else {
            return count(source, s_length, target, t_length - 1);
        }
    }
/*
            a	b	c
	    1	0	0	0
    a	1	1	0	0
    b	1	1	1	0
    c	1	1	1	1
    b	1	1	2	1
    a	1	2	2	1
    b	1	2	4	1
    c	1	2	4	5

*/

    public int countDP(String source, String target) {
        String a = target;
        String b = source;
        int m = a.length();
        int n = b.length();

        // Create a table to store
        // results of sub-problems
        int lookup[][] = new int[m + 1][n + 1];

        // If first string is empty
        for (int i = 0; i <= n; ++i)
            lookup[0][i] = 0;

        // If second string is empty
        for (int i = 0; i <= m; ++i)
            lookup[i][0] = 1;

        // Fill lookup[][] in
        // bottom up manner
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                // If last characters are
                // same, we have two options -
                // 1. consider last characters
                //    of both strings in solution
                // 2. ignore last character
                //    of first string
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    lookup[i][j] = lookup[i - 1][j - 1] +
                            lookup[i - 1][j];

                else
                    // If last character are
                    // different, ignore last
                    // character of first string
                    lookup[i][j] = lookup[i - 1][j];
            }
        }

        return lookup[m][n];
    }


    public static void main(String[] args) {
        interview_questions_subsequence inst = new interview_questions_subsequence();
        String source = "abc";
        String target = "abcbabc";
        int ret = inst.count(source, source.length(), target, target.length());
        System.out.println(ret);

        ret = inst.countDP(source, target);
        System.out.println(ret);
    }
}
