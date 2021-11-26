import sys
import os
from langdetect import detect

workingdirectory = os.getcwd()
text = sys.argv[1]
print(detect(text))