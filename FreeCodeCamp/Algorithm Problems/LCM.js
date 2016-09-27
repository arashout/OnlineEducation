function smallestCommons(arr) {
  arr.sort(function(a, b){return a-b;});
  console.log(arr);
  return arr;
}

smallestCommons([1,5]) === 60;
smallestCommons([5,1]) === 60;
smallestCommons([1,13]) === 360360;
smallestCommons([28,18]) === 6056820;
