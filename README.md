# GROW [ 구인ㆍ구직 프로젝트 ]
![logo](https://github.com/chugue/project-grow/assets/153585866/f2ad7455-56f6-4895-8d65-94a5db14a179)

___
> ## RestAPI 기능 정리

### 1.테스트  [담당 : 성훈]
- 요청 주소 (POST)
  
http://localhost:8080/join
  
- 요청 파라미터
  
application/json
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


### 1.회원가입  [담당 : 성훈]
- 요청 주소 (POST)

http://localhost:8080/join
  
- 요청 파라미터
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











