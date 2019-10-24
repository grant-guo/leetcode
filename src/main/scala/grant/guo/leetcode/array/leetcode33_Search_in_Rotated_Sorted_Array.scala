package grant.guo.leetcode.array

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 */
object leetcode33_Search_in_Rotated_Sorted_Array extends App {

  def search(nums: Array[Int], target: Int): Int = {
    /**
     * idea: based on binary search.
     *
     * once the mid, check if two sub array are sorted too.
     * For still sorted sub array, check if the target is with it
     *
     */
    search(nums, 0, nums.length -1, target)
  }

  def search(nums: Array[Int], s:Int, e:Int, target: Int): Int = {
    if (nums.length == 0){
      -1
    }
    else if(s >= e && nums(s) != target) {
      -1
    }
    else if(s + 1== e) {
      if(target == nums(s)) s else if(target == nums(e)) e else -1
    }else {
      val mid = (s + e)/2

      if(nums(mid) == target) {
        mid
      } else {
        if(isArraySorted(nums, s, mid - 1) && isArraySorted(nums, mid + 1, e)) {
          if (target >= nums(s) && target <= nums(mid - 1)) {
            search(nums, s, mid-1, target)
          } else if (target >= nums(mid+1) && target <= nums(e)) {
            search(nums, mid+1, e, target)
          } else {
            -1
          }
        } else if (isArraySorted(nums, s, mid - 1) && target >= nums(s) && target <= nums(mid - 1)) {
          search(nums, s, mid-1, target)
        } else if (isArraySorted(nums, mid + 1, e) && target >= nums(mid+1) && target <= nums(e)) {
          search(nums, mid+1, e, target)
        } else if (isArraySorted(nums, s, mid - 1) && !(target >= nums(s) && target <= nums(mid - 1))) {
          search(nums, mid+1, e, target)
        } else if(isArraySorted(nums, mid + 1, e) && !(target >= nums(mid+1) && target <= nums(e))) {
          search(nums, s, mid-1, target)
        }
        else {
          -1
        }
      }
    }
  }

  def isArraySorted(array: Array[Int], s: Int, e: Int): Boolean = {
    array(e) >= array(s)
  }

  val ret = search(Array(6,7,1,2,3,4,5), 6)
  println(ret)
}
