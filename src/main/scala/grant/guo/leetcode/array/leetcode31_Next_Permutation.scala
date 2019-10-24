package grant.guo.leetcode.array

object leetcode31_Next_Permutation extends App {
  def nextPermutation(nums: Array[Int]): Array[Int] = {
    if (!hasNext(nums)) {
      reverse(nums.toList).toArray
    } else {
      next(Array(nums(0)), nums.tail)
    }
  }

  def next(pre: Array[Int], post: Array[Int]): Array[Int] = {
    if (post.length == 2) {
      if (post(0) >= post(1)) pre ++ post else pre :+ post(1) :+ post(0)
    } else {
      hasNext(post) match {
        case true => {
          next(pre :+ post(0), post.tail)
        }
        case false => {
          val cur = pre(pre.length-1)
          var i = post.length-1
          findNext(cur, post.toList) match {
            case Some(v) => {
              val newArray = remove(v, (cur +: post.toList)).sortWith( _<_).toArray

              pre.slice(0, pre.length-1) ++ (v +: newArray)
            }
            case None => {
              pre ++ post
            }
          }

        }
      }//end of match
    }//end of else
  }

  def remove(target:Int, source:List[Int]): List[Int] = {

    def removeR(target:Int, pre: List[Int], next: List[Int]): List[Int] = {
      next match {
        case Nil => pre
        case v::rest => {
          if (v == target) {
            pre ::: rest
          } else {
            removeR(target, pre :+ v, rest)
          }
        }
      }
    }
    removeR(target, List.empty[Int], source)
}

  def findNext(target: Int, source:List[Int]): Option[Int] = {
    source match {
      case Nil => None
      case v1::rest  => {
        if (v1 > target && target >= rest.head) {
          Some(v1)
        } else {
          findNext(target, rest)
        }
      }
    }
  }

  def hasNext(nums: Array[Int]): Boolean = {
    val sorted = nums.sortWith(_>_)
    if (sorted.mkString.equals(nums.mkString)) false else true
  }

  def reverse(list: List[Int]): List[Int] = {
    list match {
      case last::Nil => List(last)
      case item::rest => reverse(rest) :+ item
    }
  }

  val ret = nextPermutation(Array(1,2,3))

  ret.foreach(i => print(i + ", "))
}
