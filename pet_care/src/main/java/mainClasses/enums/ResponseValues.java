package mainClasses.enums;

public class ResponseValues {
    public static final int EXCEPTION = 500;

    public static final int USERNAME_EXISTS = 400;
    public static final int EMAIL_EXISTS = 401;
    public static final int PHONE_EXISTS = 402;
    public static final int WRONG_PASSWORD_OR_USERNAME = 403;  //unauthorized
    public static final int PET_NOT_FOUND = 401;

    public static final int PET_OWNER_ADDED = 200;
    public static final int PET_KEEPER_ADDED = 201;

    public static final int PET_OWNER_LOGGED_IN = 200;
    public static final int PET_KEEPER_LOGGED_IN = 201;

    public static final int PET_RETRIEVED = 200;

    public static final int PET_ADDED = 200;

    public static final int PET_ID_EXISTS = 400;
    public static final int PET_DELETED = 200;
}
