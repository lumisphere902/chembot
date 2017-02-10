import java.util.*;

public class Equation {
	public ArrayList<ChemicalCompoundable> reactants;
	public ArrayList<ChemicalCompoundable> products;
	boolean balanced;

	public Equation(ArrayList<Chemical> r, ArrayList<Chemical> p) {
		reactants = r;
		products = p;
		balanced = true;
	}

	public void balance() {
		
	}

	public int indexOf(ArrayList arr, Element e) {
		for (int i = 0; i < arr.size(); i++)
			if (e.id == ((Element) arr.get(i)).id)
				return i;
		return -1;
	}

	public String toLatexString() {
		String str = "";
		for (int i = 0; i < reactants.size(); i++) {
			str += reactants.get(i).toLatexStringEquation();
			if (i < reactants.size() - 1) {
				str += " + ";
			} else {
				str += " \\rightarrow ";
			}
		}
		for (int i = 0; i < products.size(); i++) {
			str += products.get(i).toLatexStringEquation();
			if (i < products.size() - 1) {
				str += " + ";
			}
		}
		return str;
	}

	public int amountOf(Chemical c) {
		for (int i = 0; i < reactants.size(); i++) {
			if (reactants.get(i).elems.equals(c.elems)) {
				return reactants.get(i).amount;
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).elems.equals(c.elems)) {
				return products.get(i).amount;
			}
		}
		return 0;
	}
}