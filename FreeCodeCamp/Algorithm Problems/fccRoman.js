
String.prototype.reverse=function(){return this.split("").reverse().join("");}
function convertToRoman(number) {
	var romanNumValueArr = [
	{num: "I", value: 1},
	{num: "V", value: 5},
	{num: "X", value: 10},
	{num: "L", value: 50},
	{num: "C", value: 100},
	{num: "D", value: 500},
	{num: "M", value: 1000},
    ];
    var numString = number.toString();
    numString = numString.reverse();
    var romanString = "";
    var currentDigit;
    var lookUpArr;
    var lookUpDigit;
    var addTo = 0;
    var lookUpTable = {
    	"0": [],
    	"1": [0],
    	"2": [0, 0],
    	"3": [0, 0, 0],
    	"4": [0, 1],
    	"5": [1],
    	"6": [1, 0],
    	"7": [1, 0, 0],
    	"8": [1, 0, 0, 0],
    	"9": [0, 2]
    };
    for(var i = 0; i < numString.length; i++){
    	currentDigit = numString[i];
    	lookUpArr = lookUpTable[currentDigit];
    	var temp = "";
    	if(i > 0){
    		addTo += 2;
    	}
    	for(var j = 0; j < lookUpArr.length; j++){
    		lookUpDigit = lookUpArr[j] + addTo;
    		//console.log(lookUpDigit + ' ' + romanNumValueArr[lookUpDigit].num);
    		temp += romanNumValueArr[lookUpDigit].num;
    	}
    	romanString = temp + romanString;
    }

	return romanString;
}
console.log(convertToRoman(501));
