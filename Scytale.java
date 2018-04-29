package scytale;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Scytale
 * Developed by Jessa Ackerman, Harry Brennan, Tyler Wierzbicki, Steven Truong, Abishek Manojkumar
 */
public class Scytale 
{    
    //boolean go = false;
    public static void main(String[] args) 
    {
        //DECLARATIONS
Scanner in = new Scanner(System.in);        //delete out after GUI added
        
        
        EncryptedFile eFile;                                //encrypted file
        DecryptedFile dFile;                                //non-encrypted file
        EncryptionAlgorithm eAlg;                           //encryption algorithm
        DecryptionAlgorithm dAlg;                           //decryption algorithm
        Password password = new Password("");               //password
        boolean ed = true;                                  //true = encryption, false = decryption
        boolean overWrite = false;                          //overWrite Flag
        boolean cancel = false;                             //cancel encryption Flag
        boolean quit = false;                               //quit program Flag. Not used in this iteration.
        String inputFile = "";                              //string of input file name
        String outputFile = "";                             //string of output file name
        String passwordStr = "";                            //string of password input
        boolean go = false;                                 //Initializing go. This boolean triggers the encrypt/decrypt operation. It is why we have the LBPS later.
        
        //if(!args[1])
            guiInput GUIi = new guiInput(); //Create class to pass values between our programs
            guiClass GUI = new guiClass(GUIi);  //FIRE UP THE GUI!
            GUI.setVisible(true); //Now you see me!
        //DO SCYTALE
            //-there should be no inputs needed past this point after GUI and command line are implemented
        while(!quit)
        {
            inputFile = GUIi.getInput();
            outputFile = GUIi.getOutput();
            ed = GUIi.getEd().equals("true");
            passwordStr = GUIi.getPassword();
            //overWrite = GUIi.getOverWrite().equals("true"); 
            overWrite = true;
            go = GUIi.getGo().equals("true");
            //int i = 0;
            System.out.print(go + "\b\b\b\b\b"); //DO NOT DELETE, THIS IS A LOAD BEARING PRINT STATEMENT!!!!! 
            //if(go)
              //    i=i+1;
            while (!quit && GUIi.getGo().equals("true")) //also add event go button boolean == true
            {
                /**
                System.out.println("doing stuff");
                System.out.println(inputFile);
                System.out.println(outputFile);
                System.out.println(ed);
                System.out.println(passwordStr);
                System.out.println(overWrite);  //These debug messages can be deleted but have been left in for future use.
                System.out.println(go);
                //System.out.println("encrypt or decrypt? 1/0: "); //delete out after GUI added
                //ed = (in.nextInt() != 0); //delete out after GUI added
                *       **/
                //ACQUIRE FILES
                //ENCRYPTION FILES ACQUISITION
                if(ed)
                {
                    //eFile = new EncryptedFile("C:\\encryption\\Encrypted");//replace string with inputFile
                    eFile = new EncryptedFile(outputFile);//replace string with inputFile
                    
                    try {
                        eFile.createNewFile();
                        //System.out.println("Origin File exists?:" + !eFile.createNewFile());//delete out after GUI added
                    } catch (IOException ex) {
                        Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("encrypting target file failed"); //Uh Oh. Is your PC on fire?
                    }

                    //dFile = new DecryptedFile("C:\\encryption\\Decrypted");//replace string with outputFile
                    dFile = new DecryptedFile(inputFile);//replace string with outputFile
                   
                    try {
                        //System.out.println("Target File exists?:" + !dFile.createNewFile()); //delete out after GUI added
                        if(!dFile.createNewFile())
                        {
                            //System.out.println("Would you like to overwrite '" + dFile.getPathname() + "'? 1/0"); //delete out after GUI added
                            //overWrite = (in.nextInt() != 0); //delete out after GUI added
                            if(overWrite == false)
                            {
                                cancel = true;
                                //System.out.println("cancelling file manipulation");//delete out after GUI added
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("encrypting origin file failed"); //Uh oh something bad happened. Call the fire dept.
                    }
                }

                //DECRYPTION FILES ACQUISITION
                else 
                {
                    //dFile = new DecryptedFile("C:\\encryption\\Decrypted");//replace string with inputFile
                    dFile = new DecryptedFile(outputFile);//replace string with inputFile

                    try {
                        dFile.createNewFile();
                        //System.out.println("Origin File exists?:" + !dFile.createNewFile()); //delete out after GUI added
                    } catch (IOException ex) {
                        Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("decrypting target file failed");
                    }

                    //eFile = new EncryptedFile("C:\\encryption\\Encrypted");//replace string with outputFile
                    eFile = new EncryptedFile(inputFile);//replace string with outputFile

                    try {
                        //System.out.println("Target File exists?:" + !eFile.createNewFile()); //delete out after GUI added
                        if(!eFile.createNewFile())
                        {
                            //System.out.println("Would you like to overwrite '" + eFile.getPathname() + "'? 1/0"); //delete out after GUI added
                            //overWrite = (in.nextInt() != 0);//delete out after GUI added
                            if(overWrite == false)
                            {
                                cancel = true;
                                //System.out.println("cancelling file manipulation"); //delete out after GUI added
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("decrypting origin file failed");
                    }
                }

                //OBTAIN KEY
                if(cancel == false)
                {
                    //System.out.println("Enter Password: "); //delete out after GUI added
                    password = new Password(passwordStr);//replace string with passwordStr //boolean parameter overloads password to use self made hash instead of 3rd party hash, remove boolean for library's version

                    //PERFORM CRYPTOGRAPHY
                    //CRYPTO MAGIC
                    System.out.println("Performing Crypto Magic"); //delete out after GUI added
                    if(ed)
                    {
                        //ENCRYPTION
                        System.out.println("Encrypting"); //delete out after GUI added
                        try {
                            System.out.println(eFile.getLoc().getPathname() + " from " + dFile.getLoc().getPathname() + " with " + password.getKey().length()*8 + " bit key hash"); //delete out after GUI added
                            eAlg = new EncryptionAlgorithm(password.getKey(), dFile, eFile);
                        } catch (IOException ex) {
                            Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("encrypting failed");
                        }
                    }
                    else
                    {
                        //DECRYPTIONS
                        System.out.println("Decrypting"); //delete out after GUI added
                        try {
                            //System.out.println(eFile.getLoc().getPathname() + " into " + dFile.getLoc().getPathname() + " with " + password.getKey().length()*8 + " bit key hash"); //delete out after GUI added
                            dAlg = new DecryptionAlgorithm(password.getKey(), dFile, eFile);
                        } catch (IOException ex) {
                            Logger.getLogger(Scytale.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("decrypting failed");
                        }
                    }
                }
                else
                {
                    //NOT CRYPTO MAGIC
                    //System.out.println("Please verify the desired information has been input before trying again"); //delete out after GUI added
                }

                //DELETE PASSWORD AND KEY OBJECTS
                if(cancel == false)
                    password.deletePassword();

                //reset program for another use
                cancel = false;
                overWrite = false;
                go = false;
                GUIi.setGo("false");
                

                //REPEAT PROGRAM?
                //System.out.println("Continue? yes/no: 1/0"); //delete out after GUI added
                //quit = (in.nextInt() == 0); //delete out after GUI added

            }
        }
    }
}
