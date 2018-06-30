//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yunxin.core.orm;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class ImprovedPhysicalNamingStrategy implements PhysicalNamingStrategy {
    public ImprovedPhysicalNamingStrategy() {
    }

    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return this.convert(identifier, false);
    }

    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return this.convert(identifier, false);
    }

    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return this.convert(identifier, false);
    }

    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return this.convert(identifier, false);
    }

    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return this.convert(identifier, true);
    }

    private Identifier convert(Identifier identifier, boolean lower) {
        if (identifier != null && !StringUtils.isBlank(identifier.getText())) {
            String regex = "([a-z])([A-Z])";
            String replacement = "$1_$2";
            String newName = identifier.getText().replaceAll(regex, replacement);
            if (lower) {
                newName = newName.toLowerCase();
            } else {
                newName = newName.toUpperCase();
            }

            return Identifier.toIdentifier(newName);
        } else {
            return identifier;
        }
    }
}
