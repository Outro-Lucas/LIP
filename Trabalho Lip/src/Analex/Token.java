package Analex;

public enum Token {
	
	PARENTESIS_ESQ('('),
	PARENTESIS_DIR(')'),
	OPERADOR_SOMA('+'),
	EXPONENCIAL ('*'),
	OPERADOR_MULT('*'),
	OPERADOR_DIVI('/'),
	OPERADOR_SUBT('-'),
	OPERADOR_ATRIB('='),
	PONTO_VIRGULA(';'),
	PONTO ('.'),
	IDENTIFICADOR('I'),
	CONSTANTE_INTEIRO_PONTO_CONSTANTE_INTEIRO('C'),
	CONSTANTE_INTEIRO('L'), 
	FIM(' ');
	
	private char valor;
	
	Token(char valor) {
		this.valor = valor;
	}
	
	public char getValor() {
		return this.valor;
	}

}
