import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

  private static Gson gson = new Gson();
  private static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  private Adventure testGame = new Adventure(layout);

  /**
   * *********************************************************************************************************
   * Tests for Getters for Player Class
   * *********************************************************************************************************
   */
  @Test
  public void getName() {
    assertEquals("Chinmaya", testGame.player.getName());
  }

  /** Checks for item carried by player. */
  @Test
  public void getItems() {
    assertEquals("Toothbrush", testGame.player.getItems().get(0).getName());
  }

  /** Checks for value of player attack stat */
  @Test
  public void getAttack() {
    assertEquals(35.0, testGame.player.getAttack(), 0.01);
  }

  /** Checks for value of player defense stat */
  @Test
  public void getDefense() {
    assertEquals(25.0, testGame.player.getDefense(), 0.01);
  }

  /** Sets player defense stat */
  @Test
  public void setDefense() {

    testGame.player.setDefense(30.0);
    assertEquals(30.0, testGame.player.getDefense(), 0.01);
  }

  /** Checks for value of player defense stat */
  @Test
  public void getHealth() {
    assertEquals(50.0, testGame.player.getHealth(), 0.01);
  }

  /** Checks for value of player level stat */
  @Test
  public void getLevel() {
    assertEquals(1, testGame.player.getLevel());
  }

  /** Checks for value of player experience stat */
  @Test
  public void getExperience() {
    assertEquals(0.0, testGame.player.getExperience(), 0.01);
  }

  /**
   * *********************************************************************************************************
   * Tests for Finding Required Experience
   * *********************************************************************************************************
   */
  @Test
  public void levelOneExperience() {
    assertEquals(25.0, testGame.player.requiredExperience(1), 0.01);
  }

  /** Checks for experience required for level two */
  @Test
  public void levelTwoExperience() {
    assertEquals(50.0, testGame.player.requiredExperience(2), 0.01);
  }

  /** Checks for experience required for level four */
  @Test
  public void levelFourExperience() {
    assertEquals(145.75, testGame.player.requiredExperience(4), 0.01);
  }

  /** Checks for experience required for level ten */
  @Test
  public void levelTenExperience() {
    assertEquals(3946.2, testGame.player.requiredExperience(10), 0.01);
  }
}
