function listPrimes(limit) {
	//Given a limit create an array of primes, using sieve technique
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
	return primesArr;
}
function primeFactorization(num, arrPrimes){
	/*
	Given a number and a list of prime numbers
	Spit out the prime factors of the number
	*/
	var primeFactors = [];
	var i = 0;
	while(num != 1){
		if(num % arrPrimes[i] === 0){
			num = num / arrPrimes[i];
			primeFactors.push(arrPrimes[i]);
		}
		else{
			i++;
		}
	}
	return primeFactors;
}
function getUniqueNums2d(arr2d){
	var uniqueArr = [];
	for(var i = 0; i < arr2d.length; i++){
		for(var j = 0; j < arr2d[i].length; j++){
			if(uniqueArr.indexOf(arr2d[i][j] != -1)){
				uniqueArr.push(arr2d[i][j]);
			}
		}
	}
	return uniqueArr;
}
function gcd(a, b) {
    if ( ! b) {
        return a;
    }

    return gcd(b, a % b);
}

function lcm(a, b){
	return a / gcd(a, b) * b;
}

function smallestCommons(arr) {
	var leastCommonMultiple;
	//Sort ascending
	arr.sort(function(a, b){return a-b;});
	//Create array of divisors
	var arrNums = [];
	for(var i = arr[0]; i <= arr[1]; i++ ){
		arrNums.push(i);
	}

	return arrNums.reduce(lcm);
}

console.log(smallestCommons([1,5]) === 60);
console.log(smallestCommons([5,1]) === 60);
console.log(smallestCommons([1,13]) === 360360);
console.log(smallestCommons([23,18]) === 6056820);
