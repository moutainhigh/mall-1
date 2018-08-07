<#include "../../../../../../macro.include"/>
<#include "../../../../../../java_copyright.include">

<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${namespace}.entity;

<#include "../../../../../../java_imports.include">

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.microlinktech.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
* <#include "../../../../../../java_description.include">
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class ${className} implements java.io.Serializable {

private static final long serialVersionUID = 1L;

<@generateFields/>
<#--<@generateJavaColumns/>-->
<@generateCompositeIdConstructorIfis/>
<@generatePkProperties/>
<@generateNotPkProperties/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>

<#macro generateFields>

    <#if table.compositeId>
    private ${className}Id id;
        <#list table.columns as column>
            <#if !column.pk>
            private ${column.javaType} ${column.columnNameLower};
            </#if>
        </#list>
    <#else>
    //columns START
        <#list table.columns as column>
        /**
        * ${column.remarks}
        */
        ${column.hibernateValidatorExprssion}
            <#if column.isDateTimeColumn>
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            </#if>
        private ${column.simpleJavaType} ${column.columnNameLower};
        </#list>
    //columns END
    </#if>

</#macro>


<#macro generateCompositeIdConstructorIfis>

    <#if table.compositeId>
    public ${className}(){
    }
    public ${className}(${className}Id id) {
    this.id = id;
    }
    <#else>
        <@generateConstructor className/>
    </#if>

</#macro>

<#macro generatePkProperties>
    <#if table.compositeId>
    @EmbeddedId
    public ${className}Id getId() {
    return this.id;
    }

    public void setId(${className}Id id) {
    this.id = id;
    }
    <#else>
        <#list table.columns as column>
            <#if column.pk>
            @Id
            @GeneratedValue(strategy = IDENTITY)
            @Column(unique = ${column.unique?string}, nullable = ${column.nullable?string}, insertable = true, updatable = true, length = ${column.size})
            public ${column.simpleJavaType} get${column.columnName}() {
            return this.${column.columnNameLower};
            }

            public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
            this.${column.columnNameLower} = ${column.columnNameLower};
            }
            </#if>
        </#list>
    </#if>

</#macro>

<#macro generateNotPkProperties>
    <#list table.columns as column>
        <#if !column.pk>
            <#if column.isDateTimeColumn>
            @Temporal(TemporalType.TIMESTAMP)
            @JsonSerialize(using = JsonTimestampSerializer.class)
            </#if>
        @Column(unique = ${column.unique?string}, nullable = ${column.nullable?string}, insertable = true, updatable = true, length = ${column.size})
        public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
        }

        public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
        }

        </#if>
    </#list>
</#macro>

<#macro generateJavaOneToMany>
    <#list table.exportedKeys.associatedTables?values as foreignKey>
        <#assign fkSqlTable = foreignKey.sqlTable>
        <#assign fkTable    = fkSqlTable.className>
        <#assign fkPojoClass = fkSqlTable.className>
        <#assign fkPojoClassVar = fkPojoClass?uncap_first>

    private Set ${fkPojoClassVar}s = new HashSet(0);

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
    public Set<${fkPojoClass}> get${fkPojoClass}s() {
    return ${fkPojoClassVar}s;
    }

    public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
    this.${fkPojoClassVar}s = ${fkPojoClassVar};
    }
    </#list>
</#macro>

<#macro generateJavaManyToOne>
    <#list table.importedKeys.associatedTables?values as foreignKey>
        <#assign fkSqlTable = foreignKey.sqlTable>
        <#assign fkTable    = fkSqlTable.className>
        <#assign fkPojoClass = fkSqlTable.className>
        <#assign fkPojoClassVar = fkPojoClass?uncap_first>

    private ${fkPojoClass} ${fkPojoClassVar};
    public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
    this.${fkPojoClassVar} = ${fkPojoClassVar};
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        <#list foreignKey.parentColumns?values as fkColumn>
        @JoinColumn(name = "${fkColumn}",nullable = false, insertable = true, updatable = true) <#if fkColumn_has_next>,</#if>
        </#list>
    })
    public ${fkPojoClass} get${fkPojoClass}() {
    return ${fkPojoClassVar};
    }
    </#list>
</#macro>


}