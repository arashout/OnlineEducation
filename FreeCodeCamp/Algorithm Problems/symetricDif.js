function sym(args) {
	var all_numbers = {};
	var dif_array = [];
	var num;
  for( var i in arguments){
  	for(var j in arguments[i]){
  		num = arguments[i][j];
  		if(num in all_numbers){
  			if(!(i in all_numbers[num])){
  				all_numbers[num].push(i);
  			}
  		}
  		else{
  			all_numbers[num] = [i];
  		}
  	}
  }
  for(var k in all_numbers){
  		if(all_numbers[k].length === 1){
  			dif_array.push(k);
  		}
  }
  return dif_array
}



