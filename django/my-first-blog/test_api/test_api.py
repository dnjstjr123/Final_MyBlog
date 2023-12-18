import requests

HOST = 'http://127.0.0.1:8000'
res = requests.post(HOST + '/api-token-auth/', {
    'username': 'admin',
    'password': 'admin123',
})

res.raise_for_status()
token = res.json()['token']
print(token)

# 인증이 필요한 요청에 아래의 headers를 붙임
headers = {'Authorization': 'JWT ' + token, 'Accept': 'application/json'}

# Post Create
data = {
    'title': '제목 by code',
    'text': 'API내용 by code',
    'created_date': '2023-09-30T19:34:00+09:00',
    'published_date': '2023-09-30T19:34:00+09:00'
}

# 데이터를 바이트로 변환
for key, value in data.items():
    data[key] = value.encode('utf-8')

# 파일 업로드
file = {'image': open('/Users/lg/human.jpg', 'rb')}

res = requests.post(HOST + '/api_root/Post/', data=data, files=file, headers=headers)
print(res)
print(res.json())