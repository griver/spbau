/* 
 * File:   main.cpp
 * Author: griver
 *
 * Created on 28 Март 2012 г., 2:06
 */

#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

//http://codeforces.ru/problemset/problem/17/A
void problemP() {
    int n = 0,k = 0;
    vector<int> numbers, primes;
    
    cin >> n >> k;
    numbers.assign(n + 1, 0);
    primes.assign(0, 0);
    
    for(int i = 2; i <= n; ++i) {
        if(numbers[i] == 0) {
            numbers[i] = i;
            primes.push_back(i);
        }
        
        for(int j =0; j < primes.size() && i * primes[j] <= n; ++j) {
            numbers[ i * primes[j] ] = primes[j];
        }
    }
    
    int NoldbachNumber = 0;
    for(int i = 1; i < primes.size();  ++i) {
        int check = primes[i] + primes[i - 1] + 1;
        
        if(check > n) break; 
        
        if(numbers[check]  == check) ++NoldbachNumber; 
    }
    
    if(NoldbachNumber >= k) 
        cout << "YES" << endl;
    else
        cout << "NO" << endl;
    
    /*for(int i = 0; i < primes.size();  ++i) 
        cout << primes[i] << " ";
    cout << endl << NoldbachNumber << endl;*/
    
}


int main(int argc, char** argv) {
    problemP();
    return 0;
}

