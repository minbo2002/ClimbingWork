# 클라이밍 매칭 플랫폼 - ClimbingWork

### "클라이밍을 시간, 장소에 구애받지 않고 하고 싶을때"

<br>

##  ✏️ 프로젝트 기획 의도
### 클라이밍을 하기 위해 언제 어디서든 클라이밍장에 예약할 수 있도록 도와주는 프로젝트

<br>

## 📌 서비스 MVP(Minimun Valuable Product)
1. 관리자가 클라이밍장 등록/읽기/수정/삭제 (이미지 등록 가능)

2. 관리자가 클라이밍장 선택 및 특정 시간대 매칭 등록/수정/삭제

3. 사용자가 특정 클라이밍장의 특정 시간대 매칭 예약 (모집인원이 충족되거나 경기시작시간 이후에 예약 불가) 및 취소 가능

4. 사용자가 자신이 예약한 매칭 조회

<br>

## ⏰ 프로젝트 개발 기간
2023.04.27 ~ 

<br>

## 🖥 사용 기술 스택

**Backend**

<p>
  <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot -6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-137CBD?style=flat-square&logo=JSON%20Web%20Tokens&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Data JPA-460856?style=flat-square&logo=&logoColor=white"/>
  <img src="https://img.shields.io/badge/Query DSL-0769AD?style=flat-square&logo=&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
</p>

**Infra**

<p>
  <img src="https://img.shields.io/badge/Amazon EC2-3C5280?style=flat-square&logo=amazonec2&logoColor=white"/>
  <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=amazons3&logoColor=white"/>
  <img src="https://img.shields.io/badge/Amazon RDS-232F3E?style=flat-square&logo=amazonrds&logoColor=white"/>
  <img src="https://img.shields.io/badge/Github Actions-4285F4?style=flat-square&logo=Github Actions&logoColor=white"/>
</p>

<br>

## ⚙️ 시스템 아키텍처
![image](https://github.com/minbo2002/ClimbingWork/assets/68101836/e901c2ef-26cd-47ca-aa2a-ef4195fca605)

<br>

## 👁‍🗨 이슈정리
- 유저들의 중복 예약 요청 및 동시성 제어는 어떻게 해야 할까?
  
- 매치 조회 성능을 높이는 방법은 무엇일까? --> https://velog.io/@minbo2002/coveringIndex
  
- 팀을 이루어 프로젝트 진행시 Git 브랜치 전략을 어떻게 수립해야 할까?
  
- JMeter를 이용한 성능 테스트

<br>

## 💾 DB 명세서
![2023-07-28 ERD구조](https://github.com/minbo2002/ClimbingWork/assets/68101836/1ec1b0ca-9fe5-4979-a6d0-0772854cc35a)

<br>

## 🛠 API 문서
https://documenter.getpostman.com/view/15520411/2s93m62hvR
