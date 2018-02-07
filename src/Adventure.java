import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Adventure {

  public Layout layout;
  public Room currentRoom;
  private ArrayList<String> collectedItems;
  public ArrayList<String> possibleItems;
  public ArrayList<Direction> possibleDirections;

  /**
   * Loads json file from specified URL
   *
   * @param setUrl sets URL to specified value
   */
  private Layout loadGame(String setUrl) {
    try {

      //
      layout = Layout.makeApiRequest(setUrl);
      System.out.println("\n");

    } catch (UnirestException e) {

      // e.printStackTrace();
      System.out.println("Network not responding");
    } catch (MalformedURLException e) {

      System.out.println("Bad URL: " + setUrl);
    }

    return layout;
  }

  /**
   * Initializes an object of type Adventure
   *
   * @param url takes String URL as parameter
   */
  Adventure(String url) {

    // loads layout with respect to default or specified URl
    this.layout = loadGame(url);
    this.currentRoom = findRoom(layout.getStartingRoom());
    this.collectedItems = new ArrayList<>();
    this.possibleItems = currentRoom.getItems();
    this.possibleDirections = currentRoom.getDirections();
  }

  /**
   * Main method
   *
   * @param args takes an array of Strings as a parameter
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  public static void main(String[] args) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";

    // asks user for choice of game
    System.out.println("Would you like to play the default game? (Yes / No)");
    String userInput = input.readLine();

    // repeats as long as input isn't 'yes' or 'no'
    while (!userInput.equalsIgnoreCase("Yes") && !userInput.equalsIgnoreCase("No")) {

      // prompts user to enter valid option
      System.out.println("Invalid Choice! Please enter a valid option.");
      userInput = input.readLine();
    }

    // checks if user input was No
    if (userInput.equalsIgnoreCase("No")) {

      // asks user for file URL
      System.out.println("Please enter a URL for Json file");
      url = input.readLine();
    }

    // creates an object of type Adventure
    Adventure game = new Adventure(url);

    // calls methods to begin playing game
    game.playGame();
  }

  /**
   * class used to play game
   *
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  private void playGame() throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    boolean startGame = false;
    boolean endGame = false;
    boolean invalidAction = false;

    // checks boolean value for game to end
    while (!endGame) {

      // checks if current room is the ending room
      if (currentRoom.getName().equalsIgnoreCase(layout.getEndingRoom())) {
        System.out.println("You have reached your final destination.");

        // ends game if condition is met
        break;
      }

      // prints room description
      System.out.println(currentRoom.getDescription());

      // checks if game has started
      if (!startGame) {
        System.out.println("Your journey begins here.");
        startGame = true;
      }

      // handles possible null pointer exception
      try {

        System.out.println("This room contains: " + itemArrayListToString(currentRoom.getItems()));
      } catch (NullPointerException e) {

        System.out.println("This room contains nothing.");
      }

      // displays possible directions
      System.out.println(directionArrayListToString(possibleDirections));

      String userInput = input.readLine();

      // executes loop at-least once, based on user input
      do {

        // splits userInput by whitespace
        String[] inputWords = userInput.trim().split(" ");

        // checks if first word was "Go" and if second word was a valid direction
        if (inputWords[0].equalsIgnoreCase("Go") && checkDirection(inputWords[1])) {

          invalidAction = false;

          // updates current room based on input direction
          changeRoom(inputWords[1]);

          // updates possible directions based on current room
          setPossibleDirections(currentRoom.getDirections());

          // checks if first word was "Take" and if second word was a valid item
        } else if (inputWords[0].equalsIgnoreCase("Take")
            && checkItem(inputWords[1], currentRoom.getItems())) {

          invalidAction = false;

          // adds item to collected items
          collectedItems.add(findItem(inputWords[1], currentRoom.getItems()));

          // removes item from possible items
          currentRoom.getItems().remove(findItem(inputWords[1], currentRoom.getItems()));

          // updates possible items to reflects changes performed above
          setPossibleItems(currentRoom.getItems());

          // checks if first word was "Drop" and if second word was a valid item
        } else if (inputWords[0].equalsIgnoreCase("Drop")
            && checkItem(inputWords[1], collectedItems)) {

          invalidAction = false;

          // adds item to possible items
          currentRoom.getItems().add(findItem(inputWords[1], collectedItems));

          // removes item from collected items
          collectedItems.remove(findItem(inputWords[1], collectedItems));

          // updates collected and possible items to reflects changes performed above
          setPossibleItems(currentRoom.getItems());

          // checks if the first word was "List"
        } else if (inputWords[0].equalsIgnoreCase("list")) {

          invalidAction = false;

          // prints formatted list of items
          System.out.println("You are carrying " + itemArrayListToString(collectedItems));

          // checks if the first word was "Exit" or "Quit"
        } else if (inputWords[0].equalsIgnoreCase("Exit")
            || inputWords[0].equalsIgnoreCase("Quit")) {

          endGame = true;
          break;

        } else {

          // in case of invalid input
          System.out.println("I can't " + userInput);
          invalidAction = true;
          userInput = input.readLine();
        }

        // continues loop while user input is invalid
      } while (invalidAction);
    }
  }

  /** @return current room value returned */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  private void setPossibleItems(ArrayList<String> itemList) {

    // updates list of possible items
    possibleItems = itemList;
  }

  private void setPossibleDirections(ArrayList<Direction> directionList) {

    // updates list of possible directions
    this.possibleDirections = directionList;
  }

  /**
   * @param itemList ArrayList of items
   * @return return formatted String of possible items
   */
  public String itemArrayListToString(ArrayList<String> itemList) {

    // creates a new instance of type String Builder
    StringBuilder itemString = new StringBuilder();

    // checks if list of items is null
    if (itemList == null || itemList.isEmpty()) {
      itemString.append(AdventureConstants.EMPTY_ITEM_LIST);
    }

    // checks of list of items has a single item
    else if (itemList.size() == 1) {
      itemString.append(itemList.get(itemList.size() - 1));
      itemString.append(".");
    }

    // if list of items has multiple items
    else {

      // loops through all items and appends to String Builder
      for (int i = 0; i < itemList.size() - 1; i++) {
        itemString.append(itemList.get(i));
        itemString.append(", ");
      }

      // appends formatted string onto String Builder
      itemString.append("and ");
      itemString.append(itemList.get(itemList.size() - 1));
    }
    return itemString.toString();
  }

  /**
   * @param directionList ArrayList of directions
   * @return formatted String of possible directions
   */
  public String directionArrayListToString(ArrayList<Direction> directionList) {

    // creates a new instance of type String Builder
    StringBuilder directionString = new StringBuilder();

    directionString.append("From here, you can go: ");

    // checks if list of directions is null
    if (directionList == null) {
      directionString.append(AdventureConstants.EMPTY_DIRECTION_LIST);

      // checks if list of directions has a single direction
    } else if (directionList.size() == 1) {

      directionString.append(directionList.get(directionList.size() - 1).getDirectionName());

      // if list of directions has multiple items
    } else {

      // loops through all directions and appends to String Builder
      for (int i = 0; i < directionList.size() - 1; i++) {
        directionString.append(directionList.get(i).getDirectionName());
        directionString.append(", ");
      }

      // appends formatted string onto String Builder
      directionString.append("or ");
      directionString.append(directionList.get(directionList.size() - 1).getDirectionName());
    }
    return directionString.toString();
  }

  /**
   * @param inputDirection user specified input direction
   * @return boolean value of direction contained in list
   */
  public boolean checkDirection(String inputDirection) {

    if (inputDirection == null || inputDirection.isEmpty()) {
      return false;
    }

    // loops through all directions
    for (Direction direction : currentRoom.getDirections()) {

      // checks if name of direct exists in list of possible directions
      if (inputDirection.equalsIgnoreCase(direction.getDirectionName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param inputString item name
   * @param itemList list of possible items
   * @return boolean value of items contained in list
   */
  public boolean checkItem(String inputString, ArrayList<String> itemList) {

    // checks if list of items is null or empty
    if (itemList == null || itemList.isEmpty()) {
      return false;
    }

    // loops through all items in the list of items
    for (String item : itemList) {

      // checks if name of item exists in list of items
      if (inputString.equalsIgnoreCase(item)) {
        return true;
      }
    }
    return false;
  }

  /** @param inputDirection name of user specified direction */
  public boolean changeRoom(String inputDirection) {
    String roomWanted = "";
    for (Direction direction : currentRoom.getDirections()) {
      // find the room that is under the specific direction
      if (direction.getDirectionName().toLowerCase().equals(inputDirection)) {
        roomWanted = direction.getRoom();
      }
    }
    if (roomWanted.isEmpty()) {
      return false;
    }
    currentRoom = findRoom(roomWanted);
    return true;
  }

  /**
   * @param inputString name of user specified item
   * @param itemList list of items
   * @return item to e added / removed from list of items
   */
  public String findItem(String inputString, ArrayList<String> itemList) {

    // checks if inputString is empty or null
    if (inputString == null || inputString.isEmpty()) {
      return null;
    }

    // checks if itemList is empty or null
    if (itemList == null || itemList.isEmpty()) {
      return null;
    }
    // loops through all items in list of items
    for (String item : itemList) {

      // checks if name of item exists in list of items
      if (inputString.equalsIgnoreCase(item)) {

        return item;
      }
    }
    return null;
  }

  /** @return finds room absed on name */
  public Room findRoom(String roomName) {
    Room newRoom = null;
    ArrayList<Room> roomList = layout.getRooms();

    // loops through all rooms
    for (Room room : roomList) {

      // checks if room name matches desired room
      if (room.getName().equalsIgnoreCase(roomName)) {
        newRoom = room;
      }
    }
    return newRoom;
  }

  /** @return boolean value true if all rooms are connected */
  public boolean planValidator() {

    // loops through all rooms
    for (Room room : layout.getRooms()) {

      // loops through all directions
      for (Direction direction : room.getDirections()) {
        boolean pathContained = false;
        Room nextRoom = findRoom(direction.getRoom());

        // parses through all directions for
        for (Direction nextDirection : nextRoom.getDirections()) {

          // checks for equality
          if (nextDirection.getRoom().equalsIgnoreCase(room.getName())) {
            pathContained = true;
          }
        }

        // if path not found
        if (!pathContained) {
          return false;
        }
      }
    }
    return true;
  }

  ArrayList<Room> visited = new ArrayList<>();

  public boolean mapValidator(Room room) {

    // loops through all directions
    for (Direction direction : room.getDirections()) {
      Room nextRoom = findRoom(direction.getRoom());
      boolean validity;
      validity = mapValidator(nextRoom);

      if (nextRoom.getName().equalsIgnoreCase(layout.getEndingRoom())) {
        return true;
      }

      // checks for validity
      if (validity) {
        visited.add(nextRoom);
        return true;
      }
      // checks for equality
    }
    return true;
  }
}
