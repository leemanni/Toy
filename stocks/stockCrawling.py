#!/usr/bin/env python
# coding: utf-8

# In[1]:


import warnings
warnings.filterwarnings(action='ignore')
import requests
from bs4 import BeautifulSoup
from datetime import datetime as dt


# In[2]:


request = requests.get('https://finance.naver.com/sise/sise_market_sum.nhn')
html = request.text;
soup = BeautifulSoup(html , 'html.parser')


# In[3]:


stockNames = soup.findAll('a',{'class' : 'tltle'})
for i in range(len(stockNames)) :
    print(stockNames[i].text)


# In[56]:


stocks = soup.findAll('tr',{'onmouseover' : 'mouseOver(this)'})
for i in range(len(stocks)) :
    arrays = stocks[i].text.strip().split('\n')
    print('{0}등 {1:30s}\t{2:10s}원 -> (시총{2}원)'.format(arrays[0], arrays[1], arrays[2], arrays[14]))

