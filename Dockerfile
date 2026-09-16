#Docker裡用java 21
FROM eclipse-temurin:21jdk
#容器內的工作目錄
WORKDIR /app
#github copy進 docker
COPY . . 
#用mven 編譯 spring boot 產生 target/*.jar
RUN command +x mvnw
RUN .mvnw clean package -DskipTests
#宣告網站port
EXPOSE 8801

CMD [ "sh", "-c", "java -jar target/*.jar" ]

