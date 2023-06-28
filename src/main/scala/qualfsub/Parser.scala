package diamond.qualfsub

import diamond.parser._
import org.antlr.v4.runtime._

abstract class IR

class DiamondVisitor extends DiamondParserBaseVisitor[IR] {

}

object Parser {
  def parse(input: String): IR = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new DiamondLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new DiamondParser(tokens)
    val visitor = new DiamondVisitor()
    val res: IR = visitor.visit(parser.program).asInstanceOf[IR]
    res
  }

  def parseFile(filepath: String): IR = parse(scala.io.Source.fromFile(filepath).mkString)

  def main(args: Array[String]): Unit = {
    parseFile("grammar/example.dia")
  }
}
