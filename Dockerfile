# Docker 裡使用 Java 21
FROM eclipse-temurin:21-jdk

# 容器內的工作目錄
WORKDIR /app

# 把 GitHub 專案複製進 Docker
COPY . .

# 讓 Maven Wrapper 可以執行
RUN chmod +x mvnw

# 使用 Maven Wrapper 編譯 Spring Boot
# 產生 target/*.jar
RUN ./mvnw clean package -DskipTests

# 宣告網站使用 8080 Port
EXPOSE 8080

# 啟動 Spring Boot
CMD ["sh", "-c", "java -jar target/*.jar"]