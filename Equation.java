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
	/*
	public void balance() {
		balanced = true;
		
		ArrayList<Element> arr = new ArrayList<>();
		for(int i = 0;i<reactants.size();i++){
			ArrayList<Element> curElems = reactants.get(i).elems;
			for(int j = 0;j<curElems.size();j++){
				int index = arr.indexOf(curElems.get(j));
				if(index==-1){
					arr.add(new Element())
				}else{
					
				}
			}
		}
		
	}
	*/

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
