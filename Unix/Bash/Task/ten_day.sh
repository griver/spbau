#!/bin/bash


function find_old
{
    for file in $1/*
    do
        if [[ -d "$file" ]]        then
            find_old "$file"
        else
            current=`date +%s`
            update=`date -r "$file" +%s`
            ((dist = current - update ))
            ((dist = dist /(60*60*24) ))
		    if (( dist >= 10 ))
            then
			    echo "$file"
		    fi    
        fi
	done
}


if [[ -n "$1" ]]
then
  if [[ -d "$1" ]]
  then
    dir=$1
  else
    echo "Да это и не каталог вовсе. Будем искать в домашнем"
    dir=$PWD
  fi
else
  dir=$PWD 
  echo "Не задали каталог ищем в домашнем"   
fi

find_old $dir


