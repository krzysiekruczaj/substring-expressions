package it.ruczaj.expressions.translator

import it.ruczaj.expressions.characters.AnyCharacter
import it.ruczaj.expressions.characters.CharactersFactory
import it.ruczaj.expressions.characters.ExplicitCharacter
import spock.lang.Specification
import spock.lang.Unroll

class ExpressionTranslatorImplSpec extends Specification {
	def tested = new ExpressionTranslatorImpl()

	@Unroll
	def "should translate #inputString into #expectedResult"() {
		expect:
		tested.translate(inputString) == expectedResult

		where:
		inputString         | expectedResult
		"90*******ab"       | [
				explicit('9'), explicit('0'),
				any(), any(), any(), any(), any(), any(), any(),
				explicit('a'), explicit('b')
		]
		"9\\0\\******\\*ab" | [
				explicit('9'), explicit('\\'), explicit('0'), explicit('*'),
				any(), any(), any(), any(), any(),
				explicit('*'), explicit('a'), explicit('b')
		]
		"abcdef"            | [
				explicit('a'), explicit('b'), explicit('c'),
				explicit('d'), explicit('e'), explicit('f')
		]
		"*********"         | [
				any(), any(), any(), any(), any(), any(), any(), any(), any()
		]
		// Edge-cases
		""                  | []
		"\\\\\\"            | [explicit('\\'), explicit('\\'), explicit('\\')]
		"\t\b"              | [explicit('\t'), explicit('\b')]
		"💻💲"              | [
				explicit('\uD83D'), explicit('\uDCBB'),
				explicit('\uD83D'), explicit('\uDCB2')
		]
	}

	private static ExplicitCharacter explicit(String character) {
		CharactersFactory.explicit(character as char)
	}

	private static AnyCharacter any() {
		CharactersFactory.any()
	}
}
