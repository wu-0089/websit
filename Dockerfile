# 使用 Maven + Java 21 的環境來編譯 Spring Boot 專案
FROM maven:3.9.16-eclipse-temurin-21 AS builder

# 設定 Docker 容器內的工作目錄
# 後面的指令都會在 /app 底下執行
WORKDIR /app

# 把 GitHub 專案的檔案複製到 Docker 容器的 /app
COPY . .

# 使用 Maven 編譯 Spring Boot 專案
# clean：清除之前編譯產生的檔案
# package：編譯並打包成 JAR
# -DskipTests：跳過測試，加快部署時的建置速度
# 最後會產生 target/*.jar
RUN mvn clean package -DskipTests

# 第二階段：建立真正用來執行網站的環境
# 不需要 Maven，只需要 Java 21
FROM eclipse-temurin:21-jre

# 設定容器內的工作目錄
WORKDIR /app

# 把第一階段編譯完成的 JAR 複製到第二階段
# 並重新命名成 app.jar
COPY --from=builder /app/target/*.jar app.jar

# 宣告 Spring Boot 預計使用 8080 Port
EXPOSE 8080

# 啟動 Spring Boot 應用程式
# 執行編譯好的 app.jar
CMD ["java", "-jar", "app.jar"]