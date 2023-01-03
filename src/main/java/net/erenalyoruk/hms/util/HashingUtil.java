package net.erenalyoruk.hms.util;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/** Utility class for hashing passwords with SHA-256. */
public class HashingUtil {

    /**
     * Hash string to SHA-256.
     *
     * @param str Raw string to be hashed.
     * @return Encoded string
     */
    public static String sha256(String str) {
        return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
    }
}
