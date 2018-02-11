package it.ruczaj.expressions.characters;

public class CharactersFactory {
	private CharactersFactory() {
	}

	private static final AnyCharacter any = new AnyCharacter();

	public static AnyCharacter any() {
		return any;
	}

	public static ExplicitCharacter explicit(char character) {
		return new ExplicitCharacter(character);
	}
}
