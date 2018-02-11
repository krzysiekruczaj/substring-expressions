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
		if (isExpressionWithSameLengthAsProvidedString(translatedCharactersCount, inputCharacters)) {
			return validateSubstringWithEqualLength(translatedCharacters, inputCharacters);
		}
		return validateSubstringWithDifferentLength(translatedCharacters, inputCharacters);
	}

	private boolean isExpressionWithSameLengthAsProvidedString(int translatedCharactersCount, char[] inputCharacters) {
		return inputCharacters.length == translatedCharactersCount;
	}

	private boolean validateSubstringWithDifferentLength(Character[] translatedCharacters, char[] inputCharacters) {
		for (int currentIndex = 0; currentIndex < inputCharacters.length; currentIndex++) {
			char currentCharacter = inputCharacters[currentIndex];
			Character translatedCharacter = translatedCharacters[0];
			logger.debug("Checking: [{}] against [{}]", currentCharacter, translatedCharacter);
			if (isSameCharacter(translatedCharacter, currentCharacter)) {
				logger.debug("Matched: [{}] against [{}]", currentCharacter, translatedCharacter);
				if (isAmountOfSubsequentCharactersEnough(inputCharacters, translatedCharacters, currentIndex)) {
					if (allSubsequentCharactersAreMatching(inputCharacters, translatedCharacters, currentIndex)) {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean allSubsequentCharactersAreMatching(
			char[] inputCharacters,
			Character[] translatedCharacters,
			int currentIndex) {
		for (int subsequentIndex = 1; subsequentIndex < translatedCharacters.length; subsequentIndex++) {
			char subsequentCharacter = inputCharacters[currentIndex + subsequentIndex];
			Character translatedCharacter = translatedCharacters[subsequentIndex];
			if (isNotSameCharacter(translatedCharacter, subsequentCharacter)) {
				logger.debug("Not matched subsequent: [{}] against [{}]", subsequentCharacter, translatedCharacter);
				return false;
			}
			logger.debug("Matched subsequent: [{}] against [{}]", subsequentCharacter, translatedCharacter);
		}
		return true;
	}

	private boolean isAmountOfSubsequentCharactersEnough(
			char[] inputCharacters,
			Character[] translatedCharacters,
			int currentIndex) {
		return inputCharacters.length - (currentIndex + 1) >= translatedCharacters.length - 1;
	}

	private boolean validateSubstringWithEqualLength(Character[] translatedCharacters, char[] inputCharacters) {
		for (int currentIndex = 0; currentIndex < inputCharacters.length; currentIndex++) {
			char currentCharacter = inputCharacters[currentIndex];
			Character translatedCharacter = translatedCharacters[currentIndex];
			logger.debug("Checking: [{}] against [{}]", currentCharacter, translatedCharacter);
			if (isNotSameCharacter(translatedCharacter, currentCharacter)) {
				logger.debug("Not matched: [{}] against [{}]", currentCharacter, translatedCharacter);
				return false;
			}
			logger.debug("Matched: [{}] against [{}]", currentCharacter, translatedCharacter);
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
