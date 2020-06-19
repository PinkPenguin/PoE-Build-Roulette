
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	
	//TODO: try turning into local variable again.
	private Label label1;
	private Label label2;
	private Label label3;
	
	//TODO: Consider using double for a slower and smoother roll transition
	private long speed = 1;
	boolean rolling = false;

	long lastUpdate = 0;
	
	ArrayList<SkillGem> gemList = new ArrayList<SkillGem>();
	int currentIndex = 4;
	boolean includeVaal = false;
	boolean includeItems = false;

	public GUI(String[] args) {
		launch(args);
	}

	public GUI() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("PoE Build Roulette");
		Font font = new Font("System", 16);
		
		BorderPane rootPane = new BorderPane();
		
		gemList = SkillList.generateRandomGemList(includeVaal, includeItems);
		
		label1 = new Label(gemList.get(0).getName());
		label1.setFont(font);
		label2 = new Label(gemList.get(1).getName());
		label2.setFont(new Font("System", 30));
		label3 = new Label(gemList.get(2).getName());
		label3.setFont(font);
		
		Button bRoll = new Button("!roll");
		bRoll.setOnAction(event -> rolling = true);
		Button bReset = new Button("Reset");
		bReset.setOnAction(event -> {
			if(rolling) {
				gemList = SkillList.generateRandomGemList(includeVaal, includeItems);
				rolling = false;
				speed = 1;
			}
		});
		
		CheckBox vaalBox = new CheckBox("Vaal");
		vaalBox.setOnAction(event -> {
			includeVaal = !includeVaal;
			gemList = SkillList.generateRandomGemList(includeVaal, includeItems);
		});
		CheckBox itemBox = new CheckBox("Items");
		itemBox.setOnAction(event -> {
			includeItems = !includeItems;
			gemList = SkillList.generateRandomGemList(includeVaal, includeItems);
		});
		
		
		BorderPane labelsPane = new BorderPane();
		BorderPane buttonPane = new BorderPane();
		BorderPane boxPane = new BorderPane();
		
		BorderPane.setAlignment(bRoll, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(bReset, Pos.BOTTOM_RIGHT);		
		
		BorderPane.setAlignment(vaalBox, Pos.TOP_LEFT);
		BorderPane.setAlignment(itemBox, Pos.TOP_RIGHT);
		
		buttonPane.setLeft(bRoll);
		buttonPane.setRight(bReset);
		boxPane.setLeft(vaalBox);
		boxPane.setRight(itemBox);
		
		Scene scene = new Scene(rootPane, 400, 250);

		labelsPane.setTop(label1);
		BorderPane.setAlignment(label1, Pos.BOTTOM_CENTER);
		label1.setPrefHeight(70);
		labelsPane.setCenter(label2);
		BorderPane.setAlignment(label2, Pos.CENTER);
		label2.setPrefHeight(70);
		labelsPane.setBottom(label3);
		label3.setPrefHeight(70);
		BorderPane.setAlignment(label3, Pos.TOP_CENTER);
		
		labelsPane.setMaxHeight(210);
			
		rootPane.setTop(boxPane);
		rootPane.setCenter(labelsPane);
		rootPane.setBottom(buttonPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	
		/*Starts a timer updates the label, it gets progressively slower as the speed
		 * threshold gets larger*/
		new AnimationTimer() {

			@Override
			public void handle(long now) {
			
				if(rolling == true && now - lastUpdate > speed){

					label3.setText(label2.getText());
					label3.setTextFill(label2.getTextFill());
					label2.setText(label1.getText());
					label2.setTextFill(label1.getTextFill());
					
					SkillGem gem = gemList.get(currentIndex);
					if(currentIndex == gemList.size()-1) {
						currentIndex = 0;
					}
					else {
						currentIndex++;
					}
					if(gem.isVaal()) {
						label1.setTextFill(Color.DARKRED);
					}
					else {
						label1.setTextFill(Color.BLACK);
					}
					label1.setText(gem.getName());
					
					// Progressively slows the speed if the timer by larger and larger amounts
					//TODO: Maybe slow by random amount in a range to not make it entirely determenistic
					//		though that definitely has its charm.
					if(speed < 50000000) {
						speed = speed + 500000;
					}
					else if(speed < 100000000) {
						speed = speed + 5000000;
					}
					else if (speed < 800000000) {
						speed = speed + 110000000;
					}
					else {
						// The stop condition.
						//TODO: Try to figure out how to be able to stop rather than max_value
						//		to save some processing power.
						speed = Long.MAX_VALUE;
						label2.setText("-->" + label2.getText() + " <--");
						//this.stop();
						//TODO: Notify somehow that the thing has actually stopped
					}
					//System.out.println(speed);

					lastUpdate = now;
				}
				else if (rolling == false){
					//TODO: rehaul this after using Slot class
					label3.setText(label2.getText());
					label3.setTextFill(label2.getTextFill());
					label2.setText(label1.getText());
					label2.setTextFill(label1.getTextFill());
					
					SkillGem gem = gemList.get(currentIndex);
					if(currentIndex == gemList.size()-1) {
						currentIndex = 0;
					}
					else {
						currentIndex++;
					}
					
					if(gem.isVaal()) {
						label1.setTextFill(Color.DARKRED);
					}
					else {
						label1.setTextFill(Color.BLACK);
					}
					label1.setText(gem.getName());
					
				}
			}			
		}.start();

	}
}
