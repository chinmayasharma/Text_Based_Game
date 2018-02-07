import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Layout {
  @SerializedName("startingRoom")
  private String startingRoom;

  @SerializedName("endingRoom")
  private String endingRoom;

  @SerializedName("rooms")
  private ArrayList<Room> rooms = new ArrayList<>();

  /** @return name of starting room */
  public String getStartingRoom() {
    return startingRoom;
  }

  /** @return name of endingRoom */
  public String getEndingRoom() {
    return endingRoom;
  }

  /** @return list of rooms */
  public ArrayList<Room> getRooms() {
    return rooms;
  }

  /**
   * @param url link to access file from
   * @return ojejct of type layout
   * @throws UnirestException library exception thrown
   * @throws MalformedURLException thrown if URL is invalid
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
        && Objects.equals(getRooms(), layout.getRooms());
  }
}
