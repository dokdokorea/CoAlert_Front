import pandas as pd


def get_bad_ingredient(id, type, start):
    # if(등록된 화장품이 0개 이면)
    allData = pd.DataFrame(columns=['성분명', '위험도'])
    cosmetic_name = ['foundation', 'eyeshadow', 'libtint', 'sunblock']

    for cosmetic in cosmetic_name:
        data = pd.read_csv('ingredient/data/' + cosmetic + 'BadIngredient.csv')
        data = data.loc[data[type] == -5.0, ['성분명', '위험도']]
        allData = allData.append(data)
    for cosmetic in cosmetic_name:
        ingredient = pd.read_csv('ingredient/data/' + cosmetic + 'Ingredient.csv')
        data = pd.read_csv('RecommendSystem/data/new' + cosmetic + '.csv')
        for i, name in enumerate(data.loc[data['userId'] == int(id), 'name'].tolist()):
            for ingredient_names in ingredient.loc[ingredient['제품명'] == name, '성분구성'].values:
                for ingredient_name in ingredient_names.split(','):
                    bad_data = pd.read_csv('ingredient/data/' + cosmetic + 'BadIngredient.csv')
                    bad_data = bad_data.loc[
                    (bad_data['성분명'] == ingredient_name), ['성분명', '위험도']]
                    if bad_data.loc[:, '위험도'].values < -4:
                        allData = allData.append(bad_data)

    allData = allData.drop_duplicates().reset_index(drop=True)
    print(allData)
    # if(등록된 화장품이 1개 이상이면)
    return allData

# def remove():
#     data = pd.read_csv('./data/eyeshadowBadIngredient.csv').drop(columns=['Unnamed: 5', 'Unnamed: 6'], axis=1)
#     data = data.reset_index(drop=True)
#     print(data)
#     data.to_csv('eyeshadowBadIngredient.csv', index=False)
