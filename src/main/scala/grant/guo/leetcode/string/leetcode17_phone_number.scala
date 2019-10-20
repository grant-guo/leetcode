package grant.guo.leetcode.string

/**
 *https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
object leetcode17_phone_number extends App {

  val map = Map(
    '2' -> List("a", "b", "c"),
    '3' -> List("d", "e", "f"),
    '4' -> List("g", "h", "i"),
    '5' -> List("j", "k", "l"),
    '6' -> List("m", "n", "o"),
    '7' -> List("p", "q", "r", "s"),
    '8' -> List("t", "u", "v"),
    '9' -> List("w", "x", "y", "z")
  )

  def combinations(str: String, pos: Int): List[String] = {
    str.trim.isEmpty match {
      case true => List.empty
      case false => {
        if (pos == str.length -1 ){
          map.get(str.charAt(pos)).get
        } else {
          val rets = combinations(str, pos + 1)
          val cols = map.get(str.charAt(pos)).get
          rets.flatMap(substr => {
            cols.map(_ + substr)
          })
        }
      }
    }

  }

  val phone = ""
  val rets = combinations(phone, 0)

  println(rets.size)
  rets.foreach(println _)
}
