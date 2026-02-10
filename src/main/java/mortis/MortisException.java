package mortis;

/** 
 * Custom exception class for Mortis application. 
 * */

public class MortisException extends Exception {
    public MortisException(String message) {
        super(message);
    }
}