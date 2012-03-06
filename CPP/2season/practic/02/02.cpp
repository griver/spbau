#include <vector>
#include <deque>
#include <iostream>
#include <algorithm>


template <typename T, template<typename T, typename A = std::allocator<T> > class C >
class Container {
private:
	C<T> data;
public:
	int size() const {
		return data.size();
	}
	
	void push_back(T value) {
		data.push_back(value);
	}

	T pop_back() {
		T element = data[data.size()-1];
		data.pop_back();
		return element;  
	}

    T operator[](int index) const {
        return data[index];
    }

    template<typename T2>
    Container& operator=(Container<T2, C>& container) {
        data.resize(container.size());
        for(int i=0; i<data.size(); ++i)
            data[i]=(T)container[i];
        return *this;
    }
    
};

template <typename T, template<typename T, typename A = std::allocator<T> > class C >
class Container <T*, C> {
private:
	C<T*> data;
public:
	int size() const {
		return data.size();
	}
	
	void push_back(T* value) {
        std::cout<<"push_back()" << std::endl;
		data.push_back(value);
	}

	T* pop_back() {
		T* element = data[data.size()-1];
		data.pop_back();
		return element;  
	}
    
    ~Container() {
        std::cout<<"Destructor()" << std::endl;
        typename C<T*>::iterator iter;
        for(iter = data.begin(); iter != data.end(); ++iter) {
            delete *iter;            
        } 
        std::cout<<"End Destructor()" << std::endl;                   
    }
};


int main() {
	/*Container <int, std::vector > array;
	array.push_back(4);
	array.push_back(6);
	std::cout << "удалили элемент: " << array.pop_back() << std::endl;*/

    /*Container <int*, std::vector > pArray;
    int *pa  = new int(6);
    
    pArray.push_back(pa);
    std::cout << "удалили элемент: " << *pArray.pop_back() << std::endl;*/

    Container <int, std::vector > intArray;
    Container <float, std::vector > floatArray;
    intArray.push_back(6);
    floatArray.push_back(25.5);
    intArray = floatArray;
    std::cout << intArray[0] << std::endl;
	return 0;
}
