import java.util.ArrayList;
import java.util.List;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<String> getItems() {
    return items;
  }

  public void setItems(ArrayList<String> items) {
    this.items = items;
  }

  public ArrayList<Direction> getDirections() {
    return directions;
  }

  public void setDirections(ArrayList<Direction> directions) {
    this.directions = directions;
  }

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
