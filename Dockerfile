FROM openjdk:11.0.11-jre

#docker pull openjdk:11.0.11-jre
#도커파일 위치에서 실행 docker build --build-arg JAR_FILE=build/libs/*.jar -t springio/spring-boot-skeleton24x .
#원하는 스프링 프로파일로 이미지 실행 docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t springio/spring-boot-skeleton24x

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#ENV SPRING_PROFILE_ACTIVE live
#EXPOSE 8080
#ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app.jar"]