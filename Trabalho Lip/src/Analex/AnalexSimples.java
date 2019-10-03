package Analex;

import java.util.ArrayList;
import java.util.List;

	public class AnalexSimples {
		
	private String cadeia;
	private List<TokenLexema> tokenLexemaList; //Lista com Lexemas
	
	public AnalexSimples(String cadeia) {
		this.cadeia = cadeia;
		this.tokenLexemaList = new ArrayList<TokenLexema>();
	}
	
	//Verificações Analisador Lexico
	
	public List<TokenLexema> analex() {
		if(this.cadeia==null) return null;	
		char cadeiaArray[] = this.cadeia.toCharArray();
		
		for(int i=0;i<cadeiaArray.length;i++) {
			
			if (this.ehParentesis(cadeiaArray[i]));//Verifica Parentesis
			
			//Verifica Exponencial
			else if (cadeiaArray[i]=='*' && cadeiaArray[i+1]=='*'){
				this.ehExponencial(cadeiaArray[i], cadeiaArray[i+1]);
				i++;
			}
			//Verifica Operadores
			else if (this.ehOperador(cadeiaArray[i]));
			
			//Verifica Ponto Virgula
			else if(this.ehPontoVirgula(cadeiaArray[i]));
			
			//Verifica Float
			else if(this.ehFloat(cadeiaArray[i])) {
				String digito = "";
				do {
					digito += cadeiaArray[i]+"";
					i = i + 1;
				}while(i!=cadeiaArray.length && this.ehFloat(cadeiaArray[i]));
					if (cadeiaArray[i]=='.'){
					i++;
					if(this.ehFloat(cadeiaArray[i])) {
						String digito2 = "";
						do {
							digito2 += cadeiaArray[i]+"";
							i = i + 1;
						}while(i!=cadeiaArray.length && this.ehFloat(cadeiaArray[i]));
						TokenLexema d1 = new TokenLexema(Token.CONSTANTE_INTEIRO_PONTO_CONSTANTE_INTEIRO, digito+'.'+digito2);
						this.tokenLexemaList.add(d1);
					}
				}
				else 
					this.tokenLexemaList.add(new TokenLexema(Token.CONSTANTE_INTEIRO, digito));
				if(i!=cadeiaArray.length) {
					i = i - 1;
				}
			}
			
			//Verifica valores inteiros
			else if(this.ehInt(cadeiaArray[i])) {
				String digito = "";
				do {
					digito += cadeiaArray[i]+"";
					i = i + 1;
				}while(i!=cadeiaArray.length && this.ehFloat(cadeiaArray[i]));
				
				this.tokenLexemaList.add(new TokenLexema(Token.CONSTANTE_INTEIRO,
														 digito));
				if(i!=cadeiaArray.length) {
					i = i - 1;
				}
			}
			
			//Verifica Identificador
			else if(this.ehLetra(cadeiaArray[i])) {
				String identificador = "";
				do {
					identificador += cadeiaArray[i];
					i = i + 1;
				}while(i!=cadeiaArray.length && (this.ehLetra(cadeiaArray[i]) || 
												 this.ehFloat(cadeiaArray[i])));
				this.tokenLexemaList.add( new TokenLexema(Token.IDENTIFICADOR, identificador));
				
				if(i!=cadeiaArray.length) {
					i = i - 1;
					}
				}
			}
			return tokenLexemaList;
		}
	
	
	private boolean ehParentesis(char c) {
		if(c == Token.PARENTESIS_ESQ.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.PARENTESIS_ESQ,
							        ""+Token.PARENTESIS_ESQ.getValor())
					);
			return true;
		}else if (c == Token.PARENTESIS_DIR.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.PARENTESIS_DIR,
							        ""+Token.PARENTESIS_DIR.getValor())
					);
			return true;
		}
		return false;
	}
	
	private boolean ehOperador(char c) {
		if(c == Token.OPERADOR_ATRIB.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.OPERADOR_ATRIB,
							        ""+Token.OPERADOR_ATRIB.getValor())
					);
			return true;
		}else if(c == Token.OPERADOR_MULT.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.OPERADOR_MULT,
							        ""+Token.OPERADOR_MULT.getValor())
					);
			return true;
		}else if(c == Token.OPERADOR_SOMA.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.OPERADOR_SOMA,
							        ""+Token.OPERADOR_SOMA.getValor())
					);
			return true;
		}else if(c == Token.OPERADOR_SUBT.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.OPERADOR_SUBT,
							        ""+Token.OPERADOR_SUBT.getValor())
					);
			return true;
		}else if(c == Token.OPERADOR_DIVI.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.OPERADOR_DIVI,
							        ""+Token.OPERADOR_DIVI.getValor())
					);
			return true;
		}
		return false;
	}
	
	public void ehExponencial(char c, char m){
		if(c==Token.EXPONENCIAL.getValor() && m==c ){
			this.tokenLexemaList.add(
					new TokenLexema(Token.EXPONENCIAL,
					        ""+Token.EXPONENCIAL.getValor())
				);
			this.tokenLexemaList.add(
					new TokenLexema(Token.EXPONENCIAL,
					        ""+Token.EXPONENCIAL.getValor())
				);
		}
	}
	public boolean ehPontoVirgula(char c) {
		if(c == Token.PONTO_VIRGULA.getValor()) {
			this.tokenLexemaList.add(
					new TokenLexema(Token.PONTO_VIRGULA,
							        ""+Token.PONTO_VIRGULA.getValor())
					);
			return true;
		}
		return false;
	}
	
	public boolean ehFloat(char c) {
		return Character.isDigit(c);
		
	}
	public boolean ehInt(char c){
		return Character.isDigit(c);
	}

	public boolean ehLetra(char c) {
		return Character.isLetter(c);
	}
	
	@Override
	public String toString() {
		String res = "";
		for(TokenLexema tl:this.tokenLexemaList) {
			res += tl;
			res += "\n";
		}
		return res;
	}
}
