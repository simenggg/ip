import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDate date;
    //protected String by;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        //this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "/"
                + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
                //+ "(by: " + by + ")";
    }

}
