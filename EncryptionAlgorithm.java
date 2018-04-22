package scytale;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import org.jasypt.util.binary.BasicBinaryEncryptor;
/**
 */
public class EncryptionAlgorithm extends CryptographicMethod
{
    public EncryptionAlgorithm(String stringK, DecryptedFile decFile, EncryptedFile encFile) throws IOException
    {
        BasicBinaryEncryptor encryptEverything = new BasicBinaryEncryptor();
        encryptEverything.setPassword(stringK);
        Path pathA = Paths.get(decFile.getPath());
        byte[] opData = Files.readAllBytes(pathA);
        System.out.println(Arrays.toString(opData));
        byte[] finData = encryptEverything.encrypt(opData);
        Files.write(Paths.get(encFile.getPath()),finData);
    }
}
