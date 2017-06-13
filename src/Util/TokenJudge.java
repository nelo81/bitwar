package Util;

import java.util.Arrays;
import java.util.List;

public class TokenJudge {
    private static final List<String> limits
            = Arrays.asList("func","endf", "if","then","else",
            "endi","while","do","endw","is","return", "and",
            "or", "random", "current", "my", "opponent");

    private static final List<String> ops
            = Arrays.asList("+","-","*","/","=","<",">","(",")");

    public static boolean isNum(String token){
        return token.matches("[0-9]+");
    }

    public static boolean isId(String token){
        return token.matches("[a-zA-Z]+");
    }

    public static boolean isOp(char op){
        return ops.contains(String.valueOf(op));
    }

    public static boolean isLimit(String token){
        return limits.contains(token);
    }

    public static boolean isToken(String token) {
        return isId(token)||isLimit(token)||isNum(token);
    }
}
