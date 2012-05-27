#include <iostream>
#include <vector>
#include <set>
#include <climits>
#include <utility>

using std::vector;
using std::set;
using std::pair;

struct Edge {
    int target;
    int distance;
    Edge(int target, int distance) : target(target), distance(distance) {}
    Edge() : target(0), distance(0) {}
};

struct Data {
    int n;
    int m;
    int s;
    int l;
};

const int MAX_WEIGHT = 1000;


void read(Data &data, vector< vector<Edge> > &graph) {
    std::cin>> data.n >> data.m >> data.s;
    graph.resize(data.n + 1);
    
    int source, target, weight;
    for(int i = 0; i < data.m; ++i) {
        std::cin >> source >> target >> weight;
        graph[source].push_back(Edge(target, weight));
        graph[target].push_back(Edge(source, weight));
    }
    std::cin >> data.l;
}

int main() {

    Data data;
    vector< vector<Edge> > graph;
    vector< int > dist;
    vector< bool > visited;
    set< pair< int, int > > priority_queue;

    read(data, graph);

    visited.assign(data.n + 1, 0);
    dist.assign(data.n + 1, INT_MAX);
    dist[data.s] = 0;
    dist[0] = -1;

    priority_queue.insert(pair<int, int>(dist[data.s], data.s));

    int vertex = 0;
    int target = 0; 
    int weight = 0;
    int topSecretNumber = 0;
    int intersectTest = 0;

    while(!priority_queue.empty()) {

        vertex = priority_queue.begin()->second;
        visited[vertex] = true;

        if(dist[vertex] >= data.l + MAX_WEIGHT) //if we too far from capital
            break;

        //count vertiсes        
        if(dist[vertex] == data.l)
            ++topSecretNumber;

        priority_queue.erase(priority_queue.begin());

        for(size_t i = 0; i < graph[vertex].size(); ++i) {

            target = graph[vertex][i].target;
            weight = graph[vertex][i].distance;

            if(dist[vertex] + weight < dist[target]) {

                priority_queue.erase(pair<int, int>(dist[target], target));
                dist[target] = dist[vertex] + weight;
                priority_queue.insert(pair<int, int>(dist[target], target));
            }

            // count edges
            if(visited[target]) {
                if(dist[target] < data.l) {
                    intersectTest = (dist[target] - data.l) + weight - (data.l - dist[vertex]);
                    if(intersectTest >= 0 ) {
                        if(intersectTest > 0) ++topSecretNumber;
                        if(dist[vertex] < data.l) ++topSecretNumber;
                    }
                }
            }
        }
    }

    std::cout << topSecretNumber << std::endl;
    return 0;
}
