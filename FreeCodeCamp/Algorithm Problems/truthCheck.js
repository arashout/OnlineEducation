/*
Check if the predicate (second argument) is truthy on all elements of a collection (first argument).
Remember, you can access object properties through either dot notation or [] notation.
*/

function truthCheck(collection, pre) {
  // Is everyone being true?
  var propertyVal;
  for(var i in collection){
    propertyVal = collection[i][pre];
    if(detectFalsy(propertyVal)){
    	return false;
    }
  }
  return true;
}
function detectFalsy(value){
	if(value == 0 || value == "" || value == 0) {return false;}
	if(value == undefined || value == null){ return false;}
	if(isNaN(value)) {return false;}
	return true;
}
console.log(truthCheck([{"user": "Tinky-Winky", "sex": "male"}, {"user": "Dipsy"}, {"user": "Laa-Laa", "sex": "female"}, {"user": "Po", "sex": "female"}], "sex") === false);
console.log(truthCheck([{"user": "Tinky-Winky", "sex": "male", "age": 0}, {"user": "Dipsy", "sex": "male", "age": 3}, {"user": "Laa-Laa", "sex": "female", "age": 5}, {"user": "Po", "sex": "female", "age": 4}], "age") === false);
console.log(truthCheck([{"name": "Pete", "onBoat": true}, {"name": "Repeat", "onBoat": true}, {"name": "FastFoward", "onBoat": null}], "onBoat") === true);
console.log(truthCheck([{"name": "Pete", "onBoat": true}, {"name": "Repeat", "onBoat": true, "alias": "Repete"}, {"name": "FastFoward", "onBoat": true}], "onBoat") === true);
console.log(truthCheck([{"single": "yes"}], "single") === true);
console.log(truthCheck([{"single": ""}, {"single": "double"}], "single") === false);
console.log(truthCheck([{"single": "double"}, {"single": undefined}], "single") === false);
console.log(truthCheck([{"single": "double"}, {"single": NaN}], "single") === false);