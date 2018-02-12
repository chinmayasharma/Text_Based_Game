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
    assertEquals("Nerd", testGame.currentRoom.getMonstersInRoom().get(0).getName());
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
  public void getAttack() {}

  @Test
  public void getDefense() {}

  @Test
  public void getHealth() {}

  @Test
  public void getStatus() {}
}