package grant.guo.leetcode.string

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
object leetcode49_Group_Anagrams extends App {

  //Idea: convert each string into char array, then sort the array in a way.
  // for all string whose sorting results are the same, they are in the same group


  val strs = Array("eat", "tea", "tan", "ate", "nat", "bat")

  val output =
  strs.map(str => {
    (
      str.toCharArray.sortWith(_>_).foldLeft("")((acc, char) => {
        acc + char
      }),
      str
    )
  }).groupBy(tuple => {
    tuple._1
  }).mapValues(values => {
    values.map(_._2).toList
  }).values.toList

  output.foreach(arr => {
    arr.foreach(i => print(i + " "))
    println()
  })

}
