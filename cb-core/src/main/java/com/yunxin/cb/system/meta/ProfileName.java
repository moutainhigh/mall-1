package com.yunxin.cb.system.meta;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author tanggangyi
 */
public enum ProfileName {

    //system settings
    DATABASE_BACKUP_PATH("/opt"), // 数据库备份路径
    DATABASE_BACKUP_TIME("1:00"),//数据库备份时间（默认凌晨1点）
    DATABASE_BACKUP_PERIOD("day"),//数据库备份周期
    COPYRIGHT("/"), // 公司名称
    PROJECT_NAME("/"), // 系统名称

    //data syn interface param
    DATA_SYN_SECURE_SOCKET_LAYER("TLSv1.2"), // https 证书协议版本
    DATA_SYN_HTTPS_CERTIFICATE_FILE(""), // https 证书
    DATA_SYN_HTTPS_CERTIFICATE_PASSWORD(""), // https 证书密码
    DATA_SYN_FTP_SRVNM("ftpSrvnm"), // 数据同步服务器标识
    DATA_SYN_LOCATION_LIB_REMOTE_FILE_PATH("/"), // 位置库数据同步文件路径
    DATA_SYN_LOCATION_LIB_REMOTE_FILE_NAME_PREFIX("LST_UPALACCIEX"), // 位置库数据文件名前缀
    DATA_SYN_TERMINAL_LIB_REMOTE_FILE_PATH("/"), // 终端库数据同步文件路径
    DATA_SYN_TERMINAL_LIB_REMOTE_FILE_NAME_PREFIX("LST_UPATERMINALINFOEX"), // 终端库数据文件名前缀

    //imgFilePath
    DATA_DIR("/opt/data/fileUpload"), // 数据上传根路径
    UPLOAD_IMAGE_DIR("/mapImage"), // 数据上传目录

    LOGIN_ATTEMPT_COUNT("3"),
    LOGIN_ATTEMPT_LOCK_DURATION("20"),

    REFERERS(""),//REFERERS
    DISCLAIMER(""),//免责申明
    SWITCH_MAP("google_cn"),//切换地图：baidu,google,google_cn,openstreet
    ABOUT_PORTAL("V1.6"),//版本信息

    LOG_KEEP_DAYS("365")//保留多少天的日志
    ;

    private String i18nName;

    private String defaultValue;

    ProfileName(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getI18nName() {
        return i18nName;
    }

    public void setI18nName(String i18nName) {
        this.i18nName = i18nName;
    }

    public static ProfileName[] toI18nArray(MessageSource messageSource, Locale locale) {
        ProfileName[] annexTypes = ProfileName.values();
        for (ProfileName treeType : annexTypes) {
            treeType.setI18nName(messageSource.getMessage(ProfileName.class.getSimpleName() + "_" + treeType.name(), null, locale));
        }
        return annexTypes;
    }
}