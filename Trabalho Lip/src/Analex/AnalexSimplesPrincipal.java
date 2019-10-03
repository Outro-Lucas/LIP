package Analex;

public class AnalexSimplesPrincipal {
	
	public static void main(String[] args) {
		String exp = "2.21**a-b+6.0";
		AnalexSimples analexSimples = new AnalexSimples(exp);
		analexSimples.analex();
		System.out.println(analexSimples);
	}
	
	 

}
