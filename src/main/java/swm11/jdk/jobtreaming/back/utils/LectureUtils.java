package swm11.jdk.jobtreaming.back.utils;

import com.google.common.primitives.Longs;
import lombok.extern.log4j.Log4j2;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Log4j2
public final class LectureUtils {

    private static final String secretKey = "ThisIsA_SecretKeyForLivExpert_SW11_JDK!@";

    public static boolean enableJoin(String origin, Long lectureId) throws NoSuchAlgorithmException, InvalidKeyException {
        String generated = generatePassword(lectureId);
        return origin.equals(generated);
    }

    public static String generatePassword(Long lectureId) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(Longs.toByteArray(lectureId), secretKey);
        sha256_HMAC.init(secret_key);
        return new String(sha256_HMAC.doFinal(), StandardCharsets.UTF_8);
    }

}
