package com.usa.nj.gov.uhip.admin.constants;
/**
 * This class avoiding hard coding in class with also read properties(key) from yml file via through properties class.
 * flow occures yml file to prperties class to constant class(avoding hardcoding)
 * @author Dell User
 *
 */
public class AppConstants {
	public static final String INVALID_CREDENTIALS = "invalidCredentials";
    public static final String USER_ACC_CREATION_VIEW ="createUserAcc";
    public static final String USER_ACC_MODEL ="userAccModel";
    public static final String GENDERS_LIST ="genders";
    public static final String ROLES_LIST ="roles";
    public static final String ACTIVE_SW ="y";
    public static final String EXCEPTION_VIEW="error";
    public static final String DUPLICATE="duplicate";
    public static final String UNIQUE="unique";
    public static final String ACC_CREATION_EMAIL_SUB="accCreationEmailSub";
    public static final String UHIP_ADMIN_PHNO="uhipAdminPhno";
    //getMailBody()method constants
   /* public static final String FIRST_NAME="${FIRST_NAME}";
    public static final String LAST_NAME="${LAST_NAME}";
    public static final String URL="${URL}";
    public static final String EMAIL="${EMAIL}";
    public static final String PASSWORD="${PASSWORD}";
    public static final String PHONE_NO="${PHONE_NO}";
    public static final String USER_ROLE="${ROLE}";
    public static final String GET_MAIL_BODY_METHOD_STARTED="getMailBody() method started";
    public static final String FILE_LOADED_SUCCESSFULLY_AND_LOADED_TO_BUFFERED_READER="file loaded successfully";
    public static final String ENTER_INTO_WHILE_LOOP="enter into while loop";
    public static final String EXITED_FROM_WHILE_LOOP="Exited from while loop";
    public static final String EXCEPTION_GENERATED_IN_GET_MAIL_BODY_METHOD="exception generated from getmailbody()";
    public static final String GET_MAIL_BODY_METHOD_ENDED="getmailbody() method ended";
    public static final String GET_MAIL_BODY_METHOD_COMPLETED_SUCCESSFULLY="getmailbody() method successfully completed";
    */
    
    public static final String LIST_MODEL="listModel";
    public static final String NO_DATA="noData";
    public static final String NO_DATA_IN_TABLE="noDataInTable";
}
