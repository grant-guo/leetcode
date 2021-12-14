package grant.guo.leetcode.list

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 */
object leetcode23_merge_k_sorted_lists extends App {

  def mergeKLists(lists: Array[ListNode]): ListNode = {
    if (lists == null)
      null
    else {
      var current = lists.toList.filter(_ != null)
      val queues = new ListBuffer[mutable.PriorityQueue[Int]]

      while(!current.isEmpty) {
        val max = current.maxBy(_.x).x

        val queue = new mutable.PriorityQueue[Int]().reverse
        current = current.flatMap(n => {
          var node = n
          while(node != null && node.x <= max ) {
            queue.enqueue(node.x)
            node = node.next
          }//end of while
          if (node == null)
            List.empty
          else
            List(node)
        })
        queues.append(queue)
      }

      if (queues.isEmpty) {
        null
      } else {
        queues.map(priQ => priQ.foldRight((null: ListNode, null: ListNode))((v, acc) => {
          if (acc._1 == null) {
            val newNode = new ListNode(v, null)
            (newNode, newNode)
          } else {
            (new ListNode(v, acc._1), acc._2)
          }
        })).foldRight(null: ListNode)((tuple, acc) => {
          if (acc == null)
            tuple._1
          else {
            tuple._2.next = acc
            tuple._1
          }
        })
      }//end of else
    }//end of else
  }

  def printListNode(node: ListNode): Unit = {
    if (node != null) {
      print(s"${node.x}, ")
      printListNode(node.next)
    } else
      println("null")
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }

  val lists = Array(
    new ListNode(1, new ListNode(5, new ListNode(7, null))),
    new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(8, null)))),
    new ListNode(3, new ListNode(6, null))
  )

  printListNode(mergeKLists(lists))
  printListNode(mergeKLists(null))
  printListNode(mergeKLists(Array(null)))
}
