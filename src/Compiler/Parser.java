package Compiler;

import Expection.CompileException;
import Node.ProgramTree;
import Runtime.Program;

import java.io.File;

import static Compiler.Tokenizer.getTokens;

@SuppressWarnings("all")
public class Parser {
    public static void printWord(int deep, String word) {
        for(int i=0;i<deep;i++) System.out.print("\t");
        System.out.println(word);
    }

    public static ProgramTree parse(String path) throws CompileException{
        File file = new File(path);
        String code = Filer.readFile(path);
        TokenList tokens = getTokens(code);
        ProgramTree program = parse(tokens);
        System.out.println(file.getName()+": compile success");
        return program;
    }

    public static ProgramTree parse(TokenList tokens) throws CompileException{
        TokenList.init();
        ProgramTree program = new ProgramTree();
        program.grow(tokens);
        return program;
    }

    public static void printTree(String name, Program program){
        System.out.println("----------- "+name+" ----------");
        program.printTree();
        System.out.println("---------- end ----------");
    }

    public static void main(String args[]) {
        try {
            String code = Filer.readFile("strategy/t5.txt");
            TokenList tokens = getTokens(code);
            ProgramTree program = parse(tokens);
            printTree("t5.txt",new Program(program));
        }
        catch (CompileException ce){
            System.err.println(ce.getMessage());
        }
    }
}
