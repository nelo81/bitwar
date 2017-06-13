package Expection;

public class CompileException extends Exception{

    public CompileException(String message) {
        super("compile error: " + message);
    }
}
