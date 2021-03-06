import java.io.File;
import view.GameFXStart;;

/** Class : GameStart.java
 * @author: Kenneth Sales
 * @version: 1.0
 * Beta Software
 * Written: June 2017
 * Deleted extends Application and start method June 6, 2017
 *
 * This class is the launch point for game
 */
public class GameStart
{
	public static void main(String[] args)
	{
		File dbFile = new File("Aftermath.db");
		if (!dbFile.exists())
		{
			System.out.println("Aftermath.db does not exists.");
			System.exit(0);
		}

		GameFXStart.launch(GameFXStart.class);

	}
}
