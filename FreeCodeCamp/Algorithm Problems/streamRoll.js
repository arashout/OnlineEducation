//Flatten a nested array. You must account for varying levels of nesting.
function steamrollArray(arr) {
  // I'm a steamroller, baby
  var flatArr = [];
  for(var i in arr){
  	flatArr.push(dig(arr[i]));
  }
  return flatArr;
}
function dig(arr, appendArr){
	if(Array.isArray(arr)){
		return dig(arr[0]);
	}
	else{
		return arr;
	}
}

console.log(steamrollArray([[["a"]], [["b"]]]));
console.log(steamrollArray([[["a"]], [["b"]]]) === ['a','b']);
console.log(steamrollArray([1, [2], [3, [[4]]]]) === [1,2,3,4]);
console.log(steamrollArray([1, [], [3, [[4]]]]) === [1,3,4]);
console.log(steamrollArray([1, {}, [3, [[4]]]]) === [1, {}, 3, 4]);