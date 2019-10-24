package grant.guo.leetcode.array

/**
 *
 * https://leetcode.com/problems/3sum-closest/
 *
 */
object leetcode16_3sum_closest extends App {
  case class Ret(value: Option[Int], minDiff: Int)

  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    // define 3 points: i, j, k. with fixed i, run 2sum algorithms against j and k

    val sorted = nums.sortWith(_<_)
    recursive(sorted, target, Ret(None, Integer.MAX_VALUE))

  }

  def recursive(nums: Array[Int], target: Int, ret: Ret): Int = {
    var minDiff = ret.minDiff
    var result = ret.value match {
      case Some(v) => v
      case None => 0
    }
    if(nums.length < 3){
      ret.value.get
    } else {
      var i = 1
      var j = nums.length - 1

      while( i < j) {
        val sum = nums(0) + nums(i) + nums(j)
        val diff = Math.abs(sum-target)
        if(diff == 0) return sum

        if (diff < minDiff) {
          minDiff = diff
          result = sum
        }

        if(sum < target) i = i + 1 else j = j - 1
      }

      recursive(nums.tail, target, Ret(Some(result), minDiff))

    }

  }


//  val ret = threeSumClosest(Array(-1, 2, 1, -4), 1)
//  val ret = threeSumClosest(Array(1,1,1,1), -100)
  val ret = threeSumClosest(Array(1,1,1,0), -100)
  println(ret)
}
