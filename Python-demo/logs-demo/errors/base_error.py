#!/usr/bin/env python
# -*- coding: utf-8 -*-

import six
import json
import sys


class ECodeFiled(int):

    def __new__(cls, error_code, message=u""):
        err_obj = int.__new__(cls)
        err_obj.err_code = error_code
        err_obj.err_msg = message
        return err_obj


class ECodeMetaClass(type):

    def __new__(cls, name, bases, attrs):
        err_code_dic = {}
        for k, v in attrs.items():
            if getattr(v, '__class__', None) and isinstance(v, ECodeFiled):
                if err_code_dic.get(v):
                    raise ValueError('duplicated code {0} {1}'.format(k, v))

        attrs["CODE_MESSAGE_MAP"] = err_code_dic
        return type.__new__(cls, name, bases, attrs)


class BaseECode(six.with_metaclass(ECodeMetaClass)):
    CODE_MESSAGE_MAP = NotImplemented


class SErrCode(BaseECode):
    """
    Define errors code of system type, between 10001 - 19999;
    Code 9999 is a default define of unknown errors
    """
    UNKNOWN_ERROR = ECodeFiled(9999, u"Unknown error")


class UErrCode(BaseECode):
    """
    Define errors code of user type, between 20001 - 29999;
    """
    INVALID_INPUT = ECodeFiled(20001, u"Invalid input")


def raise_cli_err(error_code, error_message):
    """
    Raise errors code of cli mode
    :param error_code: errors code
    :param error_message: errors message
    :return:
    """
    print "ERROR(%s): %s" % (str(error_code), error_message)
    sys.exit(1)


def raise_rest_err(error_code, error_message):
    """
    Raise errors code of REST mode
    :param error_code: errors code
    :param error_message: errors message
    :return:
    """
    error_dic = {
        "err_code": error_code,
        "err_msg": error_message
    }

    error_data_json = json.dumps(error_dic, indent=2)
    print error_data_json
    sys.exit(1)
