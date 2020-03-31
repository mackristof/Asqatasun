FROM ubuntu:18.04
MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

# ##########################################################
#
#                      DISCLAIMER
#
# This is a fat container, that is absolutly not compliant to Docker best-practices
# https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/
# Don't use it for production as all data are wiped out at reboot / rebuild
# BUT for quick testing, that does the job :)
#
# ##########################################################

USER root
ENV  \
    DEBIAN_FRONTEND=noninteractive                                                              \
    ASQA_PREREQ_SQL_FILENAME=pre-requisites-SQL-ubuntu-1804-docker.sh                           \
    # XVFB
    XVFB_CMD="/usr/bin/Xvfb :99 -screen 0 1280x1024x24 -nolisten tcp"                           \
    # Tomcat
    WWWPORT="8080"                                                                              \
    TOMCAT_LIB_DIR="/usr/share/tomcat8/lib"                                                     \
    TOMCAT_USER="tomcat8"                                                                       \
    # Firefox
    FIREFOX_BASENAME="firefox-31.4.0esr"                                                        \
    FIREFOX_URL1="http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/"            \
    FIREFOX_URL2="31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2"                       \
    FIREFOX_URL="${FIREFOX_URL1}${FIREFOX_URL2}"                                                \
    FIREFOX_PARENT_DIR="/opt"                                                                   \
    # MariaDB
    MYSQL_ROOT_PASSWD="mysqlRootPassword"                                                       \
    # Asqatasun
    ASQA_RELEASE="4.1.0-SNAPSHOT"

EXPOSE $WWWPORT

RUN  apt-get update                              && \
     apt-get -yq --no-install-recommends  install   \
        apt-utils                                   \
        bzip2                                       \
        curl                                        \
        ca-certificates                             \
        debconf                                     \
        libgtk2.0-0                                 \
        libdbus-glib-1-2                            \
        libmysql-java                               \
        openjdk-8-jre                               \
        tomcat8                                     \
        unzip                                       \
        xvfb                                        \
        wget                                     && \
     /usr/sbin/update-java-alternatives -s java-1.8.0-openjdk-amd64 && \
     echo "mysql-server mysql-server/root_password password ${MYSQL_ROOT_PASSWD}" | debconf-set-selections && \
     echo "mysql-server mysql-server/root_password_again password ${MYSQL_ROOT_PASSWD}" | debconf-set-selections && \
     apt-get -y --no-install-recommends  install    \
        mariadb-server                           && \
     rm -rf  /var/lib/apt/lists/*


# ##########################################################
#
# Asqatasun installation
# cf http://doc.asqatasun.org/en/10_Install_doc/
#
# ##########################################################

RUN \
    # Tomcat (really useful?)
    #/bin/ln -s /usr/share/java/mysql-connector-java.jar "${TOMCAT_LIB_DIR}/mysql-connector-java.jar" && \
    # Xvfb
    /usr/bin/Xvfb :99 -screen 0 1280x1024x24 -nolisten tcp &
     # Firefox
WORKDIR "${FIREFOX_PARENT_DIR}"
RUN  wget -q "${FIREFOX_URL1}${FIREFOX_URL2}"                               && \
     tar xvfj "${FIREFOX_BASENAME}.tar.bz2"                                 && \
     rm "${FIREFOX_BASENAME}.tar.bz2"

WORKDIR "/root"
ADD "${ASQA_PREREQ_SQL_FILENAME}" /root
#RUN  \
#     # pre-requisites-SQL-ubuntu-1804.sh
#     "./${ASQA_PREREQ_SQL_FILENAME}"

CMD tail -f /var/log/mysql.log


#CMD  systemctl start mysql   && \
#     sleep   5               && \
#     systemctl start xvfb    && \
#     systemctl start tomcat7 ;  \
#     tail -f /var/log/tomcat7/catalina.out

#CMD  service mysql start                            && \
#     sleep   5                                      && \
#     tail -f /var/log/mysql.err   \
#             /var/log/mysql.log
