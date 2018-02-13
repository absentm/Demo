#!/usr/bin/env bash

# --------------------------------------------
# shell_test.sh
#
# Copyright(C) By AbsentM. 2018
#
# Author: AbsentM
# Date:   2018/02/12
#
# Description:
# bash script to the basic test
#
# -------------------------------------------

# test echo
echo "Hello, everyone!"

# define variable
show_message="Hello, variable!"
echo ${show_message}

# define readonly variable
url_address="www.baidu.com"
readonly url_address
echo "${url_address} is a read only variable"

# delete variable
unset show_message
# unset url_address, cannot delete readonly variable
echo "Now the show_message is ${show_message}."

# get string's length
one_str="abcdefg"
echo "${one_str}'s length is ${#one_str}"

# get substr of one_str
echo "${one_str}'s sub str is ${one_str:1:4}"

# find substr of one_str
echo `expr index ${one_str} c`

# define array
array_name=(value0 value1 value2 value3 value4)

# get element of array
echo "get second element of ${array_name} is ${array_name[1]}"

# print all array element
echo "get all element of ${array_name}: ${array_name[@]}"

# get array size
array_length1=${#array_name[@]}
array_length2=${#array_name[*]}
echo "The length of array is ${array_length1}"
echo "The length of array is ${array_length2}"

# test bash basic operation
# NOTE: operation two side hava " "
var_add=`expr 2 + 2`
echo "+: ${var_add}"

var_1=10
var_2=20

echo "10==20?:"
[ ${var_1} -eq ${var_2} ]

echo "10!=20?:"
[ ${var_1} -ne ${var_2} ]

echo "10>20?:"
[ ${var_1} -gt ${var_2} ]

echo "10<20?:"
[ ${var_1} -lt ${var_2} ]

echo "10>=20?:"
[ ${var_1} -ge ${var_2} ]

echo "10<=20?:"
[ ${var_1} -le ${var_2} ]

echo "10 or 20?:"
[[ ${var_1} || ${var_2} ]]

echo "10 and 20?:"
[[ ${var_1} && ${var_2} ]]

echo "10 huo 20?:"
[ ${var_1} -o ${var_2} ]

echo "10 yu 20?:"
[ ${var_1} -a ${var_2} ]


# test string operation
string_1="abc"
string_2="aaa"

echo "abc == aaa?:"
[ ${string_1} = ${string_2} ]

echo "abc != aaa?:"
[ ${string_1} != ${string_2} ]

echo "len(abc) == 0?:"
[ -z ${string_1} ]

echo "len(abc) != 0?:"
[ -n ${string_1} ]

echo "abc is empty?:"
[ ${string_1} ]

# get user input
read input_string
echo "User input is: ${input_string}"

# get '\n'
echo -e "OK! \n"
echo "-------------"

# get '\c'
echo -e "OK! \c"
echo "-------------"

# get ">"
echo "-------------" > test.log

# show command
echo `date`

# test printf
printf "%-10s %-8s %-4.2s\n" zhangsan man 66.12345
printf "%-10s %-8s %-4.2s\n" lisi women 46.12345

# use 'test' command
num1=100
num2=200

if test $[num1] -eq $[num2]
then
    echo "eq"
else
    echo "ne"
fi

echo "100+200 = $[num1+num2]"

# test for
for loop in 1 2 3 4 5 6; do
    echo "Value is: ${loop}"
done

# test case
echo "Please input a number(1 - 3): "
read input_number
case ${input_number} in
    1) echo "is 1." ;;
    2) echo "is 2." ;;
    3) echo "is 3." ;;
    *) echo "is not in 1-3." ;;
esac

# test function
showMsg() {
    echo "Welcome function!"
}

# use function
showMsg

arr=(1 2 3 4 5 6 7 8)
for item in ${arr[@]}; do
    echo ${item}
done

# simple process bar
process_bar=""
process_counter=0
process_total=20
process_array=(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20)

for loop in ${process_array[@]}; do
	process_bar+="#"
	process_counter=$((${process_counter}+1))
	process_percent=""
	process_percent=`echo "scale=2; ${process_counter} / ${process_total} * 100" | bc`
	printf "[%-20s] %s%%" ${process_bar} ${process_percent}
	printf "\n"
done

index=0
bar=""
array=("-" "\\" "|" "/")

while [ $index -le 100 ]; do
	let item=i%4
	printf "\e[31m\033[40m[%-100s]\e[32m\033[47m [%d%%] \e[30m \033[47m [%c] \e[0m\r" "${bar}" "${index}" "${array[${item}]}"
	bar+="#"
	usleep 50000
	let index++
done
printf "\n"
