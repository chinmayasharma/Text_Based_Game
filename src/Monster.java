import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Monster {

  @SerializedName("name")
  private String name;

  @SerializedName("attack")
  private Double attack;

  @SerializedName("defense")
  private Double defense;

  @SerializedName("health")
  private Double health;

  private double maxHealth;

  /**
   * @param defense
   * @param level
   * @param items
   * @param name
   * @param attack
   * @param health
   */
  public Monster(
      String name, ArrayList<Item> items, double attack, double defense, double health, int level) {
    super();
    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.health = health;
    this.maxHealth = health;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Monster withName(String name) {
    this.name = name;
    return this;
  }

  public Double getAttack() {
    return attack;
  }

  public void setAttack(Double attack) {
    this.attack = attack;
  }

  public Monster withAttack(Double attack) {
    this.attack = attack;
    return this;
  }

  public Double getDefense() {
    return defense;
  }

  public void setDefense(Double defense) {
    this.defense = defense;
  }

  public Monster withDefense(Double defense) {
    this.defense = defense;
    return this;
  }

  public Double getHealth() {
    return health;
  }

  public void setHealth(Double health) {
    this.health = health;
  }

  public Monster withHealth(Double health) {
    this.health = health;
    return this;
  }

    public Double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }
}
