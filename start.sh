echo "Generating the build."
./mvnw clean package -DskipTests
host="localhost"
port="8080"
java -jar qikserve-0.0.1-SNAPSHOT --port $port --verbose
echo "Started on host $host and port $port. PID : $!"
