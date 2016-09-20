#include <iostream>
#include <vector>

using namespace std;


vector<int> optimal_summands(int n) {
  vector<int> summands;
  //write your code here
  int i = 1;
  while (n > 0) {
	  if (n <= 2*i) {
		  i = n;
		  summands.push_back(i);
		  n -= i;
	  }
	  else {
		  summands.push_back(i);
		  n -= i;
	  }
	  i++;
  }
  return summands;
}

int main() {
  int n;
  cin >> n;
  vector<int> summands = optimal_summands(n);
  cout << summands.size() << '\n';
  for (size_t i = 0; i < summands.size(); ++i) {
    cout << summands[i] << ' ';
  }
  //system("PAUSE");
  return 0;
}
