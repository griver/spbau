#!/bin/bash

 ## open file test.data for reading 
 #exec 6<text 
 ## read until end of file 
 #while read -u 6 dta 
 #do 
 #  echo "$dta" 
 #done 
 ## close file test.data 
 #exec 6<&-

symbols[0]='O'
symbols[1]='║'
symbols[2]='═'
symbols[3]='╚'
symbols[4]='║'
symbols[5]='║'
symbols[6]='╔'
symbols[7]='╠'
symbols[8]='═'
symbols[9]='╝'
symbols[10]='═'
symbols[11]='╩'
symbols[12]='╗'
symbols[13]='╣'
symbols[14]='╦'
symbols[15]='╬'

i=0

while read line
do
    ms[$i]="$line"
    d[$i]="$line"
    ((i=1+$i)) 
done

n=$i
m=${#ms[0]}
h_border=$b
b="*"
echo "$n"
for (( i=0; i<m; i++ ))
do
    h_border=$h_border$b
done 

echo "$b$h_border$b"

for (( i=0; i<n; i++ ))
do
    str=""
    for ((j=0; j<m; j++))
    do
        sym=${ms[$i]:$j:1}
        if [[ $sym == "1" ]]
        then
            nb=0
            #проверяем верхнюю клетку
            if((i >= 1))
            then
                if [[ ${ms[$((i-1))]:$j:1} == "1" ]]
                then
                    ((nb = nb+1))    
                fi
            fi
            #проверяем нижнюю
            if(( i < n-1 ))
            then
                if [[ ${ms[$((i+1))]:$j:1} == "1" ]]
                then
                    ((nb = nb+4))    
                fi
            fi
            #проверяем левого соседа
            if((j >= 1))
            then
                if [[ ${ms[$i]:$((j-1)):1} == "1" ]]
                then
                    ((nb = nb+8))    
                fi
            fi
            #проверяем правого соседа
            if((j < m-1))
            then
                if [[ ${ms[$i]:$((j+1)):1} == "1" ]]
                then
                    ((nb = nb+2))    
                fi
            fi
            
            sym=${symbols[$nb]}                                
        else
            sym=" "
        fi
        str=$str$sym    
    done
    d[$i]="$str"
    echo "$str"
done

echo "$b$h_border$b"


