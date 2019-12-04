# coding=utf-8

globals_dic = dict()


def set_global_variable(key, value):
    """
    Set global variable with key and value
    :param key:
    :param value:
    :return:
    """
    global globals_dic
    globals_dic[key] = value


def get_global_variable(key):
    """
    Get global variable with key
    :param key:
    :return:
    """
    try:
        global globals_dic
        return globals_dic[key]
    except KeyError:
        return None
