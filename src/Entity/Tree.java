package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

public interface Tree {
    void grow(TokenList tokens) throws CompileException;

    Integer run(Map<String, Integer> localVal) throws RunningException;
}
