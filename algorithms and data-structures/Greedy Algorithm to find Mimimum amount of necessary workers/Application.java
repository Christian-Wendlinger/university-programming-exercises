import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class Application {
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Create an Application object.
     *
     * @param startTime
     * @param endTime
     */
    public Application(String startTime, String endTime) {
        this.startTime = LocalTime.of(Integer.parseInt(startTime.split(":")[0]), Integer.parseInt(startTime.split(":")[1]), 0);
        this.endTime = LocalTime.of(Integer.parseInt(endTime.split(":")[0]), Integer.parseInt(endTime.split(":")[1]), 0);
    }

    //getters
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    //to String
    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
