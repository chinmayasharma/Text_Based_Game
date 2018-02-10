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
  private ArrayList<Room> rooms = null;

  public String getStartingRoom() {
    return startingRoom;
  }

  public void setStartingRoom(String startingRoom) {
    this.startingRoom = startingRoom;
  }

  public Layout withStartingRoom(String startingRoom) {
    this.startingRoom = startingRoom;
    return this;
  }

  public String getEndingRoom() {
    return endingRoom;
  }

  public void setEndingRoom(String endingRoom) {
    this.endingRoom = endingRoom;
  }

  public Layout withEndingRoom(String endingRoom) {
    this.endingRoom = endingRoom;
    return this;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Layout withPlayer(Player player) {
    this.player = player;
    return this;
  }

  public ArrayList<Room> getRooms() {
    return rooms;
  }

  public void setRooms(ArrayList<Room> rooms) {
    this.rooms = rooms;
  }

  public Layout withRooms(ArrayList<Room> rooms) {
    this.rooms = rooms;
    return this;
  }

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
   * @param o obejcts to e compared
   * @return boolean value from comparison
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Layout)) return false;
    Layout layout = (Layout) o;
    return Objects.equals(getStartingRoom(), layout.getStartingRoom())
        && Objects.equals(getEndingRoom(), layout.getEndingRoom())
        && Objects.equals(getRooms(), layout.getRooms())
        && Objects.equals(getPlayer(), layout.getPlayer());
  }
}
