import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class LayoutTest {

  private static Gson gson = new Gson();
  public static Layout expectedLayout =
      gson.fromJson(AdventureConstants.getFileContentsAsString("siebel.json"), Layout.class);

  /**
   * Tests if layout return from URL is same as the one locally retrieved by getFileContentsAsString
   * method
   *
   * @throws MalformedURLException thrown if the URL is invalid
   * @throws UnirestException Unirest Library exception
   */
  @Test
  public void apiRequestTest() throws MalformedURLException, UnirestException {
    assertEquals(
        expectedLayout,
        Layout.makeApiRequest("https://courses.engr.illinois.edu/cs126/adventure/siebel.json"));
  }

  /** Checks if name of first room is same as starting room */
  @Test
  public void getStartingRoom() {
    assertEquals("MatthewsStreet", expectedLayout.getStartingRoom());
  }

  /** Checks if name of ending room is as expected */
  @Test
  public void getEndingRoom() {
    assertEquals("Siebel1314", expectedLayout.getEndingRoom());
  }

  /** Checks if name of first room returned by getRooms method is as expected */
  @Test
  public void getRooms() {
    assertEquals("MatthewsStreet", expectedLayout.getRooms().get(0).getName());
  }
}
