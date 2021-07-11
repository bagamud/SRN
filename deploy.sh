#!/usr/bin/env bash
mvn clean install
sudo rm -r /var/lib/tomcat9/webapps/srn/
cp /home/k/IdeaProjects/Information_Portal/target/srn.war /var/lib/tomcat9/webapps/
sudo systemctl restart tomcat9.service
