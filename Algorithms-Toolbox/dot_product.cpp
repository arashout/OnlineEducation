#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

long long min_dot_product(vector<long> a, vector<long> b) {
	long long result = 0;
	sort(a.begin(), a.end());
	sort(b.begin(), b.end());
	reverse(b.begin(), b.end());
	for (int i = 0; i < a.size(); i++) {
		result += a[i] * b[i];
	}
	return result;

}

int main() {
  size_t n;
  cin >> n;
  vector<long> a(n), b(n);
  for (size_t i = 0; i < n; i++) {
    cin >> a[i];
  }
  for (size_t i = 0; i < n; i++) {
    cin >> b[i];
  }
  cout << min_dot_product(a, b) << endl;
  //system("PAUSE");
  return 0;

}
