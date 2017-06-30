package view;

import javafx.util.Duration;
import controller.GameController;
import controller.ReturnInfo;
import controller.Room;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameUI extends Stage
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
	private Button btUp;
	private Button btDown;
	private Button btPlay;
	private Button btPause;
	private Button btExit;
	private Button btMap;
	private Button btMenu;
	private Button btHelp;
	private Button btSound;
	private TextArea centerText;
	private Label topLabel;

	public GameUI()
	{
		createMainBorderPane();
		createNavigationPane();
		createBottomPane();
		createCenterPane();
		buttonActions();
		runAnimation();

		// Create a scene and place it in the stage
		Scene scene = new Scene(bPane, 1280, 720);
		this.setTitle("Aftermath"); // Set the stage title
		this.setScene(scene); // Place the scene in the stage
		this.setResizable(false);
		this.show(); // Display the stage
	}

	private void createMainBorderPane()
	{
		bPane = new BorderPane();
		bPane.setStyle("-fx-background-color: #111111;");

		topLabel = new Label(new Room().getRoom(1011).getRoomName());
		topLabel.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		topLabel.setTextFill(Color.MEDIUMTURQUOISE);
		Reflection labelReflection = new Reflection();
		labelReflection.setFraction(0.4f);
		topLabel.setEffect(labelReflection);

		HBox hbox = new HBox();
		hbox.getChildren().add(topLabel);
		hbox.setPrefHeight(70);
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-border-color: turquoise; -fx-border-radius: 5 5 5 5;");

		BorderPane.setAlignment(topLabel, Pos.CENTER);
		BorderPane.setMargin(hbox, new Insets(10, 250, 5, 250));
		bPane.setTop(hbox);
	}

	private void createNavigationPane()
	{

		GridPane navGrid = new GridPane();
		navGrid.setPrefWidth(200);
		navGrid.setPrefHeight(200);
		navGrid.setPadding(new Insets(10, 10, 10, 10));
		navGrid.setHgap(10);
		navGrid.setVgap(10);
		navGrid.setStyle(
				"-fx-border-width: 1px; -fx-border-color: turquoise;-fx-border-radius: 5 5 5 5;"
				+ " -fx-background-radius: 5 5 5 5");

		btNorth = new Button("North");
		btNorth.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btNorth.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btNorth.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btNorth, HPos.CENTER);
		navGrid.add(btNorth, 1, 0);
		btNorth.setVisible(false);

		btEast = new Button("East");
		btEast.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btEast.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btEast.setTextFill(Color.MEDIUMTURQUOISE);
		navGrid.add(btEast, 2, 1);
		btEast.setVisible(false);

		btSouth = new Button("South");
		btSouth.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btSouth.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btSouth.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btSouth, HPos.CENTER);
		btSouth.setVisible(false);
		navGrid.add(btSouth, 1, 2);

		btWest = new Button("West");
		btWest.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btWest.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btWest.setTextFill(Color.MEDIUMTURQUOISE);
		navGrid.add(btWest, 0, 1);
		btWest.setVisible(false);

		btUp = new Button("Up");
		btUp.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btUp.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btUp.setTextFill(Color.MEDIUMTURQUOISE);
		//navGrid.add(btUp, 0, 3);
		btUp.setVisible(false);

		btDown = new Button("Down");
		btDown.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btDown.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btDown.setTextFill(Color.MEDIUMTURQUOISE);
		//navGrid.add(btDown, 2, 3);
		btDown.setVisible(false);

		btExit = new Button("Exit");
		btExit.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btExit.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btExit.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btExit, HPos.CENTER);
		navGrid.add(btExit, 1, 9);

		btMap = new Button("Map");
		btMap.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btMap.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btMap.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btMap, HPos.CENTER);
		navGrid.add(btMap, 0, 7);

		btPlay = new Button("Play");
		btPlay.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btPlay.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btPlay.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btPlay, HPos.CENTER);
		navGrid.add(btPlay, 0, 5);

		btPause = new Button("Pause");
		btPause.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btPause.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btPause.setTextFill(Color.MEDIUMTURQUOISE);
		GridPane.setHalignment(btPause, HPos.CENTER);
		navGrid.add(btPause, 1, 5);
		
	
		HBox holder = new HBox();
		//holder.setStyle("-fx-border-color: turquoise; -fx-border-radius: 5 5 5 5;");
		//holder.setMaxHeight(100);
		holder.setSpacing(10);
		VBox cardinalVBox = new VBox();
		cardinalVBox.setPrefWidth(200);
		cardinalVBox.setPrefHeight(200);
		cardinalVBox.getChildren().add(navGrid);
		VBox verticalVBox = new VBox();
		verticalVBox.setPrefWidth(200);
		verticalVBox.setPrefHeight(200);
		verticalVBox.setStyle(
				"-fx-border-width: 1px; -fx-border-color: turquoise;-fx-border-radius: 5 5 5 5;"
				+ " -fx-background-radius: 5 5 5 5");
		verticalVBox.getChildren().addAll(btUp, btDown);
		
		holder.getChildren().addAll(cardinalVBox, verticalVBox);
		
		BorderPane.setMargin(holder, new Insets(10, 10, 300, 0));
		bPane.setRight(holder);
	}

	private void createCenterPane()
	{
		centerText = new TextArea("Welcome to Aftermath!\n");
		centerText.setPrefSize(600, 600);
		centerText.setWrapText(true);
		centerText.setEditable(false);
		centerText.setStyle(
				"-fx-border-color: turquoise; -fx-border-width: 1px;  -fx-background-color: black; -fx-font-size: 12;"
						+ " -fx-control-inner-background: black; -fx-text-inner-color: turquoise; -fx-highlight-fill: turquoise; "
						+ "-fx-highlight-text-fill: black; -fx-border-radius: 5 5 5 5;");
		centerText.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		
		ScrollBar scrollBarv = (ScrollBar)centerText.lookup(".scroll-bar:vertical");
		if(!(scrollBarv == null))
		scrollBarv.setOpacity(0);
		
		BorderPane.setMargin(centerText, new Insets(10, 10, 5, 10));
		bPane.setCenter(centerText);
	}

	private void createBottomPane()
	{
		btMenu = new Button("Menu");
		btMenu.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black; "
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btMenu.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btMenu.setTextFill(Color.MEDIUMTURQUOISE);

		btHelp = new Button("Help");
		btHelp.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black; "
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btHelp.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btHelp.setTextFill(Color.MEDIUMTURQUOISE);

		btSound = new Button("Sound");
		btSound.setStyle("-fx-border-color: turquoise; -fx-border-width: 1px; -fx-background-color: black;"
				+ "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5");
		btSound.setFont(Font.font("OCR A Std", FontWeight.NORMAL, FontPosture.REGULAR, 10));
		btSound.setTextFill(Color.MEDIUMTURQUOISE);

		HBox hbox = new HBox();
		hbox.setStyle("-fx-border-color: turquoise; -fx-border-radius: 5 5 5 5;" + " -fx-background-radius: 5 5 5 5;");
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setPadding(new Insets(5, 20, 5, 5));
		hbox.setPrefHeight(50);
		hbox.getChildren().addAll(btSound, btHelp, btMenu);

		BorderPane.setMargin(hbox, new Insets(10, 10, 10, 10));
		bPane.setBottom(hbox);
	}

	private void setVisibleButtons()
	{
		if(returnInfo.getPlayerRoom().getHasNorthRoom())
		{
			btNorth.setVisible(true);
		} else
		{
			btNorth.setVisible(false);
		}
		if(returnInfo.getPlayerRoom().getHasEastRoom())
		{
			btEast.setVisible(true);
		} else
		{
			btEast.setVisible(false);
		}

		if(returnInfo.getPlayerRoom().getHasSouthRoom())
		{
			btSouth.setVisible(true);
		} else
		{
			btSouth.setVisible(false);
		}
		if(returnInfo.getPlayerRoom().getHasWestRoom())
		{
			btWest.setVisible(true);
		} else
		{
			btWest.setVisible(false);
		}
		if(returnInfo.getPlayerRoom().getHasUpRoom())
		{
			btUp.setVisible(true);
		} else
		{
			btUp.setVisible(false);
		}
		if(returnInfo.getPlayerRoom().getHasDownRoom())
		{
			btDown.setVisible(true);
		} else
		{
			btDown.setVisible(false);
		}
	}

	private void navigate(String direction) // throws SQLException
	{
		returnInfo = gc.navigateControl(direction);
		if(!(returnInfo.getMessage().equals("")))
		{
			centerText.appendText(returnInfo.getMessage());
		}
		setVisibleButtons();
		topLabel.setText(returnInfo.getPlayerRoom().getRoomName());
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

		btUp.setOnAction(e ->
		{
			navigate("up");
		});

		btDown.setOnAction(e ->
		{
			navigate("down");
		});

		btSound.setOnAction(e ->
		{
			returnInfo = gc.targetControl();
			centerText.appendText("Sound: " + returnInfo.getPlayerRoom().getRoomID() + "  " + returnInfo.getMessage());
		});

		btExit.setOnAction(e ->
		{
			Platform.exit();
		});

		btMap.setOnAction(e ->
		{
			returnInfo = gc.mapControl();
			centerText.appendText(returnInfo.getMessage());
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
			returnInfo = gc.mapControl();
			centerText.appendText(returnInfo.getMessage());
			centerText.setOnKeyPressed(eventHandler2);
			centerText.requestFocus();
			setVisibleButtons();
		});

		btPause.setOnAction(e ->
		{
			animation.pause();
			//btNorth.setVisible(false);
			//btEast.setVisible(false);
			//btSouth.setVisible(false);
			//btWest.setVisible(false);
			//btUp.setVisible(false);
			//btDown.setVisible(false);
		});
	}

	private void runAnimation()
	{
		// Event handler for animation
		eventHandler = e ->
		{
			returnInfo = gc.movePredator();
			centerText.appendText(returnInfo.getMessage());
			if(returnInfo.getPlayerRoom().getRoomID() == returnInfo.getPredatorRoom().getRoomID())
			{
				centerText.appendText("YOU GOT CAUGHT!!!");
				animation.stop();
				btNorth.setVisible(false);
				btEast.setVisible(false);
				btSouth.setVisible(false);
				btWest.setVisible(false);
				btUp.setVisible(false);
				btDown.setVisible(false);
			}
		};

		// Instantiate animation and set parameters to start game
		animation = new Timeline(new KeyFrame(Duration.millis(4000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
	}

}
