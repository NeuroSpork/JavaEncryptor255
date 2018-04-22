package scytale;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.salt.ZeroSaltGenerator;
/**
 */
public class DecryptionAlgorithm extends CryptographicMethod
{
    public DecryptionAlgorithm(String stringK, DecryptedFile decFile, EncryptedFile encFile) throws IOException
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
        //encryptEverything.setSaltGenerator();
        Path pathA = Paths.get(encFile.getPath());
        byte[] opData = Files.readAllBytes(pathA);
        //System.out.println(Arrays.toString(opData));
        encryptEverything.initialize();

        byte[] finData = encryptEverything.decrypt(opData);
        //System.out.println(Arrays.toString(finData));
        Files.write(Paths.get(decFile.getPath()),finData);
    }
}
