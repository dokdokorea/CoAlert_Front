import pandas as pd


def getReview(cosmetic_type, cname):
    read_data = pd.read_csv('RecommendSystem/data/new' + cosmetic_type + '.csv')
    list = []
    change_type = {0: '건성', 1: '지성', 2: '중성'}
    read_data['type'] = read_data['type'].map(change_type)
    for i, data in read_data.loc[(read_data['name'] == cname), ['review', 'type', 'rate']].iterrows():
        list.append({'review': data['review'], 'type': data['type'], 'rating': data['rate']})
    return list
