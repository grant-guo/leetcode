package grant.guo.leetcode.math

import grant.guo.leetcode.math.leetcode2_add_two_numbers.{ListNode, addTwoNumbers}

/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]

Example 2:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]

Example 3:
Input: l1 = [0], l2 = [0]
Output: [0]
 */


object leetcode445_add_two_numbers_ii extends App {

  type HEAD=ListNode
  type TAIL=ListNode
  def reverseListNode(l: ListNode): (HEAD, TAIL) = {
    Option(l.next) match {
      case None => {
        val node = new ListNode(l.x, null)
        (node, node)
      }
      case Some(v) => {
        val (head, tail) = reverseListNode(l.next)
        tail.next = new ListNode(l.x, null)
        (head, tail.next)
      }
    }
  }


  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    val reversedL1 = reverseListNode(l1)._1
    val reversedL2 = reverseListNode(l2)._1

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
    val reversedRet = addTwoNumbersR(reversedL1, reversedL2, 0)
    reverseListNode(reversedRet)._1
  }

  val ret = addTwoNumbers(
    new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3, null)))),
    new ListNode(5, new ListNode(6, new ListNode(4, null)))
  )

  println(ret.print())

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
    def print(): List[Int] = {
      Option(next) match {
        case None => List(x)
        case Some(n) => _x :: n.print()
      }
    }
  }
}
