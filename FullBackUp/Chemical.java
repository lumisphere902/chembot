import java.util.*;

public class Chemical implements ChemicalCompoundable{
	
	private ChemicalCompoundable[] parts;
	private ArrayList<Element> rawElements;
	private String name;
	private int amount;
	private String phase;
	private double molarMass;

	public Chemical(String formula) {
		this(formula, 1,"Unknown");
	}
	public Chemical(String formula,int amount){
		this(formula,amount,"Unknown");
	}
	public Chemical(String formula, int amount, String name) {
		this.name = name;
		this.amount = amount;
		ArrayList<String> split = splitString(formula);
		System.out.println(split);
		int numObj = split.size();
		parts = new ChemicalCompoundable[numObj];
		for(int i = 0;i<numObj;i++){
			String cur = split.get(i);
			String num = "";
			for(int j = cur.length()-1;;j--){
				if(Character.isDigit(cur.charAt(j))){
					num = cur.charAt(j)+num;
				}else{
					break;
				}
			}
			int curAmount = num.isEmpty()?1:Integer.parseInt(num);
			if(cur.charAt(0)=='('){
				parts[i] = new Chemical(cur.substring(1, cur.indexOf(')')),curAmount);
			}else{
				String elem = ""+cur.charAt(0);
				if(cur.length()>1&&Character.isLetter(cur.charAt(1))){
					elem+=cur.charAt(1);
				}
				parts[i] = new Element(getAtomicNumber(elem),curAmount);
			}
		}
		//calc molarmass and rawElements
		molarMass = 0;
		rawElements = new ArrayList<Element>();
		for(int i = 0;i<parts.length;i++){
			molarMass+=parts[i].getMolarMass();
			addOnElements(parts[i].getElements());
		}
		
	}
	private void addOnElements(ArrayList<Element> arr){
		for(int i = 0,ii=arr.size();i<ii;i++){
			Element toBeAdded = arr.get(i);
			int index = rawElements.indexOf(toBeAdded);
			if(index==-1){
				rawElements.add(new Element(toBeAdded));
			}else{
				rawElements.get(index).amount+=toBeAdded.amount;
			}
		}
	}
	private ArrayList<String> splitString(String str){
		ArrayList<String> arr = new ArrayList<String>();
		String temp="";
		boolean chem = false;
		for(int i = 0;i<str.length();i++){
			char cur = str.charAt(i);
			if(cur==' ')continue;
			if(chem){
				if(cur==')'){
					chem = false;
				}
				temp+=cur;
			}else{
				if(cur=='('){
					chem = true;
				}
				if(!temp.isEmpty()&&(cur=='('||Character.isUpperCase(cur))){
					arr.add(temp);
					temp = ""+cur;
				}else{
					temp+=cur;
				}
			}
		}
		if(!temp.isEmpty())arr.add(temp);
		return arr;
	}
	public ArrayList<Element> getElements(){
		return rawElements;
	}
	public int getAtomicNumber(String str) {
		for (int i = 0; i < 92; i++) {
			if (Element.chemTable[i].equals(str)) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		String str = "";
		for(int i = 0;i<parts.length;i++){
			Object cur = parts[i];
			str+=cur.toString();
		}
		return amount>1?"("+str+")"+amount:str;
	}
	public String toLatexString(){
		String str = "";
		for(int i = 0;i<parts.length;i++){
			str+=parts[i].toLatexString();
		}
		
		return (amount>1?"("+str+")_{"+amount+"}":str);
	}
	@Override
	public double getMolarMass(){
		return molarMass;
	}
	/*
	public static double calcMolarMass(ArrayList<Element> elems){
		double total = 0;
		for(int i = 0;i<elems.size();i++){
			Element cur = elems.get(i);
			total+=cur.amount*Element.elementMass[cur.id-1];
		}
		return total;
	}
	*/
}
