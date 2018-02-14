import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Player {

  @SerializedName("name")
  private String name;

  @SerializedName("items")
  private ArrayList<Item> items;

  @SerializedName("attack")
  private Double attack;

  @SerializedName("defense")
  private Double defense;

  @SerializedName("health")
  private Double health;

  @SerializedName("level")
  private Integer level;

  private double experience;

  private static double expBeforeThat = 25;
  private static double expBefore = 50;

  private static double maxHealth = 50;

  /**
   * Constructor for class Player.
   *
   * @param defense defense stat of player
   * @param level level
   * @param items items carried by player
   * @param name name of player
   * @param attack attack stat of player
   * @param health health stat of player
   */
  public Player(
      String name, ArrayList<Item> items, double attack, double defense, double health, int level) {
    this.name = name;
    this.items = items;
    this.attack = attack;
    this.defense = defense;
    this.health = health;
    this.level = level;
    this.experience = 0.0;
  }

  /**
   * Getter for player name.
   *
   * @return player name
   */
  String getName() {
    return name;
  }

  /**
   * Getter for player items.
   *
   * @return items held by player
   */
  ArrayList<Item> getItems() {
    return items;
  }

  /**
   * Getter for player attack stat.
   *
   * @return player attack value
   */
  double getAttack() {
    return attack;
  }

  /**
   * Setter for player attack stat.
   *
   * @param attack new player attack value
   */
  private void setAttack(double attack) {
    this.attack = attack;
  }

  /**
   * Getter for player defense stat.
   *
   * @return player defense value
   */
  double getDefense() {
    return defense;
  }

  /**
   * Setter for player defense stat.
   *
   * @param defense defense value
   */
  void setDefense(double defense) {
    this.defense = defense;
  }

  /**
   * Getter for player defense stat.
   *
   * @return player health value
   */
  double getHealth() {
    return health;
  }

  /**
   * Setter for player health stat.
   *
   * @param health health value
   */
  void setHealth(double health) {
    this.health = health;
  }

  /**
   * Getter for player level stat.
   *
   * @return player level value
   */
  int getLevel() {
    return level;
  }

  /**
   * Setter for player level stat.
   *
   * @param level level value
   */
  private void setLevel(int level) {
    this.level = level;
  }

  /**
   * Getter for player experience stat.
   *
   * @return player experience value
   */
  double getExperience() {
    return experience;
  }

  /**
   * Setter for player experience stat.
   *
   * @param experience health value
   */
  void setExperience(double experience) {
    this.experience = experience;
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
   * @return return formatted String of possible items
   */
  public String itemString() {

    // creates a new instance of type String Builder
    StringBuilder itemString = new StringBuilder();
    ArrayList<Item> itemList = getItems();

    itemString.append("You are carrying ");
    // checks if list of items is null
    if (itemList == null || itemList.isEmpty()) {
      itemString.append(AdventureConstants.EMPTY_ITEM_LIST);

    } else if (itemList.size() == 1) {
      itemString.append(itemList.get(itemList.size() - 1).getName());

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

  /** Provides health status of player. */
  public void getStatus() {

    int playerHealth = (int) ((getHealth() / maxHealth) * 20);

    System.out.println(" Player: " + getName());
    for (int i = 0; i < playerHealth; i++) {
      System.out.print("#|");
    }

    for (int i = 0; i < 20 - playerHealth; i++) {
      System.out.print("_|");
    }

    System.out.println("\n");
  }

  /**
   * Returns experience required for levelling up.
   *
   * @param level value of next level
   * @return returns amounts of experience required to reach next level
   */
  public double requiredExperience(int level) {
    if (level == 1) {

      return expBeforeThat;

    } else if (level == 2) {

      return expBefore;

    } else {

      return ((requiredExperience(level - 1) + requiredExperience(level - 2)) * 1.1);
    }
  }

  /** Updates player stats on levelling up. */
  public void levelUp() {

    double requiredExperience = requiredExperience(getLevel() + 1);

    // as long as player experience is greater than or equal to experience required for next level
    while (getExperience() >= requiredExperience) {
      setLevel(getLevel() + 1);
      System.out.println(AdventureConstants.LEVEL_UP + getLevel());

      expBeforeThat = expBefore;
      expBefore = requiredExperience;
      setExperience(experience - requiredExperience(getLevel() + 1));
      maxHealth *= 1.3;

      // setting new attack, health and defense stats
      setHealth(maxHealth);
      setAttack(getAttack() * 1.5);
      setDefense(getDefense() * 1.5);

      // Updating required experience for next level
      requiredExperience = requiredExperience(getLevel() + 1);
    }
  }

  /**
   * Checks for item carried by player.
   *
   * @param itemName name of item to e checked for
   * @return boolean value depending on success
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

  /** Displays player information. */
  public void displayInfo() {

    System.out.println("Name: " + getName());
    System.out.println("Level: " + getLevel());
    System.out.println("HP: " + getHealth());
    System.out.println("Attack: " + getAttack());
    System.out.println("Defense: " + getDefense());
    System.out.println(itemString());
  }
}
