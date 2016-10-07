/*Return an English translated sentence of the passed binary string.
The binary string will be space separated.*/
function binaryAgent(str) {
	var arrBinary = str.split(" ");
	var arrLetters = [];
	var letter;
	var decVal;
	for(var i in arrBinary){
		decVal = bin2Dec8Bit(arrBinary[i]);
		arrLetters.push(String.fromCharCode(decVal));
	}
	var newStr = arrLetters.join('');
  return newStr;
}
function bin2Dec8Bit(binaryStr){
	var decValue = 0;
	var binaryPower = 0;
	for(var j = binaryStr.length - 1; j >= 0; j--){
		decValue += parseInt(binaryStr[j]) * Math.pow(2,binaryPower);
		binaryPower++;
	}
	return decValue;
}
console.log(binaryAgent("01001001 00100000 01101100 01101111 01110110 01100101 00100000 01000110 01110010 01100101 01100101 01000011 01101111 01100100 01100101 01000011 01100001 01101101 01110000 00100001"));