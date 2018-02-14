import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

  private static Gson gson = new Gson();
  private static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  /** Checks if name of first room is same as expected */
  @Test
  public void getName() {
    assertEquals("Champaign", layout.getRooms().get(0).getName());
  }

  /** Checks if name of description of first room is same as expected */
  @Test
  public void getDescription() {
    assertEquals(
        "You are in Champaign, IL, USA. You're in the middle of nowhere, so you better start moving",
        layout.getRooms().get(0).getDescription());
  }

  /** Checks if name of item in first room is same as expected */
  @Test
  public void getItems() {
    assertEquals("Textbook", layout.getRooms().get(0).getItems().get(0).getName());
  }

  /** Checks if name of direction in first room is same as expected */
  @Test
  public void getDirections() {
    assertEquals("East", layout.getRooms().get(0).getDirections().get(0).getDirectionName());
  }

  /**
   * *********************************************************************************************************
   * String output for checking items in room
   * *********************************************************************************************************
   */
  @Test
  public void checkItemTest() {
    assertTrue(layout.getRooms().get(0).checkItem("Textbook"));
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

  /**
   * *********************************************************************************************************
   * String output for checking direction
   * *********************************************************************************************************
   */
  @Test
  public void emptyDirectionTest() {
    assertFalse(layout.getRooms().get(0).checkDirection(""));
  }

  /** checks if direction exists in the empty directionList if invalid */
  @Test
  public void checkInvalidDirectionTest() {
    assertFalse(layout.getRooms().get(0).checkDirection("Down"));
  }

  /** checks if direction exists in the empty directionList if null */
  @Test
  public void checkNullDirectionTest() {
    assertFalse(layout.getRooms().get(0).checkDirection(null));
  }
}
