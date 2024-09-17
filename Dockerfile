FROM bellsoft/liberica-openjdk-alpine:latest-cds

# install curl , jq
RUN  apk add  curl jq

WORKDIR /home/selenuim-docker

# Copy required files to run tests to the WORKDIR
ADD target/docker-resources ./
ADD scripts/runner.sh ./     

# Run the tests
##RUN chmod +x runner.sh
ENTRYPOINT sh runner.sh
