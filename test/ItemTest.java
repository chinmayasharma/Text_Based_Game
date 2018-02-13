import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

  private static Gson gson = new Gson();
  private static Layout expectedLayout =
          gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  @Test
  public void getName() {
    assertEquals(
          "Textbook", expectedLayout.getRooms().get(0).getItems().get(0).getName());
  }

  @Test
  public void getDamage() {
    assertEquals(
            10.0, expectedLayout.getRooms().get(0).getItems().get(0).getDamage(), 0.01);

  }
}