package anasin;


import java.util.List;

import analex.AnalexSimples;
import analex.Token;
import analex.TokenLexema;

public class AnasinSimples {

	private List<TokenLexema> tokenLexemaList;
	private int contador = 0;
	
	public AnasinSimples(String cadeia) {
		AnalexSimples analex = new AnalexSimples(cadeia);
		this.tokenLexemaList = analex.analex();
	}
	
	public void anasint() {
		if(this.tokenLexemaList!=null) {

			if(nextToken().getToken() == Token.BEGIN){
				this.lex();
				this.stmt_list();
					if(nextToken().getToken() == Token.END){
						this.lex();
					} else this.msg("ERA ESPERADO A EXPRESSÂO END");
					
			} else this.msg("ERA ESPERADO A EXPRESSÃO BEGIN");
			
			if(this.contador!=tokenLexemaList.size()) {
				//ERRO NO ANALISADOR SINTÁTICO
				this.msg("ERRO NO ANALISADOR SINTÁTICO");
			}
		}
		else {
			//ERRO NO ANALISADOR LÉXICO
			this.msg("ERRO NO ANALISADOR LÉXICO");
		}
	}
	private void stmt_list(){
		this.msg("Entrou stmt_list() teste id, token: " + this.nextToken());
		this.stmt();
		if (nextToken().getToken() == Token.PONTO_VIRGULA){
			this.lex();
			this.stmt_list();
		}
		this.msg("Saiu stmt_list() teste id, tokem: " + this.nextToken());
	}
	
	private void stmt(){
		this.msg("Entrou stmt() teste id, token: " + this.nextToken());
		if(nextToken().getToken() == Token.IDENTIFICADOR){
			this.lex();
			if(nextToken().getToken() == Token.OPERADOR_ATRIB){
				this.lex();
				this.expression();
			}else this.msg("ERA ESPERADO '=' ");
		}else this.msg("ERA ESPERADO UM IDENTIFICADOR");
		
		this.msg("Saiu stmt() teste id, tokem: " + this.nextToken());
	}
	private void expression(){
		this.msg("Entrou expression() teste id, token: " + this.nextToken());
		while(this.nextToken().getToken() == Token.OPERADOR_SOMA ||
				this.nextToken().getToken() == Token.OPERADOR_SUBT) {
				this.lex();
				this.term();
			}
		this.term();
		this.msg("Saiu expression() teste id, tokem: " + this.nextToken());
	}

	private void term(){
		this.msg("Entrou term() teste id, token: " + this.nextToken());
		while(this.nextToken().getToken() == Token.OPERADOR_MULT ||
				  this.nextToken().getToken() == Token.OPERADOR_DIVI) {
				this.lex();
				this.factor();
			}
		this.factor();
		this.msg("Saiu term() teste id, tokem: " + this.nextToken());
	}
	
	private void factor(){
		this.msg("Entrou factor() teste id, token: " + this.nextToken());
		this.exp();
		if(this.nextToken().getToken() == Token.EXPONENCIAL){
			this.lex();
			this.factor();
		}
		this.msg("Saiu factor() teste id, tokem: " + this.nextToken());
	}
	
	private void exp(){
		this.msg("Entrou exp() teste id, token: " + this.nextToken());
		if(nextToken().getToken() == Token.IDENTIFICADOR) this.lex();
		else if (nextToken().getToken() == Token.LITERAL_INTEIRO) this.lex();
		else if (nextToken().getToken() == Token.PONTO_FLUTUANTE) this.lex();
		
		else if(this.nextToken().getToken() == Token.PARENTESIS_ESQ) {
			this.lex();
			this.expression();
			if(this.nextToken().getToken() == Token.PARENTESIS_DIR) {
				this.lex();
			}else {
				//ERROR
				this.msg("ERRO: PARENTESIS_DIR esperado");
			}
		}else {
			//ERROR
			this.msg("ERRO: IDENTIFICADOR, LITERAL_INTEIRO, PARENTESIS_ESQ  esperados");
		}
		this.msg("Saiu exp() teste id, tokem: " + this.nextToken());
	}
	private void msg(String cadeia) {
		System.out.println(cadeia);
	}
	
	private void lex() {
		if(this.contador==this.tokenLexemaList.size()) return;
		this.contador++;
	}
	
	private TokenLexema nextToken() {
		if(this.contador==this.tokenLexemaList.size()) return new TokenLexema(Token.FIM, Token.FIM.getValor()+"");
		return this.tokenLexemaList.get(this.contador);
	}
	
	 
	
}