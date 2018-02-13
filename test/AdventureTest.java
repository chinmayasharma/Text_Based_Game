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
 * Tests for change of rooms
 * *********************************************************************************************************
 */

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


}
