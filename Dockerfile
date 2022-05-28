#pull base image
FROM openjdk:17

# Add build
COPY build/libs/bug-tracker-api-0.0.1-SNAPSHOT.jar /app/

#Expose port
EXPOSE 8080

ENTRYPOINT ["java","-server","-Xms768m","-Xmx768m","-XX:MaxMetaspaceSize=256m","-XX:MetaspaceSize=256m","-jar","app/bug-tracker-api-0.0.1-SNAPSHOT.jar"]
