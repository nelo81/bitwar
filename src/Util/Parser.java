package Util;

import Expection.CompileException;
import Entity.ProgramTree;

import java.util.HashMap;
import java.util.Map;

import static Util.Tokenizer.getTokens;

public class Parser {
    private static final Map<String, String> grammars = new HashMap<>();

    static {
        grammars.put("program", "func-stat");
        grammars.put("func-stat", "func id stmt-list endf");
        grammars.put("stmt-list", "{stmt}");
        grammars.put("stmt", "if-stmt | while-stmt | assign-stmt | return-stmt");
        grammars.put("if-stmt", "if exp then stmt-list [else stmt-list] endi");
        grammars.put("while-stmt", "while exp do stmt-list endw");
        grammars.put("assign-stmt", "id is exp");
        grammars.put("return-stmt", "return exp");
        grammars.put("exp", "logic-exp [logic logic-exp]");
        grammars.put("logic", "and | or");
        grammars.put("logic-exp", "simple-exp [comp simple-exp]");
        grammars.put("comp", "> | < | =");
        grammars.put("simple-exp", "term {addop term}");
        grammars.put("addop", "+ | -");
        grammars.put("term", "factor {mulop factor}");
        grammars.put("mulop", "* | /");
        grammars.put("factor", "(exp) | num | id | random | const");
        grammars.put("random", "random(exp)");
        grammars.put("const", "current | my(exp) | opponent(exp)");
    }

    private static ProgramTree parse(TokenList tokens) throws CompileException{
        ProgramTree program = new ProgramTree();
        program.grow(tokens);
        return program;
    }

    public static void main(String args[]) {
        try {
            String code = Filer.readFile("strategy/t1.txt");
            TokenList tokens = getTokens(code);
            ProgramTree program = parse(tokens);
        }
        catch (CompileException ce){
            System.err.println(ce.getMessage());
        }
    }
}
