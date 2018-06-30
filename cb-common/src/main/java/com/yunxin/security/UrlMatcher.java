package com.yunxin.security;

/**
 * Created by gonglei on 14/12/16.
 */
public interface UrlMatcher {

    public abstract Object compile(String paramString);

    public abstract boolean pathMatchesUrl(Object paramObject, String paramString);

    public abstract String getUniversalMatchPattern();

    public abstract boolean requiresLowerCaseUrl();
}
