function sym(args) {
  function diff(arr1, arr2){
    var dif_arr = [];
    for(var i in arr1){
      if(arr2.indexOf(arr1[i]) === -1 & dif_arr.indexOf(arr1[i]) === -1){
        dif_arr.push(arr1[i]);
      }
    }
    for(var j in arr2){
      if(arr1.indexOf(arr2[j]) === -1 & dif_arr.indexOf(arr2[j]) === -1){
        dif_arr.push(arr2[j]);
      }
    }
    return dif_arr;
  }
  var listOfLists = [];
  for(var k in arguments){
    listOfLists.push(arguments[k]);
  }
  var dif_array = listOfLists.reduce(diff);
  console.log(dif_array);
  return dif_array;
}

sym([1, 1, 2, 5], [2, 2, 3, 5], [3, 4, 5, 5]) ;
