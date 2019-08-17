1、常规启动命令脚本

java -jar -Dspring.config.location=application-dev.yml LC-ZY-Web-User.war

2、配置文件切分
	application-dev.yml
	application-test.yml
	application-check.yml
	application-prod.yml

3、常用脚本
killall -9 java
java -jar -Dspring.config.location=application-Manager-uat.yml LC-ZY-Web-Manager.war > log.file 2>&1 &
java -jar -Dspring.config.location=application-User-uat.yml LC-ZY-Web-User.war > log.file 2>&1 &
java -jar -Dspring.config.location=application-Monitor-uat.yml LC-ZY-Monitor-0.0.1-SNAPSHOT.jar > log.file 2>&1 &
top



killall -9 java
java -jar -Dspring.config.location=application-Manager-prod.yml LC-ZY-Web-Manager.war > log.file 2>&1 &
java -jar -Dspring.config.location=application-User-prod.yml LC-ZY-Web-User.war > log.file 2>&1 &
java -jar -Dspring.config.location=application-User-prod2.yml LC-ZY-Web-User.war > log.file 2>&1 &



java -jar -Dspring.config.location=application-Monitor-prod.yml LC-ZY-Monitor-0.0.1-SNAPSHOT.jar > log.file 2>&1 &
top


