package it.ruczaj.expressions.translator;

import it.ruczaj.expressions.characters.Character;

import java.util.List;

public interface ExpressionTranslator {
	List<Character> translate(String input);
}
