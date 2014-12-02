package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;
import database.dto.Rettigheder;
/**
 * 
 * @author Rúni
 *
 */
public class DtoTest {
	
//	public static void main(String[] args){
//		DtoTest t = new DtoTest();
//		t.testRekvisitionExtended();
//	}
	Connection conn;
	RekvisitionDaoImpl rekDao;
	
	@Before
	public void setUp() throws Exception{
		SQLConnector sql = new SQLConnector();
		this.conn = sql.getConnection();
		this.rekDao = new RekvisitionDaoImpl(conn);
	}
	
	/**
	 * tests if findDynamic function is doing as expected.
	 * tests for offset and count.
	 */
	@Test
	public void testRekvisitionExtended(){
		RekvisitionExtended[] r = rekDao.findDynamic(null, 0, 3, new Object[]{});
		assertEquals(3, r.length);
		RekvisitionExtended[] first = rekDao.findDynamic(null, 0, -1, new Object[]{});
		RekvisitionExtended[] second = rekDao.findDynamic(null, 5, -1, new Object[]{});
		assertEquals(5, first.length-second.length);
		
		
	}
	/**
	 * tests if the most important fields that should not be null are null
	 * is success, but can fail if patient id is 0 which should not be possible
	 */
	@Test
	public void testNotNullFieldsInRekvisition(){
		RekvisitionExtended[] rA = rekDao.findDynamic(null, 0, -1, new Object[]{});
		for (RekvisitionExtended r : rA) {
			
		
		assertNotEquals("ingen patient fundet med id: " + r.getPatientId(), null, r.getPatient());
		assertNotEquals("ingen modalitet fundet med id: " + (r.getUndersoegelsesType() == null ? -1 : r.getUndersoegelsesType().getModalitetId()), null, r.getModalitet());
		assertNotEquals("ingen rekvirent fundet med id: " + r.getRekvirentId(), null, r.getRekvirent());
		assertNotEquals("ingen undersoegelsestype fundet med id: " + r.getUndersoegelsesTypeId(), null, r.getUndersoegelsesType());
		
	
		}
	}
	/**
	 * tests if Status is approved that there also is an assessor on the rekvisition which should be required
	 * fails now because rekvisition is approved without a user with approval rights
	 */
	@Test
	public void testStatusMatchRekvisition(){
		RekvisitionExtended[] rekvList = rekDao.findDynamic(null, 0, -1, new Object[]{});
		boolean assessor = false;
		for (RekvisitionExtended r : rekvList) {
			assessor = false;
			if(r.getStatus() == Status.APPROVED){
				assertNotEquals("Visitator bør ikke være null da status er approved. \n Forsøg på at hente visitator med id: " + r.getVisitatorId() + " er fejlet eller eksisterer ikke", null, r.getVisitator());
				for(Rettigheder ret : r.getVisitator().getRettigheder()){
					if(ret.getRettighed() == Rettigheder.Rettighed.ASSESSOR){
					assessor = true;
					break;
					}
				}
				assertEquals("Rekvisition er godkendt af bruger med id: " + r.getVisitatorId() + " som ikke har visitator rettighed", true, assessor);	
			}
		}
	
	}
//TODO: This test is outdated.	
	
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

}
