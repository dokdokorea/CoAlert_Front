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
        if len(data['type']) == 2 and len(data['review']) < 100 :
            print(len(read_data))
            if i == len(read_data)-1:
                print("asdadsadsasd",round(data['rate']))
                list.append(
                    {'review': data['review'], 'type': data['type'], 'rating': round(data['rate'])})
            else:
                list.append({'review': data['review'], 'type': data['type'], 'rating': str(np.random.randint(1, 6, 1)[0])})
            print(data['type'])
    return list
