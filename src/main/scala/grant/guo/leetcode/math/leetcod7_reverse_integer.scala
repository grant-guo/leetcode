package grant.guo.leetcode.math

/*
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
Example 4:

Input: x = 0
Output: 0
 */
object leetcod7_reverse_integer extends App {

  def reverse(x: Int): Int = {
    def reverseR(x: Int): (Long, Int) = {
      x/10 match {
        case 0 => (x, 0)
        case v => {
          val tuple = reverseR(v)
          val multiplierOfTen = tuple._2 + 1
          (x%10 * Math.pow(10, multiplierOfTen).toLong + tuple._1, multiplierOfTen)
        }
      }
    }
    val ret = reverseR(x)._1
    if (ret > Math.pow(2, 31) - 1 || ret < (-1) * Math.pow(2, 31)) {
      0
    } else
      ret.toInt
  }

  println(reverse(1534236459))

}
