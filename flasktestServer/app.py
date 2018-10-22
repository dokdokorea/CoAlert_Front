from flask import Flask, request, Response
from RecommendSystem.recommendCosmetic import recommend_cosmetics, add_data_to_csv
from ingredient.getBadIngredient import get_bad_ingredient
from ingredient.getIngredient import getIngredient
from cosmeticImg.splitCosmeticCompany import search
from RecommendSystem.oneCosmeticRating import requestOneCosmeticRating
from RecommendSystem.getReview import getReview
import json

app = Flask(__name__)
kindCosmetic = {1: 'sunblock', 2: 'eyeshadow', 3: 'foundation', 4: 'libtint'}


@app.route("/", methods=["GET", 'POST'])
def root():
    return "hi flask"


@app.route("/recommendCosmetic", methods=['POST'])
def getRecommendCosmetic():
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
        pixel, company = search(cosmetic_type, data['name'])
        returnList.append(
            {'cosmeticname': data['name'], 'estimate': data['est'], 'pixel': str(pixel), 'company': company})
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
    list = []
    for i, data in response_data.iterrows():
        list.append({"ingredientName": data['성분명'], "warningRate": data['위험도']})
    return json.dumps(list)
    # if(리뷰 수가 0이 아니라면 ){
    #
    # }


@app.route("/ingredientPerCosmetic", methods=['POST'])
def ingredientPerCosmetic():
    kind = request.args.get('kind')
    cname = request.args.get('cname')
    print(kind, cname)
    cosmetic_type = kindCosmetic[int(kind)]
    result = getIngredient(cosmetic_type, cname)
    return json.dumps(result)


@app.route("/oneCosmeticRating", methods=['POST'])
def oneCosmeticRating():
    kind = request.args.get('kind')
    cname = request.args.get('cname')
    skin_type = request.args.get('type')
    cosmetic_type = kindCosmetic[int(kind)]
    result = requestOneCosmeticRating(0, cosmetic_type, int(skin_type), cname)
    print(result)
    result = result.round(2)
    return json.dumps({'Rating': result})


@app.route("/getReview", methods=['POST'])
def getReviewList():
    kind = request.args.get('kind')
    cosmetic_type = kindCosmetic[int(kind)]
    cosmetic_name = request.args.get('cname')
    result_list = getReview(cosmetic_type, cosmetic_name)
    return json.dumps(result_list)


@app.route("/setReview", methods=['POST'])
def setReviewList():
    id = request.args.get('id')
    kind_cosmetic = request.args.get('kindCosmetic')
    cname = request.args.get('cname')
    rating = request.args.get('rating')
    type = request.args.get('type')
    review = request.args.get('review')
    cosmetic_type = kindCosmetic[int(kind_cosmetic)]
    print(id, cname, rating, type, review, cosmetic_type)
    add_data_to_csv(id, cosmetic_type, cname, rating, type, review)
    return json.dumps({'result': 'ok'})


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
