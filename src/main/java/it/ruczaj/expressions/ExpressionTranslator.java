package it.ruczaj.expressions;

import it.ruczaj.expressions.characters.Character;

import java.util.List;

public interface ExpressionTranslator {
	List<Character> translate(String input);
}
