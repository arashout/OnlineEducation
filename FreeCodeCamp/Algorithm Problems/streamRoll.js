//Flatten a nested array. You must account for varying levels of nesting.
function steamrollArray(arr) {
  // I'm a steamroller, baby
  var flatArr = [];
  dig(arr, flatArr);
  return flatArr;
}
function dig(arr, appendArr){
	if(Array.isArray(arr)){
		for(var i in arr){
			dig(arr[i], appendArr);
		}
	}
	else{
		return appendArr.push(arr);
	}
}

console.log(steamrollArray([1, [2], [3, [[4]]]]));
console.log(steamrollArray([[["a"]], [["b"]]]));
console.log(steamrollArray([1, [2], [3, [[4]]]]));
console.log(steamrollArray([1, [], [3, [[4]]]]));
console.log(steamrollArray([1, {}, [3, [[4]]]]));