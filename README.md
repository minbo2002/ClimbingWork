# 클라이밍 매칭 플랫폼 - ClimbingWork

### "클라이밍을 시간, 장소에 구애받지 않고 하고 싶을때"

<br>

##  ✏️ 프로젝트 기획 의도
### 클라이밍을 하기 위해 언제 어디서든 클라이밍장에 예약할 수 있도록 도와주는 프로젝트

<br>

## 📌 서비스 MVP(Minimun Valuable Product)
1. 관리자가 클라이밍장 등록/수정/삭제

2. 관리자가 클라이밍장 선택 및 특정 시간대 매칭 등록/수정/삭제

3. 사용자가 특정 클라이밍장의 특정 시간대 매칭 예약 (모집인원이 충족되었을때 예약 불가)

4. 사용자가 예약한 매칭 조회

<br>

## 💡 프로젝트 목표
**1. 테스트코드를 통해 코드 품질을 향상, CI/CD를 활용한 자동화 구현**
- JUnit 테스트를 통해 코드 변경시 Side Effect를 줄이고 영향도를 쉽게 파악할 수 있도록 합니다.
- 하나의 서비스에 여러 개발자가 개발해 나갈때 각자의 코드가 쉽게 배포 될 수 있도록 GitHub Actions를 활용했습니다.

**2. 객체 지향 설계를 적용하여 유지보수가 용이한 코드 구현**
- 중복되는 코드들과 불필요하게 수정이 발생할 수 있는 코드를 최소화하여 확장성에 용이하도록 합니다.
- SOLID 5원칙의 이해를 토대로 도메인 주도 설계를 위해 노력합니다.

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
![image](https://github.com/minbo2002/TennisWork/assets/68101836/add9c675-a9df-4575-a722-079dea9537c2)

<br>

## 👁‍🗨 이슈정리
- 유저들의 중복예약요청 및 동시성 제어는 어떻게 해야하는가?
- 팀을 이루어 프로젝트 진행시 Git flow를 어떻게 사용해야 하는가?

<br>

## 💾 ER 다이어그램
![ERD 구조](https://user-images.githubusercontent.com/68101836/234907996-e7c4e7dd-3b24-4aac-bab1-875cb18eb846.png)

<br>

## 🛠 기획 및 설계

### [기능 명세서]

### [DB 명세서]

### [API 문서]
https://documenter.getpostman.com/view/15520411/2s93m62hvR
