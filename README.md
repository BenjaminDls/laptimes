# Application Laptimes

## Supported games
 - Assetto Corsa (lapdata only and in extend version through [Python script](AssettoCorsaScripts/Configurator/UDPForwarder.py))
 - F1 2022

## How to run
### Environment variables
Fill these variables in a .env file and source it to load the values before running.  
(Only MariaDB supported as of now)  
```shell
dbadminpass=

dbpass=
dbuser=
dbhost=
dbport=
dbprotocol=mariadb
dbdriver=org.mariadb.jdbc.Driver
dbname=
dbdialect=org.hibernate.dialect.MySQLDialect

dbpass_dev=
dbuser_dev=
dbhost_dev=
dbport_dev=
dbprotocol_dev=mariadb
dbdriver_dev=org.mariadb.jdbc.Driver
dbname_dev=
dbdialect_dev=org.hibernate.dialect.MySQLDialect

discord_bot_id=
discord_token=
discord_events_channelId=

discord_bot_id_dev=
discord_token_dev=
discord_events_channelId_dev=
```

### Build
#### JAR
```shell
gradle build
```

#### Docker image
```shell
docker build -t laptimes:dev .
```

### Run
Start the full stack of both apis and both databases.  
Each database will have the table created and the dev database will also have the data from [this file](src/main/resources/ci-scripts/db/02-data.sql) inserted.  
```shell
source .env
docker compose up -d
```
