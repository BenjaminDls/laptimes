FROM ubuntu:latest

LABEL org.opencontainers.image.authors="benjamin.danlos@live.fr"

ARG UID=1000
ARG GID=1000
ARG steamUser
ARG steamPass

RUN dpkg --add-architecture i386
RUN apt update
RUN apt install lib32gcc-s1 zlib1g:i386 wget -y

RUN mkdir /home/steam
RUN chown $PID:$GID /home/steam -R
RUN chmod -R 755 /home/steam/

WORKDIR /home/steam

RUN wget http://media.steampowered.com/client/steamcmd_linux.tar.gz
RUN tar -xvf steamcmd_linux.tar.gz
RUN rm steamcmd_linux.tar.gz

RUN ./steamcmd.sh +@ShutdownOnFailedCommand 1 +@sSteamCmdForcePlatformType windows +@NoPromptForPassword 1 +force_install_dir /home/steam/assettocorsa +login $steamUser $steamPass +app_update 302550 validate +quit

EXPOSE 9600
EXPOSE 8081

WORKDIR /home/steam/assettocorsa

ENTRYPOINT [ "/home/steam/assettocorsa/acServer" ]