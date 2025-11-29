package study.spring.community.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordEncoder {

  private static int saltSize = 8;
  private static int hashSize = 16;

  private static String getSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[saltSize];

    random.nextBytes(salt);
    StringBuilder sb = new StringBuilder();
    for (byte b : salt) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  private static String getHash(String password, String salt) {
    String saltedPasswd = salt + password;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(saltedPasswd.getBytes());
      byte[] digest = md.digest();

      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b));
      }
      sb.delete(hashSize * 2, sb.length());
      return sb.toString();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String hashpw(String password) {
    String salt = getSalt();
    log.info("Generated salt: {}", salt);
    String hash = getHash(password, salt);
    log.info("Generated hash: {}", hash);
    return "$" + salt + "$" + hash;
  }
}
