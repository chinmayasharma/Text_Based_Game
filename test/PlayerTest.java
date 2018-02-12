import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

  private static Gson gson = new Gson();
  public static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  public Adventure testGame = new Adventure(layout);

  @Test
  public void getName() {
    assertEquals("Chinmaya", testGame.player.getName());
  }

  @Test
  public void getInvalidName() {
    assertNotEquals("Sharma", testGame.player.getName());
  }

  @Test
  public void getNullName() {
    assertNotEquals(null, testGame.player.getName());
  }

  @Test
  public void getItems() {
    assertEquals("Toothbrush", testGame.player.getItems().get(0).getName());
  }

  @Test
  public void getInvalidItems() {
    assertNotEquals("Toothpaste", testGame.player.getItems().get(0).getName());
  }

  @Test
  public void getNullItems() {
    assertNotEquals(null, testGame.player.getItems().get(0).getName());
  }

  @Test
  public void getAttack() {
    assertEquals(35.0, testGame.player.getAttack(), 0.01);

  }

  @Test
  public void setAttack() {}

  @Test
  public void getDefense() {
    assertEquals(25.0, testGame.player.getDefense(), 0.01);

  }

  @Test
  public void setDefense() {}

  @Test
  public void getHealth() {}

  @Test
  public void setHealth() {}

  @Test
  public void getLevel() {
    assertEquals(1, testGame.player.getLevel());
  }

  @Test
  public void setLevel() {}

  @Test
  public void getExperience() {
    assertEquals(0.0, testGame.player.getExperience(), 0.01);

  }

  @Test
  public void setExperience() {}

  @Test
  public void addItem() {
    testGame.player.addItem("coin");
    assertTrue(testGame.player.addItem("coin"));
  }

  @Test
  public void addInvalidItem() {
    assertFalse(testGame.player.addItem("bazooka"));
  }

  @Test
  public void addNullItem() {
    assertFalse(testGame.player.addItem(null));
  }

  @Test
  public void removeItem() {
    assertTrue(testGame.player.removeItem("Toothbrush"));
  }

  @Test
  public void removeInvalidItem() {
    assertFalse(testGame.player.removeItem("bazooka"));
  }

  @Test
  public void removeNullItem() {
    assertFalse(testGame.player.removeItem(null));
  }

  @Test
  public void findItem() {
    assertEquals("Toothbrush", testGame.player.findItem("Toothbrush").getName());
  }

  @Test
  public void findInvalidItem() {
    assertNull(testGame.player.findItem("bazooka"));
  }

  @Test
  public void findNullItem() {
    assertNull(testGame.player.findItem(null));
  }

  @Test
  public void itemString() {}

  /** return list if possible items as String */
  @Test
  public void itemArrayListToStringTest() {
    assertEquals("Toothbrush, and Toilet-Paper", testGame.player.itemString());
  }

  @Test
  public void levelOneExperience() {
    assertEquals(25.0, testGame.player.requiredExperience(1), 0.01);
  }

  @Test
  public void levelTwoExperience() {
    assertEquals(50.0, testGame.player.requiredExperience(2), 0.01);
  }

  @Test
  public void levelFourExperience() {
    assertEquals(145.75, testGame.player.requiredExperience(4), 0.01);
  }

  @Test
  public void levelTenExperience() {
    assertEquals(3946.2, testGame.player.requiredExperience(10), 0.01);
  }
}
