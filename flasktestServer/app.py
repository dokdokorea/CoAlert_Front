from flask import Flask

from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)


class GetRecommendCosmetic(Resource):
    def get(self, data):
        print(data)
        return {'hi':'flask'}


api.add_resource(GetRecommendCosmetic, '/recommend')

if __name__ == '__main__':
    app.run()
