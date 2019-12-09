# coding=utf-8

from flask import Blueprint

login_blueprint = Blueprint("login", __name__)


@login_blueprint.route('/')
def login_system():
    return "Login sys."
