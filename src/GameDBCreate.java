
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;
//import controller.GameController;
import model.SQLiteDB;

/**
 * Class : GameDBCreate
 * @author: Kenneth Sales
 * @version: 2.0
 * Course:
 * Written: June 3, 2017
 *
 * This class creates the Game db if it doesn't exist
 *
 * Purpose: Create a DB
 */
public class GameDBCreate
{
	private SQLiteDB sdb;
	private FileReader fr;
	private Scanner input;

	public GameDBCreate()
	{
		try
		{
			sdb = new SQLiteDB();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Method: buildRoom
	 * Purpose: Build the Room table and load data
	 * @throws SQLException
	 * @return void
	 */
	public void buildRooms() throws SQLException
	{
		System.out.println("Creating Rooms");
		String sql = "CREATE TABLE Rooms(RoomID INT Primary Key NOT NULL, roomName text NOT NULL, "
				+ "imageName text NOT NULL, "
				+ "visited int NOT NULL, "
				+ "northRoom int NOT NULL, eastRoom int NOT NULL, southRoom int NOT NULL, "
				+ "westRoom int NOT NULL, roomDescription text NOT NULL)";
		sdb.updateDB(sql);

		try
		{
			fr = new FileReader(new File("Rooms.txt"));
			input = new Scanner(fr);
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				String[] split = line.split("\t");
				// System.out.println("0 " + split[0]);
				// System.out.println("1 " + split[1]);
				// System.out.println("2 " + split[2]);
				// System.out.println("3 " + split[3]);
				// System.out.println("4 " + split[4]);
				// System.out.println("5 " + split[5]);
				// System.out.println("6 " + split[6]);
				// System.out.println("7 " + split[7]);
				sql = "INSERT INTO Rooms(RoomID, roomName, imageName, visited, northRoom, eastRoom, southRoom, westRoom, roomDescription) "
						+ "VALUES(" + split[0] + ", \"" + split[1] + "\", \"" + split[2] + "\", " + split[3] + ", "
						+ split[4] + ", " + split[5] + ", " + split[6] + ", " + split[7] + ", \"" + split[8] + "\")";
				sdb.updateDB(sql);
			}
			sdb.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Input txt file not found.");
			System.exit(0);
		}
		System.out.println("Rooms completed");
	}


	public static void main(String[] args)
	{
		File dbFile = new File("Aftermath.db");
		if(dbFile.exists())
		{
			System.out.println("Please delete Aftermath.db and rerun program.");
		}
		else
		{
			GameDBCreate gdbc = new GameDBCreate();
			try
			{
				gdbc.buildRooms();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
