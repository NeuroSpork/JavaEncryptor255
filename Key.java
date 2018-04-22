package scytale;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import org.jasypt.digest.config.SimpleDigesterConfig;
import org.jasypt.salt.ZeroSaltGenerator;
/**
 */
public class Key
{
    //private String temp;
    private byte[] key; //3072 bit key (recommended key length by 2020)
    
    public String getKey() {
        String s = "";
        for(int i = 0; i < 384; i++)
        {
            s = s + (char) key[i];
        }
        return s;
    }
    
    public void deleteKey() {
        for(int i = 0; i < 384; i++)
        {
            key[i] = 0;
        }
        key = null;
    }
    
    //Constructor using more secure open source library instead of our own less secure hash algorithm
    public Key(String password)
    {
        key = new byte[384];
        SimpleDigesterConfig tripleStupid = new SimpleDigesterConfig();

        ZeroSaltGenerator stupid = new ZeroSaltGenerator();
        tripleStupid.setSaltGenerator(stupid);
        tripleStupid.setAlgorithm("SHA-256");
        tripleStupid.setIterations(10000);
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        //StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        passwordEncryptor.setConfig(tripleStupid);
        String [] s = new String[12];
        String keyS = "";
        s[0] = passwordEncryptor.encryptPassword(password); //256 bit hash = 64 char
        //create additional hashed strings to reach 3072 bits
        for(int i = 1; i < 12; i++)
        {
            s[i] = passwordEncryptor.encryptPassword(s[i-1]);
        }
        for(int i = 0; i < 12; i++)
        {
            keyS = keyS + s[i];
        }
        for(int i = 0; i < 384; i++)
        {
            key[i] = (byte) keyS.charAt(i);
        }
    }
    
    //Constructor using our own hash algorithm for the project        
    public Key(String password,boolean t)
    {
        key = new byte[384];
        int l = password.length();
        byte temp;
        int shift = 42;
        int l2 = l;
        
        for(int i = 0; i < 384; i++)
        {
            key[i] = '0';
        }
        
        //SALT
        if(l > 128)
        {
            l2 = l;
            l = 128;
        }
        for(int i = 0; i < l && i < 128; i++)
        {
            key[i * 3] = (byte) password.charAt(i);
        }
        
        //PADDING
        for(int i = 0; i < 256; i++)
        {
            if(i % 3 == 0)
                key[i] = (byte) (key[i] + l);
            else
                key[i] = (byte) l;//(key[i] + i);
            
        }
        
        //SHIFTING
        shift = shift + l;
        temp = 0;
        for(int j = 0; j < shift; j++)
        {
            for(int i = 0; i < 384; i++)
            {
                if(i+1 < 384)
                {
                    if(i == 0)
                        temp = key[i];
                    key[i] = key[i+1];
                }
                else
                {
                    key[i] = temp;
                }
            }
        }
        
        //TRANSFORMING
        for(int i = 0; i < 384; i++)
        {
            key[i] = (byte) (key[i]*key[i]);
            key[i] = (byte) (key[i] + (key[i]%(i+1)) + (key[i]%(l+1)));
            key[i] = (byte) (key[i]+(i*i));
            key[i] = (byte) (key[i]-(l*l));
        }
        while(l2 > 0)
        {
            for(int i = 0; i < 384; i++)
            {
                for(int j = l; j < l2 && j < 384; j++)
                {
                    key[i] = (byte) (key[i] + password.charAt(j));
                }
            }
            l2 = l2 - 384;
        }
    }
}
