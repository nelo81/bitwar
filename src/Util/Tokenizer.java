package Util;

import Expection.CompileException;

import java.util.ArrayList;
import java.util.List;

import static Util.TokenJudge.*;

public class Tokenizer {
    public static TokenList getTokens(String code) throws CompileException{
        List<String> tokens = new ArrayList<>();
        String token = "";
        for(int i=0;i<code.length();i++){
            if((code.charAt(i) == ' '|| isOp(code.charAt(i)))){
                if(isToken(token)) {
                    tokens.add(token);
                    token = "";
                }
                else if(!token.matches("[\\s]?")){
                    throw new CompileException(token + " isn't a token");
                }
            }
            else token += code.charAt(i);
            if(isOp(code.charAt(i))) tokens.add(String.valueOf(code.charAt(i)));
        }
        return new TokenList(tokens);
    }

    public static void showTokens(TokenList list){
        List<String> tokens = list.getTokens();
        for(String token:tokens){
            System.out.println(token);
        }
    }

    public static void main(String args[]) {
        try {
            String code = Filer.readFile("strategy/t1.txt");
            TokenList tokens = getTokens(code);
            showTokens(tokens);
        }
        catch (CompileException ce){
            System.err.println(ce.getMessage());
        }
    }
}
