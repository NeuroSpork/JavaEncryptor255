package scytale;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import org.jasypt.util.binary.BasicBinaryEncryptor;
/**
 */
public class DecryptionAlgorithm extends CryptographicMethod
{
    public DecryptionAlgorithm(String stringK, DecryptedFile decFile, EncryptedFile encFile) throws IOException
    {
        char end = 000;
        BasicBinaryEncryptor encryptEverything = new BasicBinaryEncryptor();
        encryptEverything.setPassword(stringK);
        //encryptEverything.setAlgorithm("PBEWITHMD5ANDDES");
        Path pathA = Paths.get(encFile.getPath());
        byte[] opData = Files.readAllBytes(pathA);
        System.out.println(Arrays.toString(opData));
        byte[] finData = encryptEverything.decrypt(opData);
        System.out.println(Arrays.toString(finData));
        Files.write(Paths.get(decFile.getPath()),finData);
    }
}
