<#include "../../../../../../java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${namespace}.dao;

<#include "../../../../../../java_imports.include">

import ${basepackage}.${namespace}.entity.*;
import net.microlinktech.core.orm.BaseDao;
import net.microlinktech.core.orm.BaseDaoImpl;


/**
* <#include "../../../../../../java_description.include">
*/
public class ${className}DaoImpl extends BaseDaoImpl<${className}> implements BaseDao<${className}> {


}