import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;

public class GetJsonFile {

  private static final int STATUS_OK = 200;

  public static Layout makeApiRequest(String url) throws UnirestException, MalformedURLException {
    final HttpResponse<String> stringHttpResponse;

    // This will throw MalformedURLException if the url is malformed.
    new URL(url);

    stringHttpResponse = Unirest.get(url).asString();
    // Check to see if the request was successful; if so, convert the payload JSON into Java objects
    Layout layoutMap = null;
    if (stringHttpResponse.getStatus() == STATUS_OK) {
      String json = stringHttpResponse.getBody();
      Gson gson = new Gson();
      layoutMap = gson.fromJson(json, Layout.class);
    }
    return layoutMap;
  }
}
