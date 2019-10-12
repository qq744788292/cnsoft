1、常规启动命令脚本

java -jar -Dspring.config.location=application-uat.yml LC-ZY-Web-Manager.war

2、配置文件切分
	application-dev.yml
	application-test.yml
	application-check.yml
	application-prod.yml

3、常用脚本
killall -9 java
cd /mnt/java
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-Manager-uat.yml /mnt/java/LC-ZY-Web-Manager.war > log.file 2>&1 &
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-User-uat.yml /mnt/java/LC-ZY-Web-User.war > log.file 2>&1 &
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-Monitor-uat.yml /mnt/java/LC-ZY-Monitor-0.0.1-SNAPSHOT.jar > log.file 2>&1 &
tail -f log.file




监控服务器启动脚本
killall -9 java
cd /mnt/java
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-Manager-prod.yml /mnt/java/LC-ZY-Web-Manager.war > log.file 2>&1 &
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-User-prod.yml /mnt/java/LC-ZY-Web-User.war > log.file 2>&1 &
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-Monitor-prod.yml /mnt/java/LC-ZY-Monitor-0.0.1-SNAPSHOT.jar > log.file 2>&1 &
tail -f log.file


生产服务器启动脚本
killall -9 java
cd /mnt/java
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-Manager-prod.yml /mnt/java/LC-ZY-Web-Manager.war > log.file 2>&1 &
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-User-prod.yml /mnt/java/LC-ZY-Web-User.war > log.file 2>&1 &
nohup java -Xms512m -Xmx1536m -jar -Dspring.config.location=/mnt/java/application-User-prod2.yml /mnt/java/LC-ZY-Web-User.war > log.file 2>&1 &
tail -f log.file
