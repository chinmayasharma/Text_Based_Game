import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class GetJsonFileTest {

  public static Gson gson = new Gson();
  public static Layout expectedLayout =
      gson.fromJson(Data.getFileContentsAsString("siebel.json"), Layout.class);

  /**
   * Tests if layout return from URL is same as the one locally retrieved by getFileContentsAsString
   * method
   *
   * @throws MalformedURLException thrown if the URL is invalid
   * @throws UnirestException Unirest Library exception
   */
  @Test
  public void makeApiRequestTest() throws MalformedURLException, UnirestException {
    assertEquals(
        expectedLayout,
        GetJsonFile.makeApiRequest(
            "https://courses.engr.illinois.edu/cs126/adventure/siebel.json"));
  }
}
