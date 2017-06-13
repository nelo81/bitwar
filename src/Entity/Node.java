package Entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Tree> sons;
    private String value;

    public Node(){
        sons = new ArrayList<>();
    }

    public Node(String value) {
        this.value = value;
        sons = new ArrayList<>();
    }

    public void add(Tree son){
        sons.add(son);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Tree> getSons() {
        return sons;
    }
}
