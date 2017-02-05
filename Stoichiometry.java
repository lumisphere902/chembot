
public class Stoichiometry {
	private Equation equation;
	public Stoichiometry(){}
	public Stoichiometry(Equation equation) {
		this.equation = equation;
	}
	public String evaluate(Chemical given, String startType, Number value, Chemical target, String endType) {
		String work = "https://latex.codecogs.com/gif.latex?";
		work += given.toLatexString(value.toString(), getUnit(startType));
		double startVal = value.getValue();
		if (startType.equals("mass")) {
			startVal /= given.molarMass;
			work+="\\cdot\\frac{" + given.toLatexString("1", "mol") + "}{" + given.toLatexString(""+given.molarMass, "g") + "}\\cdot";
		}
		else if (startType.equals("volume")) {
			startVal /= 22.4;
			work+="\\frac{" + given.toLatexString("1", "mol") + "}{" + given.toLatexString("22.4", "L") + "}\\cdot";
		}
		
		work+="\\frac{" + target.toLatexString(""+equation.amountOf(target), "mol")
			  + "}{" + given.toLatexString(""+equation.amountOf(given), "mol") + "}\\cdot";
		
		double endVal = 1;
		if (endType.equals("mass")) {
			endVal *= target.molarMass;
			work += "\\frac{" + target.toLatexString(""+target.molarMass, "g") + "}{" + target.toLatexString("1", "mol") + "}";
		}
		else if (endType.equals("volume")) {
			endVal *= 22.4;
			work += "\\frac{" + target.toLatexString("22.4", "L") + "}{" + target.toLatexString("1", "mol") + "}";
		}
		double ans = startVal * equation.amountOf(target) / equation.amountOf(given) * endVal;
		work += "=" + target.toLatexString(Number.numToString(ans, value.getSigFigs()),getUnit(endType));
		System.out.println(work);
		return work;
	}
	public String getUnit(String type) {
		if (type.equals("mass")) {
			return "g";
		}
		else if (type.equals("volume")) {
			return "L";
		}
		else {
			return "mol";
		}
	}
}