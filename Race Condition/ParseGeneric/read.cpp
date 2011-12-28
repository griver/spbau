#include <string>
#include <iostream>
#include <map>
#include <set>
#include <fstream>
#include <vector>
using namespace std;

struct Node
{
    string name;
    string type;
    map<string,string> attr;  
};


vector< Node > tree;


void read_tree(string const &file)
{
    Node current; 
    string tmp = "", last_attr = "", apl="";
    bool is_value = false;
    
    ifstream in(file.c_str());
    
    while(in>>tmp)
    {
        if(!is_value)
        {
            
            if(tmp[0] == '@')
            {
                tree.push_back(current);
                current.attr.clear();
                current.name = tmp;
                in>>current.type;
            }
            else
            {
                if(tmp[tmp.size()-1] !=':')
                {
                    in>>apl;
                    tmp+=apl;
                }
                current.attr[tmp] = "";
                last_attr = tmp;
                is_value = true;
            }            
        }
        else
        {
            current.attr[last_attr] = tmp;
            is_value = false;
        }
                
    }

}


void print_node(Node& node)
{
    cout<<"Node ("+node.name+")"<<endl;
    string t = "    ";
    cout<< t+"Node_type = "+node.type<<endl;
    map<string, string>::iterator it = node.attr.begin();
    for(it; it!=node.attr.end(); ++it)
        cout<<t+it->first+" = "+it->second<<endl;
    cout<<endl;
}

void print_tree()
{
    vector<Node>::iterator it = tree.begin();
    it++;
    for(it; it!= tree.end(); ++it)
        print_node(*it);
}

void print_node_types()
{
    set<string> node_types;
    vector<Node>::iterator it = tree.begin();
    it++;
    for(it; it != tree.end(); ++it)
    {
        node_types.insert(it->type);
    }
    
    cout<<node_types.size()<<endl;
    set<string>::iterator node_it = node_types.begin();
    for(node_it; node_it != node_types.end(); ++node_it)
        cout<<node_it->c_str()<<endl;
}

int main(int argc, char* argv[])
{
    bool print_types = false;
    string file = "";
    if(argc > 1)
    {
        file = argv[1];
        if(argc > 2)
            print_types = true;
    }
    else
        file = "input.txt";
    
    read_tree(file);
    if(print_types)
        print_node_types();
    else
        print_tree();
    return 0;
}
