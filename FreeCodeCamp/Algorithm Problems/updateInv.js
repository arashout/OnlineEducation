
function updateInventory(arr1, arr2) {
    // All inventory must be accounted for or you're fired!
  var updatedArr = arr1;
  var currentItem;
  var currentAmount;
  var j;
  for(var i in arr2){
    currentAmount = arr2[i][0];
    currentItem = arr2[i][1];
    j = 0;
    while(j < arr1.length){
      if(arr1[j][1] === currentItem){
        break;
      }
      j++;
      if(j >= arr1.length){
        j = -1;
        break;
      }
    }
    if(j !== -1){
      arr1[j][0] += currentAmount;
    }
    else{
      arr1.push([currentAmount, currentItem]);
    }
    
  }
    return updatedArr;
}

// Example inventory lists
var curInv = [
    [21, "Bowling Ball"],
    [2, "Dirty Sock"],
    [1, "Hair Pin"],
    [5, "Microphone"]
];

var newInv = [
    [2, "Hair Pin"],
    [3, "Half-Eaten Apple"],
    [67, "Bowling Ball"],
    [7, "Toothpaste"]
];

updateInventory(curInv, newInv);
