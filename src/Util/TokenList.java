package Util;

import java.util.List;

public class TokenList {
    private static int tokenIndex;
    private List<String> tokens;

    public TokenList(){}

    public TokenList(List<String> tokens) {
        this.tokens = tokens;
    }

    public boolean overflow(){
        return tokenIndex >= tokens.size();
    }

    public void init(){
        tokenIndex = 0;
    }

    public String read(){
        return overflow()?null:tokens.get(tokenIndex++);
    }

    public String watch(){
        return overflow()?null:tokens.get(tokenIndex);
    }

    public static int getTokenIndex() {
        return tokenIndex;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
