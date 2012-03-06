




#!/bin/bash
current=`date +%s`
update=`date -r "$1" +%s`
((dist = current - update ))
((dist = dist /(60*60*24) ))
echo $dist


echo "$#"


if [[ $# < 3 ]]
then
    echo "Нехавтает аргументов"
    exit
fi
i=0
while read line
do
    ms[$i]="$line"
    echo "$i: $line"
    ((i=1+i)) 
done < $1

declare -a a     
i=1; j=2    
a[$i,0]=5
a[$i,1]=8
echo ${a[$i,0]}
echo ${a[$i]}
echo "$variable"
echo -e '\E[37;41m'"\033[1m \033[0m"
str2="some"
str='\E[37;41m '


function coord
{
    echo "$1 : $2"
}


coord $((i+1)) $((j+1))     
n=4
m=4

i=3
j=2
visited[15]="кавабанга"
visited[$((i*m+j))]=44773
((l=i*m+j))
echo "${visited[$((i*m+j))]}"

if ((visited[l] == 44773))
then
    echo "афигеть удалось!"
fi


exit 




declare -a visited
echo "пустой массив посещенных"

for (( k=0; k < n; k++ ))
do
    for(( l=0; l < m; l++ ))
    do
        visited[$k, $l]=0
        printf "%s" ${visited[$k, $l]}     
    done
    printf "\n"
done


function dfs
{
    local x=$1
    local y=$2
    if ((x >= 0 && x <n && y >= 0 && y < m ))
    then
        cell=${ms[$x]:$y:1} 
        
        if [[ $cell  == "0" ]]
        then
            if [[ ${visited[$x, $y]} == 0 ]]
            then
                visited[$x, $y]=1                
                dfs $((x+1)) $((y))
                dfs $((x-1)) $((y))
                dfs $((x)) $((y+1))
                dfs $((x)) $((y-1))                       
            fi             
        fi
    fi       
}

dfs $start_x $start_y

symb="x"

echo "по идее заполненный массив посещенных"



for (( k=0; k < n; k++ ))
do
    new_str=""
    for(( l=0; l < m; l++ ))
    do
        if [[ ${visited[$k, $l]} == 0 ]]
        then
            echo "Ура мы сдесь были"
            new_str=$new_str${d[$k]:$l:1}            
        else
            new_str="$new_str$symb"
        fi    
    done
    echo "$new_str"
done





