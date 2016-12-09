package grid;

public class Token {

	public String getSymbol() {
		return symbol;
	}

	public int getColor() {
		return color;
	}

	// Definition of the variables
	private String symbol;
	private int color;

	public Token(String symbol) {
		this.symbol = symbol;
	}

	public Token(int color) {
		this.color = color;
	}

	public Token(String symbol, int color) {
		this.symbol = symbol;
		this.color = color;
	}
}