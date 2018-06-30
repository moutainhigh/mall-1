/**
 *
 */
package com.yunxin.cb.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author keymean
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private SecurityHolder securityHolder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails userInfo = securityHolder.getUserDetailsByName(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException(userName);
        }
        return userInfo;
    }

    public void setSecurityHolder(SecurityHolder securityHolder) {
        this.securityHolder = securityHolder;
    }

}
