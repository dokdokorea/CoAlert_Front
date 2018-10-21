import pandas as pd
import numpy as np


def getReview(cosmetic_type, cname):
    read_data = pd.read_csv('RecommendSystem/data/new' + cosmetic_type + '.csv')
    list = []
    change_type = {0: '건성', 1: '지성', 2: '중성', 3: '복합성', 4: '민감성'}
    # , 3: '복합성', 4: '민감성'
    read_data['type'] = read_data['type'].map(change_type)
    for i, data in read_data.loc[
        (read_data['name'] == cname) & (read_data['type'] != "nan"), ['review', 'type', 'rate']].iterrows():
        if len(data['type']) == 2:
            list.append({'review': data['review'], 'type': data['type'], 'rating': round(data['rate'])})
            print(data['type'])
    return list
