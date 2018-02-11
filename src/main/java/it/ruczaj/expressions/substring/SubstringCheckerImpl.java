package it.ruczaj.expressions.substring;

import it.ruczaj.expressions.characters.Character;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SubstringCheckerImpl implements SubstringChecker {

	private static final Logger logger = LogManager.getLogger(SubstringCheckerImpl.class);

	@Override
	public boolean isSubstring(
			String inputString,
			List<Character> comparedExpressionCharacters) {
		int translatedCharactersCount = comparedExpressionCharacters.size();
		return isInputExpressionValid(inputString, translatedCharactersCount)
				&&
				validateSubstring(inputString, comparedExpressionCharacters.toArray(new Character[0]),
						translatedCharactersCount);
	}

	private boolean validateSubstring(
			String inputString,
			Character[] translatedCharacters,
			int translatedCharactersCount) {
		char[] inputCharacters = inputString.toCharArray();
		if (inputCharacters.length == translatedCharactersCount) {
			return validateSubstringWithEqualLength(translatedCharacters, inputCharacters);

		}
		return validateSubstringWithDifferentLength(translatedCharacters, inputCharacters);
	}

	private boolean validateSubstringWithDifferentLength(Character[] translatedCharacters, char[] inputCharacters) {
		for (int currentIndex = 0; currentIndex < inputCharacters.length; currentIndex++) {
			char currentCharacter = inputCharacters[currentIndex];
			logger.debug("Checking: {}", currentCharacter);
			Character translatedCharacter = translatedCharacters[0];
			if (isSameCharacter(translatedCharacter, currentCharacter)) {
				logger.debug("Matched: {}", currentCharacter);
				if (allSubsequentCharactersAreMatching(inputCharacters, translatedCharacters, currentIndex)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean allSubsequentCharactersAreMatching(
			char[] inputCharacters,
			Character[] translatedCharacters,
			int currentIndex) {
		if (inputCharacters.length - (currentIndex + 1) >= translatedCharacters.length - 1) {
			for (int subsequentIndex = 1; subsequentIndex < translatedCharacters.length; subsequentIndex++) {
				char subsequentCharacter = inputCharacters[currentIndex + subsequentIndex];
				Character translatedCharacter = translatedCharacters[subsequentIndex];
				if (isNotSameCharacter(translatedCharacter, subsequentCharacter)) {
					return false;
				}
				logger.debug("Matched subsequent: {}", subsequentCharacter);
			}
			return true;
		}
		return false;
	}

	private boolean validateSubstringWithEqualLength(Character[] translatedCharacters, char[] inputCharacters) {
		for (int currentIndex = 0; currentIndex < inputCharacters.length; currentIndex++) {
			char currentCharacter = inputCharacters[currentIndex];
			logger.debug("Checking: {}", currentCharacter);
			Character translatedCharacter = translatedCharacters[currentIndex];
			if (isNotSameCharacter(translatedCharacter, currentCharacter)) {
				return false;
			}
		}
		return true;
	}

	private boolean isInputExpressionValid(String inputString, int translatedCharactersCount) {
		return translatedCharactersCount > 0 && inputString.length() >= translatedCharactersCount;
	}

	private boolean isSameCharacter(Character comparedCharacter, char currentCharacter) {
		return comparedCharacter.isEqual(currentCharacter);
	}

	private boolean isNotSameCharacter(Character comparedCharacter, char currentCharacter) {
		return !isSameCharacter(comparedCharacter, currentCharacter);
	}
}
