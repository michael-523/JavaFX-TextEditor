package edu.uwf.cs.acp.williamsm;

import java.util.Optional;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

public class SpellCheckUI
{
	public String displaySuggestions(String word, Set<String> suggestions)
	{
		System.out.println(suggestions.toString());
		
		if (suggestions.isEmpty())
		{
			System.out.println("No problem here officer");
			
			AlertUtil.showInfoAlertWithOkButton("Spell Check", "No suggestions found.", "The word has no suggestions.");
			return null;
		}
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Spell Check");
		alert.setHeaderText("Misspelled word: " + word);
		
		ToggleGroup group = new ToggleGroup();
		VBox content = createSuggestionsBox(group, suggestions);		
		alert.getDialogPane().setContent(content);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		return getUserSuggestion(result, group);
	}
	
	private VBox createSuggestionsBox(ToggleGroup group, Set<String> suggestions)
	{
		VBox vbox = new VBox();

		for (String suggestion : suggestions)
		{
			RadioButton rb = new RadioButton(suggestion);
			rb.setToggleGroup(group);
			vbox.getChildren().add(rb);
		}
		
		return vbox;
	}
	
	private String getUserSuggestion(Optional<ButtonType> result, ToggleGroup group)
	{
		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			RadioButton selected = (RadioButton) group.getSelectedToggle();
			if (selected != null)
			{
				return selected.getText();
			}
		}
		
		return null;
	}
}
