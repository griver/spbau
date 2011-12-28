int special_function(int* super_function_variable)
{
    *super_function_variable += 1;
    int returned_var = (*super_function_variable)%2;
    *super_function_variable = returned_var;
    return *super_function_variable;
}

int main()
{
    int important_var = 0;
    special_function(&important_var);
    return 0;    
}
