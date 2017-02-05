import java.util.*;

public class Equation {
	public ArrayList<Chemical> reactants;
	public ArrayList<Chemical> products;
	boolean balanced;

	public Equation(ArrayList<Chemical> r, ArrayList<Chemical> p) {
		reactants = r;
		products = p;
		balanced = true;
	}

	public void balance() {
		while (true) {
			ArrayList<Element> arr = new ArrayList<>();

			for (int i = 0; i < reactants.size(); i++) {
				ArrayList<Element> curElems = reactants.get(i).elems;
				int multiple = reactants.get(i).amount;
				for (int j = 0; j < curElems.size(); j++) {
					Element curElem = curElems.get(j);
					int index = indexOf(arr, curElem);
					if (index == -1) {
						arr.add(new Element(curElem.id, multiple * curElem.amount));
					} else {
						arr.get(index).amount += multiple * curElem.amount;
					}
				}
			}
			for (int i = 0; i < products.size(); i++) {
				ArrayList<Element> curElems = products.get(i).elems;
				int multiple = -products.get(i).amount;
				for (int j = 0; j < curElems.size(); j++) {
					Element curElem = curElems.get(j);
					int index = indexOf(arr, curElem);
					if (index == -1) {
						arr.add(new Element(curElem.id, multiple * curElem.amount));
					} else {
						arr.get(index).amount += multiple * curElem.amount;
						if (arr.get(index).amount == 0)
							arr.remove(index);
					}
				}
			}

			if (arr.size() > 0) {
				Element elemToChange = arr.get(0);
				if (elemToChange.amount < 0) {
					for (int i = 0; i < reactants.size(); i++) {
						Chemical cur = reactants.get(i);
						if (indexOf(cur.elems, elemToChange) != -1) {
							reactants.get(i).amount++;
							break;
						}
					}
				} else{
					for (int i = 0; i < products.size(); i++) {
						Chemical cur = products.get(i);
						if (indexOf(cur.elems, elemToChange) != -1) {
							products.get(i).amount++;
							break;
						}
					}
				}
			} else {
				break;
			}
		}
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