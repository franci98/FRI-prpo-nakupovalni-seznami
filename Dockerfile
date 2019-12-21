FROM openjdk:8-jre-slim
RUN mkdir /app

COPY ./api/target/nakupovalni-seznami.jar ./app/nakupovalni-seznami.jar
#ADD ./target /app

EXPOSE 8080

WORKDIR /app

CMD java -jar nakupovalni-seznami.jar
#CMD java -cp classes:dependency/* com.kumuluz.ee.EeApplication