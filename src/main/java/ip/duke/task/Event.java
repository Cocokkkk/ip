package ip.duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
    public String toDataString() {
        return "E" + super.toDataString() + "|" + at;
    }
}