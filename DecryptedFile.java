package scytale;

/**
 */
public class DecryptedFile extends ScytaleFile
{
    public DecryptedFile(String pathname) {
        super(pathname);
        this.setType(false);
    }
}
