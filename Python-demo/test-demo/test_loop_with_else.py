# coding=utf-8

"""
 # test_loop_with_else.py
 # 
 # Copyright(C) By AbsentM. 2018
 # 
 # Author: AbsentM
 # Date: 2018/03/18
 # 
 # Description: Test python loop with else demo
 #
 # Change Log:
 # 2018/03/18 AbsentM Create the file
 # 2018/03/18 AbsentM Add test demo
 #
"""

def run_for_else_validation(fruits):
	"""
	Test python for else
	:param fruits: A string of fruits split with ','
	"""
	if fruits is None:
		fruits_result = "*"
	else:
		fruits = fruits.split(",")
		print "fruits >>> {}".format(fruits)

		for item in fruits:
			print "item >>> {}".format(item)
		else:
			fruits_result = fruits

	print "total >>> {}".format(fruits_result)


def run_while_else_validation():
	"""
	Test python while else
	"""
	index = 0
	while index <= 10:
		index += 1
		print "index {}: {} ".format(index, index)
	else:
		print "in while else"


if __name__ == '__main__':
	print "---------------------------"
	print "Run first test..."
	test_1 = None
	run_for_else_validation(test_1)
	print "Run first test finished"

	print "---------------------------"
	print "Run second test..."
	test_2 = "apple"
	run_for_else_validation(test_2)
	print "Run second test finished"

	print "---------------------------"
	print "Run third test..."
	test_3 = "apple,pear,orange"
	run_for_else_validation(test_3)
	print "Run third test finished"

	print "---------------------------"
	print "Run fourth test..."
	run_while_else_validation()
	print "Run fourth test finished"


"""
The output as follow:

---------------------------
Run first test...
total >>> *
Run first test finished
---------------------------
Run second test...
fruits >>> ['apple']
item >>> apple
total >>> ['apple']
Run second test finished
---------------------------
Run third test...
fruits >>> ['apple', 'pear', 'orange']
item >>> apple
item >>> pear
item >>> orange
total >>> ['apple', 'pear', 'orange']
Run third test finished
---------------------------
Run fourth test...
index 1: 1 
index 2: 2 
index 3: 3 
index 4: 4 
index 5: 5 
index 6: 6 
index 7: 7 
index 8: 8 
index 9: 9 
index 10: 10 
index 11: 11 
in while else
Run fourth test finished
[Finished in 0.3s]

"""
