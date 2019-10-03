package Anasin;

import java.util.List;

import Analex.AnalexSimples;
import Analex.Token;
import Analex.TokenLexema;

public class AnasinSimples {

	private List<TokenLexema> tokenLexemaList;
	private int contador = 0;
	
	public AnasinSimples(String cadeia) {
		AnalexSimples analex = new AnalexSimples(cadeia);
		this.tokenLexemaList = analex.analex();
	}
	
	public void anasint() {
		if(this.tokenLexemaList!=null) {
			this.stmt_list();
			if(this.contador!=tokenLexemaList.size()) {
				//ERRO NO ANALISADOR SINT�TICO
				this.msg("ERRO NO ANALISADOR SINT�TICO");
			}
		}
		else {
			//ERRO NO ANALISADOR L�XICO
			this.msg("ERRO NO ANALISADOR L�XICO");
		}
	}
	private void stmt_list(){
		this.msg("Entrou stmt_list() teste id, token: " + this.nextToken());
		this.stmt();
		if (nextToken().getToken() == Token.PONTO_VIRGULA){
			this.lex();
			this.stmt_list();
		}
	}
	
	private void stmt(){
		this.msg("Entrou stmt() teste id, token: " + this.nextToken());
		if(nextToken().getToken() == Token.IDENTIFICADOR){
			this.lex();
			if(nextToken().getToken() == Token.OPERADOR_ATRIB){
				this.lex();
				this.expression();
			}else this.msg("ERRO, FALTA IGUALDADE");
		}else this.msg("ERRO, FALTA ID");
	}
	private void expression(){
		this.msg("Entrou expression() teste id, token: " + this.nextToken());
		while(this.nextToken().getToken() == Token.OPERADOR_SOMA ||
				this.nextToken().getToken() == Token.OPERADOR_SUBT) {
				this.lex();
				this.term();
			}
		this.term();
	}

	private void term(){
		this.msg("Entrou term() teste id, token: " + this.nextToken());
		while(this.nextToken().getToken() == Token.OPERADOR_MULT ||
				  this.nextToken().getToken() == Token.OPERADOR_DIVI) {
				this.lex();
				this.factor();
			}
		this.factor();
	}
	
	private void factor(){
		this.msg("Entrou factor() teste id, token: " + this.nextToken());
		this.exp();
		if(this.nextToken().getToken() == Token.EXPONENCIAL){
			this.lex();
			this.factor();
		}
	}
	
	private void exp(){
		this.msg("Entrou exp() teste id, token: " + this.nextToken());
		if(nextToken().getToken() == Token.IDENTIFICADOR) this.lex();
		else if (nextToken().getToken() == Token.CONSTANTE_INTEIRO) this.lex();
		else if (nextToken().getToken() == Token.CONSTANTE_INTEIRO_PONTO_CONSTANTE_INTEIRO) this.lex();
		
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