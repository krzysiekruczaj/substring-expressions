package it.ruczaj.expressions

import spock.lang.Specification
import spock.lang.Unroll

class SubstringCheckerMainSpec extends Specification {
	def tested = new SubstringCheckerMain()

	@Unroll
	def "should return failure message when invalid arguments were passed #arguments"() {
		when:
		def result = tested.compare(arguments as String[])

		then:
		result == "Pass two parameters - [inputString] [comparedExpression]!"

		where:
		arguments << [
				[],
				["mock"]
		]
	}

	@Unroll
	def "should return that [#comparedExpression] is substring of [#inputString]"() {
		expect:
		tested.compare([inputString, comparedExpression] as String[]) == "Provided input expression [$comparedExpression] is substring of [$inputString]!"

		where:
		inputString    | comparedExpression
		"abcd"         | "a*c"
		"9\\0*a**abcd" | "9\\0\\***\\*ab"
		"abceabcd"     | "ab*d"
		"a1b2c3d4"     | "********"
		"\\\\\\"       | "\\\\"
		"\t\b"         | "\t"
		"💻💲"         | "\uD83D\uDCBB"
	}

	@Unroll
	def "should return that [#comparedExpression] is not substring of [#inputString]"() {
		expect:
		tested.compare([inputString, comparedExpression] as String[]) == "Provided input expression [$comparedExpression] is not substring of [$inputString]!"

		where:
		inputString                                              | comparedExpression
		"abcd"                                                   | "a*cb"
		"there could be also much more characters than expected" | "ted*"
	}
}
