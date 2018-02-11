package it.ruczaj.expressions.translator;

import it.ruczaj.expressions.characters.Character;
import it.ruczaj.expressions.characters.CharactersFactory;
import it.ruczaj.expressions.characters.ExplicitCharacter;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTranslatorImpl implements ExpressionTranslator {
	@Override
	public List<Character> translate(String input) {
		char[] inputCharacters = input.toCharArray();
		boolean wasBackslashed = false;
		List<Character> translatedCharacters = new ArrayList<>(inputCharacters.length);
		for (char currentCharacter : inputCharacters) {
			if (currentCharacter == '\\') {
				translatedCharacters.add(explicit(currentCharacter));
				wasBackslashed = true;
			} else {
				if (currentCharacter == '*') {
					if (wasBackslashed) {
						translatedCharacters.set(translatedCharacters.size() - 1, explicit(currentCharacter));
					} else {
						translatedCharacters.add(CharactersFactory.any());
					}
				} else {
					translatedCharacters.add(explicit(currentCharacter));
				}
				wasBackslashed = false;
			}
		}
		return translatedCharacters;
	}

	private ExplicitCharacter explicit(char currentCharacter) {
		return CharactersFactory.explicit(currentCharacter);
	}
}
