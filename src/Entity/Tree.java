package Entity;

import Expection.CompileException;
import Util.TokenList;

import java.util.List;

public interface Tree {
    Node root = new Node();

    default Node getRoot(){
        return root;
    }

    default void addSon(Tree son){
        root.add(son);
    }

    default void setSon(int index, Tree tree){
        root.getSons().set(index, tree);
    }

    default Tree getSon(int index){
        return root.getSons().get(index);
    }

    default List<Tree> getSons(){
        return root.getSons();
    }

    void grow(TokenList tokens) throws CompileException;

    int run();
}
