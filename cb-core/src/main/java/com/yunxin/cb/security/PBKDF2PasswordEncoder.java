package com.yunxin.cb.security;

import com.yunxin.cb.util.PasswordHash;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by moxin on 17/7/20.
 */
public class PBKDF2PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return PasswordHash.createHash(rawPassword.toString());
        } catch (PasswordHash.CannotPerformOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return PasswordHash.verifyPassword(rawPassword.toString(), encodedPassword);
        } catch (PasswordHash.CannotPerformOperationException | PasswordHash.InvalidHashException e) {
            throw new RuntimeException(e);
        }
    }
}
