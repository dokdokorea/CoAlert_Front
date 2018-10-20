import pandas as pd


def get_bad_ingredient(type):
    # if(등록된 화장품이 0개 이면)
    allData = pd.DataFrame(columns=['성분명', '위험도'])
    for cosmetic in ['foundation', 'eyeshadow', 'libtint', 'suncare']:
        data = pd.read_csv('ingredient/data/' + cosmetic + 'BadIngredient.csv')
        data = data.loc[data[type] == -5.0, ['성분명', '위험도']]
        allData = allData.append(data)
    allData = allData.drop_duplicates().reset_index(drop=True)
    # if(등록된 화장품이 1개 이상이면)
    return allData


# def remove():
#     data = pd.read_csv('./data/eyeshadowBadIngredient.csv').drop(columns=['Unnamed: 5', 'Unnamed: 6'], axis=1)
#     data = data.reset_index(drop=True)
#     print(data)
#     data.to_csv('eyeshadowBadIngredient.csv', index=False)

