/**
 * @author Kia
 */

// A small custom exception just for business rule / validation failures.
// The Service layer "rejects" an operation by throwing this, with a plain
// English message, so the JFrame only ever needs one catch block to show it.
public class BusinessRuleException extends Exception {

    public BusinessRuleException(String message) {
        super(message);
    }
}