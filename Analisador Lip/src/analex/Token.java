package analex;

public enum Token {
	
	PARENTESIS_ESQ('('),
	PARENTESIS_DIR(')'),
	EXPONENCIAL('*'),
	OPERADOR_SOMA('+'),
	OPERADOR_MULT('*'),
	OPERADOR_DIVI('/'),
	OPERADOR_SUBT('-'),
	OPERADOR_ATRIB('='),
	PONTO_VIRGULA(';'),
	PONTO('.'),
	FIM('F'),
	IDENTIFICADOR('I'),
	PONTO_FLUTUANTE('F'),
	LITERAL_INTEIRO('L'),
	BEGIN ("BEGIN"),
	END ("END");
	
	private char valor;
	
	Token(char valor) {
		this.valor = valor;
	}
	
	public char getValor() {
		return this.valor;
	}
	private String ini;
	
	Token (String ini){
		this.ini = ini;
	}
	public String getValor1(){
		return this.ini;
	}

}
