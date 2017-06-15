package Runtime;

import Entity.ProgramTree;
import Expection.RunningException;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class Program {
    private ProgramTree programTree;
    private Map<String, Integer> localVal;

    public Program(ProgramTree programTree) {
        this.programTree = programTree;
        this.localVal = new HashMap<>();
    }

    public Integer run() throws RunningException{
        return programTree.run(this.localVal);
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
