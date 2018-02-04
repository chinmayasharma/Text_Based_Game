import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class Layout {
  @SerializedName("startingRoom")
  private String startingRoom;

  @SerializedName("endingRoom")
  private String endingRoom;

  @SerializedName("rooms")
  private ArrayList<Room> rooms = null;

  public String getStartingRoom() {
    return startingRoom;
  }

  public void setStartingRoom(String startingRoom) {
    this.startingRoom = startingRoom;
  }

  public String getEndingRoom() {
    return endingRoom;
  }

  public void setEndingRoom(String endingRoom) {
    this.endingRoom = endingRoom;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(ArrayList<Room> rooms) {
    this.rooms = rooms;
  }

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
