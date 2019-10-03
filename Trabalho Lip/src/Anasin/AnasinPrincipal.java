package Anasin;

public class AnasinPrincipal {
	public static void main(String[] args) {
		//String exp = "EXP = 2.0**(DIV2)";
		//String exp = "ID =(5.4); ID = ID ** (ID)";
		String exp = "ID = 2.1; Exec = Soma";
		AnasinSimples anasin = new AnasinSimples(exp);
		anasin.anasint();
	}
	
}
