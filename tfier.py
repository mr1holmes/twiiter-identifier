import sys
import os
import nltk
import random
import re
from nltk.corpus import stopwords
from collections import Counter
from nltk import word_tokenize, WordNetLemmatizer,NaiveBayesClassifier,classify

"""
Given a tweet it returns the list of important words in that tweet
"""
def preprocess(tweet):
    imp_words = []
    lemmatizer = WordNetLemmatizer()
    stoplist = stopwords.words('english')
    for word in word_tokenize(tweet):
        lword = lemmatizer.lemmatize(word.lower())
        non_punct_pattern = re.compile('.*[A-Za-z0-9].*')
        if non_punct_pattern.match(lword):
            if lword not in stoplist:
                imp_words.append(lword)
    return imp_words

"""
Returns dictionary of words and their occurence counts
"""
def get_counts(tweet):
    return {word:count for word,count in Counter(preprocess(tweet)).items()}

# read data from file
mfile = open("trainingdata.txt","r")
no_of_lines = int(mfile.readline())

i = 1
rlist = []
test_set = []

for line in mfile:
    data = line.split(" ")
    lable = data[0]
    tweet = " ".join(data[1:])
    rdict = get_counts(tweet)
    rlist += [(rdict,lable)] 
test_set = rlist[10:20]
classifier = NaiveBayesClassifier.train(rlist)
classifier.show_most_informative_features(20)
mfile.close()
print nltk.classify.accuracy(classifier,test_set)
