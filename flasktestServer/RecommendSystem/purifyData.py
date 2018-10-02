import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from surprise import Reader, Dataset, SVD, evaluate
from sklearn.externals import joblib
from konlpy.tag import Twitter


def get_recommaned_cosmetic(userId, kind_cosmetic, type=0):
    original_data = pd.read_csv('RecommendSystem//data/' + kind_cosmetic + '.csv')
    change_type = {'건성': 0, '지성': 1, '중성': 2, '복합성': 3, '민감성': 4}
    original_data['type'] = original_data['type'].map(change_type)
    id_purify_data = get_id_purify_data(original_data)
    review_data = get_review_data(id_purify_data, type)
    name_to_id = name_2_id(original_data)
    id_to_name = id_2_name(original_data)
    cosine_sim = review_to_vector(review_data)
    best_cosmetic = get_best_cosmetic(original_data, type)
    best_cosmetic = id_to_name.iloc[best_cosmetic]['name']
    cosmetic_id = name_to_id.loc[best_cosmetic, :]
    sim_scores = list(enumerate(cosine_sim[int(cosmetic_id)]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:26]
    svd = joblib.load('RecommendSystem/model/' + kind_cosmetic + '/' + kind_cosmetic + '_' + str(type) + '.pkl')
    cosmetic_id = [i[0] for i in sim_scores]
    prediction = making_predict_data(cosmetic_id, original_data)
    prediction['est'] = prediction['popId'].apply(lambda x: svd.predict(userId, x).est)
    prediction = prediction.sort_values('est', ascending=False)
    return prediction.head(10)


def get_best_cosmetic(original_data, type):
    data_per_type = original_data.loc[original_data["type"] == type, :]
    data_per_type = data_per_type.drop(['name', 'userId', 'review', 'type'], axis=1)
    rating = [np.float64(0) for i in range(0, 100)]
    cosmetic_per_popId = [np.float64(0) for i in range(0, 100)]

    for i, name in data_per_type.iterrows():
        rating[int(name['popId'])] += name['rate']
        cosmetic_per_popId[int(name['popId'])] += 1
    rating = np.array(rating)
    cosmetic_per_popId = np.array(cosmetic_per_popId)
    result = rating / cosmetic_per_popId
    result = np.nan_to_num(result, 0)
    return result.argmax()


def get_member_per_type(data, type):
    return len(data.loc[data['type'] == type, :])


def get_id_purify_data(id_purify_data):
    member_per_type = {
        0: get_member_per_type(id_purify_data, 0),
        1: get_member_per_type(id_purify_data, 1),
        2: get_member_per_type(id_purify_data, 2),
        3: get_member_per_type(id_purify_data, 3),
        4: get_member_per_type(id_purify_data, 4)}
    for i in range(5):
        np.random.seed(42)
        id_purify_data.loc[id_purify_data["type"] == i, "userId"] = [np.random.randint(i * 200, 200 * (i + 1))
                                                                     for j in range(0, member_per_type[i])]
    id_purify_data = id_purify_data.sort_values('userId')
    return id_purify_data


def get_review_data(id_purify_data, type=0):
    review_data = ['' for i in range(100)]
    review_type_popid_data = pd.DataFrame(id_purify_data[["popId", "type", "review"]])
    review_type_popid_data = review_type_popid_data.reset_index(drop=True)
    review_type_popid_data = review_type_popid_data.loc[review_type_popid_data["type"] == type, :]

    for index, row in review_type_popid_data.iterrows():
        review_data[row["popId"]] += row["review"]

    return review_data


def id_2_name(original_data):
    id_to_name = original_data[["popId", "name"]].drop_duplicates()
    id_to_name = id_to_name.reset_index(drop=True)
    id_to_name = id_to_name.drop(columns="popId", axis=1)
    return id_to_name


def name_2_id(original_data):
    name_to_id = original_data[["popId", "name"]].drop_duplicates()
    name_to_id = name_to_id.set_index("name", drop=True)
    return name_to_id


def making_evaluate_data(id_purify_data, type=0):
    evaluate_data = id_purify_data[id_purify_data["type"] == type]
    evaluate_data = evaluate_data.drop(columns=['name', 'review', 'type'], axis=1)
    reader = Reader()
    evaluate_data = Dataset.load_from_df(evaluate_data, reader)
    return evaluate_data


def review_to_vector(review_data):
    review_data = pd.Series(review_data)
    tf = TfidfVectorizer(analyzer='word', min_df=2, stop_words=['\r', '\n'], sublinear_tf=True)
    tf_matrix = tf.fit_transform(review_data)
    cosine_sim = linear_kernel(tf_matrix, tf_matrix)
    return cosine_sim


def making_predict_data(cosmetic_id, original_data):
    predict_data = original_data[['popId', 'name']]
    predict_data = predict_data.drop_duplicates()
    return predict_data.iloc[cosmetic_id]
