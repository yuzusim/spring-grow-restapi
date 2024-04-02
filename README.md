# GROW [ 구인ㆍ구직 프로젝트 ]
![logo](https://github.com/chugue/project-grow/assets/153585866/f2ad7455-56f6-4895-8d65-94a5db14a179)

___
> ## RestAPI 기능 정리


### 1.회원가입  [담당 : 성훈]
- 요청 주소 (POST)

http://localhost:8080/join
  요청 파라미터
- application/json
```
{
  "username":"getinthere",
  "password":"1234",
  "email":"getinthere@nate.com"
}
```
- 응답 바디

application/json

```
{
    "code": 1,
    "msg": "회원가입완료",
    "data": {
    "id": 3,
    "username": "getinthere",
    "password": null,
    "email": "getinthere@nate.com",
    "created": "2021-07-10T07:45:15.764705",
    "updated": "2021-07-10T07:45:15.764705"
}
```
<hr>

### .기업 공고 전부조회  [담당 : 승진]
- 요청 주소 (GET)

http://localhost:8080/jobs/info

- 응답 바디

application/json

```
{
    "status": 200,
    "msg": "성공",
    "body": [
        {
            "id": 9,
            "title": "[채용공고] 백엔드 개발자 정규직 채용",
            "career": "경력무관",
            "area": "서울",
            "deadline": "2024-05-22",
            "imgFileName": "d9010d71-a222-478a-b4c1-ef7193ed72e8_ncsoft.png",
            "compName": "LG",
            "skills": [
                {
                    "name": "Java"
                },
                {
                    "name": "Spring"
                }
            ]
        },
        {
            "id": 8,
            "title": "[경력] Frontend Engineer",
            "career": "미들(4~8년)",
            "area": "서울",
            "deadline": "2024-04-10",
            "imgFileName": "c227570a-2df9-4969-bbb5-5c49b3700e46_netmarble.png",
            "compName": "한국닌텐도(주)",
            "skills": [
                {
                    "name": "Spring"
                },
                {
                    "name": "Vue.js"
                },
                {
                    "name": "React"
                }
            ]
        },
        {
            "id": 7,
            "title": "24년도 풀스택 Web 개발자 채용 공고",
            "career": "신입",
            "area": "부산",
            "deadline": "2024-04-29",
            "imgFileName": "b59207f1-ee5f-4a40-9af7-54945bb1d687_doosan.png",
            "compName": "(주)넥슨컴퍼니",
            "skills": [
                {
                    "name": "Java"
                },
                {
                    "name": "Spring"
                },
                {
                    "name": "React"
                }
            ]
        }
    ]
}
```
<hr>

### .기업 공고 수정  [담당 : 승진]
- 요청 주소 (GET)

http://localhost:8080/jobs/3/update-jobs-form

- 응답 바디

application/json

```
{
    "status": 200,
    "msg": "성공",
    "body": {
        "id": 3,
        "compName": "네이버",
        "phone": "010-5678-5822",
        "businessNumber": "301-15-12412",
        "homepage": "www.naver.com",
        "title": "프런트 엔드/백엔드 개발자 채용",
        "edu": "학력무관",
        "career": "시니어(10년 이상)",
        "content": "프론트엔드(React)+백엔드(Spring) 풀스택 경력 2년 이상 또는 모바일(Android, iOS, Flutter)+백엔드(Spring) 경력 2년 이상의 경험이 있으신 분",
        "area": "서울",
        "deadLine": "2024-04-09",
        "task": "풀스택",
        "skills": [
            {
                "name": "HTML/CSS"
            },
            {
                "name": "Spring"
            },
            {
                "name": "MySql"
            },
            {
                "name": "React"
            }
        ]
    }
}
```
<hr>





> ## 시연 영상 Youtube
링크 걸기

<br>

> ## 발표 자료 PDF
PDF 올리기

<br>

> ## 프로젝트 소개
* 구직자는 이력서를, 구인 회사는 공고를 등록한다.
* 구직자는 공고를 보고 입사 지원을 하고, 구인 회사는 이력서를 보고 구인 제안을 한다.
* 구인 회사는 이력서를 보고 합격ㆍ불합격 통보를 할 수 있고, 구직자는 합격ㆍ불합격 통보를 확인할 수 있다.

<br>

> ## 기술 스택
![기술스택](https://github.com/chugue/project-grow/assets/153585866/8f93f7a9-4368-4b70-830e-d3a9c2de545f)  
* JDK 21
* Springboot 3.2.2
* 테스트 h2 DB
* JAVAScript
* HTML
* CSS
* Bootstrap

<br>

> ## 기능 정리
### [ 1단계 ] - 웹화면 UI 설계
**1. 회원가입, 로그인**  
  * 회원가입 시, 필요한 정보 입력 받기
  * 로그인 시, 필요한 정보 입력 받기
    
**2. 메인 페이지**
   * 공고/이력서 목록, Header, Footer
     
**3. 개인 페이지**
   * 이력서 작성ㆍ수정ㆍ삭제
   * 기업에게 제안 받은 공고 확인
   * 공고 상세 보기
   * 정보 수정  
     
**4. 기업 페이지**
   * 공고 작성ㆍ수정ㆍ삭제
   * 이력서를 보고 구직자에게 제안하기
   * 정보 수정  

### [ 2단계 ]
* 회원가입 시, 중복 ID 여부 확인
* 로그인 시, 비밀번호 확인 
* 개인ㆍ기업 정보 수정

### [ 3단계 ]
* 이력서ㆍ공고 작성, 수정, 삭제
* 이력서ㆍ공고 상세보기 페이지
* 받은 지원ㆍ제안 확인
* 합격ㆍ불합격 통보 

### [ 4단계 ]
* 페이징
* 스크랩 
* 검색 기능 
* 사진 변경 및 추가
* 비밀번호 암호화

<br>

> ## 테이블 모델링 
![테이블](https://github.com/chugue/project-grow/assets/153585866/68639d5f-ec6f-470b-9866-058a43843df3)

<br>

> ## 컨벤션
* 적어도 되고, 노션 링크를 올려도 될 듯 함 

<br>

> ## 담당 기능 *-> !!기능 정리와 비슷하기 때문에 필요없으면 해당 부분은 삭제해도 될 듯함!!* 
### 공통
* 테이블 설계
* View <-*백엔드 기능 구현을 위주로 적으면 좋을 것 같아서 뷰는 공통사항으로 뺌.*
  
### 김성훈 (팀장)
* 담당기능을 적으세요
* 여기다가
* 쭉 적으세요
  
### 김지훈
* 담당기능을 적으세요
* 쭉
* 적으세요
  
### 심유주
* 이력서 수정
* 이력서 삭제

### 하승진
* 담당 기능을 적으세요
* 담당
* 쭉쭉

### 송채현
* 로그아웃
* 회원가입 시 ID 중복체크
* 이력서 작성

<br>

> ## 구현 화면 -> 이건 꼭 들어가야함!!! 
*시간도 없고... gif 로 올릴 필요 없이, 무슨 페이지인지 설명해주고, 캡쳐 붙여넣기만 해도 충분할 듯함*
## 회원가입
이미지 

## 로그인
이미지 

<br>











