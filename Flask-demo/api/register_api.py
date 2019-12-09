# coding=utf-8

from flask import Blueprint

register_blueprint = Blueprint("register", __name__)


@register_blueprint.route('/')
def user_register():
    return "register sys."
