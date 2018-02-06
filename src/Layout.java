import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class Layout {
  @SerializedName("startingRoom")
  private String startingRoom;

  @SerializedName("endingRoom")
  private String endingRoom;

  @SerializedName("rooms")
  private ArrayList<Room> rooms = null;

  /** @return name of starting room */
  public String getStartingRoom() {
    return startingRoom;
  }

  /** @return name of endingRoom */
  public String getEndingRoom() {
    return endingRoom;
  }

  /** @return list of rooms */
  public ArrayList<Room> getRooms() {
    return rooms;
  }

  /**
   * @param o obejcts to e compared
   * @return boolean value from comparison
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Layout)) return false;
    Layout layout = (Layout) o;
    return Objects.equals(getStartingRoom(), layout.getStartingRoom())
        && Objects.equals(getEndingRoom(), layout.getEndingRoom())
        && Objects.equals(getRooms(), layout.getRooms());
  }
}
