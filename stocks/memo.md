# 8월 20일-크롤링(python)

[http://dowellcomputer.com/main.jsp](http://dowellcomputer.com/main.jsp) ⇒ 개발자 모드

body ⇒ b 테그 해보기

![Untitled](8%E1%84%8B%E1%85%AF%E1%86%AF%2020%E1%84%8B%E1%85%B5%E1%86%AF-%E1%84%8F%E1%85%B3%E1%84%85%E1%85%A9%E1%86%AF%E1%84%85%E1%85%B5%E1%86%BC(python)%206fe1fe2a6d4e401baace3106f2e97acb/Untitled.png)

# 크롤링

## 세팅

```python
import warnings
warnings.filterwarnings(action='ignore')
```

# 다시 경고 메시지가 출력되게 하려면 action='defualt' 로 수정해서 실행하면 된다.

크롤링에 사용할 라이브러리를 설치하고 import한다.

!pip unstall requests # 크롤링할 사이트에 접속해서 html 문서를 읽어온다.

!pip install beatifulsiop4 # requets 모듈을 사용해 읽어온 html 문서를 pharsing(분석) 한다.

cmd 창에서 저 위의 두개를 설치

```python
import requests
from bs4 import BeautifulSoup
```

## 크롤링 시작

1. request.get(url)
⇒ import 한 requests 의 get 메소드로 크롤링할 해당 사이트를 가져옴
2. 타겟 사이트에서 얻어온 정보중에 html 문서를 기준으로 얻어온다

    ```python
    html = request.text
    ```

3. 분석작업을 실행한다
⇒ bs4 모듈의 BeautifulSoup() 함수로 해당 사이트에서 얻어온 html 문서를 html  분석기를 사용하기위해 준비한다

    ```python
    soup = BeautifulSoup(html, 'html.parser')
    ```

4. 원하는 값을 가져오는 방법
분석작업를 변수에 담고, findAll() 함수를 이용해서 값을 가져온다
  - findAll( 테그 ,{ 속성 : 속성값} ⇒ {} 딕셔너리는 생략가능
  - select() ⇒  css 선택자를 이용하는 법
    ⇒ select(  a  >  b ) → b가 a의 자식이다
    ⇒ select(  a  (공백)  c) → c가 a의 자손이다
5. 크롤랑할 데이터 테그 가 없어서 가져올 수 없는 경우

    ⇒ 전 테그를 선택한다음 'next_sibling' 을 사용한다

    ⇒ 크롤링한 데이터의 인덱스 가져옴 ⇒ index(크롤링데이터배열)
