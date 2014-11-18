package tests;

import static org.junit.Assert.*;
import helperClasses.Validator;
import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Test;

public class ValidatorTest {
	
	@Test
	public void testPhoneNoValidator(){
		
		String test = null;
		boolean testRes = Validator.validatePhoneNo(test);
		assertEquals(false, testRes);
		
		test = "+123123456";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 1: " + test, true, testRes);
		
		test = "+12123456";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 2: " + test, true, testRes);
		
		test = "0012312345678";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 3: " + test, true, testRes);
		
		test = "+123123456789";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 4: " + test, true, testRes);
		
		test = "+123123456789012";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 5: " + test, true, testRes);
	}
	
	@Test
	public void testPhoneNoValidatorAdv(){
		String test = null;
		boolean testRes = Validator.validatePhoneNo(test);
		assertEquals(false, testRes);
		
		test = "+123123456";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 1: " + test, true, testRes);
		
		test = "+12123456";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 2: " + test, true, testRes);
		
		test = "0012312345678";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 3: " + test, true, testRes);
		
		test = "+123123456789";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 4: " + test, true, testRes);
		
		test = "+123123456789012";
		testRes = Validator.validatePhoneNo(test);
		assertEquals("test phone 5: " + test, false, testRes);
	}
	
	@Test
	public void testCprValidator(){
		String test = null;
		boolean res = Validator.validateCpr(test);
		assertEquals("test cpr 1: " + test, false, res);
		
		test = "123456-1234";
		res = Validator.validateCpr(test);
		assertEquals("test cpr 2: " + test, true, res);
		
		test = "123456-asd1";
		res = Validator.validateCpr(test);
		assertEquals("test cpr 3: " + test, true, res);
		
		test = "1234-123456";
		res = Validator.validateCpr(test);
		assertEquals("test cpr 4: " + test, false, res);
		
		
		
		
		
		
		
		
		
		
	}

}
