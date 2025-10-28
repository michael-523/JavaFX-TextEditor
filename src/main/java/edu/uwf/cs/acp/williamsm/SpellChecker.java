package edu.uwf.cs.acp.williamsm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.TextArea;

public class SpellChecker
{
	private final Set<String> DICTIONARY;
	private final SuggestionGenerator suggestionGenerator;
	private final SpellCheckUI spellCheckUI;
	
	public SpellChecker(String fileName)
	{
		DICTIONARY = new HashSet<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			
			while ((line = br.readLine()) != null)
			{
				DICTIONARY.add(line.toLowerCase().trim());
			}
		}
		catch (IOException e)
		{
			AlertUtil.showErrorAlertWithOkButton("Dictionary Error", "Failed to Load Spell Check Dictionary.", e.getMessage());
		}
		
		suggestionGenerator = new SuggestionGenerator(DICTIONARY);
		spellCheckUI = new SpellCheckUI();
	}
	
	public void spellCheck(TextArea textArea)
	{
		String content = textArea.getText();
		StringBuilder updatedContent = new StringBuilder();
		
		int index = 0;
		
		while (index < content.length())
		{
			char c = content.charAt(index);
			
			if (!Character.isLetter(c))
			{
				updatedContent.append(c);
				index++;
				continue;
			}
			
			int start = index;
			index = getWordEndingIndex(content, start);
			String word = content.substring(start, index);
			updatedContent.append(getCorrectedWord(word));
		}
		
		textArea.setText(updatedContent.toString());
	}
	
	private int getWordEndingIndex(String content, int start)
	{
		int i = start;
		while (i < content.length() && Character.isLetter(content.charAt(i)))
		{
			i++;
		}
		
		return i;
	}
	
	private String getCorrectedWord(String word)
	{
		if (DICTIONARY.contains(word.toLowerCase()))
		{
			return word;
		}
		
		String replacement = spellCheckUI.displaySuggestions(word, suggestionGenerator.getSuggestions(word));
		
		if (replacement == null || replacement.equalsIgnoreCase(word))
		{
			return word;
		}
		
		return matchCasing(word, replacement);
	}
		
	private String matchCasing(String original, String replacement)
	{
		if (original.isEmpty())
		{
			return replacement;
		}
		
		if (original.equals(original.toUpperCase()))
		{
			return replacement.toUpperCase();
		}
		
		if (original.equals(original.toLowerCase()))
		{
			return replacement.toLowerCase();
		}
		
		if (Character.isUpperCase(original.charAt(0)) && original.substring(1).equals(original.substring(1).toLowerCase()))
		{
			return Character.toUpperCase(replacement.charAt(0)) + replacement.substring(1).toLowerCase();
		}
		
		return replacement;
	}
}
