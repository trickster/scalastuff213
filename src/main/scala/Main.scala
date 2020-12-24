import cats.parse.{Parser => P, Parser1 => P1}

import scala.math.{exp, _}

sealed trait Op
case object Addition extends Op
case object Multiplication extends Op

object Main  {
  def main(args: Array[String]): Unit = {
    val result = parseInput("1 + 2")
    println(result)
  }

  def parseInput(in: String) = {
    val digits = P.charIn('0' to '9').rep1.string.map(BigInt(_))
    val ws = P.char(' ')

    val ADD = ws.soft *> P.char('+').as[Op](Addition) <* ws
    val MULT = ws.soft *> P.char('*').as[Op](Multiplication) <* ws
    val OP = P.oneOf1(ADD::MULT::Nil)

    def exp : P1[BigInt] = {
      val recurse = P.defer1(exp)

      val parened = P.char('(') *> recurse <* P.char(')')
      val term = P.oneOf1(parened :: digits :: Nil)

      (term ~ (OP ~ term).rep).map {
        case (lhs,chunks) => chunks.foldLeft(lhs) {
          case (lhs, (op, rhs)) => op match {
            case Addition => lhs + rhs
            case Multiplication => lhs * rhs
          }
        }
      }
    }
    exp.parseAll(in)
  }


}
