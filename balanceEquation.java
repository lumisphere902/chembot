public String toLatexString(double amount, String unit){
		String str = amount+"\\:";
		for(int i = 0;i<elems.size();i++){
			Element cur = elems.get(i);
			//5\:H_{2}\:g
			str+=chemTable[cur.id-1]+"_{"+cur.amount+"}";
		}
		str+="\\:"+unit;
		return str;
	}
