package Runtime;

import Node.ProgramTree;
import Expection.RunningException;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class Program {
    private ProgramTree programTree;
    private Map<String, Integer> localVal;
    public static final int SINGLE_RUN = 0;
    public static final int FIRST_RUN = 1;
    public static final int SECOND_RUN = 2;
    public static final int MY_CURRENT = 1;
    public static final int OPPONENT_CURRENT = 2;


    public Program(ProgramTree programTree) {
        this.programTree = programTree;
        this.localVal = new HashMap<>();
    }

    public Integer run(int order) throws RunningException{
        clearLocalVal();
        setLocalVal("my", order);
        setLocalVal("current", MY_CURRENT);
        return programTree.run(this.localVal);
    }

    public Integer run() throws RunningException{
        return run(SINGLE_RUN);
    }

    public void printTree(){
        programTree.print(0);
    }

    public Map<String, Integer> getLocalVal() {
        return localVal;
    }

    public void setLocalVal(String id, Integer value) {
        this.localVal.put(id, value);
    }

    public void clearLocalVal(){
        this.localVal.clear();
    }
}
