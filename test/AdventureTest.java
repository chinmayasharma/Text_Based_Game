import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdventureTest {

  private static Gson gson = new Gson();
  private static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  private Adventure testGame = new Adventure(layout);

  /**
   * *********************************************************************************************************
   * Tests for change of rooms
   * *********************************************************************************************************
   */
  @Test
  public void monsterPresentDirectionTest() {
    assertFalse(testGame.changeRoom("East"));
  }

  /** checks if direction exists in the empty directionList if empty */
  @Test
  public void emptyDirectionTest() {
    assertFalse(testGame.changeRoom(""));
  }

  /** checks if direction exists in the empty directionList if invalid */
  @Test
  public void checkInvalidDirectionTest() {
    assertFalse(testGame.changeRoom("Down"));
  }

  /** checks if direction exists in the empty directionList if null */
  @Test
  public void checkNullDirectionTest() {
    assertFalse(testGame.changeRoom(null));
  }

  /**
   * *********************************************************************************************************
   * Tests for Finding Items
   * *********************************************************************************************************
   */
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
}
