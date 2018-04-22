package scytale;

/**
 */
public class guiInput {
    private String input;
    private String output;
    private String ed;
    private String password;
    private String overWrite;
    private String go;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getOverWrite() {
        return overWrite;
    }

    public void setOverWrite(String overWrite) {
        this.overWrite = overWrite;
    }

    public String getGo() {
        return go;
    }

    public void setGo(String go) {
        this.go = go;
    }
    
    public guiInput()
    {
        input = new String("");
        output = new String("");
        ed = new String("");
        password = new String("");
        overWrite = new String("");
        go = new String("");
    }
}
