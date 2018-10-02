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
    recommend_cosmetic = get_recommaned_cosmetic(int(id), type=int(type),
                                                 kind_cosmetic=kindCosmetic[int(kindCosmeticNum)])
    returnList = []
    for i, data in recommend_cosmetic.iterrows():
        data['est'] = round(data['est'], 2)
        returnList.append({'cosmeticname': data['name'], 'estimate': data['est']})
    return Response(json.dumps(returnList), mimetype='application/json')


@app.route("/emailCheck", methods=['POST'])
def integrity():
    id = request.args.get('email')
    print(id)
    return Response(json.dumps({'emailCheck': False}))


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
