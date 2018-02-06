import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LayoutTest {

  /** Checks if name of first room is same as starting room */
  @Test
  public void getStartingRoom() {
    assertEquals("MatthewsStreet", GetJsonFileTest.expectedLayout.getStartingRoom());
  }

  /** Checks if name of ending room is as expected */
  @Test
  public void getEndingRoom() {
    assertEquals("Siebel1314", GetJsonFileTest.expectedLayout.getEndingRoom());
  }

  /** Checks if name of first room returned by getRooms method is as expected */
  @Test
  public void getRooms() {
    assertEquals("MatthewsStreet", GetJsonFileTest.expectedLayout.getRooms().get(0).getName());
  }
}
