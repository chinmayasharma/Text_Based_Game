import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class AdventureTest {

  Adventure testGame;

  @Before
  public void setUp() throws Exception {
    Gson gson = new Gson();
    GetJsonFileTest.expectedLayout =
        gson.fromJson(Data.getFileContentsAsString("siebel.json"), Layout.class);
    testGame = new Adventure(false, false, GetJsonFileTest.expectedLayout.getRooms().get(0));
  }

  @Test
  public void main() {
    findStartingRoom();
  }

  /** checks if starting Room as returned by findStaringRoom is same as expected room */
  @Test
  public void findStartingRoom() {
    System.out.println(testGame.currentRoom.getName());
    assertEquals(testGame.currentRoom, testGame.findStartingRoom());
  }

  /** return list if possible items as String */
  @Test
  public void itemArrayListToString() {
    assertEquals(
        "This room contains coin.", testGame.itemArrayListToString(testGame.possibleItems));
  }

  /** return list if possible directions as String */
  @Test
  public void directionArrayListToString() {
    assertEquals(
        "From here, you can go: East",
        testGame.directionArrayListToString(testGame.possibleDirections));
  }

  /** checks if direction exists in the directionList */
  @Test
  public void checkDirection() {
    assertEquals(true, testGame.checkDirection("East", testGame.possibleDirections));
  }

  /** checks if item exists in the itemList */
  @Test
  public void checkItem() {
    assertEquals(true, testGame.checkItem("coin", testGame.possibleItems));
  }

  /** compares updated room to room change using changeRoom method */
  @Test
  public void changeRoom() {
    assertEquals("SiebelEntry", testGame.changeRoom("East", testGame.possibleDirections).getName());
  }

  /** compared expected item with item in first room */
  @Test
  public void findItem() {
    assertEquals("coin", testGame.findItem("coin", testGame.possibleItems));
  }
}
