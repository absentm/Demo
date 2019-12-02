# coding=utf-8
import logging
import logging.config
import os
import json


def register_logger(log_name):
    """

    :param log_name:
    :return:
    """
    if log_name not in logging.Logger.manager.loggerDict:
        log_conf_path = "D:\\Devs\\Demo\\Flask-demo\\log\\log_conf.json"

        if os.path.exists(log_conf_path):
            with open(log_conf_path, 'r') as f:
                log_conf = json.load(f)
                logging.config.dictConfig(log_conf)

    result = logging.getLogger(log_name)
    return result
