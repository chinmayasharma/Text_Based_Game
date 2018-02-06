import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Direction {

  @SerializedName("directionName")
  private String directionName;

  @SerializedName("room")
  private String room;

  /** @return name of direction */
  public String getDirectionName() {
    return directionName;
  }

  /** @return room associated with the direction */
  public String getRoom() {
    return room;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Direction)) return false;
    Direction direction = (Direction) o;
    return Objects.equals(getDirectionName(), direction.getDirectionName())
        && Objects.equals(getRoom(), direction.getRoom());
  }
}
