package view;

import javafx.util.Duration;
import controller.GameController;
import controller.ReturnInfo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class : GameUI.java
 * @author: Kenneth Sales
 * @version: 1.0
 * Beta Software
 * Written: May 2017
 *
 * View class for MVC
 *
 */
public class GameUI extends Application
{
	GameController gc = new GameController();
	ReturnInfo returnInfo;
	
	Timeline animation;
	EventHandler<ActionEvent> eventHandler;
	
	// Main pane for primary Stage
	BorderPane bPane;

	// Buttons for right gridpane
	private Button btNorth;
	private Button btEast;
	private Button btSouth;
	private Button btWest;
	private Button btPlay;
	private Button btPause;
	private Button btReset;

	private Button btExit;

	private Button btMap;

	private TextArea taCenter;

	Image image;
	private ImageView iv = new ImageView();
	
	private void createMainBorderPane()
	{
		// Instantiate a BorderPane as the main pane
		bPane = new BorderPane();
		bPane.setPadding(new Insets(20, 20, 20, 20));
		bPane.setPrefSize(800.0, 600.0);
		bPane.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");

		// Place the title in the top center of the BorderPane
		Label label = new Label("Aftermath");
		label.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 30));
		label.setTextFill(Color.MEDIUMTURQUOISE);
		HBox hbox = new HBox();
		hbox.getChildren().add(label);
		hbox.setPrefHeight(50);
		hbox.setAlignment(Pos.TOP_CENTER);
		BorderPane.setAlignment(label, Pos.CENTER);
		bPane.setTop(hbox);
	}

	private void createNavigationPane()
	{
		GridPane navPane = new GridPane();
		navPane.setPrefWidth(200);
		navPane.setPadding(new Insets(10, 10, 10, 10));
		navPane.setHgap(10);
		navPane.setVgap(10);
		navPane.setStyle("-fx-border-width: 1px; -fx-border-color: turquoise;");

		btNorth = new Button("North");
		btNorth.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btNorth.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btNorth.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btNorth, HPos.CENTER);
		navPane.add(btNorth, 1, 0);
		btNorth.setVisible(false);
		
		btEast = new Button("East");
		btEast.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btEast.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btEast.setTextFill(Color.MEDIUMTURQUOISE);
		navPane.add(btEast, 2, 1);
		btEast.setVisible(false);
		
		btSouth = new Button("South");
		btSouth.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btSouth.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btSouth.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btSouth, HPos.CENTER);
		btSouth.setVisible(false);
		navPane.add(btSouth, 1, 2);
		
		btWest = new Button("West");
		btWest.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btWest.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btWest.setTextFill(Color.MEDIUMTURQUOISE);
		navPane.add(btWest, 0, 1);
		btWest.setVisible(false);

		btExit = new Button("Exit");
		btExit.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btExit.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btExit.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btExit, HPos.CENTER);
		navPane.add(btExit, 1, 8);

		btMap = new Button("Map");
		btMap.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btMap.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btMap.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btMap, HPos.CENTER);
		navPane.add(btMap, 0, 6);
		
		btPlay = new Button("Play");
		btPlay.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btPlay.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btPlay.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btPlay, HPos.CENTER);
		navPane.add(btPlay, 0, 4);
		
		btPause = new Button("Pause");
		btPause.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btPause.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btPause.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btPause, HPos.CENTER);
		navPane.add(btPause, 1, 4);	
		
		btReset = new Button("Reset");
		btReset.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;");
		btReset.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btReset.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btReset, HPos.CENTER);
		navPane.add(btReset, 2, 4);
		
		bPane.setRight(navPane);
	}


	private void createCenterPane()
	{
		try
		{
			String temp2 = "image0";
			String temp = "file:" + temp2 + ".jpg";
			image = new Image(temp, 500, 0, true, false);
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("something wrong");
		}
		iv.setImage(image);
		bPane.setCenter(iv);
	}

	private void createBottomPane()
	{
		taCenter = new TextArea("Welcome to Pursuit!\n");
		taCenter.setPrefSize(100, 250);
		//taCenter.setPadding(new Insets(5, 5, 5, 5));
		taCenter.setWrapText(true);
		taCenter.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px;  -fx-background-color: black; -fx-font-size: 12; -fx-control-inner-background: black;");
		taCenter.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 12)); // This is only changing the font. Weight and Posture does not change.
		//Cannot solve how to change text area background to Black or dark grey
		//Cannot solve hot to change text area text color to turquoise
		//Issue when clicking inside text area, font changes back to standard. 
		bPane.setBottom(taCenter);
	}

	private void setVisibleButtons()
	{		
		if(returnInfo.getPlayerRoom().getHasNorthRoom())
		{
			btNorth.setVisible(true);
		}
		else
		{
			btNorth.setVisible(false);
		}
		if(returnInfo.getPlayerRoom().getHasEastRoom())
		{
			btEast.setVisible(true);
		}
		else
		{
			btEast.setVisible(false);
		}

		if(returnInfo.getPlayerRoom().getHasSouthRoom())
		{
			btSouth.setVisible(true);
		}
		else
		{
			btSouth.setVisible(false);
		}
		if(returnInfo.getPlayerRoom().getHasWestRoom())
		{
			btWest.setVisible(true);
		}
		else
		{
			btWest.setVisible(false);
		}
	}

	private void navigate(String direction) // throws SQLException
	{
		returnInfo = gc.commandControl(direction);
		if(!(returnInfo.getMessage().equals("")))
		{
			taCenter.appendText(returnInfo.getMessage());
			String temp = "file:" + returnInfo.getPlayerRoom().getImageName() + ".jpg";
			image = new Image(temp, 500, 0, true, false);
			iv.setImage(image);
		}
		setVisibleButtons();
	}
	
	private void buttonActions()
	{		
		btNorth.setOnAction(e ->
		{
			navigate("north");
		});
		btEast.setOnAction(e ->
		{
			navigate("east");
		});

		btSouth.setOnAction(e ->
		{
			navigate("south");
		});

		btWest.setOnAction(e ->
		{
			navigate("west");
		});

		btExit.setOnAction(e ->
		{
			Platform.exit();
		});

		btMap.setOnAction(e ->
		{
			returnInfo = gc.commandControl("map");
			taCenter.appendText(returnInfo.getMessage());
		});
		
		EventHandler<KeyEvent> eventHandler2 = e -> 
		{	
			switch (e.getCode())
			{
			case UP:
			{
				navigate("north");
				setVisibleButtons();			
			}
				break;
			case LEFT:
			{
				navigate("west");
				setVisibleButtons();			
			}
				break;
			case DOWN:
			{
				navigate("south");
				setVisibleButtons();			
			}
				break;			
			case RIGHT:
			{
				navigate("east");
				setVisibleButtons();				
			}
			break;
			default:
				break;
			}
		};
		
		btPlay.setOnAction(e -> 
		{
			animation.play();
			returnInfo = gc.commandControl("map");
			taCenter.appendText(returnInfo.getMessage());
			taCenter.setOnKeyPressed(eventHandler2);
			taCenter.requestFocus();
			setVisibleButtons();
		});
		
		btPause.setOnAction(e ->
		{
			animation.pause();
			btNorth.setVisible(false);
			btEast.setVisible(false);
			btSouth.setVisible(false);
			btWest.setVisible(false);
		});
		
		btReset.setOnAction(e ->
		{
			returnInfo = gc.reset();
			taCenter.appendText("\n" + returnInfo.getMessage());
		});
	}
	
	private void runAnimation()
	{
		// Event handler for animation
		eventHandler = e -> 
		{	
			returnInfo = gc.movePrey();
			taCenter.appendText(returnInfo.getMessage()); 
			if (returnInfo.getPlayerRoom().getRoomID() == returnInfo.getPreyRoom().getRoomID())
			{
				taCenter.appendText("YOU GOT CAUGHT!!!");
				animation.stop();
				btNorth.setVisible(false);
				btEast.setVisible(false);
				btSouth.setVisible(false);
				btWest.setVisible(false);
			}
		};
	
		// Instantiate animation and set parameters to start game
		animation = new Timeline(new KeyFrame(Duration.millis(4000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);		
	}
	

	/**
	 * Method: start
	 * Purpose: start of the Game Demo application
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Create main BorderPane including title
		createMainBorderPane();

		// Create Navigation Pane on right using GridPane
		createNavigationPane();

		// Create Bottom Pane
		createBottomPane();

		// Create Center Image Pane
		createCenterPane();
		
		// Define button actions
		buttonActions();
		
		// method for prey animation
		runAnimation();
				
		// Create a scene and place it in the stage
		Scene scene = new Scene(bPane);
		primaryStage.setTitle("Aftermath"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage

	}

}
