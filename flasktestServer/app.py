from flask import Flask, request

app = Flask(__name__)


@app.route("/", methods=["GET", 'POST'])
def root():
    return "hi flask"


@app.route("/recommendCosmetic", methods=['POST'])
def getRecommendCosmetic():
    print(request.query_string)


if __name__ == '__main__':
    app.run(host='0.0.0.0')
