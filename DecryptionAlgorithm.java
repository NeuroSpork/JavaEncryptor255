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
public class DecryptionAlgorithm {
    public DecryptionAlgorithm(Byte[] byteK, DecryptedFile decFile, EncryptedFile encFile)
	{
		String stringK = new String(byteK);
		BasicBinaryEncryptor encryptEverything = new BasicBinaryEncryptor();
		encryptEverything.setPassword(stringK);
		Path pathA = Paths.get(encFile.getPath());
		byte[] opData = Files.readAllBytes(PathA);
		byte[] finData = encryptEverything.decrypt(opData);
		Files.write(Paths.get(decFile.getPath,finData);
	}
}
