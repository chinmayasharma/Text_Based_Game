import com.google.gson.annotations.SerializedName;

public class Monster {

  @SerializedName("name")
  private String name;

  @SerializedName("attack")
  private Double attack;

  @SerializedName("defense")
  private Double defense;

  @SerializedName("health")
  private Double health;

  private static double maxHealth = 30;

  /**
   * Constructor for class Monster.
   *
   * @param defense defense stat of monster
   * @param name name of monster
   * @param attack attack of monster
   * @param health health of monster
   */
  public Monster(String name, double attack, double defense, double health) {
    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.health = health;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getAttack() {
    return attack;
  }

  public Double getDefense() {
    return defense;
  }

  public Double getHealth() {
    return health;
  }

  public void setHealth(Double health) {
    this.health = health;
  }

  /** Provides health status of monster. */
  public void getStatus() {

    int monsterHealth = (int) ((getHealth() / maxHealth) * 20);

    System.out.println("Monster: " + getName());
    for (int i = 0; i < monsterHealth; i++) {
      System.out.print("#|");
    }

    for (int i = 0; i < 20 - monsterHealth; i++) {
      System.out.print("_|");
    }
  }
}
