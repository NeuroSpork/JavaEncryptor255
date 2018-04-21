package scytale;

import java.io.File;

/**
 */
public class ScytaleFile extends File
{
    Location loc; //pathname
    boolean type; //encryption status: false = not encrypted, true = encrypted

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    
    public String getPathname()
    {
        return loc.getPathname();
    }
    
    public ScytaleFile(String pathname) 
    {
        super(pathname);
        loc = new Location(pathname);
        type = false;
    }
}
