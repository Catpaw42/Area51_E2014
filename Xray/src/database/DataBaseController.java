//package database;
//
//import java.sql.Connection;
//
//import javax.sql.DataSource;
//
//import database.DataSourceConnector;
//import database.dao.mysql.BrugerDaoImpl;
//import database.dto.Bruger;
//import database.interfaces.IDataBaseController;
//
//public class DataBaseController implements IDataBaseController {
//
//
//	@Override
//	public Bruger getUserFromUsername(String username)
//			throws DatabaseException, UserNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean validateUser(Bruger loginUser) throws DatabaseException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
////TODO: This test is outdated.	
//	
//	CConnector cconnector;
//
//	@Before
//	public void setUp() throws Exception {
//		this.cconnector = new CConnector();
//		//remember to set IP !!!
//		cconnector.setIP("192.168.5.10");
//		
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		if(cconnector != null) 
//		{
//			cconnector.disconnect();
//		}
//	}
//
//	@Test
//	public void testConnection() {
//		
//		try 
//		{
//
//			cconnector.connect();
//			Measurement[] measure;
//			Measurement[] measure2;
//			
//			//test static output (always 1.0 v)
//			measure = cconnector.readMeasurements(MeasurementType.TESTRANDOM, 1, 1);
//			
//			System.out.println("Received from server:" + measure[0].getMeasureValue());
//			
////			try 
////			{
////			    Thread.sleep(1000);
////			} catch(InterruptedException ex) {
////			    Thread.currentThread().interrupt();
////			}
//			
//			
//			
//			//test random output (between 0 and 2 v)
//			//measure2 = cconnector.readMeasurements(MeasurementType.TESTRANDOM, 1, 1);
//			
//			//System.out.println("Received from server:" + measure2[0].getMeasureValue());			
//			
//			//System.out.println(cconnector.writeToSocket("98;1;1;<EOF>",1,1));
//	
//		} catch (ConnectionException e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//}
