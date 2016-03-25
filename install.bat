set MVN=mvn

call %MVN% -f bankroll/pom.xml install:install-file -Dfile=../lib/com.hundsun.amqp.message-ext-1.0.0.jar -DgroupId=com.hundsun.amqp -DartifactId=message-ext -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true
