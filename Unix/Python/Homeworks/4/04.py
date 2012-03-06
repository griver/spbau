#!/bin/env python
# -*- coding: utf-8 -*- 
#python3

import urllib.request
import re

que = input("input some request:")
url = "http://www.google.ru/search?q="
req = urllib.request.Request(url + que, headers={'User-Agent' : "Magic Browser"}) 
page = urllib.request.urlopen( req ).read()


ref_regexp = re.compile("<cite>.*?</cite>")
dom_regexp = re.compile("\.[a-zA-Z]+/")

statistic = {}
for ref in ref_regexp.findall(str(page)):
    print(ref)
    for dom in dom_regexp.findall(str(ref)):
        dom = dom.strip('./')
        if dom in statistic:
            statistic[dom] += 1
        else:
            statistic[dom] = 1
 
print(statistic)
