import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class LayoutTest {

  private static Gson gson = new Gson();
  public static Layout layout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  public Adventure testGame = new Adventure(layout);

  /** Checks if name of first room returned by getRooms method is as expected */
  @Test
  public void getRooms() {
    assertEquals("Champaign", layout.getRooms().get(0).getName());
  }

  /**
   * *********************************************************************************************************
   * Starting and Ending Room Tests
   * *********************************************************************************************************
   */
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
  @Test
  public void roomItemStringTest() {
    assertEquals("Textbook.", layout.getRooms().get(0).itemString());
  }

  /** return list of possible items as String */
  @Test
  public void roomEmptyItemStringTest() {
    assertEquals("nothing", layout.getRooms().get(3).itemString());
  }

  /** return list of possible items as String */
  @Test
  public void roomMultipleItemStringTest() {
    assertEquals("Map, and Ticket.", layout.getRooms().get(1).itemString());
  }

  /** return list of possible items as String */
  @Test
  public void playerItemStringTest() {
    assertEquals("You are carrying Toothbrush, and Boxers", layout.getPlayer().itemString());
  }

  /** return list of possible directions as String */
  @Test
  public void directionStringTest() {
    assertEquals("From here, you can go: East", layout.getRooms().get(0).directionString());
  }

  /** return list of possible directions as String */
  @Test
  public void directionMultipleStringTest() {
    assertEquals(
        "From here, you can go: West, Northeast, North, or East",
        layout.getRooms().get(1).directionString());
  }

  /** return list of possible monsters as String */
  @Test
  public void monsterStringTest() {
    assertEquals("Zombie.", layout.getRooms().get(0).monsterString());
  }

  /** return list of possible monsters as String */
  @Test
  public void monsterEmptyStringTest() {
    assertEquals("no monster", layout.getRooms().get(6).monsterString());
  }

  /** return list of possible monsters as String */
  @Test
  public void monsterMultipleStringTest() {
    assertEquals("Gremlin, and Werewolf", layout.getRooms().get(4).monsterString());
  }
}
