package gr.aueb.cf.schoolapp2.core.exceptions;

public class EntityGenericException extends Exception {
    private final String code;

    public EntityGenericException(String code, String message) {
        super(message);
        this.code = code;
    }
}
