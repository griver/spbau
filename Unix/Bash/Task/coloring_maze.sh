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

if [[ $# < 3 ]]
then
    echo "Нехавтает аргументов"
    exit
fi

while read line
do
    ms[$i]="$line"
    d[$i]="$line"
    ((i=1+$i)) 
done < $1

start_x=$2
start_y=$3

n=$i
m=${#ms[0]}
h_border=$b
b="."


for (( i=0; i<m; i++ ))
do
    h_border=$h_border$b
done 


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
   # echo "$str"
done

#echo ${ms[$start_x]:$start_y:1}
if [[ ${ms[$start_x]:$start_y:1} == "1" ]]
then
    echo "Вы оказались в стене и умерли"
    exit
fi
 
for (( k=0; k < n*m; k++ ))
do
    visited[$k]=0
done

((start=start_x*m+start_y))
visited[start]=1
queue_x[0]=$start_x
queue_y[0]=$start_y
hd=0
tl=1


function move
{
    echo "move:"
    x_1=$1
    y_1=$2
    echo "move to $x_1 $y_1"
    if ((x_1 >= 0 && x_1 < n && y_1 >= 0 && y_1 < m ))
    then
        echo "прошел проверку на границы $x_1 $y_1"
        if [[ ${ms[$x_1]:$y_1:1} == "0" ]]
        then
            echo "прошел проверку на то что НЕ СТЕНА $x_1 $y_1"
            ((l=x_1*m+y_1))
            if (( visited[l] == 0 ))
            then
                echo "Не был посещен и  $x_1 $y_1"
                visited[$l]=1
                queue_x[$tl]=$x_1
                queue_y[$tl]=$y_1
                ((tl++))
            fi        
        fi        
    fi
}

function wave
{    
    while ((hd < tl ))
    do

        x=${queue_x[$hd]}
        y=${queue_y[$hd]}
        echo "Голова: $hd Хвост: $tl"
        echo "Текущая вершина волны:[$x,$y]"                
        move $((x + 1)) $y  
        move $((x - 1)) $y
        move $x $((y + 1))
        move $x $((y - 1))
        ((hd++))
    done
}

wave

symb="x"

echo "$b$h_border$b"
for (( k=0; k < n; k++ ))
do
    #new_str="$b"
    echo -n "$b"
    for(( l=0; l < m; l++ ))
    do
        ((ind=k*m+l))
        #printf "%s" ${visited[$ind]}
        if (( visited[ind] == 0 ))
        then
            #new_str=$new_str${d[$k]:$l:1}
            echo -n "${d[$k]:$l:1}"            
        else
            #new_str="$new_str$symb"
            echo -ne '\E[37;41m'"\033[1m \033[0m"
        fi    
    done
    echo "$b"
    #echo "$new_str$b"        
done
echo "$b$h_border$b"









