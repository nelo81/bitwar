package Expection;

public class CmdException extends Exception {
    private static final String tips = "\nyou can input 'help' to see how to use";

    public CmdException(String message){
        super("error: " + message + tips);
    }
}
