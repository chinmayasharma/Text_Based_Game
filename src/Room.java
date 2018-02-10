import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class Room {

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  @SerializedName("items")
  private ArrayList<Item> items = null;

  @SerializedName("directions")
  private ArrayList<Direction> directions = null;

  @SerializedName("monstersInRoom")
  private ArrayList<Monster> monstersInRoom = null;

  /** @return name of room */
  public String getName() {
    return name;
  }

  /** @return description of room */
  public String getDescription() {
    return description;
  }

  /** @return list of items contained in room */
  public ArrayList<Item> getItems() {
    return items;
  }

  /** @return list of possible directions to go in from the room */
  public ArrayList<Direction> getDirections() {
    return directions;
  }

  /**
   * @param directions
   * @param items
   * @param description
   * @param name
   * @param monstersInRoom
   */
  public Room(
      String name,
      String description,
      ArrayList<Item> items,
      ArrayList<Direction> directions,
      ArrayList<Monster> monstersInRoom) {
    this.name = name;
    this.description = description;
    this.items = items;
    this.directions = directions;
    this.monstersInRoom = monstersInRoom;
  }

  /** @param name */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param name
   * @return
   */
  public Room withName(String name) {
    this.name = name;
    return this;
  }

  public Room withDescription(String description) {
    this.description = description;
    return this;
  }

  public void setItems(ArrayList<Item> items) {
    this.items = items;
  }

  public Room withItems(ArrayList<Item> items) {
    this.items = items;
    return this;
  }

  public void setDirections(ArrayList<Direction> directions) {
    this.directions = directions;
  }

  public Room withDirections(ArrayList<Direction> directions) {
    this.directions = directions;
    return this;
  }

  public ArrayList<Monster> getMonstersInRoom() {
    return monstersInRoom;
  }

  public void setMonstersInRoom(ArrayList<Monster> monstersInRoom) {
    this.monstersInRoom = monstersInRoom;
  }

  public Room withMonstersInRoom(ArrayList<Monster> monstersInRoom) {
    this.monstersInRoom = monstersInRoom;
    return this;
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
