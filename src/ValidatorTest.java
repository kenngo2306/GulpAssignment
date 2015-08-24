import static org.junit.Assert.*;

import org.junit.Test;


public class ValidatorTest
{

	@Test
	public void testEmail()
	{
		assertFalse(Validator.validateEmail("dpasdasd"));
		assertTrue(Validator.validateEmail("phucuongngo@uh.edu"));
		assertFalse(Validator.validateEmail("cpngo@uhedu"));
	}

}
