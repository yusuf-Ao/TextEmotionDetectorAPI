# -*- coding: utf-8 -*-


# EDA Pkgs
import numpy as np

# Utils
import joblib
import os
import sys

pipe_lr = joblib.load(open("C:\\Users\\user\\Desktop\\7TH SEM\\B.TECH PROJ "
                           "1\\RestfulApi_MajorProject1\\src\\main\\java\\io\\aycodes\\RestfulApi_MajorProject1"
                           "\\training\\emotion_classifier_pipe_lr_25_November_2021.pkl", "rb"))


# Fxn
def predict_emotions(docx):
    results = pipe_lr.predict([docx])
    return results[0]


def get_prediction_proba(docx):
    results = pipe_lr.predict_proba([docx])
    return results


workingdirectory = os.getcwd()

emotions_emoji_dict = {"anger": "Anger", "disgust": "Disgust", "fear": "Fear", "happy": "Happy", "joy": "Joy",
                       "neutral": "Neutral", "sad": "Sad", "sadness": "Sadness", "shame": "Shame",
                       "surprise": "Surprise"}

raw_text = sys.argv[1]
prediction = predict_emotions(raw_text)
probability = get_prediction_proba(raw_text)
emoji_icon = emotions_emoji_dict[prediction]
print(emoji_icon)
print(np.max(probability))
