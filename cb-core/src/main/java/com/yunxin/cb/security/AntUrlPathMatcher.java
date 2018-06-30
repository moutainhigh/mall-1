package com.yunxin.cb.security;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by gonglei on 14/12/16.
 */
public class AntUrlPathMatcher implements UrlMatcher {
    private boolean requiresLowerCaseUrl;
    private PathMatcher pathMatcher;

    public AntUrlPathMatcher() {
        this(true);
    }

    public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = true;
        this.pathMatcher = new AntPathMatcher();

        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public Object compile(String path) {
        if (this.requiresLowerCaseUrl) {
            return path.toLowerCase();
        }

        return path;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public boolean pathMatchesUrl(Object path, String url) {
        if (("/**".equals(path)) || ("**".equals(path))) {
            return true;
        }
        return this.pathMatcher.match(url + "**", (String) path);
    }

    public String getUniversalMatchPattern() {
        return "/**";
    }

    public boolean requiresLowerCaseUrl() {
        return this.requiresLowerCaseUrl;
    }

    public String toString() {
        return super.getClass().getName() + "[requiresLowerCase='" +
                this.requiresLowerCaseUrl + "']";
    }

}
