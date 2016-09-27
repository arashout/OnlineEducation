function sumPrimes(limit) {
	var sumNums = 0;
	var intArr = [];
	var primesArr = [2];
	//Populate array
	for(var c = 3; c <= limit; c++){
		intArr.push(c);
	}
	var i = 0;
	var j = 0;
	var curPrime;
	while(primesArr[j] <= limit){
		curPrime = primesArr[j];
		//Loop through remaining integer array
		i = 0;
		while(intArr[i] <= limit){
			//If divisable by current prime
			if(intArr[i] % curPrime === 0){
				intArr.splice(i, 1);
			}
			else{
				i++;
			}
		}
		if(intArr.length !== 0) primesArr.push(intArr[0]);
		j++;
	}
	for(var k in primesArr){
		sumNums += primesArr[k];
	}
	return sumNums;
}

sumPrimes(11);