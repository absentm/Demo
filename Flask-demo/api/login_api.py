# coding=utf-8

from flask import Blueprint, request, jsonify
from log.log_util import register_logger
import json

login_blueprint = Blueprint("login", __name__)

log = register_logger("demo")


@login_blueprint.route('/', methods=["POST", "GET"])
def login_system():
    if request.method == "POST":
        log.debug("POST data: " + str(request.data))
        log.debug("POST data type: " + str(type(request.data)))

        json_data = json.loads(request.data)
        username = json_data.get("username")
        password = json_data.get("password")
        log.debug("POST username: " + str(username))
        log.debug("POST password: " + str(password))

        if username is None or username == "":
            raise Exception("Username should be not empty!")

        if password is None or password == "":
            raise Exception("Password should be not empty!")

        # check and compare db

        return request.data
    elif request.method == "GET":
        log.debug("GET value: " + str(request.values))
        username = request.values.get("username")
        password = request.values.get("password")
        log.debug("GET username: " + str(username))
        log.debug("GET password: " + str(password))
        return request.values
    else:
        return "Login sys."
