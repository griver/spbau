/* 
 * File:   problemZ.cpp
 * Author: griver
 *
 * Created on 28 Март 2012 г., 14:03
 */

#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <vector>
#include <climits>
#include <cstdio>
using namespace std;

struct edge {
    int diameter;
    int target;
    
    edge() : diameter(0), target(0) {}       
    edge(int diameter, int target) : diameter(diameter), target(target) {} 
    
};


//http://codeforces.ru/problemset/problem/107/A
void problemZ() {
    int n = 0,  p = 0;
    vector<int> sources;
    vector<edge> buildings;
    
    cin >> n >> p;
    sources.assign(n + 1, 0);
    buildings.assign(n + 1, edge(0,0));
    
    int a, b, d;
    for(int i = 0; i < p; ++i) {
        cin >> a >> b >> d;
        sources[b] = 1;
        buildings[a] = edge(d, b);
    }
    
    int t = 0;
    vector<pair<int, edge> > answer;
    
    for(int i = 1; i <= n; ++i) {
        if(sources[i] == 0) {
            if(buildings[i].target != 0) {
                int target = i, diameter = INT_MAX;
                
                while(buildings[target].target != 0) {
                    diameter = min(diameter, buildings[target].diameter);
                    target = buildings[target].target;
                    
                }
                
                answer.push_back(pair<int,edge>(i, edge(diameter, target)));
                ++t;
            }
        }
    }
   
    cout << t << endl;
    for(int i = 0; i < answer.size(); ++i) {
        cout<< answer[i].first << " " <<answer[i].second.target 
            << " " << answer[i].second.diameter << endl;
    }
    
}


int main(int argc, char** argv) {
    //freopen("inputZ", "r", stdin);
    problemZ();
    return 0;
}

