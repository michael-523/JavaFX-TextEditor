package edu.uwf.cs.acp.williamsm;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
	private TextArea textArea;
	private FileHandler fileHandler;
	private SpellChecker spellChecker;
	private File currentFile;
	
	
    @Override
    public void start(Stage stage)
    {
    	textArea = new TextArea();
    	fileHandler = new FileHandler();
    	spellChecker = new SpellChecker("Words.txt");
    	currentFile = null;
    	
    	MenuBar menuBar = createMenuBar(stage);
        VBox vbox = new VBox(menuBar, textArea);
        Scene scene = new Scene(vbox, 800, 600);
        
        stage.setTitle("Project 2: Text Editor");
        stage.setScene(scene);
        stage.show();
    }
    
    private MenuBar createMenuBar(Stage stage)
    {
    	Menu fileMenu = createFileMenu(stage);
    	Menu editMenu = createEditMenu(stage);
    	
    	return new MenuBar(fileMenu, editMenu);
    }
    
    private Menu createFileMenu(Stage stage)
    {
    	Menu menu = new Menu("File");
    	
    	MenuItem open = new MenuItem("Open");
    	open.setOnAction(e -> { currentFile = fileHandler.openFile(stage, textArea); });
    	
    	MenuItem save = new MenuItem("Save");
    	save.setOnAction(e -> {	currentFile = fileHandler.saveFile(stage, textArea, currentFile); });
    	
    	MenuItem saveAs = new MenuItem("Save As");
    	saveAs.setOnAction(e -> { currentFile = fileHandler.saveFileAs(stage, textArea); });
    	
    	MenuItem exit = new MenuItem("Exit");
    	exit.setOnAction(e -> System.exit(0));

    	
    	menu.getItems().addAll(open, save, saveAs, exit);
    	
    	return menu;
    }
    
    private Menu createEditMenu(Stage stage)
    {
    	Menu menu = new Menu("Edit");
    	
    	MenuItem spellCheck = new MenuItem("Spell Check");
    	spellCheck.setOnAction(e -> spellChecker.spellCheck(textArea));
    	
    	menu.getItems().add(spellCheck);
    	return menu;
    }

    public static void main(String[] args)
    {
        launch();
    }
}