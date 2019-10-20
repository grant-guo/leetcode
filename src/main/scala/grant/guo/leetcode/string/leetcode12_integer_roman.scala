package grant.guo.leetcode.string

/**
 * https://leetcode.com/problems/integer-to-roman/
 *
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 */
object leetcode12_integer_roman extends App {

  val num = 1994

  val i = num % 10
  val x = (num/10) % 10
  val c = (num/100) % 10
  val m = (num/1000) % 10

  val buffer = new StringBuffer()
  m match {
    case 0 =>
    case _ => buffer.append("M"*m)
  }

  c match {
    case 0 =>
    case n if (n < 4) => buffer.append("C"*c)
    case 4 => buffer.append("CD")
    case 5 => buffer.append("D")
    case n if (n < 9) => buffer.append("D" + "C"*(n-5))
    case 9 => buffer.append("CM")
  }

  x match {
    case 0 =>
    case n if (n < 4) => buffer.append("X"*x)
    case 4 => buffer.append("XL")
    case 5 => buffer.append("L")
    case n if (n < 9) => buffer.append("L" + "X"*(n-5))
    case 9 => buffer.append("XC")
  }

  i match {
    case 0 =>
    case n if (n < 4) => buffer.append("I"*i)
    case 4 => buffer.append("IV")
    case 5 => buffer.append("V")
    case n if (n < 9) => buffer.append("V" + "I"*(n-5))
    case 9 => buffer.append("IX")
  }

  println(buffer.toString)

}
