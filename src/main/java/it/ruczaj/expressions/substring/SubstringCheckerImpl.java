package it.ruczaj.expressions.substring;

import it.ruczaj.expressions.characters.Character;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SubstringCheckerImpl implements SubstringChecker {

	private static final Logger logger = LogManager.getLogger(SubstringCheckerImpl.class);

	private final int expressionCharactersCount;
	private final int inputCharactersCount;
	private final char[] inputCharacters;
	private final Character[] expressionCharacters;
	private int currentIndex = 0;

	public SubstringCheckerImpl(String inputString, List<Character> comparedExpressionCharacters) {
		this.inputCharacters = inputString.toCharArray();
		this.expressionCharacters = comparedExpressionCharacters.toArray(new Character[0]);
		this.expressionCharactersCount = comparedExpressionCharacters.size();
		this.inputCharactersCount = inputString.length();
	}

	@Override
	public boolean isSubstring() {
		return isInputExpressionValid() && isValidSubstring();
	}

	private boolean isInputExpressionValid() {
		return expressionCharactersCount > 0 && inputCharactersCount >= expressionCharactersCount;
	}

	private boolean isValidSubstring() {
		if (isExpressionWithSameLengthAsProvidedString()) {
			return checkSubstringWithEqualLength();
		}
		return checkSubstringWithDifferentLength();
	}

	private boolean isExpressionWithSameLengthAsProvidedString() {
		return inputCharactersCount == expressionCharactersCount;
	}

	private boolean checkSubstringWithDifferentLength() {
		for (; currentIndex < inputCharacters.length; currentIndex++) {
			char currentCharacter = inputCharacters[currentIndex];
			Character translatedCharacter = expressionCharacters[0];
			logger.debug("Checking: [{}] against [{}]", currentCharacter, translatedCharacter);
			if (isSameCharacter(translatedCharacter, currentCharacter)) {
				logger.debug("Matched: [{}] against [{}]", currentCharacter, translatedCharacter);
				if (isAmountOfSubsequentCharactersEnough()) {
					if (allSubsequentCharactersAreMatching()) {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean allSubsequentCharactersAreMatching() {
		for (int subsequentIndex = 1; subsequentIndex < expressionCharactersCount; subsequentIndex++) {
			char subsequentCharacter = inputCharacters[currentIndex + subsequentIndex];
			Character translatedCharacter = expressionCharacters[subsequentIndex];
			if (isNotSameCharacter(translatedCharacter, subsequentCharacter)) {
				logger.debug("Not matched subsequent: [{}] against [{}]", subsequentCharacter, translatedCharacter);
				return false;
			}
			logger.debug("Matched subsequent: [{}] against [{}]", subsequentCharacter, translatedCharacter);
		}
		return true;
	}

	private boolean isAmountOfSubsequentCharactersEnough() {
		return inputCharactersCount - (currentIndex + 1) >= expressionCharactersCount - 1;
	}

	private boolean checkSubstringWithEqualLength() {
		for (; currentIndex < inputCharactersCount; currentIndex++) {
			char currentCharacter = inputCharacters[currentIndex];
			Character translatedCharacter = expressionCharacters[currentIndex];
			logger.debug("Checking: [{}] against [{}]", currentCharacter, translatedCharacter);
			if (isNotSameCharacter(translatedCharacter, currentCharacter)) {
				logger.debug("Not matched: [{}] against [{}]", currentCharacter, translatedCharacter);
				return false;
			}
			logger.debug("Matched: [{}] against [{}]", currentCharacter, translatedCharacter);
		}
		return true;
	}

	private boolean isSameCharacter(Character comparedCharacter, char currentCharacter) {
		return comparedCharacter.isEqual(currentCharacter);
	}

	private boolean isNotSameCharacter(Character comparedCharacter, char currentCharacter) {
		return !isSameCharacter(comparedCharacter, currentCharacter);
	}
}
