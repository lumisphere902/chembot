function balanceEquation(reactants, products) {
	var reactantElements = {};
	for (var i = 0; i < reactants.length; i++) {
		for (var j = 0; j < reactants[i].length; j++) {
			if (reactantElements[reactants[i].elemNum] != null) {
				reactantElements[reactants[i].elemNum] += reactants[i].amount;
			}
			else {
				reactantElements[reactants[i].elemNum] = reactants[i].amount;
			}
		}
	}
	var productElements = {};
	for (var i = 0; i < products.length; i++) {
		for (var j = 0; j < products[i].length; j++) {
			if (productElements[products[i].elemNum] != null) {
				productElements[products[i].elemNum] += products[i].amount;
			}
			else {
				productElements[products[i].elemNum] = products[i].amount;
			}
		}
	}
	console.log(reactantElements);
	console.log(productElements);
}
