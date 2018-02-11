package it.ruczaj.expressions;

import it.ruczaj.expressions.characters.Character;
import it.ruczaj.expressions.substring.SubstringChecker;
import it.ruczaj.expressions.substring.SubstringCheckerImpl;

import java.util.List;

public class SubstringCheckerMain {
	private final ExpressionTranslator expressionTranslator = new ExpressionTranslatorImpl();
	private final SubstringChecker substringChecker = new SubstringCheckerImpl();

	public static void main(String[] args) {
		SubstringCheckerMain logicExecutor = new SubstringCheckerMain();
		logicExecutor.compare(args);
	}

	public String compare(String[] args) {
		if (hasValidArgumentsLength(args)) {
			final String inputString = args[0];
			final String comparedExpression = args[1];

			List<Character> translatedCharacters = expressionTranslator.translate(comparedExpression);
			if (substringChecker.isSubstring(inputString, translatedCharacters)) {
				return buildSubstringMessage(inputString, comparedExpression);
			} else {
				return buildNotSubstringMessage(inputString, comparedExpression);
			}
		} else {
			return "Pass two parameters - [inputString] [comparedExpression]!";
		}
	}

	private boolean hasValidArgumentsLength(String[] args) {
		return args.length == 2;
	}

	private String buildNotSubstringMessage(String inputString, String comparedExpression) {
		return String.format(
				"Provided input expression [%s] is not substring of [%s]!", comparedExpression, inputString
		);
	}

	private String buildSubstringMessage(String inputString, String comparedExpression) {
		return String.format(
				"Provided input expression [%s] is substring of [%s]!", comparedExpression, inputString
		);
	}
}
