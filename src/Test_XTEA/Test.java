package Test_XTEA;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;

public class Test {
    
    public static void main(String[] args) {

        int[] keyIntegers = new KeyGenerator().core("Key Generator String");

        long [] key = new long[4];

        key[0] = keyIntegers[0];
        key[1] = keyIntegers[1];
        key[2] = keyIntegers[2];
        key[3] = keyIntegers[3];
        
//                    0123456701234567012345670123456701234567012345670123456701234567    ---- Multiple of 8 !!!
        String str = "Hello from the world of hell: XXTEA / XTEA / TEA and Java !!!";
        
        // if length of string is not a multiple of 8, pad with white spaces
        if(str.length() % 8 != 0)
        {
            System.out.println(str.length() + ": " + str);
            int nSpaces = 8 - str.length() % 8;
            System.out.println("Adding " + nSpaces + " white spaces");
            
            for(int i=0; i<nSpaces; i++)
            {
                str += " ";
            }
            System.out.println(str.length() + ": " + str);
        }
        StringBuilder result = new StringBuilder();
        

        byte[] b = str.getBytes(Charset.forName("UTF-8"));

        XTEAEncryptionEngine xtea = new XTEAEncryptionEngine();
        
        try {
            xtea.init(key);
        }
        catch(InvalidKeyException e) {
            System.out.println(e);
        }

        try {
            for(int ii=0; ii<str.length(); ii+=8)
            {
                byte [] encrypted; 
                encrypted = xtea.encrypt(b, ii);

                byte [] decrypted;
                decrypted = xtea.decrypt(encrypted);

                result.append(new String(decrypted, "UTF-8"));
            }
            System.out.printf("\n\t'%s'\n\n", result.toString().trim());
        }
        catch(IOException e) 
        {
            System.out.println(e);
        }
    }
}
