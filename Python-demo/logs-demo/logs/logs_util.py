#!/usr/bin/env python
# -*- coding: utf-8 -*-

import logging
import logging.config
import os
import json


def register_logger(name):
    if name not in logging.Logger.manager.loggerDict:
        module_path = os.path.dirname(__file__)
        path = module_path + '../../conf/logs_conf.json'

        if os.path.exists(path):
            with open(path, 'r') as f:
                config = json.load(f)
                logging.config.dictConfig(config)

    result_logger = logging.getLogger(name)
    return result_logger


if __name__ == '__main__':
    logger = register_logger("cli")
    logger.debug("xxxxx1")

    logger = register_logger("cli")
    logger.debug("xxxxx2")
