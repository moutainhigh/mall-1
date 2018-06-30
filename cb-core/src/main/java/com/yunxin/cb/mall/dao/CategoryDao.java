package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by gonglei on 16/1/14.
 */
public interface CategoryDao extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>, BaseDao<Category> {

    @Query("select category from Category category left join fetch category.parentCategory where category.enabled=true and category.categoryId=?1")
    Category getCategoryById(int categoryId);

    List<Category> findByLevelAndEnabled(int level, boolean enabled);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Category> findByEnabledOrderByCategoryIdAsc(boolean enabled);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select category from Category category left join fetch category.parentCategory p where category.enabled=true and p.categoryId=?1 order by category.sortOrder")
    List<Category> findByParentCategoryOrderBySortOrderAsc(int categoryId);
}
