import base64

with open("cosmeticImg/foundation/CNP(차앤박)_프로폴리스 앰플 인 쿠션 SPF50+ PA+++.jpg", "rb") as image_file:
    encoded_string = base64.b64encode(image_file.read())
    print(encoded_string)

