-- 사용자 테이블
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('bluepig4b@naver.com','김지훈','1234','010-9011-5822','부산광역시 부산진구 범향빌딩 405호','1999-09-30',1,'517f9d79-8671-40d0-bb5e-6d272e767a9d_user09.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('blueocean@naver.com','이서현','1234','010-1555-5842','부산광역시 사상구 괘법동 234번지 괘법아파트','2000-10-06',1,'702385bb-c937-4204-a142-7583f908bfaf_user02.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('hamimi1997@naver.com','김하형','1234','010-2684-5842','부산광역시 해운대구 우동 789번지 센텀시티 202호','1997-05-23',1,'706ffd65-ca99-4b91-a4e1-c73741f9b23f_user03.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('96hoho@gmail.com','양승호','1234','010-3535-9985','대전광역시 유성구 궁동 789번지 유성타워 3동 405호','1996-11-25','1','89826566-1f51-4ab0-ada7-17cd7e3f6e58_user04.png',now()),
       ('jjangjung@naver.com','장현정','1234','010-9252-8834','서울특별시 마포구 새창로2길 17 대우아파트 1001호','1990-05-12','1','0b80de68-14b2-4f75-8864-cd23a663c3b3_user05.png',now()),
       ('universe07@daum.net','최주호','1234','010-6452-2311','경기도 용인시 수지구 동천로 567번지 수지그린아파트 102동 1203호','1981-05-06','1','dc3d8891-d3d8-45ae-beb4-fda2bbebbb52_user06.png',now()),
       ('jaesung00@gmail.com','류재성','1234','010-4456-6343','광주광역시 서구 상무대로 111번길 23 우동 1502호','1992-02-15','1', '30b8a3ab-c54f-42be-b417-c6ef2660eea6_user07.png',now()),
       ('sungkim_87@naver.com','김성훈','1234','010-6255-2435','서울특별시 강남구 역삼동 123번지 아이파크','1987-07-20','1', 'a542d45d-5420-4284-b903-f527f20a7c5c_user08.png',now()),
       ('snowhoon@naver.com','설동훈','1234','010-1125-0988','부산광역시 동래구 명장동 678번지 명장타워 703호','1996-12-01','1','f6ae38c5-a212-4436-b08e-e483fdda324c_user14.png',now()),
       ('babie@gmail.com','서지민','1234','010-0253-7898','부산광역시 남구 대연동 345번지 대연센트럴빌라 506호','2003-06-22','1','fb14a8c5-d17d-4324-b742-0d84e5923900_user10.png',now()),
       ('minjung@naver.com','이민정','1234','010-2214-3354','서울특별시 송파구 잠실동 789번지 롯데 캐슬 1107동 303호','2000-03-25','1','5c2ad87d-6b7d-4670-a766-2a3d586c6f0b_user11.png',now()),
       ('woo1988@gmail.com','최혁우','1234','010-9985-3114','서울특별시 강남구 역삼동 123번지 아이파크','1988-09-18','1','d0f6a6b4-5769-49c1-ab31-c8abc43305b0_user12.png' ,now()),
       ('manhou@gmail.com','박만호','1234','010-3567-1252','대전광역시 대덕구 송촌동 555번지 송촌 아파트 601호', '1993-06-22','1','a497e36e-3ded-487d-a867-0fdca503116c_user13.png',now());


-- 기업 사용자 테이블
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name,created_at)
values ('blackpig4b@naver.com','삼성','1234','010-1234-5822','경기도 수원시 영통구 삼성로 129 (매탄동, 삼성디지털시티)','1938-03-01','606-58-33101','SAMSUNG','www.samsung.com',2,'/images/sam.png','0cba0e4d-debb-4413-b079-d9195992e447_lg.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name, created_at)
values ('g@n','네이버','1234','010-5678-5822','부산광역시 부산진구 범향빌딩403호','1999-06-02','301-15-12412','네이버','www.naver.com',2, '/images/naver.png','7d7ce466-b2b0-424d-bc2b-c233d98030f6_kakao.png', now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name,created_at)
values ('6@c','배달의민족','1234','010-9824-5421','서울특별시 송파구 위례성대로 2 (방이동) 장은빌딩','2011-03-10','606-17-12120','(주)우아한형제들','www.bamin.com',2, '/images/bemin.png','8a6fc873-37df-48d4-8756-c4bf86193552_kia.png', now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name,created_at)
values ('yun@n','최상연','1234','010-8868-5232','서울특별시 송파구 송파대로 570','2010-07-01','709-88-35610','쿠팡','www.coupang.com','2', '/images/coupang.png','38bf61fe-a713-47e1-83f6-6cd79f1f5ec3_sk.png' ,now()),
       ('y1@n','김정혁','1234','010-3252-6789','경기도 성남시 분당구 대왕판교로644번길 12','1997-03-11','176-67-88160','(주)엔씨소프트','www.ncsoft.com','2', '/images/ncsoft.png', '130c456d-5f28-4060-bf99-455d60f7ff63_youtube.png',now()),
       ('y2@n','이민정','1234','010-2467-0053','서울 구로구 디지털로26길 38 G타워','2000-05-12','303-91-33085','넷마블(주)','www.netmarble.com','2', '/images/netmarble.png','223af633-9908-4fff-bf1a-fbcaaf83a236_nintendo.png',now()),
       ('y3@n','강혜정','1234','010-8865-2476','경기도 성남시 분당구 판교역로 166 카카오 판교아지트 A동 3층','1995-02-16','607-45-16054','(주)카카오','www.kakao.com','2', '/images/kakao.png', '2002e31a-eaa0-4d5c-a8cc-c7dc47258daa_nexon.png',now()),
       ('y4@n','지현수','1234','010-5457-0975','경기도 성남시 분당구 판교로256번길 7 (삼평동)','1994-02-15','107-56-88921','(주)넥슨컴퍼니','www.nexon.com','2', '/images/nexon.png','b59207f1-ee5f-4a40-9af7-54945bb1d687_doosan.png', now()),
       ('y5@n','정수지','1234','010-3758-1353','서울특별시 중구 통일로2길 16, 2층 (순화동, AIA타워)','2006-07-07','301-93-11325','한국닌텐도(주)','www.nintendo.im','2', '/images/nintendo.png','c227570a-2df9-4969-bbb5-5c49b3700e46_netmarble.png', now()),
       ('y6@n','허만정','1234','010-6674-1422','서울특별시 영등포구 여의대로 128 (여의도동)','1947-01-05','606-32-44986','LG','www.lg.me','2', '/images/lg.png','d9010d71-a222-478a-b4c1-ef7193ed72e8_ncsoft.png', now());



-- 이력서 테이블
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link,  created_at)
values (1, '신입 백엔드 개발자', '부산', '대졸', '신입', 'ERD를 설계하고, MySQL, MariaDB를 사용할 수 있습니다. JavaScript, TypeScript, HTML/CSS 프론트엔드 개발도 가능합니다.', 'naver.com/universe00',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (1, 'Back-End / Software Engineer', '부산', '고졸', '신입', '[성장]을 목표로 달려나가며, 도전적인 것을 좋아하는 이 서 현 입니다!', 'nate.com/blueocean',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (3, 'Java-Spring 개발자', '부산', '대졸', '미들(4~8년)', 'Java를 메인으로 Back-end 개발에 관심을 가지고 있습니다. 다양한 환경과 회사를 접하며 웹서비스를 개발하고, 서비스 해본 경험이 있습니다.', 'naver.com/githa',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (4, '프론트엔드 개발자 이력서', '대전', '대졸', '미들(4~8년)', '빠르고 원활한 웹 경험을 제공하는데 기여했으며, UX경험을 최적화 시켜, 프런트 엔드 분야에서 성장하고자 합니다.', 'naver.com/hoho',now());
INSERT INTO resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
VALUES (5, '끊임없이 개선하고, 발전하는 개발자', '서울', '대졸', '시니어(10년 이상)', '사용자들에게 편리함을 제공하는 것을 최우선으로 삼고 개발하는 개발자입니다.', 'naver.com/codingsin', NOW()),
       (6, '능력있고 성실한 개발자!', '판교분당', '대졸', '시니어(10년 이상)', '겟인데어 회사 대표, 한국전력 계측,계량,고조파 측정 플러터 앱 개발', 'naver.com/getinthere',now()),
       (7, 'Web Developer', '광주', '고졸', '신입', '신입이지만 다양한 프로젝트 경험을 갖고 있으며 팀원들과의 협업을 중요시합니다.', 'naver.com/projectexp', NOW()),
       (8, '4년자 백엔드 개발자', '부산', '고졸', '미들(4~8년)', '자바 개발자로서의 역량을 키워나가고 있습니다. 성장 가능성이 높은 개발자!', 'naver.com/javadev', NOW()),
       (9, '서버 개발자', '부산', '대졸', '신입', '데이터 분석 및 머신러닝에 관심이 많으며 실무 경험도 풍부합니다.', 'naver.com/snowhoon', NOW()),
       (10, '백엔드 개발자(Java, Spring)', '부산', '대졸', '신입', '다양한 언어와 기술에 능숙하며 프로젝트 관리 역량도 뛰어납니다.', 'naver.com/datasci', NOW()),
       (11, '프론트엔드 개발자', '서울', '고졸', '신입', '기술적으로 성장하기 위해 노력하며, 동료와 좋은 커뮤니케이션 하고자 하는 개발자입니다!', 'naver.com/fullstackdev', NOW()),
       (12, '데이터 엔지니어', '서울', '대졸', '시니어(10년 이상)', '대량의 데이터 처리와 데이터 파이프라인 구축에 경험이 있습니다', 'naver.com/dataengineer', NOW()),
       (13, '준비된 풀스택 개발자', '대전', '대졸', '미들(4~8년)', '웹개발에 흥미가 있으며, 풍부한 실전 경험을 바탕으로 자기주도적으로 공부하고 성장하는 개발자입니다.', 'naver.com/androiddev', NOW());




-- 공고 테이블
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (15, '경기도', '대규모 | 백엔드 개발자 (Backend Developer)', '학력무관', '경력무관', 'Java/Kotlin, Spring, JPA 기반 애플리케이션 개발 경험 및 역량 보유한 분, MySQL, Oracle 등 활용 역량이 있는 분', '2024-04-10', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (15, '부산', '백엔드 개발자 - Spring (경력무관)', '대졸', '신입', 'Spring 기반으로 백엔드 비즈니스 로직을 개발하고 배포. 백엔드 개발 경험이 있고, 기본적인 SQL 문법, JOIN, Subquery 등을 활용할 줄 아시는 분', '2024-05-22', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (15, '서울', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '프론트엔드(React)+백엔드(Spring) 풀스택 경력 2년 이상 또는 모바일(Android, iOS, Flutter)+백엔드(Spring) 경력 2년 이상의 경험이 있으신 분', '2024-04-09', '풀스택', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (18, '경기도', '게임 개발자 채용 공고', '학력무관', '미들(4~8년)', '게임 개발 프로젝트에 주도적으로 참여한 경험 (엔지니어로서 기획을 조율하고 게임 모딩에 적합한 기능구성 필요)', '2024-04-15', '게임개발', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (19, '서울', '백엔드 개발자 (Java/Kotlin)', '대졸', '신입', 'Java/Kotlin 중 1개 이상의 프로그래밍 언어에 익숙하신 분, Spring 등 웹 프레임워크 활용 경험이 있으신 분', '2024-05-22', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (20, '경기도', '프론트엔드 개발자 (React, TypeScript)', '학력무관', '경력무관', '- JavaScript, HTML, CSS 등 마크업, TypeScript에 대한 이해가 있으신 분', '2024-04-09', '프론트', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (21, '부산', '24년도 풀스택 Web 개발자 채용 공고', '학력무관', '신입', 'HTML, CSS, JavaScript, MySQL 등 웹 기본 전반, 열정과 근성이 있는 분', '2024-04-29', '풀스택', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (22, '서울', '[경력] Frontend Engineer', '학력무관', '미들(4~8년)', '5년 이상 Frontend 개발 경력 혹은 그에 준하는 실력이 있는 분', '2024-04-10', '프론트', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (23, '서울', '[채용공고] 백엔드 개발자 정규직 채용', '대졸', '경력무관', 'Java, Spring boot를 다룰 수 있고, Git을 통해 협업을 해보신 분', '2024-05-22', '백엔드', NOW());





-- 스킬 테이블 (개인)
insert into skill_tb (resume_id, name) values(1, 'Java');
insert into skill_tb (resume_id, name) values(1, 'JavaScript');
insert into skill_tb (resume_id, name) values(1, 'HTML/CSS');
insert into skill_tb (resume_id, name) values(2, 'JavaScript');
insert into skill_tb (resume_id, name) values(3, 'Java');
insert into skill_tb (resume_id, name) values(3, 'HTML/CSS');
insert into skill_tb (resume_id, name) values
                                                 (4, 'Java'),
                                                 (4, 'JavaScript'),
                                                 (4, 'HTML/CSS'),
                                                 (4, 'MySql'),
                                                 (5, 'React'),
                                                 (5, 'Spring'),
                                                 (6, 'Java'),
                                                 (6, 'MySql'),
                                                 (6, 'JSP'),
                                                 (7, 'Java'),
                                                 (7, 'JSP'),
                                                 (7, 'Jquery'),
                                                 (7, 'Vue.js'),
                                                 (7, 'React'),
                                                 (8, 'Spring'),
                                                 (8, 'HTML/CSS'),
                                                 (9, 'Spring'),
                                                 (10, 'JavaScript'),
                                                 (10, 'React'),
                                                 (10, 'JSP'),
                                                 (11, 'JavaScript'),
                                                 (11, 'JSP'),
                                                 (12, 'Java');

-- 스킬 테이블 (기업)
INSERT INTO skill_tb(jobs_id, name) VALUES(1, 'Java');
INSERT INTO skill_tb(jobs_id, name) VALUES(1, 'Oracle');
INSERT INTO skill_tb(jobs_id, name) VALUES(2, 'Spring');
INSERT INTO skill_tb(jobs_id, name) VALUES(3, 'HTML/CSS'),
                                                (3, 'Spring'),
                                                (3, 'MySql'),
                                                (3, 'React'),
                                                (4, 'Java'),
                                                (4, 'Spring'),
                                                (4, 'JSP'),
                                                (5, 'Jquery'),
                                                (5, 'JSP'),
                                                (5, 'Oracle'),
                                                (6, 'JavaScript'),
                                                (6, 'HTML/CSS'),
                                                (7, 'Java'),
                                                (7, 'Spring'),
                                                (7, 'React'),
                                                (8, 'Spring'),
                                                (8, 'Vue.js'),
                                                (8, 'React'),
                                                (9, 'Java'),
                                                (9, 'Spring');

--- 지원내역 테이블
INSERT INTO apply_tb(resume_id, jobs_id, is_pass, created_at)
VALUES (1,1,'2',NOW()),
       (2,1,'3',NOW()),
       (1,2,'4',NOW()),
       (2,2,'2',NOW()),
       (3,2,'2',NOW());


-- 커뮤니티 테이블
insert into board_tb(title, content, user_id, created_at)
values ('면접 준비 어떻게 하시나요?', '안녕하세요 면접 준비를 제 나름대로 면접 질문에 대한 대답을 준비하고 있는데 모의 면접처럼 여러번 연습 해야 될까요?', 1, now()),
       ('코딩 테스트 다들 하셨나요? 너무 어려워요ㅜㅜ', '안녕하세요 저는 이제 막 취업 준비를 하고 있는데요 1차 면접때 준비 한 것들로 어찌저찌 잘 대답을 해서 2차 코딩테스트만 남은 상황인데요.. 제가 코드 잡는 건 좀 약해서요 ㅜ 코딩 테스트 여러개 보면서 준비도 하고 있는데 자신감이 떨어져요ㅜㅜ 만약 진짜 모르는 거 나오면 다들 어떻게 대처 하시나요??  ', 1, now()),
       ('취뽀 했어요!!! 소소한 팁 공유합니다!!','안녕하세요 저는 다른 일을 하다가 벡엔드 개발자 공부 해서 신입으로 취업하게 되었어요~ 저는 비전공자이다보니 코딩에 코자도 모르고 처음에 개념 잡는 것도 너무 어렵고 막막하더라구요 너무 많은 양이 머리에 들어와서 숙지하는 것도 사실 더딘 편이라 공부 과정 막바지 쯤 1달 정도 다시 인강도 들으면서 빡세게 준비 했어요!', 2, now()),
       ('코린이 탈출하고 싶어요ㅜㅜ', '안녕하세요~ 정말 코딩을 해도해도 안느는 것 같고.. 여러 개념들도 나름 잡아가면서 공부하고 있는데 면접 할때 설명을 못하겠어요 ㅜㅜ', 3, now());

-- 댓글 테이블
insert into reply_tb(comment, board_id, user_id, created_at)
values('음.. 개념 잡은 것들을 리스트로 정리해서 암기 해서라도 면접 보셔야 할 것 같아요.. 저두 비슷한 경험이 있는데요..면접때 대답 못하면 끝입니다..ㅜ 응원해요~!! ', 4, 1, now()),
      ('어떻게 개념들 정리 하셨는지 소소한 tip 팁 같은게 있으실까요?', 4, 3, now()),
      ('네 코드는 좀 잘 못해도 개념들은 어느정도 익히셔야 해요~', 4, 2, now()),
      ('와 대단하시다!! 저두 개발자 취업 준비 중인데요~ 개념 잡는데 좀 시간이 걸렸던 것 같아요', 3, 1, now());

