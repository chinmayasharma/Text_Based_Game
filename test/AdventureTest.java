import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdventureTest {

  public Adventure standardGame;

  @Before
  public void setUp() {
    standardGame = new Adventure("https://chinmayasharma.github.io/siebel.json");
  }

  /**
   * *********************************************************************************************************
   * Starting and Ending Room Tests
   * *********************************************************************************************************
   */

  /** checks if starting Room as returned by findStaringRoom is same as expected room */
  @Test
  public void findStartingRoomTest() {
    assertEquals(
        standardGame.currentRoom,
        standardGame.layout.findRoom(standardGame.layout.getStartingRoom()));
  }

  /** checks if ending Room as returned by findStaringRoom is same as expected room */
  @Test
  public void findEndingRoomTest() {
    assertEquals(
        "Siebel1314", standardGame.layout.findRoom(standardGame.layout.getEndingRoom()).getName());
  }

  /**
   * *********************************************************************************************************
   * String output for list of items and directions
   * *********************************************************************************************************
   */

  /** return list if possible items as String */
  @Test
  public void itemArrayListToStringTest() {
    assertEquals("coin.", standardGame.currentRoom.itemString(standardGame.currentRoom.getItems()));
  }

  /** return list if possible null items as String */
  @Test
  public void invalidItemArrayListToStringTest() {
    assertEquals("nothing", standardGame.currentRoom.itemString(null));
  }

  /** return list if possible directions as String */
  @Test
  public void directionArrayListToStringTest() {
    assertEquals("From here, you can go: East", standardGame.currentRoom.directionString());
  }

  /**
   * *********************************************************************************************************
   * String output for checking direction
   * *********************************************************************************************************
   */

  /** checks if direction exists in the empty directionList if empty */
  @Test
  public void emptyDirectionTest() {
    assertFalse(standardGame.changeRoom(""));
  }

  /** checks if direction exists in the empty directionList if invalid */
  @Test
  public void checkInvalidDirectionTest() {
    assertFalse(standardGame.changeRoom("Down"));
  }

  /** checks if direction exists in the empty directionList if null */
  @Test
  public void checkNullDirectionTest() {
    assertFalse(standardGame.changeRoom(null));
  }

  /**
   * *********************************************************************************************************
   * String output for checking items
   * *********************************************************************************************************
   */

  /** checks if item exists in the itemList, if valid */
  @Test
  public void checkItemTest() {
    assertTrue(standardGame.currentRoom.addItem("coin"));
  }

  /** checks if item exists in the itemList, if invalid */
  @Test
  public void findInvalidItemTest() {
    assertNull(standardGame.currentRoom.findItem("can"));
  }

  /** checks if item exists in the itemList, if null */
  @Test
  public void findNullItemTest() {
    assertNull(standardGame.currentRoom.findItem(null));
  }

  /** checks if item exists in the itemList, if empty */
  @Test
  public void findEmptyStringItemTest() {
    assertNull(standardGame.currentRoom.findItem(""));
  }

  /**
   * *********************************************************************************************************
   * Tests for floor plan validator
   * *********************************************************************************************************
   */

  /** validates floor plan, as valid */
  @Test
  public void planValidatorTest() {
    assertTrue(standardGame.layout.planValidator());
  }
}
