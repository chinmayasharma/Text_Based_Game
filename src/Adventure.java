import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class Adventure {

  private Layout layout;
  public Room currentRoom;

  private boolean startGame = false;
  private boolean endGame = false;
  private boolean engaged = false;
  private boolean invalidAction = false;
  private boolean print = true;

  private static Gson gson = new Gson();

  public Player player;

  /**
   * Loads json file from specified URL.
   *
   * @param setUrl sets URL to specified value
   */
  private Layout loadGame(String setUrl) {
    try {

      // loads game from given URL
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
   * Initializes an object of type Adventure.
   *
   * @param layout takes layout object as parameter
   */
  Adventure(Layout layout) {
    this.layout = layout;
    this.currentRoom = layout.findRoom(layout.getStartingRoom());
    this.player = layout.getPlayer();
  }

  /**
   * Initializes an object of type Adventure.
   *
   * @param url takes String URL as parameter
   */
  private Adventure(String url) {

    // loads layout with respect to default or specified URl
    this.layout = loadGame(url);
    this.currentRoom = layout.findRoom(layout.getStartingRoom());
    this.player = layout.getPlayer();
  }

  /**
   * Main method.
   *
   * @param args takes an array of Strings as a parameter
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  public static void main(String[] args) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Adventure game;

    // checks for presence of command line arguments
    if (args.length > 0) {

      // extracts layout from specified file
      Layout expectedLayout =
          gson.fromJson(AdventureConstants.getFileContentsAsString(args[0]), Layout.class);
      // creates an object of type Adventure
      game = new Adventure(expectedLayout);

      // in the absence of command lien arguments, allows user to specify URL
    } else {

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
      game = new Adventure(url);
    }

    // calls methods to begin playing game
    game.playGame();
  }

  /**
   * class used to play game.
   *
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  private void playGame() throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // checks boolean value for game to end
    while (!endGame) {

      // checks if game has already started
      if (!startGame) {
        System.out.println(AdventureConstants.BEGIN_MESSAGE);
        startGame = true;
      }
      // checks if current room is the same as the final destination
      if (currentRoom.getName().equalsIgnoreCase(layout.getEndingRoom())) {
        System.out.println(AdventureConstants.END_MESSAGE);
        endGame = true;
        break;
      }

      // prints information of room when desired
      if (print) {
        currentRoom.roomInfo();
      }

      // takes user input for actions
      String userInput = reader.readLine();
      actionHandler(userInput);
    }
  }

  /**
   * Handles regular user input.
   *
   * @param input takes user input as a parameter
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  private void actionHandler(String input) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Monster monster;
    // executes loop at-least once, based on user input
    do {

      String[] words;
      words = input.trim().split(" ");

      boolean hasMonster = currentRoom.hasMonster();

      if (inputLength(words, 2)
          && words[0].equalsIgnoreCase("Duel")
          && currentRoom.checkMonster(words[1])) {

        monster = currentRoom.findMonster(words[1]);

        engaged = true;
        invalidAction = false;
        print = true;

        System.out.println("You picked a fight with the " + monster.getName());
        attackHandler(monster);

      } else if (inputLength(words, 2)
          && words[0].equalsIgnoreCase("Go")
          && currentRoom.checkDirection(words[1])) {

        invalidAction = false;

        if (!hasMonster
            && changeRoom(words[1])) { // updates possible directions based on current room

          currentRoom.setDirections(currentRoom.getDirections());
          print = true;

        } else {
          System.out.println(AdventureConstants.CANT_MOVE);
          print = false;
        }

      } else if (inputLength(words, 2)
          && words[0].equalsIgnoreCase("Take")
          && currentRoom.checkItem(words[1])) {

        invalidAction = false;
        print = false;

        if (!hasMonster) {

          Item item = currentRoom.findItem(words[1]);

          player.getItems().add(item);
          currentRoom.getItems().remove(item);

        } else {

          System.out.println(AdventureConstants.CANT_TAKE);
        }

      } else if (inputLength(words, 2)
          && words[0].equalsIgnoreCase("Drop")
          && player.checkItem(words[1])) {

        invalidAction = false;
        print = false;

        Item item = player.findItem(words[1]);

        currentRoom.getItems().add(item);
        player.getItems().remove(item);

      } else if (words[0].equalsIgnoreCase("list")) {

        invalidAction = false;
        print = false;

        System.out.println(player.itemString());

      } else if (words[0].equalsIgnoreCase("Playerinfo")) {

        player.displayInfo();
        print = false;

      } else if (words[0].equalsIgnoreCase("Exit") || words[0].equalsIgnoreCase("Quit")) {

        endGame = true;
        break;

      } else {

        System.out.println("I can't " + input);
        invalidAction = true;
        print = false;
        input = reader.readLine();
      }

      // continues loop while user input is invalid
    } while (invalidAction);
  }

  /**
   * Handles user input for attacks.
   *
   * @param monster takes a monster as a parameter
   * @throws IOException exception is thrown (mandated by BufferedReader syntax)
   */
  private void attackHandler(Monster monster) throws IOException {

    // creates an object of type BufferedReader that will read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    do {

      String input = reader.readLine();
      // splits userInput by whitespace
      String[] words = input.trim().split(" ");

      if (words[0].equalsIgnoreCase("Attack")
          && inputLength(words, 3)
          && player.checkItem(words[2])) {

        attackWithItem(monster, words[2]);

      } else if (words[0].equalsIgnoreCase("Attack") && inputLength(words, 1)) {

        attack(monster);

      } else if (engaged && words[0].equalsIgnoreCase("Status")) {

        player.getStatus();
        monster.getStatus();

      } else if (engaged && words[0].equalsIgnoreCase("Disengage")) {

        engaged = false;

      } else if (words[0].equalsIgnoreCase("list")) {

        // prints formatted list of items
        System.out.println(player.itemString());

        // checks if the first word was "Exit" or "Quit"
      } else if (words[0].equalsIgnoreCase("Playerinfo")) {

        player.displayInfo();

      } else if (words[0].equalsIgnoreCase("Exit") || words[0].equalsIgnoreCase("Quit")) {

        engaged = false;
        endGame = true;
        break;

      } else {

        System.out.println("I can't " + input);
      }

    } while (engaged);
  }

  /**
   * Changes room based on direction.
   *
   * @param inputDirection name of user specified direction
   */
  boolean changeRoom(String inputDirection) {
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

    currentRoom = layout.findRoom(roomWanted);
    return true;
  }

  /**
   * Checks if length of string array matches desired length.
   *
   * @param input takes an array of Strings as input
   * @param specifiedLength desired length of array
   * @return whether the array was of desired size or not
   */
  private boolean inputLength(String[] input, int specifiedLength) {

    return (input != null && input.length == specifiedLength);
  }

  /**
   * Attacks monster.
   *
   * @param monster monster object to be attacked
   */
  private void attack(Monster monster) {

    double damage = player.getAttack() - monster.getDefense();

    if (damage < 0) {
      damage = 0;
    }
    monster.setHealth(monster.getHealth() - damage);

    counterAttack(monster);
  }

  /**
   * Attacks monster with item.
   *
   * @param monster monster object to be attacked
   * @param itemName name of item to attack with
   */
  private void attackWithItem(Monster monster, String itemName) {

    Item item = player.findItem(itemName);
    double damage = player.getAttack() + item.getDamage() - monster.getDefense();

    if (damage < 0) {
      damage = 0;
    }
    monster.setHealth(monster.getHealth() - damage);
    player.getItems().remove(item);

    counterAttack(monster);
  }

  /**
   * Facilitates counter-attack.
   *
   * @param monster monster object to be evaluated
   */
  private void counterAttack(Monster monster) {
    //
    if (monster.getHealth() > 0.0) {
      double damage = monster.getAttack() - player.getDefense();
      if (damage < 0) {
        damage = 0;
      }
      player.setHealth(player.getHealth() - damage);

      //
      if (player.getHealth() <= 0.0) {
        System.out.println(AdventureConstants.DEATH_MESSAGE);
        endGame = true;
      }
    } else {

      double expGained = (monster.getAttack() + monster.getDefense() + monster.getHealth());
      player.setExperience(player.getExperience() + expGained);

      player.levelUp();

      System.out.println("You killed the " + monster.getName());
      currentRoom.getMonstersInRoom().remove(monster);

      if (!currentRoom.getMonstersInRoom().contains(monster)) {

        engaged = false;
        invalidAction = false;
        print = true;
      }
    }
  }
}
