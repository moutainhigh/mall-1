# 自定义基础image
FROM 192.168.0.22/jre/8-alpine
LABEL maintainer="chenp@999shuijingqiu.com"

VOLUME /tmp
ADD ./cb-admin/build/libs/cb-admin-1.0.war cb-admin-1.0.war

# 使用 host network 不需要暴露端口
#EXPOSE 8761

ENTRYPOINT [ "java", "-server", "-Xms128m", "-Xmx512m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/cb-admin-1.0.war" ]