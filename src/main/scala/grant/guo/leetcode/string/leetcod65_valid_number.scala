package grant.guo.leetcode.string

object leetcod65_valid_number extends App {
  def isNumber(s: String): Boolean = {
    val sm = new StateMachine
    def isNumberR(list: List[Char], sm: StateMachine): Boolean = {
      sm.state match {
        case State.ERROR => false
        case _ => {
          list match {
            case Nil =>
              sm.state match {
                case State.INTEGER | State.DECIMAL | State.EXPO_INTEGER => true
                case _ => false
              }
            case first :: rest => sm.process(first); isNumberR(rest, sm)
          }//end of list match
        }//end of case _
      }//end of sm.state match
    }
    isNumberR(s.toList, sm)
  }

  object State extends Enumeration {
    val START, SIGN, INTEGER, DECIMAL, DECIMAL_PENDING, EXPO, EXPO_SIGN, EXPO_INTEGER, ERROR = Value
  }

  class StateMachine {
    var state: State.Value = State.START
    def process(c: Char): Unit = {
      state match {
        case State.START =>
          c match {
            case '-' | '+' => state = State.SIGN
            case '.' => state = State.DECIMAL_PENDING
            case v if v.isDigit => state = State.INTEGER
            case _ => state = State.ERROR
          }
        case State.DECIMAL_PENDING =>
          c match {
            case v if v.isDigit => state = State.DECIMAL
            case _ => state = State.ERROR
          }
        case State.SIGN =>
          c match {
            case '.' => state = State.DECIMAL_PENDING
            case v if v.isDigit => state = State.INTEGER
            case _ => state = State.ERROR
          }
        case State.INTEGER =>
          c match {
            case '.' => state = State.DECIMAL
            case 'e' | 'E' => state = State.EXPO
            case v if v.isDigit =>
            case _ => state = State.ERROR
          }
        case State.DECIMAL =>
          c match {
            case 'e' | 'E' => state = State.EXPO
            case v if v.isDigit =>
            case _ => state = State.ERROR
          }
        case State.EXPO =>
          c match {
            case '-' | '+' => state = State.EXPO_SIGN
            case v if v.isDigit => state = State.EXPO_INTEGER
            case _ => state = State.ERROR
          }
        case State.EXPO_SIGN =>
          c match {
            case v if v.isDigit => state = State.EXPO_INTEGER
            case _ => state = State.ERROR
          }
        case State.EXPO_INTEGER =>
          c match {
            case v if v.isDigit =>
            case _ => state = State.ERROR
          }
      }
    } //end of process

  }//end of StateMachine

//  List("2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789").foreach(v => println(isNumber(v)))

  List("abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53").foreach(v => println(isNumber(v)))
}
