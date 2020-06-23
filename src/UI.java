import javafx.application.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UI extends Application
{
	private HBox mainLayout;
	private Button chooseFolder;
	private Button chooseFile;
	private List<String> fileNames = new ArrayList<String>();
	private final FileChooser fileChooser = new FileChooser();
	private double[] buttonSize = new double[] {50, 50};
	private double[] appSize = new double[] {400, 530};
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		setWidgets();
		setButtonFuncs(stage);
		stage.setTitle("File Name Copy");
		stage.setScene(new Scene(mainLayout));
		stage.setResizable(false);
		stage.show();
	}
	
	private void setWidgets() {
		chooseFolder = new Button();
		chooseFolder.setText("Choose Folder");
		chooseFolder.setMinSize(buttonSize[0], buttonSize[1]);
		
		chooseFile = new Button();
		chooseFile.setText("Choose File");
		chooseFile.setMinSize(buttonSize[0], buttonSize[1]);
		
		mainLayout = new HBox();
		mainLayout.setAlignment(Pos.CENTER);
		mainLayout.setMinSize(appSize[0], appSize[1]);
		mainLayout.getChildren().addAll(chooseFolder, chooseFile);
	}
	
	private void setButtonFuncs(Stage stage) {
		chooseFolder.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				List<File> files = fileChooser.showOpenMultipleDialog(stage);
				if(files != null) {
					fileNames.clear();
					for(File file : files) {
						fileNames.add(file.getName());
					}
				}
			}
		});
		
		chooseFile.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				File file = fileChooser.showSaveDialog(stage);
				if(file != null) {
					try {
						BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
						for(String fileName : fileNames) {
							fileWriter.write(fileName);
							fileWriter.newLine();
						}
						fileWriter.close();
					} catch(IOException exc) {
						System.out.println("File Error");
					}
				}
			}
		});
	}
}
