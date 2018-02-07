import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomTest {

  private static Gson gson = new Gson();
  public static Layout expectedLayout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  @Before
  public void setUp() throws Exception {}

  /** Checks if name of first room is same as expected */
  @Test
  public void getName() {
    assertEquals("MatthewsStreet", expectedLayout.getRooms().get(0).getName());
  }

  /** Checks if name of description of first room is same as expected */
  @Test
  public void getDescription() {
    assertEquals(
        "You are on Matthews, outside the Siebel Center",
        expectedLayout.getRooms().get(0).getDescription());
  }

  /** Checks if name of item in first room is same as expected */
  @Test
  public void getItems() {
    ArrayList<String> itemList = new ArrayList<>();
    itemList.add("coin");
    assertEquals(itemList, expectedLayout.getRooms().get(0).getItems());
  }

  /** Checks if name of direction in first room is same as expected */
  @Test
  public void getDirections() {
    assertEquals(
        "East", expectedLayout.getRooms().get(0).getDirections().get(0).getDirectionName());
  }
}
