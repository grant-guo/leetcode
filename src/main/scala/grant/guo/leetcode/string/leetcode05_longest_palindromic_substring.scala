package grant.guo.leetcode.string

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 */
object leetcode05_longest_palindromic_substring extends App {

//  val input = "babad"
//  val input = "cbbd"
  val input = "bbb"

  val ret = longestPalindrome(input)
  println(ret)

  def longestPalindrome(s: String): String = {

    s.length match {
      case 0 | 1 => s
      case _ => {
        palindrome(s, 0, s.substring(0,1))
      }
    }
  }

  private def palindrome(s: String, pos: Int, max: String): String = {
    (pos == s.length-1) match {
      case true => max
      case false => {
        val max1 = palindromeBetween(s, pos-1, pos + 1, max)
        val max2 = (s(pos).equals(s(pos+1))) match {
          case true => {
            val r = palindromeBetween(s, pos -1, pos + 2, max)
            Array(r, max, s.substring(pos, pos+2)).sortWith(_.length > _.length)(0)
          }
          case false => max
        }
        palindrome(s, pos + 1,
          Array(max1, max2, max).sortWith(_.length > _.length)(0)
        )
      }
    }

  }

  private def palindromeBetween(s: String, left: Int, right:Int, max: String): String = {

    (left < 0 || right == s.length || !s(left).equals( s(right)) ) match {
      case true => max
      case false => palindromeBetween(s, left - 1, right + 1,
        if (max.length > (right - left + 1)) max else s.substring(left, right+1)
      )
    }
  }// end of palindrome

}
