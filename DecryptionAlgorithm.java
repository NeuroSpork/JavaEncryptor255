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
        StandardPBEByteEncryptor encryptEverything = new StandardPBEByteEncryptor();    //Create new encryptor
        SimplePBEConfig sConfig = new SimplePBEConfig();                                //Create encryptor config for complex settings
        encryptEverything.setConfig(sConfig);                                           //Assign config to encryptor
        encryptEverything.setPassword(stringK);                                         //Assign password to PBE system
        encryptEverything.setKeyObtentionIterations(85);                                //Not as strong as we would like, acceptable for proof of concept. Will be changed for V.2
        encryptEverything.setAlgorithm("PBEWithMD5AndTripleDES");                       //Set the algorithm. Will eventually be changed to accept a string as input.
        ZeroSaltGenerator stupid = new ZeroSaltGenerator();                             //Salt is bad for te heart. Acceptable for proof of concept.
        encryptEverything.setSaltGenerator(stupid);                                     //Assigning salt gen to encryptEverything
        //encryptEverything.setSaltGenerator();
        Path pathA = Paths.get(encFile.getPath());                                      //Get path of input
        byte[] opData = Files.readAllBytes(pathA);                                      //uses file lib to read in data into string
        //System.out.println(Arrays.toString(opData));  
        encryptEverything.initialize();                                                 //This Encryptionstation is FULLY OPERATIONAL!

        byte[] finData = encryptEverything.decrypt(opData);                             //Englishify encrypted data.
        //System.out.println(Arrays.toString(finData));
        Files.write(Paths.get(decFile.getPath()),finData);                              //Write Englishified data to file.
    }
}
