
public class Element {
	public int id;
	public int amount;
	public Element(int i,int a){
		id = i;
		amount = a;
	}
	public String toString(){
		return "{id:"+id+",amount:"+amount+"}";
	}
	@Override
	public boolean equals(Object e){
		return id == ((Element)e).id;
	}
}
