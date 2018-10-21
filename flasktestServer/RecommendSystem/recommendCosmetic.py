import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from surprise import Reader, Dataset, SVD, evaluate


def recommend_cosmetics(user_id, kind_cosmetic, start, skin_type):
    np.random.seed(42)
    pd.set_option('display.expand_frame_repr', False)
    id_purify_data = pd.read_csv('RecommendSystem/data/new' + kind_cosmetic + '.csv')
    id_purify_data = id_purify_data.drop('Unnamed: 0', axis=1)
    review_data = get_review_data(id_purify_data, skin_type)
    cosine_sim = review_to_vector(review_data)
    best_cosmetic_id = get_best_cosmetic(id_purify_data, skin_type)
    prediction_cosmetic_id = get_similarity_prediction_cosmetic(cosine_sim, best_cosmetic_id)
    svd = making_model(id_purify_data, skin_type)
    prediction_data = making_predict_data(prediction_cosmetic_id, id_purify_data)
    recommend_cosmetic = predict(user_id, svd, prediction_data)
    return recommend_cosmetic.iloc[start:start + 10, :]


def predict(user_id, svd, prediction):
    prediction['est'] = prediction['popId'].apply(lambda x: svd.predict(user_id, x).est)
    prediction = prediction.sort_values('est', ascending=False)
    return prediction


def making_model(id_purify_data, skin_type):
    evaluate_data = making_evaluate_data(id_purify_data, skin_type)
    evaluate_data = evaluate_data.build_full_trainset()
    svd = SVD()
    svd.fit(evaluate_data)
    return svd


def making_predict_data(cosmetic_id, original_data):
    predict_data = original_data[['popId', 'name']]
    predict_data = predict_data.drop_duplicates()
    return predict_data.iloc[cosmetic_id]


def read_csv_data(kind_cosmetic):
    original_data = pd.read_csv('./data/' + kind_cosmetic + '.csv')
    change_type = {'건성': 0, '지성': 1, '중성': 2, '복합성': 3, '민감성': 4}
    original_data['type'] = original_data['type'].map(change_type)
    return original_data


def get_similarity_prediction_cosmetic(cosine_sim, best_cosmetic_id):
    sim_scores = list(enumerate(cosine_sim[int(best_cosmetic_id)]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[0:30]
    prediction_cosmetic_id = [i[0] for i in sim_scores]
    return prediction_cosmetic_id


def get_review_data(id_purify_data, type=0):
    review_data = ['' for i in range(100)]
    review_type_popid_data = pd.DataFrame(id_purify_data[["popId", "type", "review"]])
    review_type_popid_data = review_type_popid_data.reset_index(drop=True)
    review_type_popid_data = review_type_popid_data.loc[review_type_popid_data["type"] == type, :]

    for index, row in review_type_popid_data.iterrows():
        review_data[row["popId"]] += row["review"].replace('\n', '')
    return review_data


def id_2_name(original_data):
    original_data = original_data.sort_values('popId')
    id_to_name = original_data[["popId", "name"]].drop_duplicates()
    id_to_name = id_to_name.reset_index(drop=True)
    id_to_name = id_to_name.drop(columns="popId", axis=1)
    return id_to_name


def name_2_id(original_data):
    original_data = original_data.sort_values('popId')
    name_to_id = original_data[["popId", "name"]].drop_duplicates()
    name_to_id = name_to_id.set_index("name", drop=True)
    return name_to_id


def review_to_vector(review_data):
    review_data = pd.Series(review_data)
    tf = TfidfVectorizer(analyzer='word', min_df=2, stop_words=['\r', '\n'], sublinear_tf=True)
    tf_matrix = tf.fit_transform(review_data)
    cosine_sim = linear_kernel(tf_matrix, tf_matrix)
    return cosine_sim


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
    id_to_name = id_2_name(original_data)
    name_to_id = name_2_id(original_data)
    best_cosmetic = id_to_name.iloc[result.argmax()]['name']
    best_cosmetic_id = name_to_id.loc[best_cosmetic, :]

    return best_cosmetic_id


def making_evaluate_data(id_purify_data, skin_type):
    evaluate_data = id_purify_data[id_purify_data["type"] == skin_type]
    evaluate_data = evaluate_data.drop(columns=['name', 'review', 'type'], axis=1)
    reader = Reader()
    evaluate_data = Dataset.load_from_df(evaluate_data, reader)
    return evaluate_data


def add_data_to_csv(id, kind_cosmetic, cosmetic_name, rate, skin_type, review=""):
    pd.set_option('display.expand_frame_repr', False)
    read_data = pd.read_csv('RecommendSystem/data/new' + kind_cosmetic + '.csv')
    name_to_id = name_2_id(read_data)
    pop_id = name_to_id.loc[cosmetic_name, :]
    data = [id, cosmetic_name, pop_id['popId'], rate, review, skin_type]
    read_data = read_data.drop('Unnamed: 0', axis=1)
    read_data.loc[len(read_data)] = data
    read_data.to_csv('RecommendSystem/data/new' + kind_cosmetic + '.csv')
