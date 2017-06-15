package Runtime;

import Entity.ProgramTree;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class GlobalValue {
    private static Map<String, Program> programs = new HashMap<>();
    private static Map<String, Object> globalVal = new HashMap<>();

    public static void inputProgram(String name, ProgramTree program){
        programs.put(name, new Program(program));
    }

    public static Program getProgram(String name){
        return programs.get(name);
    }

    public static void setGlobalVal(String id, Object value){
        globalVal.put(id, value);
    }

    public static Object getGlobalVal(String id) {
        return globalVal.get(id);
    }

    public static boolean contain(String id){
        return globalVal.containsKey(id);
    }

    public static void clearGlobalVal(){
        globalVal.clear();
    }

    public static Map<String, Program> getPrograms(){
        return programs;
    }
}
