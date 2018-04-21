package scytale;

/**
 */
public class Location 
{
    String pathname;

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
    
    public boolean compareLocation(Location destination)
    {
        boolean isEqual = false;
        isEqual = pathname.equals(destination.getPathname());
        return isEqual;
    }
    
    public Location(String pathname) 
    {
        this.pathname = pathname;
    }
}