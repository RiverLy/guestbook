# guestbook

1. 환경 설정
    - 사용 스펙
        - Java 11 (adoptopenjdk-11)
        - SpringBoot 2.6.2
        - MariaDB
    - 추가 의존성
        - Thymeleaf
        - Lombok
        - Spring Data JPA
        - Spring Web
        - Spring Boot DevTools
        - MariaDB Driver
    - IDE
        - IntelliJ Community
2. 데이터 베이스 연결
    - application.yml 설정
        - driver : org.mariadb.jdbc.Driver
        - url : jdbc:mariadb://localhost:3306/test_db
