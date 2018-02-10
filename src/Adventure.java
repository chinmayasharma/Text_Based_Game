import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Adventure {

  public Layout layout;
  public Room currentRoom;
  private boolean startGame = false;
  private boolean endGame = false;
  private boolean isDueling = false;
  private ArrayList<Item> collectedItems;
  public ArrayList<Item> possibleItems;
  public ArrayList<Direction> possibleDirections;
  public ArrayList<Monster> possibleMonsters;
  public Player player;

  /**
   * Loads json file from specified URL
   *
   * @param setUrl sets URL to specified value
   */
  private Layout loadGame(String setUrl) {
    try {

      //
      layout = Layout.makeApiRequest(setUrl);
      System.out.println(AdventureConstants.LOAD_GAME_MESSAGE);
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
    this.collectedItems = layout.getPlayer().getItems();
    this.possibleItems = currentRoom.getItems();
    this.possibleDirections = currentRoom.getDirections();
    this.possibleMonsters = currentRoom.getMonstersInRoom();
    this.player = layout.getPlayer();
  }

  /**
   * Main method
   *
   * @param args takes an array of Strings as a parameter
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  public static void main(String[] args) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String url = "https://chinmayasharma.github.io/siebel.json";

    // asks user for choice of game
    System.out.println("Would you like to play the default game? (Yes / No)");
    String userInput = reader.readLine();

    // repeats as long as input isn't 'yes' or 'no'
    while (!userInput.equalsIgnoreCase("Yes") && !userInput.equalsIgnoreCase("No")) {

      // prompts user to enter valid option
      System.out.println("Invalid Choice! Please enter a valid option.");
      userInput = reader.readLine();
    }

    // checks if user input was No
    if (userInput.equalsIgnoreCase("No")) {

      // asks user for file URL
      System.out.println("Please enter a URL for Json file");
      url = reader.readLine();
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
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

        System.out.println("This room contains " + itemString(currentRoom.getItems()));
      } catch (NullPointerException e) {

        System.out.println("This room contains" + AdventureConstants.EMPTY_ITEM_LIST);
      }

      // handles possible null pointer exception
      try {

        System.out.println("This room contains " + monsterString(currentRoom.getMonstersInRoom()));
      } catch (NullPointerException e) {

        System.out.println("This room contains" + AdventureConstants.EMPTY_MONSTER_LIST);
      }

      // displays possible directions
      System.out.println(directionString(possibleDirections));

      String userInput = reader.readLine();
      actionHandler(userInput);
    }
  }

  /**
   * @param input takes user input as a parameter
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  private void actionHandler(String input) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Monster monster;
    boolean invalidAction = false;

    // executes loop at-least once, based on user input
    do {

      // splits userInput by whitespace
      String[] words = input.trim().split(" ");

      if (inputLength(words, 2)
          && words[0].equalsIgnoreCase("Duel")
          && (findMonster(words[1]) != null)) {

        monster = findMonster(words[1]);
        isDueling = true;
        invalidAction = false;
        System.out.println("You picked a fight with the " + monster.getName());
        attackHandler(monster);

      }

      //
      else if (words[0].equalsIgnoreCase("Disengage") && isDueling && inputLength(words, 1)) {

        isDueling = false;
        invalidAction = false;

      }

      // checks if first word was "Go" and if second word was a valid direction
      else if (inputLength(words, 2)
          && !hasMonster()
          && words[0].equalsIgnoreCase("Go")
          && changeRoom(words[1])) {

        invalidAction = false;

        // updates possible directions based on current room
        setPossibleDirections(currentRoom.getDirections());

        // checks if first word was "Take" and if second word was a valid item
      } else if (!isDueling
          && inputLength(words, 2)
          && words[0].equalsIgnoreCase("Take")
          && itemHandler(words[0], words[1], currentRoom.getItems())) {

        invalidAction = false;

        // updates possible items to reflects changes performed above
        setPossibleItems(currentRoom.getItems());

        // checks if first word was "Drop" and if second word was a valid item
      }

      //
      else if (!isDueling
          && inputLength(words, 2)
          && words[0].equalsIgnoreCase("Drop")
          && itemHandler(words[0], words[1], collectedItems)) {

        invalidAction = false;

        // updates collected and possible items to reflects changes performed above
        setPossibleItems(currentRoom.getItems());

        // checks if the first word was "List"
      }

      //
      else if (words[0].equalsIgnoreCase("list")) {

        invalidAction = false;

        // prints formatted list of items
        System.out.println("You are carrying " + itemString(collectedItems));

        // checks if the first word was "Exit" or "Quit"
      } else if (words[0].equalsIgnoreCase("Playerinfo")) {

        System.out.println("Player");

      } else if (words[0].equalsIgnoreCase("Exit") || words[0].equalsIgnoreCase("Quit")) {

        endGame = true;
        break;

      } else {

        // in case of invalid input
        System.out.println("I can't " + input);
        invalidAction = true;
        input = reader.readLine();
      }

      // continues loop while user input is invalid
    } while (invalidAction);
  }

  private void attackHandler(Monster monster) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    boolean invalidAction;

    // executes loop at-least once, based on user input
    do {

      String input = reader.readLine();
      // splits userInput by whitespace
      String[] words = input.trim().split(" ");

      if (words[0].equalsIgnoreCase("Attack") && attack(monster)) {

        isDueling = true;
        invalidAction = false;
      }

      //
      else if (words[0].equalsIgnoreCase("Attack")
          && inputLength(words, 3)
          && attackWithItem(monster, words[2])) {

        invalidAction = false;
      }

      //
      else if (isDueling && words[0].equalsIgnoreCase("Status")) {

        invalidAction = false;
        statusUpdate(player, monster);
      }

      //
      else if (isDueling && words[0].equalsIgnoreCase("Disengage")) {

        isDueling = false;
        invalidAction = false;

      } else {

        // in case of invalid input
        System.out.println("I can't " + input);
        invalidAction = true;
      }

      // continues loop while user input is invalid
    } while (invalidAction);
  }

  private void setPossibleItems(ArrayList<Item> itemList) {

    // updates list of possible items
    possibleItems = itemList;
  }

  private void setPossibleDirections(ArrayList<Direction> directionList) {

    // updates list of possible directions
    this.possibleDirections = directionList;
  }

  /**
   * @param directionList ArrayList of directions
   * @return formatted String of possible directions
   */
  public String directionString(ArrayList<Direction> directionList) {

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

  /** @param inputDirection name of user specified direction */
  public boolean changeRoom(String inputDirection) {
    String roomWanted = "";

    if (inputDirection == null || inputDirection.isEmpty()) {
      return false;
    }

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

  /** @return finds room based on its name */
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

  private boolean inputLength(String[] input, int specifiedLength) {

    return (input != null && input.length >= specifiedLength);
  }

  /**
   * @param itemList ArrayList of items
   * @return return formatted String of possible items
   */
  public String itemString(ArrayList<Item> itemList) {

    // creates a new instance of type String Builder
    StringBuilder itemString = new StringBuilder();

    // checks if list of items is null
    if (itemList == null || itemList.isEmpty()) {
      itemString.append(AdventureConstants.EMPTY_ITEM_LIST);
    }

    // checks of list of items has a single item
    else if (itemList.size() == 1) {
      itemString.append(itemList.get(itemList.size() - 1).getName());
      itemString.append(".");
    }

    // if list of items has multiple items
    else {

      // loops through all items and appends to String Builder
      for (int i = 0; i < itemList.size() - 1; i++) {
        itemString.append(itemList.get(i).getName());
        itemString.append(", ");
      }

      // appends formatted string onto String Builder
      itemString.append("and ");
      itemString.append(itemList.get(itemList.size() - 1).getName());
    }
    return itemString.toString();
  }

  /**
   * @param inputString name of user specified item
   * @param itemList list of items
   * @return item to e added / removed from list of items
   */
  public Item findItem(String inputString, ArrayList<Item> itemList) {

    // checks if inputString is empty or null
    if (inputString == null || inputString.isEmpty()) {
      return null;
    }

    // checks if itemList is empty or null
    if (itemList == null || itemList.isEmpty()) {
      return null;
    }
    // loops through all items in list of items
    for (Item item : itemList) {

      // checks if name of item exists in list of items
      if (inputString.equalsIgnoreCase(item.getName())) {

        return item;
      }
    }
    return null;
  }

  /**
   * @param action
   * @param itemName
   * @param itemList
   * @return
   */
  public boolean itemHandler(String action, String itemName, ArrayList<Item> itemList) {

    Item item;
    try {
      item = findItem(itemName, itemList);

      if (item == null) {
        return false;
      }

      if (action.equalsIgnoreCase("Take")) {
        // adds item to collected items
        collectedItems.add(item);

        // removes item from possible items
        currentRoom.getItems().remove(item);
      }

      if (action.equalsIgnoreCase("Drop")) {
        // adds item to collected items
        currentRoom.getItems().add(item);

        // removes item from possible items
        collectedItems.remove(item);
      }

      return true;

    } catch (NullPointerException e) {

      return false;
    }
  }

  /**
   * @param monsterList ArrayList of items
   * @return return formatted String of possible items
   */
  public String monsterString(ArrayList<Monster> monsterList) {

    // creates a new instance of type String Builder
    StringBuilder monsterString = new StringBuilder();

    // checks if list of items is null
    if (monsterList == null || monsterList.isEmpty()) {
      monsterString.append(AdventureConstants.EMPTY_MONSTER_LIST);
    }

    // checks of list of items has a single item
    else if (monsterList.size() < 2) {
      monsterString.append(monsterList.get(monsterList.size() - 1).getName());
      monsterString.append(".");
    } else {

      // loops through all items and appends to String Builder
      for (int i = 0; i < monsterList.size() - 1; i++) {
        monsterString.append(monsterList.get(i).getName());
        monsterString.append(", ");
      }

      // appends formatted string onto String Builder
      monsterString.append("and ");
      monsterString.append(monsterList.get(monsterList.size() - 1).getName());
    }
    return monsterString.toString();
  }

  private boolean hasMonster() {

    return (currentRoom.getMonstersInRoom().size() > 0);
  }

  public Monster findMonster(String monsterName) {

    // checks if inputString is empty or null
    if (monsterName == null || monsterName.isEmpty()) {
      return null;
    }

    ArrayList<Monster> monsterList = currentRoom.getMonstersInRoom();

    // checks if itemList is empty or null
    if (monsterList == null || monsterList.isEmpty()) {
      return null;
    }

    for (Monster monster : monsterList) {
      if (monsterName.equalsIgnoreCase(monster.getName())) {
        return monster;
      }
    }
    return null;
  }

  public boolean attack(Monster monster) {

    try {

      if (monster == null) {
        return false;
      }

      monster.setHealth(monster.getHealth() - player.getAttack());

      if (monster.getHealth() <= 0.0) {
        System.out.println("You killed " + monster.getName());
        currentRoom.getMonstersInRoom().remove(monster);

      } else {
        player.setHealth(player.getHealth() - monster.getAttack());
        if (player.getHealth() <= 0) {
          System.out.println(AdventureConstants.DEATH_MESSAGE);
          endGame = true;
        }
      }
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  public void statusUpdate(Player player, Monster monster) {

    int playerHealth = (int) ((player.getHealth() * 10) / player.getMaxHealth());
    int monsterHealth = (int) ((monster.getHealth() * 10) / monster.getMaxHealth());

    System.out.print("Player: ");
    for (int i = 0; i < playerHealth; i++) {
      System.out.print("#");
    }
    for (int i = 0; i < 10 - playerHealth; i++) {
      System.out.print("_");
    }

    System.out.println("\n");

    System.out.print("Monster: ");
    for (int i = 0; i < monsterHealth; i++) {
      System.out.print("#");
    }

    for (int i = 0; i < 10 - monsterHealth; i++) {
      System.out.print("_");
    }
  }

  public boolean attackWithItem(Monster monster, String itemName) {
    try {
      Item item = findItem(itemName, collectedItems);

      if (monster == null || item == null) {
        return false;
      }
      monster.setHealth(monster.getHealth() - player.getAttack() - item.getDamage());
      collectedItems.remove(item);
      if (monster.getHealth() <= 0.0) {
        System.out.println("You killed " + monster.getName());
        currentRoom.getMonstersInRoom().remove(monster);
      } else {
        player.setHealth(player.getHealth() - monster.getAttack());
        if (player.getHealth() <= 0.0) {
          System.out.println(AdventureConstants.DEATH_MESSAGE);
          endGame = true;
        }
      }

      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }
}
