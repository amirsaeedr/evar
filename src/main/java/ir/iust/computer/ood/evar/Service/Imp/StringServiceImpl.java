package ir.iust.computer.ood.evar.Service.Imp;


import ir.iust.computer.ood.evar.Service.StringService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Random;

@Service
public class StringServiceImpl implements StringService {

  @Override
  public String encodeBase64(String input) {
    return new String(Base64.encodeBase64(input.getBytes()));
  }

  @Override
  public String decodeBase64(String input) {
    return new String(Base64.decodeBase64(input.getBytes()));
  }

  @Override
  public String set_String() {
    int n = 10;
    byte[] array = new byte[256];
    new Random().nextBytes(array);
    String randomString
            = new String(array, Charset.forName("UTF-8"));
    StringBuffer r = new StringBuffer();
    for (int k = 0; k < randomString.length(); k++) {
      char ch = randomString.charAt(k);
      if (((ch >= 'a' && ch <= 'z')
              || (ch >= 'A' && ch <= 'Z')
              || (ch >= '0' && ch <= '9'))
              && (n > 0)) {
        r.append(ch);
        n--;
      }
    }
    return r.toString();
  }
}
