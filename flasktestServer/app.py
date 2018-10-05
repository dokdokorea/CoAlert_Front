from flask import Flask, request, Response
from RecommendSystem.purifyData import get_recommaned_cosmetic
import json
import numpy as np

app = Flask(__name__)


@app.route("/", methods=["GET", 'POST'])
def root():
    return "hi flask"


@app.route("/recommendCosmetic", methods=['POST'])
def getRecommendCosmetic():
    kindCosmetic = {1: 'sunblock', 2: 'eyeShadow', 3: 'foundation', 4: 'libTint'}
    id = request.args.get('id')
    type = request.args.get('persontype')
    kindCosmeticNum = request.args.get('cosmetictype')
    startNum = request.args.get('start')
    recommend_cosmetic = get_recommaned_cosmetic(userId=int(id), start=startNum,
                                                 kind_cosmetic=kindCosmetic[int(kindCosmeticNum)], type=int(type))
    returnList = []
    for i, data in recommend_cosmetic.iterrows():
        data['est'] = round(data['est'], 2)
        returnList.append({'cosmeticname': data['name'], 'estimate': data['est']})
    return json.dumps(returnList)


@app.route("/idcheck", methods=['POST'])
def integrity():
    id = request.args.get('email')
    print(id)
    return json.dumps({'emailCheck': True})


@app.route("/login", methods=['POST'])
def login():
    id = request.args.get('id')
    password = request.args.get('password')
    print(id, password)
    return ''


@app.route("/signup", methods=['POST'])
def signup():
    id = request.args.get('id')
    password = request.args.get('password')
    print(id, password)
    return json.dumps({'id': id})


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
