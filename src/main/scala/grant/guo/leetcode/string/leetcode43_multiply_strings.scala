package grant.guo.leetcode.string

/**
 * https://leetcode.com/problems/multiply-strings/
 *
 * Given two non-negative BigIntegers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigBigInteger library or convert the inputs to BigInteger directly.
 */
object leetcode43_multiply_strings extends App {

  def charToInt(char: Char): Int = {
    char match {
      case '0' => 0
      case '1' => 1
      case '2' => 2
      case '3' => 3
      case '4' => 4
      case '5' => 5
      case '6' => 6
      case '7' => 7
      case '8' => 8
      case '9' => 9
    }
  }

  def intToChar(num: Int): String = {
    num match {
      case 0 => "0"
      case 1 => "1"
      case 2 => "2"
      case 3 => "3"
      case 4 => "4"
      case 5 => "5"
      case 6 => "6"
      case 7 => "7"
      case 8 => "8"
      case 9 => "9"
    }
  }

  def strToBigInt(str: String, pos: Int): BigInt = {
    val length = str.length
    if (pos == length) {
      0
    } else {
      val cur = charToInt(str.charAt(pos))
      (cur * bigIntPow(10, length - pos - 1))+ strToBigInt(str, pos + 1)
    }
  }

  def bigIntPow(base:Int, expo: Int): BigInt = {
    if (expo == 0) {
      BigInt(1)
    } else {
      base * bigIntPow(base, expo - 1)
    }
  }

  def bigIntToStr(num: BigInt): String = {
    if (num < 10) {
      intToChar(num.toInt)
    } else {
      bigIntToStr(num/10) + intToChar(((num % 10).toInt))
    }
  }

//  val num1 = "6913259244"
//  val num2 = "71103343"

  val num1 = "401716832807512840963"
  val num2 = "167141802233061013023557397451289113296441069"

  val output = bigIntToStr(strToBigInt(num1, 0) * strToBigInt(num2, 0))

  println(Int.MaxValue)
  println(strToBigInt(num1, 0))
  println(strToBigInt(num2, 0))
  println(strToBigInt(num1, 0) * strToBigInt(num2, 0))
  println(output)
}
