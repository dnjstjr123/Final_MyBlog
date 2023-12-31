import os
import sys
import requests
import json
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'C:/Users/SEC/Downloads/grand-principle-408417-ef478de7dec7.json'
from google.cloud import vision
def detect_safe_search(path):


    client = vision.ImageAnnotatorClient()

    with open(path, "rb") as image_file:
        content = image_file.read()

    image = vision.Image(content=content)

    response = client.safe_search_detection(image=image)
    safe = response.safe_search_annotation

    # Names of likelihood from google.cloud.vision.enums
    likelihood_name = (
        "UNKNOWN",
        "VERY_UNLIKELY",
        "UNLIKELY",
        "POSSIBLE",
        "LIKELY",
        "VERY_LIKELY",
    )
    print("Safe search:")
    danger = 0
    if(likelihood_name[safe.adult]=="LIKELY" or likelihood_name[safe.adult]=="VERY_LIKELY"):
        danger += 1
    if(likelihood_name[safe.medical]=="LIKELY" or likelihood_name[safe.medical]=="VERY_LIKELY"):
        danger += 1
    if(likelihood_name[safe.spoof]=="LIKELY" or likelihood_name[safe.spoof]=="VERY_LIKELY"):
        danger += 1
    if(likelihood_name[safe.violence]=="LIKELY" or likelihood_name[safe.violence]=="VERY_LIKELY"):
        danger += 1
    if(likelihood_name[safe.racy]=="LIKELY" or likelihood_name[safe.racy]=="VERY_LIKELY"):
        danger += 1    
    print(f"adult: {likelihood_name[safe.adult]}")
    print(f"medical: {likelihood_name[safe.medical]}")
    print(f"spoofed: {likelihood_name[safe.spoof]}")
    print(f"violence: {likelihood_name[safe.violence]}")
    print(f"racy: {likelihood_name[safe.racy]}")
    danger_list = [likelihood_name[safe.adult], likelihood_name[safe.medical], likelihood_name[safe.spoof], likelihood_name[safe.violence], likelihood_name[safe.racy]]
    
    if response.error.message:
        raise Exception(
            "{}\nFor more info on error messages, check: "
            "https://cloud.google.com/apis/design/errors".format(response.error.message)
        )
    return danger_list

print(detect_safe_search('캡처.PNG'))