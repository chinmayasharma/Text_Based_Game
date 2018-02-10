import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("damage")
  @Expose
  private Double damage;

    /**
     *
     * @param name
     * @param damage
     */
    public Item(String name, Double damage) {
        super();
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item withName(String name) {
        this.name = name;
        return this;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Item withDamage(Double damage) {
        this.damage = damage;
        return this;
    }

}
