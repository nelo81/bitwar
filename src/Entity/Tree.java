package Entity;

import Expection.CompileException;
import Util.TokenList;

public interface Tree {
    Node root = new Node();

    default String getValue(){
        return root.getValue();
    }

    default void setValue(String value){
        root.setValue(value);
    }

    default String getCondition(){
        return root.getCondition();
    }

    default void setCondition(String condition){
        root.setCondition(condition);
    }

    default Node getRoot(){
        return root;
    }

    void grow(TokenList tokens) throws CompileException;

    int run();
}
