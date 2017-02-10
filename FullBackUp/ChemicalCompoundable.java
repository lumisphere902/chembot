import java.util.ArrayList;

public interface ChemicalCompoundable {
	public String toLatexString();
	public double getMolarMass();
	public ArrayList<Element> getElements();
}