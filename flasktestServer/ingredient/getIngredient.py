import pandas as pd
import os


def getIngredient(kind, cname):
    #ingredient
    ingredient_data = pd.read_csv('ingredient/data/' + kind + 'Ingredient.csv')
    badgredient_data = pd.read_csv('ingredient/data/' + kind + 'BadIngredient.csv')
    ingredient = ingredient_data.loc[ingredient_data['제품명'] == cname, '성분구성']
    check_badgredient_data = badgredient_data.loc[:, '성분명']
    print(cname)
    ingredient = ingredient.values[0].split(',')
    list = []
    print(badgredient_data)
    for name in ingredient:
        if name in check_badgredient_data.tolist():
            print(name,str(badgredient_data.loc[badgredient_data['성분명'] == name, '위험도'].values[0]))
            list.append({'ingredientName': name,
                     'warningRate': str(badgredient_data.loc[badgredient_data['성분명'] == name, '위험도'].values[0])})
    return list
