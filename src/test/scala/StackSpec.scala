import collection.mutable.Stack
import org.scalatest.flatspec._

class StackSpec extends AnyFlatSpec {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    assertThrows[NoSuchElementException] {
      emptyStack.pop()
    }
  }

  "parsing" should "be successful" in {

    val rawString = "1 * 2 * (2 + 3)"

    val result = Main.parseInput(rawString)

    assert(Right(10) == result)
  }

}