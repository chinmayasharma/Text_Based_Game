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

  public ArrayList<Direction> possibleDirections;

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
   * Handles adding and removing items.
   *
   * @param itemName name of item
   * @return boolean value true ir flase depending on feasibility
   */
  public boolean addItem(String itemName) {

    Item item;
    try {
      item = findItem(itemName);

      // adds item to collected items
      getItems().add(item);

      return true;

    } catch (NullPointerException e) {

      return false;
    }
  }

  /**
   * Handles adding and removing items.
   *
   * @param itemName name of item
   * @return boolean value true ir flase depending on feasibility
   */
  public boolean removeItem(String itemName) {

    Item item;
    try {
      item = findItem(itemName);

      // removes item from possible items
      getItems().remove(item);

      return true;

    } catch (NullPointerException e) {

      return false;
    }
  }

  /**
   * finds item based on name.
   *
   * @param inputString name of user specified item
   * @return item to e added / removed from list of items
   */
  public Item findItem(String inputString) {

    // checks if inputString is empty or null
    if (inputString == null || inputString.isEmpty()) {
      return null;
    }

    // checks if itemList is empty or null
    if (getItems() == null || getItems().isEmpty()) {
      return null;
    }
    // loops through all items in list of items
    for (Item item : getItems()) {

      // checks if name of item exists in list of items
      if (inputString.equalsIgnoreCase(item.getName())) {

        return item;
      }
    }
    return null;
  }

  /**
   * Formats array list of items into String.
   *
   * @param itemList ArrayList of items
   * @return return formatted String of possible items
   */
  public String itemString(ArrayList<Item> itemList) {

    // creates a new instance of type String Builder
    StringBuilder itemString = new StringBuilder();

    // checks if list of items is null
    if (itemList == null || itemList.isEmpty()) {
      itemString.append(AdventureConstants.EMPTY_ITEM_LIST);

    } else if (itemList.size() == 1) {
      itemString.append(itemList.get(itemList.size() - 1).getName());
      itemString.append(".");

    } else {

      // loops through all items and appends to String Builder
      for (int i = 0; i < itemList.size() - 1; i++) {
        itemString.append(itemList.get(i).getName());
        itemString.append(", ");
      }

      // appends formatted string onto String Builder
      itemString.append("and ");
      itemString.append(itemList.get(itemList.size() - 1).getName());
    }
    return itemString.toString();
  }

  /**
   * Checks if room has monsters.
   *
   * @return if the room has monsters
   */
  public boolean hasMonster() {

    return (getMonstersInRoom().size() > 0);
  }

  /**
   * Finds monster from name.
   *
   * @param monsterName name of monster to bbe searched
   * @return monster object searched from name
   */
  public Monster findMonster(String monsterName) {

    // checks if inputString is empty or null
    if (monsterName == null || monsterName.isEmpty()) {
      return null;
    }

    ArrayList<Monster> monsterList = getMonstersInRoom();

    // checks if itemList is empty or null
    if (monsterList == null || monsterList.isEmpty()) {
      return null;
    }

    for (Monster monster : monsterList) {
      if (monsterName.equalsIgnoreCase(monster.getName())) {
        return monster;
      }
    }
    return null;
  }

  /**
   * Formats array list of monsters into a string.
   *
   * @return return formatted String of possible monsters
   */
  public String monsterString() {

    // creates a new instance of type String Builder
    StringBuilder monsterString = new StringBuilder();

    // checks if list of items is null
    if (getMonstersInRoom() == null || getMonstersInRoom().isEmpty()) {
      monsterString.append(AdventureConstants.EMPTY_MONSTER_LIST);

    } else if (getMonstersInRoom().size() < 2) {
      monsterString.append(getMonstersInRoom().get(getMonstersInRoom().size() - 1).getName());
      monsterString.append(".");

    } else {

      // loops through all items and appends to String Builder
      for (int i = 0; i < getMonstersInRoom().size() - 1; i++) {
        monsterString.append(getMonstersInRoom().get(i).getName());
        monsterString.append(", ");
      }

      // appends formatted string onto String Builder
      monsterString.append("and ");
      monsterString.append(getMonstersInRoom().get(getMonstersInRoom().size() - 1).getName());
    }
    return monsterString.toString();
  }

  /**
   * Formats array list of directions to String.
   *
   * @return formatted String of possible directions
   */
  public String directionString() {

    // creates a new instance of type String Builder
    StringBuilder directionString = new StringBuilder();

    directionString.append("From here, you can go: ");

    // checks if list of directions is null
    if (getDirections() == null) {
      directionString.append(AdventureConstants.EMPTY_DIRECTION_LIST);

      // checks if list of directions has a single direction
    } else if (getDirections().size() == 1) {

      directionString.append(getDirections().get(getDirections().size() - 1).getDirectionName());

      // if list of directions has multiple items
    } else {

      // loops through all directions and appends to String Builder
      for (int i = 0; i < getDirections().size() - 1; i++) {
        directionString.append(getDirections().get(i).getDirectionName());
        directionString.append(", ");
      }

      // appends formatted string onto String Builder
      directionString.append("or ");
      directionString.append(getDirections().get(getDirections().size() - 1).getDirectionName());
    }
    return directionString.toString();
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
