#docker image'ını configure et
#sıfır kurulumu olan bir linux makinaya bu uygulamayı kurmak

#alias => takma ad
# java kurmak
FROM openjdk:17-slim as build

# build dosyalarını kopyalamak
#COPY LOCAL_YOL                              CLOUD_YOLU
COPY  target/notificationservice-0.0.1-SNAPSHOT.jar notificationservice-0.0.1-SNAPSHOT.jar

# build dosyalarını ayağa kaldırmak
#java -jar orderservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "notificationservice-0.0.1-SNAPSHOT.jar"]

# Docker Compose