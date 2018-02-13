import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Layout {

  @SerializedName("startingRoom")
  private String startingRoom;

  @SerializedName("endingRoom")
  private String endingRoom;

  @SerializedName("player")
  private Player player;

  @SerializedName("rooms")
  private ArrayList<Room> rooms;

  public String getStartingRoom() {
    return startingRoom;
  }

  public String getEndingRoom() {
    return endingRoom;
  }

  /**
   * Constructor for class Layout.
   *
   * @param startingRoom name of starting room
   * @param endingRoom name of ending room
   * @param player player object in layout
   * @param rooms rooms objects in layout
   */
  public Layout(String startingRoom, String endingRoom, Player player, ArrayList<Room> rooms) {

    this.startingRoom = startingRoom;
    this.endingRoom = endingRoom;
    this.player = player;
    this.rooms = rooms;
  }

  public Player getPlayer() {

    return player;
  }

  public ArrayList<Room> getRooms() {

    return rooms;
  }

  /**
   * Make an API request to fetch game file.
   *
   * @param url takes url of json file as input
   * @return layout
   * @throws UnirestException library exception
   * @throws MalformedURLException thrown for invalid input
   */
  public static Layout makeApiRequest(String url) throws UnirestException, MalformedURLException {

    final HttpResponse<String> stringHttpResponse;

    // This will throw MalformedURLException if the url is malformed.
    new URL(url);

    stringHttpResponse = Unirest.get(url).asString();
    // Check to see if the request was successful; if so, convert the payload JSON into Java objects
    Layout layoutMap = null;

    if (stringHttpResponse.getStatus() == AdventureConstants.STATUS_OK) {
      String json = stringHttpResponse.getBody();
      Gson gson = new Gson();
      layoutMap = gson.fromJson(json, Layout.class);
    }

    return layoutMap;
  }

  /**
   * Finds room from name.
   *
   * @return finds room based on its name
   */
  public Room findRoom(String roomName) {

    Room newRoom = null;
    ArrayList<Room> roomList = getRooms();

    // loops through all rooms
    for (Room room : roomList) {

      // checks if room name matches desired room
      if (room.getName().equalsIgnoreCase(roomName)) {
        newRoom = room;
      }
    }
    return newRoom;
  }

  /**
   * Equals method for class Layout.
   *
   * @param o obejcts to e compared
   * @return boolean value from comparison
   */
  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (!(o instanceof Layout)) {
      return false;
    }

    Layout layout = (Layout) o;
    return Objects.equals(getStartingRoom(), layout.getStartingRoom())
        && Objects.equals(getEndingRoom(), layout.getEndingRoom())
        && Objects.equals(getRooms(), layout.getRooms())
        && Objects.equals(getPlayer(), layout.getPlayer());
  }
}
