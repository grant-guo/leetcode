package grant.guo.leetcode.string

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
object leetcode08_atoi {

  object State extends Enumeration {
    val INIT, MINUS, NUMBER, DONE, FAILED = Value
  }

  def isNumber(c: Char): Boolean = {
    c.isDigit
  }


  case class StateMachine(buffer: String, state: State.Value, isMinus: Boolean) {
    def transform(c: Char): StateMachine = {
      state match {
        case State.INIT => {
          c match {
            case ' ' =>  this.copy(state = State.INIT)
            case number: Char if (isNumber(number)) =>  this.copy(buffer = buffer+number, state = State.NUMBER)
            case minus: Char if (minus.equals('-')) =>  this.copy(state = State.MINUS, isMinus = true)
            case _ =>  this.copy(state = State.FAILED)
          }
        }
        case State.MINUS => {
          c match {
            case number: Char if (isNumber(number)) => this.copy(buffer = buffer+number, state = State.NUMBER)
            case _ => this.copy(state = State.FAILED)
          }
        }
        case State.NUMBER => {
          c match {
            case number: Char if(isNumber(number)) => this.copy(buffer = buffer+number)
            case _ => this.copy(state = State.DONE)
          }
        }//case NUMBER,
        case State.FAILED => this
        case State.DONE => this
      }// end of state match
    }// end of transform
  }

  val input = "" + "d"
  val output = input.toCharArray.foldLeft(StateMachine("",State.INIT, false))((sm, char) => {
    sm.transform(char)
  })

  output.state match {
    case State.FAILED => {}
    case State.DONE => {}
  }

}