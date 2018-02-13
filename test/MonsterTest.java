import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

  private static Gson gson = new Gson();
  public static Layout layout =
          gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  public Adventure testGame = new Adventure(layout);

  @Test
  public void getName() {
    assertEquals("Zombie", testGame.currentRoom.getMonstersInRoom().get(0).getName());
  }

  @Test
  public void getInvalidName() {
    assertNotEquals("Bird", testGame.currentRoom.getMonstersInRoom().get(0).getName());
  }

  @Test
  public void getNullName() {
    assertNotEquals(null, testGame.currentRoom.getMonstersInRoom().get(0).getName());
  }

  @Test
  public void getAttack() {
    assertEquals(30, testGame.currentRoom.getMonstersInRoom().get(0).getAttack(), 0.01);

  }

  @Test
  public void getDefense() {
    assertEquals(15, testGame.currentRoom.getMonstersInRoom().get(0).getDefense(), 0.01);
  }

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
