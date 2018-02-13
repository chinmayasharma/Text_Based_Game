import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class Direction {

  @SerializedName("directionName")
  private String directionName;

  @SerializedName("room")
  private String room;

  /**
   * Returns the direction name of a room.
   *
   * @return name of direction
   */
  public String getDirectionName() {
    return directionName;
  }

  /**
   * Returns a room.
   *
   * @return room associated with the direction
   */
  public String getRoom() {
    return room;
  }

  /**
   * Constructor for class Direction.
   *
   * @param directionName initial direction
   * @param room initial room pointing to
   */
  public Direction(String directionName, String room) {
    this.directionName = directionName;
    this.room = room;
  }

  /**
   * Compares objects of type direction.
   *
   * @param o object to be compared
   * @return boolean value from comparison
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Direction)) {
      return false;
    }
    Direction direction = (Direction) o;
    return Objects.equals(getDirectionName(), direction.getDirectionName())
        && Objects.equals(getRoom(), direction.getRoom());
  }
}
