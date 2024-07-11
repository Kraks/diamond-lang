#! /usr/bin/env bash

ANTLR4=antlr-4.12.0-complete.jar
java -jar $ANTLR4 DiamondLexer.g4
java -jar $ANTLR4 -visitor DiamondParser.g4

DST=../src/main/java/diamond
echo "Copy Diamond parsers into $DST"

for file in "DiamondLexer.java" "DiamondParserBaseVisitor.java" "DiamondParserListener.java" "DiamondParserBaseListener.java" "DiamondParser.java" "DiamondParserVisitor.java"
do
  #sed -i "1ipackage diamond.parser;$line" $file
  sed -i '' '1s/^/package diamond.parser;\n/' $file
  cp $file $DST/$file
done
