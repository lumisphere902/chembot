import java.util.*;

public class Equation {
	public ArrayList<Chemical> reactants;
	public ArrayList<Chemical> products;
	boolean balanced;

	public Equation(ArrayList<Chemical> r, ArrayList<Chemical> p) {
		reactants = r;
		products = p;
		balanced = false;
	}

	public void balance() {
		balanced = true;

		ArrayList<Element> arr = new ArrayList<>();
		
		for (int i = 0; i < reactants.size(); i++) {
			ArrayList<Element> curElems = reactants.get(i).elems;
			int multiple = reactants.get(i).amount;
			for (int j = 0; j < curElems.size(); j++) {
				Element curElem = curElems.get(j);
				int index = arr.indexOf(curElem);
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
				int index = arr.indexOf(curElem);
				if (index == -1) {
					arr.add(new Element(curElem.id, multiple * curElem.amount));
				} else {
					arr.get(index).amount += multiple * curElem.amount;
					if (arr.get(index).amount == 0)
						arr.remove(index);
				}
			}
		}
		while (arr.size() > 0) {
			Element elemToChange = arr.get(0);
			if (elemToChange.amount < 0) {
				// add react
				for (int i = 0; i < reactants.size(); i++) {
					Chemical cur = reactants.get(i);
					if (cur.elems.indexOf(elemToChange) != -1) {
						reactants.get(i).amount++;
						arr.get(0).amount += cur.elems.get(cur.elems.indexOf(elemToChange)).amount;
					}
				}
			} else if (elemToChange.amount > 0){
				// add prod
				for (int i = 0; i < products.size(); i++) {
					Chemical cur = products.get(i);
					if (cur.elems.indexOf(elemToChange) != -1) {
						products.get(i).amount++;
						arr.get(0).amount -= cur.elems.get(cur.elems.indexOf(elemToChange)).amount;
					}
				}
			}
			else {
				arr.remove(0);
			}
		}
	}

	public String toLatexString() {
		String str = "";
		return str;
	}

	public int amountOf(Chemical c) {
		for (int i = 0; i < reactants.size(); i++) {
			if (reactants.get(i).name.equals(c.name)) {
				return c.amount;
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).name.equals(c.name)) {
				return c.amount;
			}
		}
		return 0;
	}
}
