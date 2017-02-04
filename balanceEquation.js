/** This is a sample code for your bot**/
function MessageHandler(context, event) {
    /*
    context.console.log("test")
    if(event.message.toLowerCase() == "httptest") {
        context.simplehttp.makeGet("http://ip-api.com/json");
    }
    else if(event.message.toLowerCase() == "testdbget") {
        context.simpledb.doGet("putby")
    }
    else if(event.message.toLowerCase() == "testdbput") {
        context.simpledb.doPut("putby", event.sender);
    }
    else {
        context.sendResponse('No keyword found : '+event.message); 
    }
    */
    var react = [];
    var prod = [];
    react.push({num:1,elem:createChemicalFormula("H2")});
    react.push({num:1,elem:createChemicalFormula("O")});
    prod.push({num:1,elem:createChemicalFormula("H2O")});
    balanceEquation(react,prod);
    context.sendResponse("hi");
}
/** Functions declared below are required **/
function EventHandler(context, event) {
    if(! context.simpledb.botleveldata.numinstance)
        context.simpledb.botleveldata.numinstance = 0;
    numinstances = parseInt(context.simpledb.botleveldata.numinstance) + 1;
    context.simpledb.botleveldata.numinstance = numinstances;
    context.sendResponse("Thanks for adding me. You are:" + numinstances);
}

function HttpResponseHandler(context, event) {
    // if(event.geturl === "http://ip-api.com/json")
    context.sendResponse(event.getresp);
}

function DbGetHandler(context, event) {
    context.sendResponse("testdbput keyword was last get by:" + event.dbval);
}

function DbPutHandler(context, event) {
    context.sendResponse("testdbput keyword was last put by:" + event.dbval);
}
function createChemicalFormula(string){
   var formula = {};
   formula.name = string;
   formula.elems=[];
   var temp = "";
   var tempNum="";
   for(var i = 0;i<string.length;i++){
       if(isLetter(string[i])) {
           if (tempNum.length == 0) {
               temp += string[i];
           }else {
               formula.elems.push({elemNum:/*elemTable[temp]*/temp, amount: (tempNum.length==0?1:+tempNum)});
               tempNum = "";
               temp = string[i];
           }
       }else if (string.charCodeAt(i)<=57&&string.charCodeAt(i)>=48) {
           tempNum+=string[i];
       }
   }
   formula.elems.push({elemNum:/*elemTable[temp]*/temp, amount: (tempNum.length==0?1:+tempNum)});
   console.log(formula);
   return formula
}
function balanceEquation(reactants, products) {
    console.log("reactants",reactants,"products: ",products);
	var reactantElements = {};
	for (var i = 0; i < reactants.length; i++) {
	    console.log(i);
		for (var j = 0; j < reactants[i].formula.elems.length; j++) {
		    console.log(j);
			if (reactantElements[reactants[i].formula.elems[j].elemNum]) {
				reactantElements[reactants[i].formula.elems[j].elemNum] += reactants[i].formula.elems[j].amount;
			}
			else {
				reactantElements[reactants[i].formula.elems[j].elemNum] = reactants[i].formula.elems[j].amount;
			}
		}
	}
	var productElements = {};
	for (var i = 0; i < products.length; i++) {
	    console.log(i);
		for (var j = 0; j < products[i].elems.length; j++) {
		    console.log(j);
			if (productElements[products[i].elems[j].elemNum]) {
				productElements[products[i].elems[j].elemNum] += products[i].elems[j].amount;
			}
			else {
				productElements[products[i].elems[j].elemNum] = products[i].elems[j].amount;
			}
		}
	}
	console.log(reactantElements);
	console.log(productElements);
}

function isLetter(str) {
  return str.length === 1 && str.match(/[a-z]/i);
}
