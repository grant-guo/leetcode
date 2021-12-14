package grant.guo.leetcode.list
/*
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 */
object leetcode19_remove_nth_node_from_end_of_list extends App {
  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    var fasterPointer = head
    var slowerPointer = head
    if (n == 1) {
      if (fasterPointer.next == null)
        null
      else {
        while(fasterPointer.next.next != null) {
          fasterPointer = fasterPointer.next
        }
        fasterPointer.next = null
        head
      }
    } else {
      var count = n -1
      while(count > 0 && fasterPointer != null) {
        fasterPointer = fasterPointer.next
        count = count - 1
      }
      if (fasterPointer == null) {
        head
      } else {
        while(fasterPointer.next != null) {
          fasterPointer = fasterPointer.next
          slowerPointer = slowerPointer.next
        }
        if (slowerPointer == head)
          slowerPointer.next
        else {
          slowerPointer.x = slowerPointer.next.x
          slowerPointer.next = slowerPointer.next.next
          head
        }
      }
    }
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
  def printListNode(node: ListNode): Unit = {
    if (node != null) {
      print(s"${node.x}, ")
      printListNode(node.next)
    }
  }

  val head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))))
//  val head = new ListNode(1, new ListNode(2, null))

  val ret = removeNthFromEnd(head, 5)

  printListNode(ret)
}
