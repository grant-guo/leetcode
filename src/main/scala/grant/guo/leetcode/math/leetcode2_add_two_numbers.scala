package grant.guo.leetcode.math

import grant.guo.leetcode.string.leetcode12_integer_roman.x

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
