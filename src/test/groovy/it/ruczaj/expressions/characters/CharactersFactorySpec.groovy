package it.ruczaj.expressions.characters

import spock.lang.Specification

class CharactersFactorySpec extends Specification {
	def "should return the same instance when creating any character"() {
		given:
		char character = 'a'

		when:
		def any = CharactersFactory.any()
		def anotherAny = CharactersFactory.any()

		then:
		any.is(anotherAny)
		any.equals(anotherAny)
		any.hashCode() == anotherAny.hashCode()
		any.isEqual(character)
	}

	def "should return separate instances when creating explicit character"() {
		given:
		char character = 'a'

		when:
		def explicit = CharactersFactory.explicit(character)
		def anotherExplicit = CharactersFactory.explicit(character)

		then:
		!explicit.is(anotherExplicit)
		explicit.equals(anotherExplicit)
		explicit.hashCode() == anotherExplicit.hashCode()
		explicit.isEqual(character)
		anotherExplicit.isEqual(character)
	}
}
