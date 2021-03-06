import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

  private static Gson gson = new Gson();
  private static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  private Adventure testGame = new Adventure(layout);

  /**
   * *********************************************************************************************************
   * Tests for validity of getters for class Monster
   * *********************************************************************************************************
   */
  @Test
  public void getName() {
    assertEquals("Zombie", testGame.currentRoom.getMonstersInRoom().get(0).getName());
  }

  /** Checks if attack stat of monster returned is valid */
  @Test
  public void getAttack() {
    assertEquals(30, testGame.currentRoom.getMonstersInRoom().get(0).getAttack(), 0.01);
  }

  /** Checks if defense stat of monster returned is valid */
  @Test
  public void getDefense() {
    assertEquals(15, testGame.currentRoom.getMonstersInRoom().get(0).getDefense(), 0.01);
  }

  /** Checks if health stat of monster returned is valid */
  @Test
  public void getHealth() {
    assertEquals(30, testGame.currentRoom.getMonstersInRoom().get(0).getHealth(), 0.01);
  }

  /** return list of possible monsters as String */
  @Test
  public void monsterEmptyStringTest() {
    assertNull(layout.getRooms().get(6).getMonstersInRoom());
  }
}
