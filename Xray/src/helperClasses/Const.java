package helperClasses;

/**
 * 
 * @author Rúni, Morten, Christian
 *
 */
public final class Const {
	public static final long serialVersionUID = 1L;
	public static final Boolean DEBUG = true;

	// pages
	public static final String REKVISITION_PAGE = "WEB-INF/rekvisitionPage.jsp";
	public static final String LOGIN_PAGE = "WEB-INF/loginPage.jsp";
	public static final String NEW_REKVISITION_PAGE = "WEB-INF/nyRekvisitionPage.jsp";
	public static final String ADMIN_PAGE = "WEB-INF/adminPage.jsp";
	public static final String BOOKING_PAGE = "WEB-INF/bookingPage.jsp";
	public static final String VISITATION_PAGE = "WEB-INF/visitationPage.jsp";
	public static final String SHOW_REKVISITION_PAGE = "WEB-INF/visRekvisition.jsp";
	
	//Page titles
	public static final String PAGEHEADING = "PageHeading";
	public static final String VISITATION_TITLE = "Visitation";
	public static final String REKVISITION_TITLE = "Rekvisition";
	public static final String BOOKING_TITLE = "Booking";
	public static final String ADMINISTRER_TITLE = "Administrer brugere";
	
	
	// servlets names
//	public static final String MENU_SERVLET = "MenuServlet";
	public static final String ADMIN_SERVLET = "AdminServlet";
	public static final String MAIN_SERVLET = "MainServlet";
	public static final String REKVISITION_SERVLET = "RekvisitionServlet";
	public static final String NEW_REKVISITION_SERVLET = "NyRekvisitionServlet";
	public static final String BOOKING_SERVLET = "BookingServlet";
	public static final String VISITATION_SERVLET = "VisitationServlet";
	public static final String LOGIN_SERVLET = "LoginServlet";
	public static final String CT_KONTROLSKEMA_SERVLET = "CTKKontrolSkemaServlet";
	public static final String MR_KONTROLSKEMA_SERVLET = "MRKontrolSkemaServlet";
	public static final String PET_CT_KONTROLSKEMA_SERVLET = "PETCTKontrolSkemaServlet";
	public static final String UL_INV_KONTROLSKEMA_SERVLET = "ULInvKontrolSkemaServlet";
	


	// attributes that are objects
	public static final String MODALITY_LIST = "modalityList";
	public static final String STATUS_LIST = "statusList";
	public static final String DATABASE = "database";
	public static final String DATABASE_CONTROLLER = "databaseController";
	public static final String REKVISITION_SELECTED = "selectedRekvisition";


	
	// Attributes
	public static final String PASSWORD = "password";
	public static final String USERNAME = "username";
	public static final String ACTIVE_USER = "activeUser";
	public static final String REKVISITION_LIST = "rekvisitionList";
	
	// parameters
	public static final String PARAM_CPR = "cpr";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_MODALITY = "modality";
	public static final String PARAM_DEPARTMENT = "department";
	public static final String PARAM_FROM_DATE = "fromDate";
	public static final String PARAM_TO_DATE = "toDate";
	public static final String PARAM_STATUS = "status";

	public static final String LOGIN_FAILED = "loginFailed";
	
	// database columns name
	public static final String REKVIRENT_ID_COND = "rekvirent_id=?";

	
	public static final String ACTIVE_REKVISITION = "aktiv_rekvisition";
	public static final String REKVISITION_ID = "rekvisition_Id";
	
	public static final String UL_INV_KONTROL = "ULInvKontrol";
	public static final String MRKontrol = "MRKontrol";
	public static final String CTKKontrol = "CTKKontrol";
	public static final String PETCTKontrol = "PETCETKontrol";

	public static final String BOOKING_ACTION = "book";
	public static final String BOOKING_ACTION_ID = "bookingId";

	public static final String REVISIT_ACTION = "revisit";
	public static final String REVISIT_ACTION_ID = "revisitId";
	
	public static final String PARAM_ACTION = "action";




;
	


}
