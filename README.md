logproducer
======

This application was built for two purposes. The first purpose is to simulate an application running under user load. The only real output of this simulation is a constant stream of log messages. The second purpose of this application is to demonstrate the use of logback with a custom layout which includes the Application name and Space Name, which are obtained from Cloud Foundry's VCAP_APPLICATION environment variable.

# Build
./gradlew build

# Run
export VCAP_APPLICATION="{\"limits\":{\"mem\":1024,\"disk\":1024,\"fds\":16384},\"application_version\":\"24eca33d-351e-4a1a-bae4-ea0e3eeef2a2\",\"application_name\":\"logproducer\",\"application_uris\":[],\"version\":\"24eca33d-351e-4a1a-bae4-ea0e3eeef2a2\",\"name\":\"logproducer\",\"space_name\":\"development\",\"space_id\":\"d16fd8e8-2d7e-473b-9c82-d9c2025ff6fd\",\"uris\":[],\"users\":null,\"application_id\":\"9ee091dc-dafa-47c7-b1e4-67baf2f79eb4\",\"instance_id\":\"155ccb533c8247378a2917e9180f1e1d\",\"instance_index\":0,\"host\":\"0.0.0.0\",\"port\":61258,\"started_at\":\"2015-08-27 14:30:14 +0000\",\"started_at_timestamp\":1440685814,\"start\":\"2015-08-27 14:30:14 +0000\",\"state_timestamp\":1440685814}"

./gradlew bootRun

# Deploy to CF
cf push logproducer -p build/libs/logproducer-1.0.jar -n logproducer --no-route 