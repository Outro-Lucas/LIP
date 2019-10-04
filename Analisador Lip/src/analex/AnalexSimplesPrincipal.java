package analex;

public class AnalexSimplesPrincipal {
	
	public static void main(String[] args) {
		String exp = "BEGIN soma END";
		AnalexSimples analexSimples = new AnalexSimples(exp);
		analexSimples.analex();
		System.out.println(analexSimples);
	}
	
	 

}
