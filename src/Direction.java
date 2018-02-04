
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Direction {

  @SerializedName("directionName")
  private String directionName;
  @SerializedName("room")
  private String room;

  public String getDirectionName() {
    return directionName;
  }

  public void setDirectionName(String directionName) {
    this.directionName = directionName;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Direction)) return false;
    Direction direction = (Direction) o;
    return Objects.equals(getDirectionName(), direction.getDirectionName()) &&
            Objects.equals(getRoom(), direction.getRoom());
  }

}