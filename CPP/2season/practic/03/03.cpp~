#include <vector>
#include <deque>
#include <iostream>
#include <algorithm>

template <typename Z > 
struct Element {
public:
    Z _item;
    Element<Z>* _next;
};

template <class T>
class Iterator {
private:
    Element<T>* _pointer;

private: 
    void copy_Iterator(Iterator<T> const &t) {
        _pointer = t._pointer;
    }

public:
    Iterator() : _pointer(NULL){}
    
    Iterator(Element<T>* p) : _pointer(p) {}

    Iterator(Iterator<T> const &t) : _pointer(NULL) {
        copy_Iterator(t);
    }

    Iterator<T>& operator=(Iterator<T> const &t) {
        copy_Iterator(t);
        return *this;
    }
    
    Iterator<T>& operator++() { 
        _pointer = _pointer->_next;
        return *this
    } 

    Iterator<T> operator++(int) {
        Iterator<T>* iter = this;
        this->operator++();
        return *iter;   
    }
    
    T& operator*() {
        return _pointer->_item;
    }

    T* operator->() {
        return &(_pointer->_item); 
    }

    bool operator==(Iterator<T> const &t) {
        if(t._pointer == this->_pointer)
            return true;
        else
            return false;
    }

    bool operator!=(Iterator<T> const &t) {
        return !(this->operator==(t));
    }
}; 



template <typename T >
class List {
private:
    int _size;
    Element<T>* _first;
    Element<T>* _end;

public:
    typedef Iterator<T> iterator;

    List(): _first(NULL), _end(NULL), _size(0) {
        _first = _end;
    }       

	int size() const {
		return _size;
	}
	
	void push_front(T value) {
		Element<T>* second = _first;
        _first = new Element<T>();
        _first->_item = value;
        _first->_next = second;
        _size++;
	}

	void pop_front() { 
		Element<T>* for_delete = _first;
        _first = for_delete->_next;
        _size--;
        delete for_delete;
	}

    Iterator<T> begin() const {
        return Iterator<T>(_first);
    }
    
    Iterator<T> end() const {
        return Iterator<T>(_end);
    }
       
};


int main() {

    std::cout << "какой то текст успешности"<<std::endl;
    List<int> list;
    list.push_front(1);
    list.push_front(2);
    list.push_front(4);
    list.push_front(8);
    List<int>::iterator it = list.begin();
    for(it; it!=list.end(); ++it )
        std::cout<<*it<<std::endl;
	return 0;
}
