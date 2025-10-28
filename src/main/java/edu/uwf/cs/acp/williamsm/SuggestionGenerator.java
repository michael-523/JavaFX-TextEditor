package edu.uwf.cs.acp.williamsm;

import java.util.HashSet;
import java.util.Set;

public class SuggestionGenerator
{
	private final Set<String> DICTIONARY;
	
	SuggestionGenerator(Set<String> DICTIONARY)
	{
		this.DICTIONARY = DICTIONARY;
	}
	
	public Set<String> getSuggestions(String word)
	{		
		Set<String> suggestions = new HashSet<>();
		String lowerWord = word.toLowerCase();
		
		insertion(lowerWord, suggestions);
		deletion(lowerWord, suggestions);
		swap(lowerWord, suggestions);
		substituion(lowerWord, suggestions);
		
		return suggestions;
	}
	
	private void insertion(String word, Set<String> suggestions)
	{
		for (int i = 0; i <= word.length(); i++)
		{
			for (char c = 'a'; c <= 'z'; c++)
			{
				String suggestion = word.substring(0, i) + c + word.substring(i);
				addSuggestion(suggestion, suggestions);
			}
		}
	}
	
	private void deletion(String word, Set<String> suggestions)
	{
		for (int i = 0; i < word.length(); i++)
		{
			String suggestion = word.substring(0, i) + word.substring(i + 1);
			addSuggestion(suggestion, suggestions);
		}
	}
	
	private void swap(String word, Set<String> suggestions)
	{
		for (int i = 0; i < word.length() - 1; i++)
		{
			char[] chars = word.toCharArray();
			
			char temp = chars[i];
			chars[i] = chars[i + 1];
			chars[i + 1] = temp;
			
			String suggestion = new String(chars);
			addSuggestion(suggestion, suggestions);
		}
	}
	
	private void substituion(String word, Set<String> suggestions)
	{
		for (int i = 0; i < word.length(); i++)
		{
			substituteAtPosition(word, i, suggestions);
		}
	}
	
	private void substituteAtPosition(String word, int index, Set<String> suggestions)
	{
		for (char c = 'a'; c <= 'z'; c++)
		{
			if (word.charAt(index) == c)
			{
				continue;
			}
			
			String suggestion = word.substring(0, index) + c + word.substring(index + 1);
			addSuggestion(suggestion, suggestions);
		}
	}
	
	private void addSuggestion(String suggestion, Set<String> suggestions)
	{
		if (DICTIONARY.contains(suggestion.toLowerCase()))
		{
			suggestions.add(suggestion.toLowerCase());
		}
	}
}
