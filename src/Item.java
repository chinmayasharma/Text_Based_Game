import com.google.gson.annotations.SerializedName;

public class Item {

  @SerializedName("name")
  private String name;

  @SerializedName("damage")
  private Double damage;

  /**
   * Constructor for class Item.
   *
   * @param name name of item
   * @param damage damage caused by item during attack
   */
  public Item(String name, Double damage) {

    this.name = name;
    this.damage = damage;
  }

  /**
   * getter for item name.
   *
   * @return name of item
   */
  public String getName() {

    return name;
  }

  /**
   * getter for item damage.
   *
   * @return amount of damage done by using item during attack
   */
  public Double getDamage() {

    return damage;
  }
}
