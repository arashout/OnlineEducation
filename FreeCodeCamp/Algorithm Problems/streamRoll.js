//Flatten a nested array. You must account for varying levels of nesting.
function steamrollArray(arr) {
  // I'm a steamroller, baby
  return arr;
}

steamrollArray([1, [2], [3, [[4]]]]);
steamrollArray([[["a"]], [["b"]]]) === ["a","b"];
steamrollArray([1, [2], [3, [[4]]]]) === [1,2,3,4];
steamrollArray([1, [], [3, [[4]]]]) === [1,3,4];
steamrollArray([1, {}, [3, [[4]]]]) === [1, {}, 3, 4];