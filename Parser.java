import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parser {
	public static String returnUrl(String str) {
		StringTokenizer st = new StringTokenizer(str);
		String typeS = st.nextToken();
		Number amount = new Number(st.nextToken());
		Chemical given = new Chemical(st.nextToken());
		String typeE = st.nextToken();
		Chemical target = new Chemical(st.nextToken());
		boolean isProduct = false;
		ArrayList<Chemical> reactants = new ArrayList<Chemical>();
		ArrayList<Chemical> products = new ArrayList<Chemical>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.equals("->")) {
				isProduct = true;
			}
			else if (!token.equals("+")) {
				if (isProduct) {
					products.add(new Chemical(token));
				}
				else {
					reactants.add(new Chemical(token));
				}
			}
		}
		Stoichiometry s = new Stoichiometry(new Equation(reactants, products));
		return s.evaluate(given, typeS, amount, target, typeE);
	}
	
}
class Driver {
	public static void main(String[] args) {
		Parser.returnUrl("mass 27.54 O2 mass H2O H2(g) + O2(g) -> H2O(g)");
	}
}