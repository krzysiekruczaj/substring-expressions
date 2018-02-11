package it.ruczaj.expressions.characters;

public class ExplicitCharacter implements Character {
	private char character;

	ExplicitCharacter(char character) {
		this.character = character;
	}

	@Override
	public boolean isEqual(char character) {
		return this.character == character;
	}

	@Override
	public String toString() {
		return "ExplicitCharacter{" +
				"character=" + character +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ExplicitCharacter that = (ExplicitCharacter) o;

		return character == that.character;
	}

	@Override
	public int hashCode() {
		return (int) character;
	}
}
