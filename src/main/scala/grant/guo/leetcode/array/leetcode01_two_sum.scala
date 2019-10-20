package grant.guo.leetcode.array

/**
 * https://leetcode.com/problems/two-sum/
 *
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
object leetcode01_two_sum extends App {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val sorted = nums.zipWithIndex.sortWith(_._1 < _._1)
    var left = 0
    var right = sorted.length - 1
    while(left < right) {
      val cal = sorted(left)._1 + sorted(right)._1
      if(cal > target) {
        right = right - 1
      } else if (cal < target) {
        left = left + 1
      } else {
        return Array(sorted(left)._2, sorted(right)._2)
      }
    }
    return Array.emptyIntArray
  }

  twoSum(Array(3,2,4), 6)
}
