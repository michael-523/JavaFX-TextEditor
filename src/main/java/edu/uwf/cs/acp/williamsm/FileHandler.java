package edu.uwf.cs.acp.williamsm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler
{
	public File openFile(Stage stage, TextArea textArea)
	{
		FileChooser fileChooser = createFileChooser("Open");
		File file = fileChooser.showOpenDialog(stage);
		
		if (file != null)
		{
			try
			{
				textArea.setText(Files.readString(file.toPath()));
			}
			catch (IOException e)
			{
				AlertUtil.showErrorAlertWithOkButton("File Error", "File Failed To Open", e.getMessage());
			}
		}
		
		return file;
	}
	
	public File saveFile(Stage stage, TextArea textArea, File currentFile)
	{		
		if (currentFile == null)
		{
			return saveFileAs(stage, textArea);
		}
		
		try
		{
			Files.writeString(currentFile.toPath(), textArea.getText());
		}
		catch (IOException e)
		{
			AlertUtil.showErrorAlertWithOkButton("File Error", "File Failed To Save", e.getMessage());
		}
		
		return currentFile;
	}
	
	public File saveFileAs(Stage stage, TextArea textArea)
	{
		FileChooser fileChooser = createFileChooser("Save File As");
		File file = fileChooser.showSaveDialog(stage);
		
		if (file != null)
		{
			try
			{
				Files.writeString(file.toPath(), textArea.getText());
			}
			catch (IOException e)
			{
				AlertUtil.showErrorAlertWithOkButton("File Error", "File Failed To Save", e.getMessage());
			}
		}
		
		return file;
	}
	
	private FileChooser createFileChooser(String title)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		
		return fileChooser;
	}
}
