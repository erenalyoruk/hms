package net.erenalyoruk.hms.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashingUtil {

	public static String sha256(String str) {
		return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
	}

}
