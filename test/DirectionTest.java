import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

  private static Gson gson = new Gson();
  private static Layout expectedLayout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  /**
   * *********************************************************************************************************
   * Tests for Getters in Direction Class
   * *********************************************************************************************************
   */
  @Test
  public void getDirectionName() {
    assertEquals(
        "East", expectedLayout.getRooms().get(0).getDirections().get(0).getDirectionName());
  }

  /** Checks if name of room returned by getRoom method is the same as expected */
  @Test
  public void getRoom() {
    assertEquals("Champaign", expectedLayout.getRooms().get(0).getName());
  }
}
