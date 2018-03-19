from mcpayment/ubuntu1404-java8

ENV JAVA_OPTS -Xms256m -Xmx1024m

RUN wget http://mirrors.sonic.net/apache/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz

RUN tar -zxf apache-maven-3.2.5-bin.tar.gz

RUN sudo cp -R apache-maven-3.2.5 /usr/local

RUN sudo ln -s /usr/local/apache-maven-3.2.5/bin/mvn /usr/bin/mvn

copy ./india-latest.osm.pbf /india-latest.osm.pbf

copy ./* /map-matching/

copy ./matching-core /map-matching/matching-core

copy ./matching-web /map-matching/matching-web

WORKDIR /map-matching

RUN ls

RUN ./map-matching.sh action=import datasource=/india-latest.osm.pbf vehicle=car


CMD ./map-matching.sh  action=start-server
