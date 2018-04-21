package scytale;

/**
 */
public class EncryptedFile extends ScytaleFile
{
    public EncryptedFile(String pathname) {
        super(pathname);
        this.setType(true);
    }
}
