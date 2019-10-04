package anasin;

import anasin.AnasinSimples;

public class AnasinPrincipal {
	public static void main(String[] args) {
		//String exp = "BEGIN EXP = 2.0**(DIV2) END";
		String exp = "ID =(5.4); ID = ID ** (ID) END";
		//String exp = "BEGIN ID = 2.1; Exec = Soma END";
		AnasinSimples anasin = new AnasinSimples(exp);
		anasin.anasint();
	}
	
}

