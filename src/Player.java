import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Player {

  @SerializedName("name")
  private String name;

  @SerializedName("items")
  private ArrayList<Item> items = null;

  @SerializedName("attack")
  private Double attack;

  @SerializedName("defense")
  private Double defense;

  @SerializedName("health")
  private Double health;

  @SerializedName("level")
  private Integer level;

  private double maxHealth;

  /**
   * @param defense
   * @param level
   * @param items
   * @param name
   * @param attack
   * @param health
   */
  public Player(
      String name, ArrayList<Item> items, double attack, double defense, double health, int level) {
    super();
    this.name = name;
    this.items = items;
    this.attack = attack;
    this.defense = defense;
    this.health = health;
    this.maxHealth = health;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Player withName(String name) {
    this.name = name;
    return this;
  }

  public ArrayList<Item> getItems() {
    return items;
  }

  public void setItems(ArrayList<Item> items) {
    this.items = items;
  }

  public Player withItems(ArrayList<Item> items) {
    this.items = items;
    return this;
  }

  public Double getAttack() {
    return attack;
  }

  public void setAttack(Double attack) {
    this.attack = attack;
  }

  public Player withAttack(Double attack) {
    this.attack = attack;
    return this;
  }

  public Double getDefense() {
    return defense;
  }

  public void setDefense(Double defense) {
    this.defense = defense;
  }

  public Player withDefense(Double defense) {
    this.defense = defense;
    return this;
  }

  public Double getHealth() {
    return health;
  }

  public void setHealth(Double health) {
    this.health = health;
  }

  public Double getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(double maxHealth) {
    this.maxHealth = maxHealth;
  }

  public Player withHealth(Double health) {
    this.health = health;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Player withLevel(Integer level) {
    this.level = level;
    return this;
  }
}
