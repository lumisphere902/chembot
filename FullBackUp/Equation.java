import java.util.*;

public class Equation {
	public ArrayList<ChemicalCompoundable> reactants;
	public ArrayList<ChemicalCompoundable> products;
	public int[] reactantAmounts;
	public int[] productAmounts;
	boolean balanced;

	public Equation(ArrayList<ChemicalCompoundable> r, ArrayList<ChemicalCompoundable> p) {
		reactants = r;
		products = p;
		reactantAmounts = new int[reactants.size()];
		Arrays.fill(reactantAmounts, 1);
		productAmounts = new int[products.size()];
		Arrays.fill(productAmounts, 1);
		balanced = false;
	}
	public Equation(String str) {
		StringTokenizer st = new StringTokenizer(str);
		boolean isProduct = false;
		reactants = new ArrayList<ChemicalCompoundable>();
		products = new ArrayList<ChemicalCompoundable>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.equals("->")) {
				isProduct = true;
			} else if (!token.equals("+")) {
				if (isProduct) {
					products.add(new Chemical(token));
				} else {
					reactants.add(new Chemical(token));
				}
			}
		}
		reactantAmounts = new int[reactants.size()];
		Arrays.fill(reactantAmounts, 1);
		productAmounts = new int[products.size()];
		Arrays.fill(productAmounts, 1);
		balanced = false;
	}

	public void balance() {
		balanced = true;

	}

	public int indexOf(ArrayList arr, Element e) {
		for (int i = 0; i < arr.size(); i++)
			if (e.id == ((Element) arr.get(i)).id)
				return i;
		return -1;
	}

	public String toLatexString() {
		String str = "";
		System.out.println("reactants: " + reactants + ", products:" + products);
		for (int i = 0; i < reactants.size(); i++) {
			if (reactantAmounts[i] != 1) {
				str += reactantAmounts[i];
			}
			str += reactants.get(i).toLatexString();
			if (i < reactants.size() - 1) {
				str += "%20+%20";
			} else {
				str += "%20\\rightarrow%20";
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if (productAmounts[i] != 1) {
				str += productAmounts[i];
			}
			str += products.get(i).toLatexString();
			if (i < products.size() - 1) {
				str += "%20+%20";
			}
		}
		return str;
	}

	public int amountOf(Chemical c) {
		for (int i = 0; i < reactants.size(); i++) {
			if (reactants.get(i).getElements().equals(c.getElements())) {
				return reactantAmounts[i];
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getElements().equals(c.getElements())) {
				return productAmounts[i];
			}
		}
		return 0;
	}
}