public void balance() {
		balanced = true;
		
		ArrayList<Element> arr = new ArrayList<>();
		for(int i = 0;i<reactants.size();i++){
			ArrayList<Element> curElems = reactants.get(i).elems;
			int multiple = reactants.get(i).amount;
			for(int j = 0;j<curElems.size();j++){
				Element curElem = curElems.get(j);
				int index = arr.indexOf(curElem);
				if(index==-1){
					arr.add(new Element(curElem.id,multiple*curElem.amount));
				}else{
					arr.get(index).amount+=multiple*curElem.amount;
				}
			}
		}
		for(int i = 0;i<products.size();i++){
			ArrayList<Element> curElems = products.get(i).elems;
			int multiple = -products.get(i).amount;
			for(int j = 0;j<curElems.size();j++){
				Element curElem = curElems.get(j);
				int index = arr.indexOf(curElem);
				if(index==-1){
					arr.add(new Element(curElem.id,multiple*curElem.amount));
				}else{
					arr.get(index).amount+=multiple*curElem.amount;
					if(arr.get(index).amount==0)arr.remove(index);
				}
			}
		}
		while(arr.size()>0){
			Element elemToChange = arr.get(0);
			if(elemToChange.amount<0){
				//add react
				for(int i = 0;i<reactants.size();i++){
					Chemical cur = reactants.get(i);
					if(cur.elems.indexOf(elemToChange)!=-1){
						
					}
				}
			}else{
				//add prod
			}
			break;
		}
	}
