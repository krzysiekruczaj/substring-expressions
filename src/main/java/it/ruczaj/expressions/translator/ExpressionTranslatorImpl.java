package it.ruczaj.expressions.translator;

import it.ruczaj.expressions.characters.Character;
import it.ruczaj.expressions.characters.CharactersFactory;
import it.ruczaj.expressions.characters.ExplicitCharacter;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTranslatorImpl implements ExpressionTranslator {
	/**
	 * Translates input into character wrappers using following logic:
	 * <ul>
	 *     <li>Each occurrence of * in the input means that it can be a match for zero or more characters of the first string.</li>
	 *     <li>Asterisk (*) may be considered as a regular character, if it is preceded by a backslash (\).</li>
	 *     <li>Backslash (\) is considered as a regular character in all cases other than preceding the asterisk (*).</li>
	 * </ul>
	 *
	 * @param input string that will be translated
	 * @return list of translated characters. Length of list can be different than length of input string.
	 */
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
