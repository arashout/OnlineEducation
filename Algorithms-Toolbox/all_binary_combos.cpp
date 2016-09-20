// Example program
#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
#include <cmath>

using namespace std;

void all_binary_combos(int n) {
	//Print all combinations except all zeros
	vector<int> A(n, 0);
	for (size_t i = 0; i < pow(2, n) - 1; i++) {
		int j = n - 1;
		while (A[j] != 0 && j > 0) {
			A[j] = 0;
			j--;
		}
		A[j] = 1;
		/*
		//For testing purposes
		for (size_t k = 0; k < A.size(); k++) {
			cout << A[k] << ',';
		}
		cout << endl;
		*/
	}
	return;
}

int main()
{
	unsigned int s;
	cin >> s;
	all_binary_combos(s);
	system("PAUSE");
	return 0;
}
