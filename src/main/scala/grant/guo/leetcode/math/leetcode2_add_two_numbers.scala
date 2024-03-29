package grant.guo.leetcode.math

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


 */

object leetcode2_add_two_numbers extends App {

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    def addTwoNumbersR(l1: ListNode, l2: ListNode, advance: Int): ListNode = {
      (Option(l1), Option(l2)) match {
        case (None, None) => {
          if (advance == 0)
            null
          else
            new ListNode(advance, null)
        }
        case (Some(v1), Some(v2)) => {
          (v1.x + v2.x + advance) match {
            case value if value < 10 => new ListNode(value, addTwoNumbersR(l1.next, l2.next, 0))
            case value if value >= 10 => new ListNode(value - 10, addTwoNumbersR(l1.next, l2.next, 1))
          }
        }
        case (Some(v1), None) => {
          (v1.x + advance) match {
            case value if value < 10 => new ListNode(value, addTwoNumbersR(l1.next, null, 0))
            case value if value >= 10 => new ListNode(value - 10, addTwoNumbersR(l1.next, null, 1))
          }
        }
        case (None, Some(v2)) => {
          (v2.x + advance)match {
            case value if value < 10 => new ListNode(value, addTwoNumbersR(null, l2.next, 0))
            case value if value >= 10 => new ListNode(value - 10, addTwoNumbersR(null, l2.next, 1))
          }
        }
      }

    }

    addTwoNumbersR(l1, l2, 0);
  }

   class ListNode(_x: Int = 0, _next: ListNode = null) {
       var next: ListNode = _next
       var x: Int = _x
      def print(): List[Int] = {
        Option(_next) match {
          case None => List(_x)
          case Some(next) => _x :: _next.print()
        }
      }
   }

  val ret = addTwoNumbers(
    new ListNode(2, new ListNode(4, new ListNode(3, null))),
    new ListNode(5, new ListNode(6, new ListNode(4, null)))
  )

  println(ret.print())

}
