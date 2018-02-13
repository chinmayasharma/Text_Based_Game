import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class AdventureConstants {

  public static String LOAD_GAME_MESSAGE = "Your game has been loaded.";
  public static String BEGIN_MESSAGE = "Your journey begins here.";
  public static String END_MESSAGE = "You have reached your final destination.";
  public static String ROOM_MESSAGE = "This room contains ";
  public static String EMPTY_ITEM_LIST = "nothing";
  public static String EMPTY_MONSTER_LIST = "no monster";
  public static String EMPTY_DIRECTION_LIST = "nowhere";
  public static String DEATH_MESSAGE = "You have died.";
  public static String CANT_MOVE = "There are still monsters here, I can’t move.";
  public static String CANT_TAKE = "There are still monsters here, I can’t take that.";
  public static String LEVEL_UP = "LEVEL UP!!!. You are now Level ";
  public static final int STATUS_OK = 200;

  /**
   * Reads contents of a file located in the project's 'data' directory into a String.
   *
   * @param filename contains the name of file
   * @return a String containing the file's contents
   */
  public static String getFileContentsAsString(String filename) {

    // Java uses Paths as an operating system-independent specification of the location of files.
    // In this case, we're looking for files that are in a directory called 'data' located in the
    // root directory of the project, which is the 'current working directory'.
    final Path path = FileSystems.getDefault().getPath("data", filename);

    try {

      // Read all of the bytes out of the file specified by 'path' and then convert those bytes
      // into a Java String.  Because this operation can fail if the file doesn't exist, we
      // include this in a try/catch block
      return new String(Files.readAllBytes(path));

    } catch (IOException e) {

      // Since we couldn't find the file, there is no point in trying to continue.  Let the
      // user know what happened and exit the run of the program.  Note: we're only exiting
      // in this way because we haven't talked about exceptions and throwing them in CS 126 yet.
      System.out.println("Couldn't find file: " + filename);
      System.exit(-1);

      return null; // note that this return will never execute, but Java wants it there.
    }
  }
}
