import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdventureTest {

  private static Gson gson = new Gson();
  public static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  public Adventure testGame = new Adventure(layout);

  /**
   * *********************************************************************************************************
   * Starting and Ending Room Tests
   * *********************************************************************************************************
   */

  /** checks if starting Room as returned by findStaringRoom is same as expected room */
  @Test
  public void findStartingRoomTest() {
    assertEquals(layout.getRooms().get(0), layout.findRoom(layout.getStartingRoom()));
  }

  /** checks if ending Room as returned by findStaringRoom is same as expected room */
  @Test
  public void findEndingRoomTest() {
    assertEquals("Miami", layout.findRoom(layout.getEndingRoom()).getName());
  }

  /**
   * *********************************************************************************************************
   * String output for list of items and directions
   * *********************************************************************************************************
   */

  /** return list if possible items as String */
  @Test
  public void itemArrayListToStringTest() {
    assertEquals("Textbook.", layout.getRooms().get(0).itemString());
  }

  /** return list if possible directions as String */
  @Test
  public void directionArrayListToStringTest() {
    assertEquals("From here, you can go: East", layout.getRooms().get(0).directionString());
  }

  /**
   * *********************************************************************************************************
   * String output for checking direction
   * *********************************************************************************************************
   */

  /** checks if direction exists in the empty directionList if empty */
  @Test
  public void directionTest() {
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
   * String output for checking items
   * *********************************************************************************************************
   */

  /** checks if item exists in the itemList, if valid */
  @Test
  public void checkItemTest() {
    assertTrue(layout.getRooms().get(0).addItem("coin"));
  }

  /** checks if item exists in the itemList, if invalid */
  @Test
  public void findInvalidItemTest() {
    assertNull(layout.getRooms().get(0).findItem("can"));
  }

  /** checks if item exists in the itemList, if null */
  @Test
  public void findNullItemTest() {
    assertNull(layout.getRooms().get(0).findItem(null));
  }

  /** checks if item exists in the itemList, if empty */
  @Test
  public void findEmptyStringItemTest() {
    assertNull(layout.getRooms().get(0).findItem(""));
  }


}
