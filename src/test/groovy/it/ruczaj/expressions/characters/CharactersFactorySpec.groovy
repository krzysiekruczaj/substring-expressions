package it.ruczaj.expressions.characters

import spock.lang.Specification

class CharactersFactorySpec extends Specification {
	def "should return the same instance when creating any character"() {
		expect:
		CharactersFactory.any() is CharactersFactory.any()
	}

	def "should return separate instances when creating explicit character"() {
		when:
		def explicit = CharactersFactory.explicit('a' as char)

		then:
		def anotherExplicit = CharactersFactory.explicit('a' as char)
		!explicit.is(anotherExplicit)
	}
}
