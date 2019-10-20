package grant.guo.leetcode.string

import scala.collection.mutable

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 *
 *
 * with a sliding window: w[i, j] represented by a mutable HashMap[Char, Int], key is the character itself, value is the location of the character
 * if w[j+1] is not in the HashMap, extend the window to the right.
 * if w[j+1] is in the HashMap, find the duplicated index called "duplicated", remove the characters from i to duplicated, assign i to duplicated, assign j to j+1
 * until j == length of the string
 *
 */
object leetcode03_longestsubstring extends App {

  val target = ""
//  val target = "abcabcbb"
//  val target = "bbbbb"
//  val target = "pwwkew"

  val array = target.toCharArray
  array.size match {
    case 0 => println(0)
    case _ => {
      val window = new mutable.HashMap[Char, Int]()
      var i, j: Int = 0
      window.put(array(0), i)

      val ret = longgestSubstring(array, i, j, window, 1)
      println(ret)
    }
  }

  def longgestSubstring(array: Array[Char], i: Int, j:Int, window: mutable.HashMap[Char, Int], max:Int): Int = {
    if (j == array.length-1) {
      max
    } else {
      val next = j + 1;
      window.get(array(next)) match {
        case Some(duplicated) => {
          (i to duplicated).foreach(index => {
            window.remove(array(index))
          })
          window.put(array(next), next)
          longgestSubstring(array, duplicated+1, next, window, max)
        }
        case None => {
          window.put(array(next), next)
          longgestSubstring(array, i, next, window,
            max > window.size match {
              case true => max
              case false => window.size
            }
          )
        }
      }
    }
  }


}
