/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scytale;
import org.jasypt.*;
import java.io.File.*;
import java.nio.file.*
/**
 *
 * @author Akuma
 */
public class EncryptionAlgorithm {
    public EncryptionAlgorithm(Byte[] byteK, DecryptedFile decFile, EncryptedFile encFile)
	{
		String stringK = new String(byteK);
		BasicBinaryEncryptor encryptEverything = new BasicBinaryEncryptor();
		encryptEverything.setPassword(stringK);
		Path pathA = Paths.get(decFile.getPath());
		byte[] opData = Files.readAllBytes(PathA);
		byte[] finData = encryptEverything.encrypt(opData);
		Files.write(Paths.get(encFile.getPath,finData);
	}
}
