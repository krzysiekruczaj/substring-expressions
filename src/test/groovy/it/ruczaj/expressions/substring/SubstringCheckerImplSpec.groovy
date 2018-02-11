package it.ruczaj.expressions.substring

import it.ruczaj.expressions.characters.Character
import spock.lang.Specification
import spock.lang.Unroll

class SubstringCheckerImplSpec extends Specification {

	def tested = new SubstringCheckerImpl()

	@Unroll
	def "should return true when checking if [#inputString] contains as substring translated [#comparedExpression]"() {
		expect:
		tested.isSubstring(inputString, comparedExpression)

		where:
		inputString                    | comparedExpression
		"anything"                     | [
				mockValid('n'), mockValid('g')
		]
		"anything multi word occurred" | [
				mockAny(), mockValid('r'), mockValid('e'), mockAny()
		]
		"anything"                     | [
				mockValid('a'), mockValid('n'), mockValid('y'),
				mockAny(), mockAny(), mockAny(),
				mockValid('n'), mockValid('g')
		]
	}

	@Unroll
	def "should return false when checking if [#inputString] contains as substring translated [#comparedExpression]"() {
		expect:
		!tested.isSubstring(inputString, comparedExpression)

		where:
		inputString                                              | comparedExpression
		""                                                       | []
		"aaaaaa"                                                 | []
		"there could be also much more characters than expected" | [
				mockValid('t'), mockValid('e'), mockValid('d'), mockValid('x')
		]
		"anything"                                               | [
				mockValid('b'),
				mockAny(), mockAny(), mockAny(), mockAny(), mockAny(), mockAny(), mockAny()
		]
		"anything"                                               | [
				mockValid('\\'), mockValid('\\')
		]
		"anything"                                               | [
				// Should match two characters, but shouldn't find the last match.
				mockValid('n'), mockValid('g'), mockAny()
		]
		"nope"                                                   | [
				// Translations longer than provided string.
				mockAny(), mockAny(), mockAny(), mockAny(), mockAny()
		]
	}


	def mockAny() {
		def mock = Mock(Character)
		mock.isEqual(_) >> true
		mock
	}

	def mockValid(String character) {
		char expectedCharacter = character as char
		def mock = Mock(Character)
		mock.isEqual(_) >> { char passedCharacter ->
			return passedCharacter == expectedCharacter
		}
		mock
	}
}
