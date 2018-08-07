<#include "../../../../../../java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${namespace}.dao;

<#include "../../../../../../java_imports.include">

import ${basepackage}.${namespace}.entity.*;
import net.microlinktech.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
* <#include "../../../../../../java_description.include">
*/
public interface ${className}Dao extends JpaRepository<${className}, Integer> ,JpaSpecificationExecutor<${className}>, BaseDao<${className}> {

<#list table.columns as column>
    <#if column.unique && !column.pk>
    ${className} findTopBy${column.columnName}(${column.simpleJavaType} ${column.columnNameLower});
    </#if>
</#list>
}