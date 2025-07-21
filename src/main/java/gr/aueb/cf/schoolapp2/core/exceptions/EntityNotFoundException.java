package gr.aueb.cf.schoolapp2.core.exceptions;

public class EntityNotFoundException extends EntityGenericException {
    private static final String DEFAULT_CODE = "NotFound";

    public EntityNotFoundException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
