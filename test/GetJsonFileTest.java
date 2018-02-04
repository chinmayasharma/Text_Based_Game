import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class GetJsonFileTest {

  Layout expectedLayout = null;

  @Before
  public void setUp() throws Exception {
    Gson gson = new Gson();
    expectedLayout = gson.fromJson(Data.getFileContentsAsString("siebel.json"), Layout.class);
  }

  @Test
  public void makeApiRequestTest() throws MalformedURLException, UnirestException {
    assertEquals(
        expectedLayout,
        GetJsonFile.makeApiRequest(
            "https://courses.engr.illinois.edu/cs126/adventure/siebel.json"));
  }
}
