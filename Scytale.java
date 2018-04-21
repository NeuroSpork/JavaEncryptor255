package scytale;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Group name here/all member names?
 */
public class Scytale 
{    
    public static void main(String[] args) 
    {
        //DECLARATIONS
Scanner in = new Scanner(System.in);        //delete out after GUI added
        
        
        EncryptedFile eFile;                                    //encrypted file
        DecryptedFile dFile;                                    //non-encrypted file
        EncryptionAlgorithm eAlg;                           //encryption algorithm
        DecryptionAlgorithm dAlg;                           //decryption algorithm
        Password password = new Password("");   //password
        boolean ed = true;                                      //true = encryption, false = decryption
        boolean overWrite = false;                           //overWrite Flag
        boolean cancel = false;                               //cancel encryption Flag
        boolean quit = false;                                   //quit program Flag
        String inputFile;                                          //string of input file name
        String outputFile;                                       //string of output file name
        String passwordStr;                                   //string of password input
        
        /*if(!args[1])
            GUI call with all parameters
            variables used in main filled in with GUI values = String inputFile, String outputFile, boolean ed, boolean overWrite, 
        else
            variables used in main filled in with args values = String inputFile, String outputFile, boolean ed (I'm thinking maybe use a char instead for cmd line 'e' vs 'd' - I'm thinking auto-overwrite from command line
        */
        
        //DO SCYTALE
            //-there should be no inputs needed past this point after GUI and command line are implemented
        while (quit != true) //also add event go button boolean == true
        {
System.out.println("encrypt or decrypt? 1/0: "); //delete out after GUI added
ed = (in.nextInt() != 0); //delete out after GUI added

            //ACQUIRE FILES
            //ENCRYPTION FILES ACQUISITION
            if(ed)
            {
                eFile = new EncryptedFile("C:\\Users\\Akuma\\Desktop\\Encrypted.txt");//replace string with inputFile
                try {
System.out.println("Origin File exists?:" + !eFile.createNewFile());//delete out after GUI added
                } catch (IOException ex) {
                    Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                }

                dFile = new DecryptedFile("C:\\Users\\Akuma\\Desktop\\Decrypted.txt");//replace string with outputFile
                try {
System.out.println("Target File exists?:" + !dFile.createNewFile()); //delete out after GUI added
                    if(!dFile.createNewFile())
                    {
System.out.println("Would you like to overwrite '" + dFile.getPathname() + "'? 1/0"); //delete out after GUI added
                        overWrite = (in.nextInt() != 0); //delete out after GUI added
                        if(overWrite == false)
                        {
                            cancel = true;
System.out.println("cancelling file manipulation");//delete out after GUI added
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //DECRYPTION FILES ACQUISITION
            else 
            {
                dFile = new DecryptedFile("C:\\Users\\Akuma\\Desktop\\Decrypted.txt");//replace string with inputFile
                try {
System.out.println("Origin File exists?:" + !dFile.createNewFile()); //delete out after GUI added
                } catch (IOException ex) {
                    Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                }

                eFile = new EncryptedFile("C:\\Users\\Akuma\\Desktop\\Encrypted.txt");//replace string with outputFile
                try {
System.out.println("Target File exists?:" + !eFile.createNewFile()); //delete out after GUI added
                    if(!eFile.createNewFile())
                    {
System.out.println("Would you like to overwrite '" + eFile.getPathname() + "'? 1/0"); //delete out after GUI added
overWrite = (in.nextInt() != 0);//delete out after GUI added
                        if(overWrite == false)
                        {
                            cancel = true;
System.out.println("cancelling file manipulation"); //delete out after GUI added
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //OBTAIN KEY
            if(cancel == false)
            {
System.out.println("Enter Password: "); //delete out after GUI added
                password = new Password("password", true);//replace string with passwordStr //boolean parameter overloads password to use self made hash instead of 3rd party hash, remove boolean for library's version
                
                /*
                String s = "";
                Password [] passwordTest = new Password [10000];
                for(int i = 0; i < 10000; i++)
                {
                    s = s+" ";
                    passwordTest[i] = new Password(s);
                    System.out.println(i);
                    for(int j = i; j >= 0; j--)
                    {
                        if(i != j && passwordTest[i].getKey().equals(passwordTest[j].getKey()))
                            System.out.println("==Collision at " + i + " and " + j + "==");
                    }
                }*/


                //PERFORM CRYPTOGRAPHY
                //CRYPTO MAGIC
System.out.println("Performing Crypto Magic"); //delete out after GUI added
                if(ed)
                {
                    //ENCRYPTION
System.out.println("Encrypting"); //delete out after GUI added
System.out.println(eFile.getLoc().getPathname() + " from " + dFile.getLoc().getPathname() + " with " + password.getKey().length()*8 + " bit key hash"); //delete out after GUI added
                    //GUI.success()?
                }
                else
                {
                    //DECRYPTIONS
System.out.println("Decrypting"); //delete out after GUI added
System.out.println(eFile.getLoc().getPathname() + " into " + dFile.getLoc().getPathname() + " with " + password.getKey().length()*8 + " bit key hash"); //delete out after GUI added
                    //GUI.success()?
                }
            }
            else
            {
                //NOT CRYPTO MAGIC
System.out.println("Please verify the desired information has been input before trying again"); //delete out after GUI added
            }
            
            //DELETE PASSWORD AND KEY OBJECTS
            password.deletePassword();
            
            //reset program for another use
            cancel = false;
            overWrite = false;
            
            //REPEAT PROGRAM?
System.out.println("Continue? yes/no: 1/0"); //delete out after GUI added
quit = (in.nextInt() == 0); //delete out after GUI added
            
        }
    }
}
