package grant.guo.leetcode.string
/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:
1 <= n <= 8
 */
object leetcode22_generate_parentheses extends App {
  def generateParenthesis(n: Int): List[String] = {
    val buffer = new Array[List[String]](9)
    buffer(1) = List("()")
    buffer(2) = List("(())", "()()")
    def generateParenthesisR(n: Int): List[String] = {
      if (buffer(n) != null)
        buffer(n)
      else {
        val list = (1 to n/2).flatMap(index => {
          if (index == 1) {
            generateParenthesisR(n-index).flatMap(str => {
              List(s"()${str}", s"(${str})", s"${str}()")
            }).toSet.toList
          } else {
            val ret = for(
                str1 <- generateParenthesisR(index);
                str2 <- generateParenthesisR(n-index)
              ) yield (s"$str1$str2", s"$str2$str1")
            ret.flatMap(tuple => List(tuple._1, tuple._2))
          }
        }).toSet.toList
        buffer(n) = list
        list
      }
    }
    generateParenthesisR(n)
  }

  println(generateParenthesis(1))
  println(generateParenthesis(3))
  println(generateParenthesis(4))
  println(generateParenthesis(5))
}
