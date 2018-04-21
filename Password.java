package scytale;

/**
 */
public class Password {

    private String password; //password - intentionally no getter or setter to avoid changes to the key or passing the password itself as a variable
    private Key key;
    
    public String getKey()
    {
        return key.getKey();
    }
    
    public void deletePassword()
    {
        password = "";
        key.deleteKey();
    }
    
    public String createKey()
    {
        Key shortLife = new Key(password);
        return shortLife.getKey();
    }
    public String createKey(boolean t)
    {
        Key shortLife = new Key(password,t);
        return shortLife.getKey();
    }
        
    Password(String p, boolean t) 
    {
        password = p;
        key = new Key(password,t);
    }
    Password(String p)
    {
        password = p;
        key = new Key(password);
    }
}
