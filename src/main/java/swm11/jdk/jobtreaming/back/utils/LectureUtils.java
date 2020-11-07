package swm11.jdk.jobtreaming.back.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Log4j2
public final class LectureUtils {

    private static final String secretKey = "ThisIsA_SecretKeyForLivExpert_SW11_JDK!@";

    public static boolean enableJoin(Long lectureId, String password) throws NoSuchAlgorithmException, InvalidKeyException {
        char[] generated = generatePassword(String.valueOf(lectureId));
        return Arrays.equals(generated, password.toCharArray());
    }

    public static char[] generatePassword(String lectureId) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(lectureId.getBytes(StandardCharsets.UTF_8), secretKey);
        sha256_HMAC.init(secret_key);
        return Hex.encode(sha256_HMAC.doFinal(lectureId.getBytes(StandardCharsets.UTF_8)));
    }
}
