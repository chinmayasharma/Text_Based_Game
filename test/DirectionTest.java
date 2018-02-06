import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

  /** Checks if direction name returned is same as expected */
  @Test
  public void getDirectionName() {
    assertEquals(
        "East",
        GetJsonFileTest.expectedLayout.getRooms().get(0).getDirections().get(0).getDirectionName());
  }

  /** Checks if name of room returned y getRoom method is the same as expected */
  @Test
  public void getRoom() {
    assertEquals("MatthewsStreet", GetJsonFileTest.expectedLayout.getRooms().get(0).getName());
  }
}
