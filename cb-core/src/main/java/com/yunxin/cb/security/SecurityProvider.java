package com.yunxin.cb.security;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.yunxin.cb.security.SecurityConstants.SUPER_ROLE;

public abstract class SecurityProvider {
    private final Map<Privilege, Collection<ConfigAttribute>> privilegeMap = new HashMap();
    private List<Privilege> allPrivileges;
    private List<Privilege> menuPrivileges;

    public SecurityProvider() {
    }

    private static List<Privilege> loadMenuResources(List<Privilege> rescs) {
        if (CollectionUtils.isEmpty(rescs)) {
            return new ArrayList(0);
        } else {
            List<Privilege> privileges = new ArrayList();
            Iterator var2 = rescs.iterator();

            while(var2.hasNext()) {
                Privilege privilege = (Privilege)var2.next();
                if (privilege.getType() == PrivilegeType.MENU) {
                    Privilege resource = privilege.clone();
                    privileges.add(resource);
                    List<Privilege> childPrivileges = privilege.getChildren();
                    if (CollectionUtils.isNotEmpty(childPrivileges)) {
                        resource.setChildren(loadMenuResources(childPrivileges));
                    }
                }
            }

            return privileges;
        }
    }

    public abstract List<? extends IPermission> getAllPermissions();

    public abstract List<String> getPrivilegeCodesByUserName(String var1);

    public Privilege getPrivilegeByRequest(HttpServletRequest request) {
        return (Privilege)this.privilegeMap.keySet().parallelStream().filter((privilege) -> {
            return (new AntPathRequestMatcher(privilege.getValue())).matches(request);
        }).findFirst().orElse(null);
    }

    public Collection<ConfigAttribute> filterConfigAttributes(HttpServletRequest request) {
        Optional var10000 = this.privilegeMap.keySet().parallelStream().filter((resource) -> {
            return (new AntPathRequestMatcher(resource.getValue())).matches(request);
        }).findFirst();
        Map var10001 = this.privilegeMap;
        this.privilegeMap.getClass();
        return (Collection)var10000.map(var10001::get).orElse((Object)null);
    }

    private void buildPermissionMap(List<Privilege> privileges, List<? extends IPermission> permissions) {
        privileges.forEach((privilege) -> {
            Collection<ConfigAttribute> configAttributes = (Collection)permissions.stream().filter((IPermission) -> {
                return IPermission.getPrivilegeCode().equalsIgnoreCase(privilege.getCode());
            }).map((IPermission) -> {
                return new SecurityConfig(IPermission.getRoleCode());
            }).collect(Collectors.toList());
            if (configAttributes.isEmpty()) {
                configAttributes.add(new SecurityConfig(SUPER_ROLE));
            }

            List<Privilege> children = privilege.getChildren();
            if (CollectionUtils.isNotEmpty(children)) {
                this.buildPermissionMap(children, permissions);
            }

            if (StringUtils.isNotBlank(privilege.getValue())) {
                this.privilegeMap.put(privilege, configAttributes);
            }

        });
    }

    public void loadPrivileges() throws IOException {
        ClassPathResource resource = new ClassPathResource("resources.json");
        this.allPrivileges = (List)(new Gson()).fromJson(new InputStreamReader(resource.getInputStream(), "UTF-8"), (new TypeToken<List<Privilege>>() {
        }).getType());
        this.menuPrivileges = loadMenuResources(this.allPrivileges);
        this.privilegeMap.clear();
        List<? extends IPermission> permissions = this.getAllPermissions();
        this.buildPermissionMap(this.allPrivileges, permissions);
    }

    public List<Privilege> getAllPrivileges() {
        return this.allPrivileges;
    }

    public List<Privilege> getMenuPrivileges() {
        return this.menuPrivileges;
    }

    public List<Privilege> loadUserPrivileges(Authentication authentication) {
        List<Privilege> privileges = null;
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        Iterator var4 = grantedAuthorities.iterator();

        while(var4.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var4.next();
            if (Objects.equals(SUPER_ROLE, grantedAuthority.getAuthority())) {
                privileges = Collections.unmodifiableList(this.menuPrivileges);
                break;
            }
        }

        if (privileges == null) {
            UserDetails userDetails = (UserDetails)authentication.getPrincipal();
            List<String> userPrivilegeCodes = this.getPrivilegeCodesByUserName(userDetails.getUsername());
            privileges = this.filterUserResources(this.menuPrivileges, userPrivilegeCodes);
        }

        return privileges;
    }

    private List<Privilege> filterUserResources(List<Privilege> privileges, List<String> codes) {
        List<Privilege> userRescs = new ArrayList();
        Iterator var4 = privileges.iterator();

        while(var4.hasNext()) {
            Privilege privilege = (Privilege)var4.next();
            if (codes.contains(privilege.getCode())) {
                Privilege resource = privilege.clone();
                userRescs.add(resource);
                List<Privilege> childResources = privilege.getChildren();
                if (CollectionUtils.isNotEmpty(childResources)) {
                    resource.setChildren(this.filterUserResources(childResources, codes));
                }
            }
        }

        return userRescs;
    }
}