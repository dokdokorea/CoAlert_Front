import os
import PIL.Image as pilImg
import numpy as np


def search(dirname, img_name):
    filenames = os.listdir('cosmeticImg/' + dirname + '/')
    for filename in filenames:
        result = filename.split('_')
        if result[1] == img_name+'.jpg':
            img = pilImg.open('cosmeticImg/' + dirname + '/' + filename)
            pix = np.array(img)
            return pix, result[0]
