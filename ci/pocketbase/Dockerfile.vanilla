FROM alpine:3.16.3

RUN wget https://github.com/pocketbase/pocketbase/releases/download/v0.7.10/pocketbase_0.7.10_linux_amd64.zip -O pocketbase.zip
RUN unzip pocketbase.zip
RUN rm pocketbase.zip

RUN mkdir /pb_data
VOLUME /pb_data

EXPOSE 8090

ENTRYPOINT ["./pocketbase", "serve"]
