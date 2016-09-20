#include <vector>
#include <iostream>

using namespace std;

vector<int> poly_product(int n, vector<int> &a, vector<int> &b) {
	//Create 2 array with multiplied pairs
	vector<vector<int>> pairs (n,vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			pairs[i][j] = a[i] * b[j];
			//cout << pairs[i][j] << ",";
		}
		//cout << endl;
	}
	vector<int> p(2 * n - 1, 0);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			p[i + j] += pairs[i][j];
			//cout << pairs[i][j] << ",";
		}
		//cout << endl;
	}
	return p;

}

int main() {
	int n;
	cin >> n;

	vector<int> a(n), b(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	for (int i = 0; i < n; i++) {
		cin >> b[i];
	}

	vector<int> prod = poly_product(n, a, b);
	//Prints ouput
	
	for (size_t i = 0; i < prod.size(); ++i) {
		cout << prod[i] << " ";
	}
	
	system("PAUSE");
	return 0;
}