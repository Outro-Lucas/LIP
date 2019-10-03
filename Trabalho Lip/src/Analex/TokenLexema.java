package Analex;

public class TokenLexema {
	
	private Token token;
	private String lexema;
	public TokenLexema(Token token, String lexema) {
		this.token = token;
		this.lexema = lexema;
	}
	
	@Override
	public String toString() {
		String res = "";
		res += "Token: " + this.token + " Lexema: " + lexema;
		return res;
	}
	

	public Token getToken() {
		return this.token;
	}

}
