package grant.guo.leetcode.array

import scala.::
import scala.collection.mutable.ListBuffer

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:
Input: nums = []
Output: []

Example 3:
Input: nums = [0]
Output: []
 */
object  leetcode13_3sum extends App {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val target = 0
    nums.length match {
      case length if length < 3 => List.empty
      case length if length >3000 => List.empty
      case _ => {
        nums.sortWith(_ < _) match {
          case Array(first, rest@_*) => {
            val ret = (twoSum(rest.toArray, target - first) match {
              case Nil => Nil
              case list => list.map(first :: _)
            }) ::: threeSum(rest.toArray)

            ret.toSet.toList
          }
        }//end of nums match
      }//end of case _
    }
  }

  def twoSum(nums: Array[Int], target: Int): List[List[Int]] = {
    var left = 0
    var right = nums.length - 1
    var ret = List.empty[List[Int]]
    while(left < right) {
      val leftValue = nums(left)
      val rightValue = nums(right)
      val sum = leftValue + rightValue
      if (sum == target) {
        ret = (leftValue :: rightValue :: Nil) :: ret
        right = right - 1
        left = left + 1
      } else if (sum > target) {
        right = right - 1
      } else {
        left  = left + 1
      }
    }
    ret
  }

  val array = Array(0,0,0,0)
  println(threeSum(array))

}
