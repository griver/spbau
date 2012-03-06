#!/bin/env python
#python3
from xml.etree import ElementTree
from urllib.request import urlopen

indent = "   "

def printrss(parent, level):
    print((level*indent+"<level {0:d}>").format(level))
    if parent.text:
        lines = parent.text.split("\n")
        for line in lines:
            print(level*indent+line)
        for child in parent:
            printrss(child, level+1)

rss_file = urlopen("http://rssportal.ru/feed/10167.xml");
tree = ElementTree.parse(rss_file)
root = tree.getroot();
printrss(root, 0)

