#include <iostream>
#include <cassert>
#include <vector>

using namespace std;

int binary_search(const vector<int> &a, int x, int low = 0,int high = -1) {
    if (high == -1) {
        high = (int)a.size();
    }
    //write your code here
    int m = (high - low) / 2 + low;
    if (a[m] == x) {
        return m;
    }
    else if ((high - low) == 1) {
        return -1;
    }
    else if (a[m] < x) {
        return binary_search(a, x,m,high);
    }
    else {
        return binary_search(a, x,0,m);
    }
}        

int main() {
	int n;
	cin >> n;
	vector<int> a(n);
	for (size_t i = 0; i < a.size(); i++) {
		cin >> a[i];
	}
	int m;
	cin >> m;
	vector<int> b(m);
	for (int i = 0; i < m; ++i) {
		cin >> b[i];
	}
	for (int i = 0; i < m; ++i) {
		//replace with the call to binary_search when implemented
		//cout << linear_search(a, b[i]) << ' ';
		//cout << binary_search(a, b[i]) << ' ';
		cout << binary_search_iter(a, b[i]) << ' ';
	}
	//system("PAUSE");
	return 0;
}
