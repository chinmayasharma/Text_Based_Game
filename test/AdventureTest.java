import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdventureTest {

  public Adventure standardGame;
  public Adventure circularGame;

  @Before
  public void setUp() {
    standardGame = new Adventure("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
    circularGame = new Adventure("https://courses.engr.illinois.edu/cs126/adventure/circular.json");
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
        standardGame.currentRoom, standardGame.findRoom(standardGame.layout.getStartingRoom()));
  }

  /** checks if ending Room as returned by findStaringRoom is same as expected room */
  @Test
  public void findEndingRoomTest() {
    assertEquals(
        "Siebel1314", standardGame.findRoom(standardGame.layout.getEndingRoom()).getName());
  }

  /**
   * *********************************************************************************************************
   * String output for list of items and directions
   * *********************************************************************************************************
   */

  /** return list if possible items as String */
  @Test
  public void itemArrayListToStringTest() {
    assertEquals("coin.", standardGame.itemArrayListToString(standardGame.possibleItems));
  }

  /** return list if possible null items as String */
  @Test
  public void invalidItemArrayListToStringTest() {
    assertEquals("nothing", standardGame.itemArrayListToString(null));
  }

  /** return list if possible directions as String */
  @Test
  public void directionArrayListToStringTest() {
    assertEquals(
        "From here, you can go: East",
        standardGame.directionArrayListToString(standardGame.possibleDirections));
  }
  /** return list if possible, null directions list as String */
  @Test
  public void invalidDirectionArrayListToStringTest() {
    assertEquals("From here, you can go: nowhere", standardGame.directionArrayListToString(null));
  }

  /**
   * *********************************************************************************************************
   * String output for checking direction
   * *********************************************************************************************************
   */

  /** checks if direction exists in the empty directionList if empty */
  @Test
  public void emptyDirectionTest() {
    assertEquals(false, standardGame.checkDirection(""));
  }

  /** checks if direction exists in the empty directionList if valid */
  @Test
  public void checkDirectionTest() {
    assertTrue(standardGame.checkDirection("East"));
  }

  /** checks if direction exists in the empty directionList if invalid */
  @Test
  public void checkInvalidDirectionTest() {
    assertFalse(standardGame.checkDirection("Down"));
  }

  /** checks if direction exists in the empty directionList if null */
  @Test
  public void checkNullDirectionTest() {
    assertFalse(standardGame.checkDirection(null));
  }

  /**
   * *********************************************************************************************************
   * String output for checking items
   * *********************************************************************************************************
   */

  /** checks if item exists in the itemList, if valid */
  @Test
  public void checkItemTest() {
    assertEquals(true, standardGame.checkItem("coin", standardGame.possibleItems));
  }

  /** checks if item exists in the itemList, if null */
  @Test
  public void invalidItemTest() {
    assertEquals(false, standardGame.checkItem("coin", null));
  }

  /** checks if item exists in the itemList, if invalid */
  @Test
  public void findInvalidItemTest() {
    assertEquals(null, standardGame.findItem("can", standardGame.possibleItems));
  }

  /** checks if item exists in the itemList, if null */
  @Test
  public void findNullItemTest() {
    assertEquals(null, standardGame.findItem(null, standardGame.possibleItems));
  }

  /** checks if item exists in the itemList, if itemList is null */
  @Test
  public void findItemNullListTest() {
    assertEquals(null, standardGame.findItem("coin", null));
  }

  /** checks if item exists in the itemList, if empty */
  @Test
  public void findEmptyStringItemTest() {
    assertEquals(null, standardGame.findItem("", standardGame.possibleItems));
  }

  /**
   * *********************************************************************************************************
   * Tests for changing room
   * *********************************************************************************************************
   */

  /** compares updated room to room change using changeRoom method */
  @Test
  public void changeRoomTest() {
    boolean change = standardGame.changeRoom("east");
    assertEquals(standardGame.layout.getRooms().get(1), standardGame.getCurrentRoom());
  }

  /** compares updated room to room change using changeRoom method, if invalid */
  @Test
  public void invalidChangeRoomTest() {
    assertFalse(standardGame.changeRoom("est"));
  }

  /**
   * *********************************************************************************************************
   * Tests for floor plan validator
   * *********************************************************************************************************
   */

  /** validates floor plan, as invalid */
  @Test
  public void invalidPlanValidatorTest() {
    assertFalse(circularGame.planValidator());
  }

  /** validates floor plan, as valid */
  @Test
  public void planValidatorTest() {
    assertTrue(standardGame.planValidator());
  }
}
