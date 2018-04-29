package scytale;

/**
 */
public class CryptographicMethod 
{
    private boolean EDF = false;
    private String CryptoType = "";
    public void cryptomethod(boolean EDFlag, String CryptoName)
    {
    //PLACEHOLDER, this will be used in V.2
    EDF = EDFlag;
    CryptoType = CryptoName;
    }

    public boolean isEDF() {
        return EDF;
    }

    public void setEDF(boolean EDF) {
        this.EDF = EDF;
    }

    public String getCryptoType() {
        return CryptoType;
    }

    public void setCryptoType(String CryptoType) {
        this.CryptoType = CryptoType;
    }
    
}
