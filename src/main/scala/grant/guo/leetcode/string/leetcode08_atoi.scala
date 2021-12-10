package grant.guo.leetcode.string

import grant.guo.leetcode.string.leetcode08_atoi.State.LEADING

import scala.::

/**
 *
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 */
object leetcode08_atoi extends App {

  def myAtoi(s: String): Int = {
    val sm = new StateMachine()
    def myAtoiR(list: List[Char], sm: StateMachine): Int = {
      sm.state match {
        case State.DONE => sm.done()
        case State.INVALID => 0
        case _ => {
          list match {
            case Nil => sm.state = State.DONE; myAtoiR(Nil, sm)
            case head :: rest => sm.process(head); myAtoiR(rest, sm)
          }
        }//end of case _
      }//end of sm.state match
    }
    myAtoiR(s.toList, sm)
  }

  object State extends Enumeration {
    val LEADING, SIGN, NUMBER, FIRST_NUMBER, INVALID, DONE = Value
  }

  class StateMachine() {
    var state: State.Value = State.LEADING
    var sign: Int = 1
    var buffer: List[Int] = List.empty
    def process(char: Char): Unit = {
      state match {
        case State.LEADING => {
          char match {
            case ' ' =>
            case '-' | '+' => {
              state = State.SIGN
              process(char)
            }
            case v if v.isDigit => {
              state = State.FIRST_NUMBER
              process(char)
            }
            case _ => state = State.INVALID
          }
        }
        case State.SIGN => {
          char match {
            case '-' => sign = -1; state = State.FIRST_NUMBER
            case '+' => sign = 1; state = State.FIRST_NUMBER
          }
        }
        case State.FIRST_NUMBER => {
          char match {
            case '0' =>
            case c if c.isDigit => state = State.NUMBER;process(char)
            case _ => state = State.INVALID
          }
        }
        case State.NUMBER => {
          char match {
            case v if v.isDigit => buffer =  buffer :+ char.toDigitValue()
            case v if !v.isDigit => state = State.DONE
          }
        }
      }
    }//end of process

    implicit class CharConversion(c: Char) {
      def toDigitValue(): Int = {
        c match {
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
    }

    def done(): Int = {
      buffer.size match {
        case size if size > 10 => {
          sign match {
            case 1 => Int.MaxValue
            case -1 => Int.MinValue
          }
        }
        case size if size == 10 && buffer(0) > 2 => {
          sign match {
            case 1 => Int.MaxValue
            case -1 => Int.MinValue
          }
        }
        case size => {
          buffer match {
            case Nil => 0
            case first :: Nil => first * sign
            case first :: rest => {
              val num1 = first * ( 1 to (size-1)).foldLeft(1)((accu, _) => accu * 10)
              val num2 = listToInt(rest)
              addTwoInt(num1, num2)
            }
          }
        }//end of case _
      }
    }//end done

    def listToInt(list: List[Int]): Int = {
      list match {
        case Nil => 0
        case first :: Nil => first
        case first :: rest => first * ( 1 to rest.size).foldLeft(1)((accu, _) => accu * 10) + listToInt(rest)
      }
    }//end of listToInt

    //need to check if the number is overflow
    def addTwoInt(i1: Int, i2: Int): Int = {
      val ret = i1 + i2
      if (ret < 0) {
        sign match {
          case 1 => Int.MaxValue
          case -1 => Int.MinValue
        }
      }
      else
        ret * sign
    }
  }


  println(myAtoi("-345435423985948"))
}