package analex;

import java.util.ArrayList;
import java.util.List;

import analex.Token;
import analex.TokenLexema;

public class AnalexSimples {
	
	private List<TokenLexema> tokenLexemaList;
	private String cadeia;
	private int i;
	private char cadeiaArray[];

	/**
	 * Recebe uma cadeia a ser analisada
	 */
	public AnalexSimples(String cadeia) {
		this.cadeia = cadeia;
		this.tokenLexemaList = new ArrayList<TokenLexema>();
	}

	/**
	 * Analisa a cadeia.
	 * @return uma lista de {@link TokenLexema} ou <code>null</code>, caso a cadeia tenha um lexema inválido.
	 */
	public List<TokenLexema> analex() {
		if (this.cadeia == null)
			return null;
		 
		if(cadeia.toUpperCase().contains("BEGIN")){
			this.tokenLexemaList.add(new TokenLexema(Token.BEGIN, "BEGIN"));
		}
		

		
		cadeiaArray = this.cadeia.toCharArray();
		for (i = 0; i < cadeiaArray.length; i++) {
			if (this.ehParentesis(cadeiaArray[i]));
			else if (cadeiaArray[i]=='*' && cadeiaArray[i+1]=='*'){
				this.ehExponencial(cadeiaArray[i], cadeiaArray[i+1]);
				i++;
			}
			else if (this.ehOperador(cadeiaArray[i]));
			else if (this.ehPontoVirgula(cadeiaArray[i]));
			else if (this.ehIdentificador(cadeiaArray[i]));
			else if (this.ehLiteralInteiro(cadeiaArray[i]));
			else if (this.ehChar(cadeiaArray[i]));
			else if (this.ehLiteralInteiro(cadeiaArray[i]));
			else if (Character.isWhitespace(cadeiaArray[i]));
		}
		if(cadeia.toUpperCase().contains("END")){
			this.tokenLexemaList.add(new TokenLexema(Token.END, "END"));
		}
		
		return this.tokenLexemaList;
	}

	private boolean ehParentesis(char c) {
		if (c == Token.PARENTESIS_ESQ.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PARENTESIS_ESQ, "" + Token.PARENTESIS_ESQ.getValor()));
			return true;
		} else if (c == Token.PARENTESIS_DIR.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PARENTESIS_DIR, "" + Token.PARENTESIS_DIR.getValor()));
			return true;
		}
		return false;
	}

	private boolean ehOperador(char c) {
		if (c == Token.OPERADOR_ATRIB.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_ATRIB, "" + Token.OPERADOR_ATRIB.getValor()));
			return true;
		} else if (c == Token.OPERADOR_SOMA.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_SOMA, "" + Token.OPERADOR_SOMA.getValor()));
			return true;
		} else if (c == Token.OPERADOR_SUBT.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_SUBT, "" + Token.OPERADOR_SUBT.getValor()));
			return true;
		}else if (c == Token.OPERADOR_MULT.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_MULT, "" + Token.OPERADOR_MULT.getValor()));
			return true;
		} else if (c == Token.OPERADOR_DIVI.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_DIVI, "" + Token.OPERADOR_DIVI.getValor()));
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
		if (c == Token.PONTO_VIRGULA.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PONTO_VIRGULA, "" + Token.PONTO_VIRGULA.getValor()));
			return true;
		}
		return false;
	}

	public boolean ehPonto (char c){
		if(c == Token.PONTO.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PONTO, "" + Token.PONTO.getValor()));
			return true;
		}
		return false;
	}
	public boolean ehLiteralInteiro(char c) {
		if (Character.isDigit(c)) {
			String digito = "";
			do {
				digito += cadeiaArray[i] + "";
				i = i + 1;
			} while (i != cadeiaArray.length && Character.isDigit(cadeiaArray[i]));
			
			if(i < cadeiaArray.length) {
				if(cadeiaArray[i] == '.') {
					if (i != cadeiaArray.length) {
						i = i - digito.length();
					}
					return false;
				}
			}
			this.tokenLexemaList.add(new TokenLexema(Token.LITERAL_INTEIRO, digito));
			if (i != cadeiaArray.length) {
				i = i - 1;
			}
			return true;
		}
		return false;
	}
	
	
	public boolean ehFloat(char c) {
		return Character.isDigit(c);
	}
	
	public boolean ehPontoFlutuante (char c){
		if (Character.isDigit(c)) {
			String digito = "";
			do {
				digito += cadeiaArray[i] + "";
				i = i + 1;
			} while (i != cadeiaArray.length && Character.isDigit(cadeiaArray[i]));
			
			if(this.ehPonto(cadeiaArray[i])){
				i++;
				if(Character.isDigit(cadeiaArray[i])) {
					String digito2 = "";
					do {
						digito2 += cadeiaArray[i]+"";
						i = i + 1;
					}while(i!=cadeiaArray.length && Character.isDigit(cadeiaArray[i]));
					
					TokenLexema d1 = new TokenLexema(Token.PONTO_FLUTUANTE, digito+'.'+digito2);
					this.tokenLexemaList.add(d1);
				}
			}
			
			this.tokenLexemaList.add(new TokenLexema(Token.PONTO_FLUTUANTE, digito));
			if (i != cadeiaArray.length) {
				i = i - 1;
			} 
			return true;
		}
			return false;
	}


	public boolean ehIdentificador(char c) {
		if (Character.isLetter(c)) {
			
			if(cadeiaArray[i]=='B'){
				i++;
				if(cadeiaArray[i]=='E'){
					i++;
					if(cadeiaArray[i]=='G'){
						i++;
						if(cadeiaArray[i]=='I'){
							i++;
							if(cadeiaArray[i]=='N'){
							return false;
							}	
						}	
					}	
				}
			}
			if(cadeiaArray[i]=='E'){
				i++;
				if(cadeiaArray[i]=='N'){
					i++;
					if(cadeiaArray[i]=='D'){
						return false;
					}	
				}	
			}
			String identificador = "";
			do {
				identificador += cadeiaArray[i];
				i = i + 1;
			} while (i != cadeiaArray.length && (Character.isLetter(cadeiaArray[i]) || Character.isDigit(cadeiaArray[i])));
			this.tokenLexemaList.add(new TokenLexema(Token.IDENTIFICADOR, identificador));

			if (i != cadeiaArray.length) {
				i = i - 1;
			}
			return true;
		}
		return false;
	}
	

	public boolean ehChar(char c) {
		if (Character.isDigit(c)) {
			String identificador = "";
			do {
				identificador += cadeiaArray[i];
				i = i + 1;
			} while (i != cadeiaArray.length && (Character.isDigit(cadeiaArray[i]) || cadeiaArray[i]=='.'));
			this.tokenLexemaList.add(new TokenLexema(Token.PONTO_FLUTUANTE, identificador));

			if (i != cadeiaArray.length) {
				i = i - 1;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String res = "";
		for (TokenLexema tl : this.tokenLexemaList) {
			res += tl;
			res += "\n";
		}
		return res;
	}

}
