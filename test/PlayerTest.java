import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

  private static Gson gson = new Gson();
  private static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  private Adventure testGame = new Adventure(layout);

  @Test
  public void getName() {
    assertEquals("Chinmaya", testGame.player.getName());
  }

  @Test
  public void getItems() {
    assertEquals("Toothbrush", testGame.player.getItems().get(0).getName());
  }

  @Test
  public void getAttack() {
    assertEquals(35.0, testGame.player.getAttack(), 0.01);
  }

  @Test
  public void getDefense() {
    assertEquals(25.0, testGame.player.getDefense(), 0.01);
  }

  @Test
  public void setDefense() {
    testGame.player.setDefense(30.0);

    assertEquals(30.0, testGame.player.getDefense(), 0.01);
  }

  @Test
  public void getHealth() {
    assertEquals(50.0, testGame.player.getHealth(), 0.01);
  }

  @Test
  public void getLevel() {
    assertEquals(1, testGame.player.getLevel());
  }

  @Test
  public void getExperience() {
    assertEquals(0.0, testGame.player.getExperience(), 0.01);
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
    assertEquals("You are carrying Toothbrush, and Boxers", testGame.player.itemString());
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
