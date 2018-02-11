package it.ruczaj.expressions.characters;

public class AnyCharacter implements Character {
	AnyCharacter() {

	}

	@Override
	public boolean isEqual(char character) {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
