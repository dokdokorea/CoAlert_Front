from flask import Flask, request, Response
from RecommendSystem.recommendCosmetic import recommend_cosmetics
from ingredient.getIngredient import get_bad_ingredient
from cosmeticImg.splitCosmeticCompany import search
import json

app = Flask(__name__)


@app.route("/", methods=["GET", 'POST'])
def root():
    return "hi flask"


@app.route("/recommendCosmetic", methods=['POST'])
def getRecommendCosmetic():
    kindCosmetic = {1: 'sunblock', 2: 'eyeshadow', 3: 'foundation', 4: 'libtint'}
    id = request.args.get('id')
    type = request.args.get('persontype')
    kindCosmeticNum = request.args.get('cosmetictype')
    startNum = request.args.get('start')
    cosmetic_type = kindCosmetic[int(kindCosmeticNum)]
    recommend_cosmetic = recommend_cosmetics(user_id=int(id), start=int(startNum),
                                             kind_cosmetic=cosmetic_type, skin_type=int(type))
    returnList = []
    for i, data in recommend_cosmetic.iterrows():
        data['est'] = round(data['est'], 2)
        # print(data['name'])
        pixel, company = search(cosmetic_type, data['name'])
        returnList.append({'cosmeticname': data['name'], 'estimate': data['est'], 'pixel': str(pixel), 'company':company})
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
    return json.dumps({'id': id, 'password': password})


@app.route("/search_bar", methods=['POST'])
def wordsRequest():
    id = request.args.get('')


@app.route("/signup", methods=['POST'])
def signup():
    id = request.args.get('id')
    password = request.args.get('password')
    return json.dumps({'id': id})


@app.route("/badIngredient", methods=['POST'])
def badIngredient():
    request_id = request.args.get('id')
    person_type = request.args.get('person_type')
    response_data = get_bad_ingredient(person_type)
    print(response_data)
    list = []
    for i, data in response_data.iterrows():
        list.append({"ingredientName": data['성분명'], "warningRate": data['위험도']})
    return json.dumps(list)
    # if(리뷰 수가 0이 아니라면 ){
    #
    # }


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
