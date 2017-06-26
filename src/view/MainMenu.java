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

public class MainMenu extends Stage
{
	private StackPane mainSP;
	private Label title;
	private Button btNew;
	private Button btContinue;
	private Button btOptions;
	private Button btExit;
	private MediaPlayer mp;

	public MainMenu()
	{
		buildMainMenu();
		buildButtonActions();
		playMusic();

		Scene menuScene = new Scene(mainSP, 1280, 720);
		this.setScene(menuScene);
		this.setResizable(false);
		this.show();
	}

	private void buildMainMenu()
	{
		mainSP = new StackPane();
		mainSP.setBackground(new Background(new BackgroundImage(new Image("/resources/MainMenu01.jpg"),BackgroundRepeat.REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		
		title = new Label();
		title.setText("A f t e r m a t h");
		title.setFont(Font.font("OCR A Std", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 80));
		title.setTextFill(Color.MEDIUMTURQUOISE);
		Reflection labelReflection = new Reflection();
		labelReflection.setFraction(0.5f);
		title.setEffect(labelReflection);

		btNew = new Button("New Game");
		btNew.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btNew.setTextFill(Color.MEDIUMTURQUOISE);
		btNew.setPadding(new Insets(100,0,0,0));
		btNew.setStyle("-fx-background-color: transparent;");

		btContinue = new Button("Continue Game");
		btContinue.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btContinue.setTextFill(Color.MEDIUMTURQUOISE);
		btContinue.setStyle("-fx-background-color: transparent;");

		btOptions = new Button("Options");
		btOptions.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btOptions.setTextFill(Color.MEDIUMTURQUOISE);
		btOptions.setStyle("-fx-background-color: transparent;");

		btExit = new Button("Exit");
		btExit.setFont(Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 20));
		btExit.setTextFill(Color.MEDIUMTURQUOISE);
		btExit.setStyle("-fx-background-color: transparent;");;

		VBox menuVBox = new VBox();
		menuVBox.setAlignment(Pos.CENTER);
		menuVBox.setSpacing(30);
		menuVBox.getChildren().addAll( title, btNew, btContinue, btOptions, btExit);

		mainSP.getChildren().add(menuVBox);
		mainSP.setAlignment(Pos.CENTER);
	}

	private void buildButtonActions()
	{
		btNew.setOnAction(e ->
		{
			
		});
		
		btContinue.setOnAction(e ->
		{
			
		});
		
		btOptions.setOnAction(e ->
		{
			
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
			mp = new MediaPlayer(new Media(new File("Monster-Stake-Out.mp3").toURI().toString()));
			mp.play();
			mp.setCycleCount(Integer.MAX_VALUE);
		}
		catch (IllegalArgumentException iae)
		{
			System.out.println("Still incorrect");
			System.exit(0);
		}
		catch (MediaException me)
		{
			System.out.println("Still incorrect2");
			System.exit(0);				
		}	
	}
}
