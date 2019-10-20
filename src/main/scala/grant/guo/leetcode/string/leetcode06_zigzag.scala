package grant.guo.leetcode.string

/**
 *
 *
 *https://leetcode.com/problems/zigzag-conversion/
 *
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 *
 * 2
 * P Y A I H R N    1
 * A P L S I I G
 *
 * 3
 * P   A   H   N    3
 * A P L S I I G    1
 * Y   I   R
 *
 * 4
 * P     I    N   0  5
 * A   L S  I G   1  3,1,3,1
 * Y A   H R      2  1,3,1,3
 * P     I        3  5
 *
 * 5
 * P       H      0  7
 * A     S I      1  5,1,5,1
 * Y   I   R      2  3,3,3
 * P L     I G    3  1,5,1,5
 * A       N      4  7
 *
 *
 * 6
 * P      P     P  0 9
 * P     PP    P   1 7,1,7,1
 * P    P P   P    2 5,3,5,3
 * P  P   P  P     3 3,5,3.5
 * P P    P P      4 1,7,1,7
 * P      P        5 9
 *
 */
object leetcode06_zigzag {

  val numOfRows = 4

}
