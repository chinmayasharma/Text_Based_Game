import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class Room {

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  @SerializedName("items")
  private ArrayList<String> items = null;

  @SerializedName("directions")
  private ArrayList<Direction> directions = null;

  /** @return name of room */
  public String getName() {
    return name;
  }

  /** @return description of room */
  public String getDescription() {
    return description;
  }

  /** @return list of items contained in room */
  public ArrayList<String> getItems() {
    return items;
  }

  /** @return list of possible directions to go in from the room */
  public ArrayList<Direction> getDirections() {
    return directions;
  }

  /**
   * @param o obejcts to e compared
   * @return boolean value from comparison
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Room)) return false;
    Room room = (Room) o;
    return Objects.equals(getName(), room.getName())
        && Objects.equals(getDescription(), room.getDescription())
        && Objects.equals(getItems(), room.getItems())
        && Objects.equals(getDirections(), room.getDirections());
  }
}
