#/bin/bash
cd /mnt/java
echo "server shutdown..."
killall -9 java
echo "server Manager start..."
java -jar -Dspring.config.location=application-Manager-uat.yml LC-ZY-Web-Manager.war > log.file 2>&1 &
echo "server User start..."
java -jar -Dspring.config.location=application-User-uat.yml LC-ZY-Web-User.war > log.file 2>&1 &
echo "server Monitor start..."
java -jar -Dspring.config.location=application-Monitor-uat.yml LC-ZY-Monitor-0.0.1-SNAPSHOT.jar > log.file 2>&1 &
echo "server start..."
tail -f log.file