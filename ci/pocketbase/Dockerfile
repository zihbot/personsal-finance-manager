FROM buster

WORKDIR /

RUN mkdir /pb_data
VOLUME /pb_data

COPY pb_schema.json /pb_schema.json
COPY pb_public /pb_public
COPY pfm-pocketbase /pfm-pocketbase

EXPOSE 8090

ENTRYPOINT ["./pfm-pocketbase", "serve"]
