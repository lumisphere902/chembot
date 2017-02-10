import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChemBotTestingDriver {
	public static void main(String[] args) {
//		ArrayList<Chemical> reactants = new ArrayList<Chemical>();
//		ArrayList<Chemical> products = new ArrayList<Chemical>();
//		reactants.add(new Chemical("Cr2O7"));
//		reactants.add(new Chemical("H"));
//		products.add(new Chemical("Cr"));
//		products.add(new Chemical("H2O"));
//		Equation eq = new Equation(reactants, products);
//		System.out.println("BALANCE");
//		eq.balance();
//		System.out.println(eq.toLatexString());
		Chemical c = new Chemical("Magnesium nitrate");
		System.out.println("final: "+c.debugString());
	}
//	public static String returnUrl(String str) {
//		System.out.println("parsed String: "+str);
//		StringTokenizer st = new StringTokenizer(str);
//		String typeS = st.nextToken();
//		String typeE = st.nextToken();
//		Number amount = new Number(st.nextToken());
//		Chemical given = new Chemical(st.nextToken());
//		Chemical target = new Chemical(st.nextToken());
//		boolean isProduct = false;
//		ArrayList<Chemical> reactants = new ArrayList<Chemical>();
//		ArrayList<Chemical> products = new ArrayList<Chemical>();
//		while (st.hasMoreTokens()) {
//			String token = st.nextToken();
//			if (token.equals("->")) {
//				isProduct = true;
//			} else if (!token.equals("+")) {
//				if (isProduct) {
//					products.add(new Chemical(token));
//				} else {
//					reactants.add(new Chemical(token));
//				}
//			}
//		}
//		Stoichiometry s = new Stoichiometry(new Equation(reactants, products));
//		return s.evaluate(given, typeS, amount, target, typeE);
//	}
}
