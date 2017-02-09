import java.util.*;

public class Chemical {
	public static String[] chemTable = new String[] { "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg",
			"Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn",
			"Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd",
			"Ln", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy",
			"Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Ti", "Pb", "Bi", "Po",
			"At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U" };
	public static double[] elementMass = new double[] { 1.01, 4.00, 6.94, 9.01, 10.81, 12.01, 14.01, 16, 19, 20.18,
			22.99, 24.31, 26.98, 28.09, 30.97, 32.07, 35.45, 39.95, 39.1, 40.08, 44.96, 47.87, 50.94, 52, 54.94, 55.85,
			58.93, 58.69, 63.55, 65.39, 69.72, 72.61, 74.92, 78.96, 79.9, 83.8, 85.47, 87.62, 88.91, 91.22, 92.91,
			95.94, 98, 101.07, 102.91, 106.42, 107.87, 112.41, 114.82, 118.71, 121.76, 127.6, 126.9, 131.29, 132.91,
			137.33, 138.91, 140.12, 140.91, 144.24, 145, 150.36, 151.96, 157.25, 158.93, 162.5, 178.49, 167.26, 169.93,
			173.04, 174.97, 178.49, 180.95, 183.84, 186.21, 190.23, 192.22, 195.08, 196.97, 200.57, 204.38, 207.2,
			208.98, 209, 210, 222, 223, 226, 227, 232.04, 231.04, 238.03 };
	public ArrayList<Element> elems;
	public String name;
	public int amount;
	public String phase;
	public double molarMass;

		
	public static double calcMolarMass(Chemical c){
		return calcMolarMass(c.elems);
	}
	public static double calcMolarMass(ArrayList<Element> elems){
		double total = 0;
		for(int i = 0;i<elems.size();i++){
			Element cur = elems.get(i);
			total+=cur.amount*elementMass[cur.id-1];
		}
		return total;
	}
	public Chemical(String str) {
		this(str, 1);
	}

	public Chemical(String str, int amount) {
		this.amount = amount;
		name = str;
		elems = new ArrayList<>();
		String temp = "";
		String tempNum = "";
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if(cur=='('){
				if (str.charAt(i+1)=='a') {
					this.phase = "aq";
				}
				else {
					this.phase =""+ str.charAt(i+1);
				}
				break;
			}
			if (Character.isLetter(cur)) {
				if (cur == Character.toUpperCase(cur) && !temp.isEmpty()) {
					int chemId = getAtomicNumber(temp);
					int curAmount = tempNum.length() == 0 ? 1 : Integer.parseInt(tempNum);
					boolean found = false;
					for (int j = 0; j < elems.size(); j++) {
						if (elems.get(j).id == chemId) {
							found = true;
							elems.get(j).amount += curAmount;
							break;
						}
					}
					if (!found) {
						elems.add(new Element(chemId, curAmount));
					}
					tempNum = "";
					temp = "" + cur;
				} else {
					temp += cur;
				}
			} else if (Character.isDigit(cur)) {
				tempNum += cur;
			}
		}
		int chemId = getAtomicNumber(temp);
		int curAmount = tempNum.length() == 0 ? 1 : Integer.parseInt(tempNum);
		boolean found = false;
		for (int j = 0; j < elems.size(); j++) {
			if (elems.get(j).id == chemId) {
				found = true;
				elems.get(j).amount += curAmount;
				break;
			}
		}
		if (!found) {
			elems.add(new Element(chemId, curAmount));
		}
		molarMass = calcMolarMass(elems);
	}

	public int getAtomicNumber(String str) {
		for (int i = 0; i < 92; i++) {
			if (chemTable[i].equals(str)) {
				return i + 1;
			}
		}
		return -1;
	}

	public String toString() {
		return "{name:" + name + ",elems:" + elems + ",amount:" + amount + "}";
	}
	public String toLatexString(String amount, String unit){
		String str = amount+"\\:"+unit+"\\:";
		for(int i = 0;i<elems.size();i++){
			Element cur = elems.get(i);
			str+=chemTable[cur.id-1]+(cur.amount>1?"_{"+cur.amount+"}":"");
		}
		return str;
	}
	public String toLatexStringEquation(){
		String str = "";
		if (amount != 1) {
			str+=amount;
		}
		boolean endsWith = false;
		for(int i = 0;i<elems.size();i++){
			if (i > 0) {
				str+="}";
			}
			Element cur = elems.get(i);
			str+=chemTable[cur.id-1]+"_{"+(cur.amount == 1 ? "":cur.amount);
		}
		str+="("+phase+")}";
		return str;
	}
}