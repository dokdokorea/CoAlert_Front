import os
import PIL.Image as pilImg
import numpy as np
import io
import base64


def search(dirname, img_name):
    filenames = os.listdir('cosmeticImg/' + dirname + '/')
    for filename in filenames:
        result = filename.split('_', 1)
        if result[1] == img_name + '.jpg':
            with open('cosmeticImg/' + dirname + '/' + filename, "rb") as imageFile:
                str = base64.b64encode(imageFile.read())
                return str, result[0]