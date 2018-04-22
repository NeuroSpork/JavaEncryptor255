package scytale;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.salt.*;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
/**
 */
public class EncryptionAlgorithm extends CryptographicMethod
{
    public EncryptionAlgorithm(String stringK, DecryptedFile decFile, EncryptedFile encFile) throws IOException
    {
        //System.out.println("password: "+stringK);
        StandardPBEByteEncryptor encryptEverything = new StandardPBEByteEncryptor();
        SimplePBEConfig doubleStupid = new SimplePBEConfig();
        encryptEverything.setConfig(doubleStupid);
        encryptEverything.setPassword(stringK);
        encryptEverything.setKeyObtentionIterations(85);
        encryptEverything.setAlgorithm("PBEWithMD5AndTripleDES");
        ZeroSaltGenerator stupid = new ZeroSaltGenerator();
        encryptEverything.setSaltGenerator(stupid);
        Path pathA = Paths.get(decFile.getPath());
        byte[] opData = Files.readAllBytes(pathA);
        //System.out.println("Original " + Arrays.toString(opData));
        encryptEverything.initialize();
        byte[] finData = encryptEverything.encrypt(opData);
        //System.out.println("Encrypted " + Arrays.toString(finData));
        byte[] decTest = encryptEverything.decrypt(finData);
        StandardPBEByteEncryptor decryptTest = new StandardPBEByteEncryptor();
        decryptTest.setPassword(stringK);
        decryptTest.setAlgorithm("PBEWithMD5AndTripleDES");
        decryptTest.setSaltGenerator(stupid);

        //decTest = decryptTest.decrypt(finData);
        //System.out.println("Unenc'd " + Arrays.toString(decTest));
        Files.write(Paths.get(encFile.getPath()),finData);
    }
}
