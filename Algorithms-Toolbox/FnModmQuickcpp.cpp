#include <iostream>
#include <vector>
#include <algorithm>    // std::search

using namespace std;

long long get_fibonaccihuge(long long n, long long m) {

	long long previous = 0;
	long long current = 1;
	long long next;
	long long period = 0;
	long long remainder;
	//write your code here
	vector<long long> mod_nums = { previous % m, current % m };
	long long needle[] = { 0,1 }; //Pisano period start 

	vector<long long>::iterator it;

	while (1 < 2) {
		next = (previous % m) + (current % m);
		mod_nums.push_back(next % m);
		previous = next - previous % m;
		current = next % m;
		period++;
		/*
		it = search(mod_nums.begin() + 1, mod_nums.end(), needle, needle + 2);
		if (it != mod_nums.end()) { //If pattern found jump out of loop
			period = (it - mod_nums.begin());
			//cout << "needle1 found at position " << period << '\n';
			break;
		}
		*/
		if (previous == 0 && current == 1) {
			break;
		}
		if (n < mod_nums.size()) {
			break;
		}
	}
	/*
	for (long long j = 0; j < mod_nums.size() - 1; j++) {
		cout << mod_nums[j] << ",";
	}
	cout << endl << period << endl;
	*/
	//RESULT
	if (n >= mod_nums.size()) {
		long long position = n % period;
		remainder = mod_nums[position];
	}
	else {
		remainder = mod_nums[n];
	}
	return remainder;
}

int main() {
	long long n, m;
	cin >> n >> m;
	cout << get_fibonaccihuge(n, m) << '\n';
	//system("PAUSE");
	return 0;
}
