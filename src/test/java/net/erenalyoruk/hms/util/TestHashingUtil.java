package net.erenalyoruk.hms.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestHashingUtil {

	@Test
	@DisplayName("Ensure SHA-256 hashing works")
	public void testSha256() {
		Assertions.assertEquals(HashingUtil.sha256("password123"), HashingUtil.sha256("password123"));
		Assertions.assertNotEquals(HashingUtil.sha256("password1"), HashingUtil.sha256("password12"));
		Assertions.assertNotEquals(HashingUtil.sha256(""), HashingUtil.sha256("1"));
	}

}
