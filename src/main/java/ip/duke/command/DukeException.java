package ip.duke.command;

public class DukeException extends Exception{
    protected String category;

    public DukeException(String category) {
        this.category = category;
    }
}
