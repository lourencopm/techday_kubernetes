FROM postgres:16.1


ENV POSTGRES_PASSWORD db_pass
ENV POSTGRES_DB app_db

EXPOSE 5432

COPY init.sql /docker-entrypoint-initdb.d/