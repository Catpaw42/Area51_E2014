package tests;

import static org.junit.Assert.assertEquals;
import helperClasses.Validator;

import org.junit.Test;

public class ValidatorTest {

	@Test
	public void testCPRBirthday(){
		String[] test = new String[]{"", "280193-1243", "1234561234"};
		for(String s : test){
			String value = Validator.parseCPRBirthday(s);
			System.out.println(value);
		}
	}

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
		testRes = Validator.validatePhoneNoAdv(test);
		assertEquals("test phone 1: " + test, true, testRes);

		test = "+12123456";
		testRes = Validator.validatePhoneNoAdv(test);
		assertEquals("test phone 2: " + test, true, testRes);

		test = "0012312345678";
		testRes = Validator.validatePhoneNoAdv(test);
		assertEquals("test phone 3: " + test, true, testRes);

		test = "+123123456789";
		testRes = Validator.validatePhoneNoAdv(test);
		assertEquals("test phone 4: " + test, true, testRes);

		test = "+123123456789012";
		testRes = Validator.validatePhoneNoAdv(test);
		assertEquals("test phone 5: " + test, false, testRes);

		test = "+12 3 12 3 4567 89";
		testRes = Validator.validatePhoneNoAdv(test);
		assertEquals("test phone 6: " + test, true, testRes);


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
