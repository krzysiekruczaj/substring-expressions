package it.ruczaj.expressions;

import it.ruczaj.expressions.characters.Character;
import it.ruczaj.expressions.substring.SubstringChecker;
import it.ruczaj.expressions.substring.SubstringCheckerImpl;
import it.ruczaj.expressions.translator.ExpressionTranslator;
import it.ruczaj.expressions.translator.ExpressionTranslatorImpl;

import java.util.List;

public class SubstringCheckerMain {
	private final ExpressionTranslator expressionTranslator = new ExpressionTranslatorImpl();

	public static void main(String[] args) {
		SubstringCheckerMain logicExecutor = new SubstringCheckerMain();
		System.out.println(logicExecutor.compareInputWithExpression(args));
	}

	public String compareInputWithExpression(String[] args) {
		if (hasValidArgumentsLength(args)) {
			final String inputString = args[0];
			final String comparedExpression = args[1];

			List<Character> translatedCharacters = expressionTranslator.translate(comparedExpression);
			SubstringChecker substringChecker = new SubstringCheckerImpl(inputString, translatedCharacters);
			if (substringChecker.isSubstring()) {
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
