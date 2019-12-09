# coding=utf-8

from flask import Flask

from api.login_api import login_blueprint
from api.notes_api import note_blueprint
from api.register_api import register_blueprint
from log.log_util import register_logger

log = register_logger("demo")

app = Flask(__name__)
app.register_blueprint(login_blueprint, url_prefix="/login")
app.register_blueprint(register_blueprint, url_prefix="/register")
app.register_blueprint(note_blueprint, url_prefix="/note")


@app.route('/')
def index():
    log.debug("Query home index...")
    return "Hello, welcome!"


if __name__ == '__main__':
    app.run(debug=True)
