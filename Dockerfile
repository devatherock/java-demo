FROM padraoix/maven-graalvm:centos-20.1.0

COPY build/libs/java-demo.jar /scripts/java-demo.jar

ENTRYPOINT ["java", "-jar", "/scripts/java-demo.jar"]