package it.ruczaj.expressions.substring;

import it.ruczaj.expressions.characters.Character;

import java.util.List;

public interface SubstringChecker {
	boolean isSubstring(
			String inputString,
			List<Character> comparedExpressionCharacters);
}
