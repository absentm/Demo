#coding:utf-8

import sys
import hashlib

def test_sha256(username, password):
	m = hashlib.sha256()
	salt = password + username
	password = salt + password
	m.update(password)
	password = m.hexdigest()

	return password

def main_test():
	username = sys.argv[1]
	password = sys.argv[2]

	print username
	print password

	if (username != None) and (password != None):
		pw_sha256 = test_sha256(username, password)
		print pw_sha256
	else:
		print "ERROR!"

if __name__ == '__main__':
	main_test()
