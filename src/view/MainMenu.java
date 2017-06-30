package view;

import java.io.File;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenu extends Stage
{
	private Scene menuScene;
	private StackPane mainSP;
	private Label title;
	private Button btNew;
	private Button btContinue;
	private Button btOptions;
	private Button btBack;
	private Button btCredits;
	private Button btExit;
	private MediaPlayer mp;

	public MainMenu()
	{
		initButtons();
		buildMainMenu();
		buildButtonActions();
		playMusic();
	}

	private void buildMainMenu()
	{
		mainSP = new StackPane();
		mainSP.setBackground(
				new Background(new BackgroundImage(new Image("/resources/MainMenu01.jpg"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		title = new Label();
		title.setText("A f t e r m a t h");
		title.setFont(Font.font("OCR A Std", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 80));
		title.setTextFill(Color.MEDIUMTURQUOISE);
		Reflection labelReflection = new Reflection();
		labelReflection.setFraction(0.5f);
		title.setEffect(labelReflection);

		btNew.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btNew.setTextFill(Color.MEDIUMTURQUOISE);
		btNew.setPadding(new Insets(100, 0, 0, 0));
		btNew.setStyle("-fx-background-color: transparent;");

		btContinue.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btContinue.setTextFill(Color.MEDIUMTURQUOISE);
		btContinue.setStyle("-fx-background-color: transparent;");

		btOptions.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btOptions.setTextFill(Color.MEDIUMTURQUOISE);
		btOptions.setStyle("-fx-background-color: transparent;");

		btCredits.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btCredits.setTextFill(Color.MEDIUMTURQUOISE);
		btCredits.setStyle("-fx-background-color: transparent;");

		btExit.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btExit.setTextFill(Color.MEDIUMTURQUOISE);
		btExit.setStyle("-fx-background-color: transparent;");

		VBox menuVBox = new VBox();
		menuVBox.setAlignment(Pos.CENTER);
		menuVBox.setSpacing(30);
		menuVBox.getChildren().addAll(title, btNew, btContinue, btOptions, btCredits, btExit);

		mainSP.getChildren().add(menuVBox);
		mainSP.setAlignment(Pos.CENTER);

		menuScene = new Scene(mainSP, 1280, 720);
		this.setScene(menuScene);
		this.setResizable(false);
		this.show();
	}

	private void options()
	{
		mainSP = new StackPane();
		mainSP.setBackground(
				new Background(new BackgroundImage(new Image("/resources/MainMenu01.jpg"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		title = new Label();
		title.setText("Options");
		title.setFont(Font.font("OCR A Std", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 60));
		title.setTextFill(Color.MEDIUMTURQUOISE);

		btBack.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btBack.setTextFill(Color.MEDIUMTURQUOISE);
		btBack.setStyle("-fx-background-color: transparent;");

		VBox menuVBox = new VBox();
		menuVBox.setAlignment(Pos.CENTER);
		menuVBox.setSpacing(30);
		menuVBox.getChildren().addAll(title, btBack);

		mainSP.getChildren().add(menuVBox);
		mainSP.setAlignment(Pos.CENTER);

		menuScene = new Scene(mainSP, 1280, 720);
		this.setScene(menuScene);
		this.setResizable(false);
		this.show();
	}

	private void credits()
	{
		mainSP = new StackPane();
		mainSP.setBackground(
				new Background(new BackgroundImage(new Image("/resources/MainMenu01.jpg"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		title = new Label();
		title.setText("Credits");
		title.setTextFill(Color.MEDIUMTURQUOISE);
		title.setFont(Font.font("OCR A Std", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 60));

		Label name1 = new Label("Daniel Sales");
		name1.setTextFill(Color.MEDIUMTURQUOISE);
		name1.setFont((Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		Label name2 = new Label("Kenneth Sales");
		name2.setTextFill(Color.MEDIUMTURQUOISE);
		name2.setFont((Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		btBack.setTextFill(Color.MEDIUMTURQUOISE);
		btBack.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btBack.setStyle("-fx-background-color: transparent;");
		btBack.setPadding(new Insets(50, 0, 0, 0));

		VBox menuVBox = new VBox();
		menuVBox.setAlignment(Pos.CENTER);
		menuVBox.setSpacing(20);
		menuVBox.getChildren().addAll(title, name1, name2, btBack);

		mainSP.getChildren().add(menuVBox);
		mainSP.setAlignment(Pos.CENTER);

		menuScene = new Scene(mainSP, 1280, 720);
		this.setScene(menuScene);
		this.setResizable(false);
		this.show();
	}

	private void initButtons()
	{
		btNew = new Button("New Game");
		btContinue = new Button("Continue Game");
		btOptions = new Button("Options");
		btCredits = new Button("Credits");
		btExit = new Button("Exit");
		btBack = new Button("Back");
	}

	private void buildButtonActions()
	{
		btNew.setOnAction(e ->
		{
			new GameUI();
			mp.stop();
			this.close();
		});

		btContinue.setOnAction(e ->
		{

		});

		btOptions.setOnAction(e ->
		{
			options();
		});

		btBack.setOnAction(e ->
		{
			buildMainMenu();
		});

		btCredits.setOnAction(e ->
		{
			credits();
		});

		btExit.setOnAction(e ->
		{
			Platform.exit();
		});
	}

	private void playMusic()
	{
		try
		{
			mp = new MediaPlayer(
					new Media(getClass().getClassLoader().getResource("resources/Monster-Stake-Out.mp3").toString())); // magic
																														// incantation
																														// I
																														// used
																														// to
																														// pull
																														// mp3
																														// from
																														// resources
			mp.play();
			mp.setCycleCount(Integer.MAX_VALUE);
		} catch (IllegalArgumentException iae)
		{
			System.out.println("IllegalArgumentException in playMusic in MainMenu");
			System.exit(0);
		} catch (MediaException me)
		{
			System.out.println("Media Exception in playMusic in MainMenu");
			System.exit(0);
		}
	}
}
