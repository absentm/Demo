# coding:utf-8

"""
 # simple_run.py
 #
 # Copyright(C) by AbsentM. 2018
 #
 # Author: AbsentM
 # Date:   2018/02/10
 #
 # Description:
 # Use a generic function to simulate the decorator's execution flow.
 #
"""


def wrapper(func):
	"""
	Define a wrapper function, and use function as params. 
	:param func: A function as param
	:return: A new function 
	"""
	def inner_func(*args, **kwargs):
		"""
		A real inner function to run parammter function.
		:param args: default args
		:param kwargs: default more args
		:return: None
		"""
		print "Entering function "
		func(*args, **kwargs)
		print "Exiting function"

	return inner_func


def show_message():
	"""
	Define a function to show some info msg.
	:return: None
	"""
	print "Hello everyone!"


def simple_main_run():
	"""
	Main test function
	:return: None
	"""
	print "----------------------------------"	
	ret_fun = wrapper(show_message)
	ret_fun()
	print "----------------------------------"


if __name__ == '__main__':
	simple_main_run()