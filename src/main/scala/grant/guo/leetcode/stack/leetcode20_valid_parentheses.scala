package grant.guo.leetcode.stack

import scala.collection.mutable.Stack

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
Example 4:

Input: s = "([)]"
Output: false
Example 5:

Input: s = "{[]}"
Output: true
 */
object leetcode20_valid_parentheses extends App {
  def isValid(s: String): Boolean = {

    val ret = s.toList.foldLeft((true, Stack[Char]()))((acc, c) => {
      acc._1 match {
        case true => {
          c match {
            case '(' | '{' | '[' => acc._2.push(c); (true, acc._2)
            case ')' => if (!acc._2.isEmpty && acc._2.top == '(') {acc._2.pop();(true, acc._2) } else (false, acc._2)
            case ']' => if (!acc._2.isEmpty && acc._2.top == '[') {acc._2.pop();(true, acc._2) } else (false, acc._2)
            case '}' => if (!acc._2.isEmpty && acc._2.top == '{') {acc._2.pop();(true, acc._2) } else (false, acc._2)
          }
        }
        case false => acc
      }
    })

    if (ret._1 == true && ret._2.size == 0)
      true
    else
      false

  }

  println(isValid("("))
  println(isValid("()"))
  println(isValid("()[]{}"))
  println(isValid("(]"))
  println(isValid("([)]"))
  println(isValid("{[]}"))
}
