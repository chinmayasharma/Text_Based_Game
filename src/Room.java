import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Room {

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  @SerializedName("items")
  private ArrayList<Item> items;

  @SerializedName("directions")
  private ArrayList<Direction> directions;

  @SerializedName("monstersInRoom")
  private ArrayList<Monster> monstersInRoom;

  /**
   * Getter for room name.
   *
   * @return name of room
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for room description.
   *
   * @return description of room
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for items in room.
   *
   * @return list of items contained in room
   */
  public ArrayList<Item> getItems() {
    return items;
  }

  /**
   * Getter for possible directions from room.
   *
   * @return list of possible directions to go in from the room
   */
  public ArrayList<Direction> getDirections() {
    return directions;
  }

  /**
   * Constructor for class Room.
   *
   * @param directions directions in room
   * @param items items in room
   * @param description description of room
   * @param name name of room
   * @param monstersInRoom monsters in room
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

  /**
   * Setter for room directions.
   *
   * @param directions take in modified directions from room as parameters.
   */
  public void setDirections(ArrayList<Direction> directions) {
    this.directions = directions;
  }

  /**
   * Getter for monsters in room.
   *
   * @return
   */
  public ArrayList<Monster> getMonstersInRoom() {
    return monstersInRoom;
  }

  /** Displays the information of the room. */
  public void roomInfo() {

    // prints room description
    System.out.println(getDescription());

    // handles possible null pointer exception
    try {

      System.out.println(AdventureConstants.ROOM_MESSAGE + itemString());
    } catch (NullPointerException e) {

      System.out.println(AdventureConstants.ROOM_MESSAGE + AdventureConstants.EMPTY_ITEM_LIST);
    }

    // handles possible null pointer exception
    try {

      System.out.println(AdventureConstants.ROOM_MESSAGE + monsterString());
    } catch (NullPointerException e) {

      System.out.println(AdventureConstants.ROOM_MESSAGE + AdventureConstants.EMPTY_MONSTER_LIST);
    }

    // displays possible directions
    System.out.println(directionString());
  }

  /**
   * finds item based on name.
   *
   * @param inputString name of user specified item
   * @return item to e added / removed from list of items
   */
  public Item findItem(String inputString) {

    ArrayList<Item> itemList = getItems();

    // checks if inputString is empty or null
    if (inputString == null || inputString.isEmpty()) {
      return null;
    }

    // checks if itemList is empty or null
    if (itemList == null || itemList.isEmpty()) {
      return null;
    }

    // loops through all items in list of items
    for (Item item : itemList) {

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
   * @return return formatted String of possible items
   */
  public String itemString() {

    // creates a new instance of type String Builder
    StringBuilder itemString = new StringBuilder();
    ArrayList<Item> itemList = getItems();
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
      itemString.append(".");
    }

    return itemString.toString();
  }

  /**
   * Checks if room has monsters.
   *
   * @return if the room has monsters
   */
  public boolean hasMonster() {

    return (!(getMonstersInRoom() == null || getMonstersInRoom().isEmpty()));
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
  String monsterString() {

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
   * Changes room absed on direction.
   *
   * @param inputDirection name of user specified direction
   */
  public boolean checkDirection(String inputDirection) {
    String roomWanted = "";

    if (inputDirection == null || inputDirection.isEmpty()) {
      return false;
    }

    for (Direction direction : getDirections()) {
      // find the room that is under the specific direction
      if (direction.getDirectionName().toLowerCase().equals(inputDirection)) {
        roomWanted = direction.getRoom();
      }
    }
    return !roomWanted.isEmpty();
  }

  /**
   * Formats array list of directions to String.
   *
   * @return formatted String of possible directions
   */
  public String directionString() {

    // creates a new instance of type String Builder
    StringBuilder directionString = new StringBuilder();
    ArrayList<Direction> directionList = getDirections();

    directionString.append("From here, you can go: ");

    // checks if list of directions is null
    if (directionList == null) {
      directionString.append(AdventureConstants.EMPTY_DIRECTION_LIST);

      // checks if list of directions has a single direction
    } else if (directionList.size() == 1) {

      directionString.append(directionList.get(directionList.size() - 1).getDirectionName());

      // if list of directions has multiple items
    } else {

      // loops through all directions and appends to String Builder
      for (int i = 0; i < directionList.size() - 1; i++) {
        directionString.append(directionList.get(i).getDirectionName());
        directionString.append(", ");
      }

      // appends formatted string onto String Builder
      directionString.append("or ");
      directionString.append(directionList.get(directionList.size() - 1).getDirectionName());
    }
    return directionString.toString();
  }

  /**
   * Checks for item in room.
   *
   * @param itemName nam eof item to be checked for
   * @return if item exists
   */
  public boolean checkItem(String itemName) {
    ArrayList<Item> itemList = getItems();

    if (itemList == null || itemList.isEmpty()) {
      return false;
    }

    for (Item item : itemList) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks for monster in room.
   *
   * @param monsterName name of item to be checked for
   * @return if item exists
   */
  public boolean checkMonster(String monsterName) {
    ArrayList<Monster> monsterList = getMonstersInRoom();

    if (monsterList == null || monsterList.isEmpty()) {
      return false;
    }

    for (Monster monster : monsterList) {
      if (monster.getName().equalsIgnoreCase(monsterName)) {
        return true;
      }
    }
    return false;
  }
}
