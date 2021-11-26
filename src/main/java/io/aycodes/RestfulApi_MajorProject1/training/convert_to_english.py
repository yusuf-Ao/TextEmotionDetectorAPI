import sys
import os
from googletrans import Translator

workingdirectory = os.getcwd()
translator = Translator()
text = sys.argv[1]
source = sys.argv[2]
target = 'en'
translation = translator.translate(text, src=source, dest=target)
print(translation.text)