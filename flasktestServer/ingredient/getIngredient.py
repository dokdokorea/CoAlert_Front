import pandas as pd
import os


def getIngredient(kind, cname):
    ingredient_data = pd.read_csv('ingredient/data/' + kind + 'Ingredient.csv')
    badgredient_data = pd.read_csv('ingredient/data/' + kind + 'BadIngredient.csv')
    ingredient = ingredient_data.loc[ingredient_data['제품명'] == cname, '성분구성']
    ingredient = ingredient.values[0].split(',')
    list = []
    print(ingredient)
    for name in ingredient:
        print(name, str(badgredient_data.loc[badgredient_data['성분명'] == name, '위험도'].values[0]))
        list.append({'ingredientName': name,
                     'warningRate': str(badgredient_data.loc[badgredient_data['성분명'] == name, '위험도'].values[0])})

    return list

