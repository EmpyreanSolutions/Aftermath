package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class : GameUI.java
 * 
 * @author: Kenneth Sales, Daniel Sales
 * @version: 2.0 Beta Software Written: May 2017 Major Rework 26 June 2017 View
 *           class for Aftermath
 *
 */
public class GameUI extends Application
{
	/**
	 * Method: start Purpose: start of the Game Demo application
	 * 
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		new MainMenuGUI();
	}
}
