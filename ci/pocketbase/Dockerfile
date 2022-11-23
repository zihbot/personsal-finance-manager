FROM alpine:3.16.3

WORKDIR /

RUN mkdir /pb_data
VOLUME /pb_data

COPY pb_public /pb_public
COPY pb_schema.json /pb_schema.json
COPY pfm-pocketbase /pfm-pocketbase

EXPOSE 8090

ENTRYPOINT ["./pfm-pocketbase", "serve"]
