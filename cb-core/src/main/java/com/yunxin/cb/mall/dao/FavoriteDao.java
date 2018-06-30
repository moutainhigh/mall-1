/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author z001075
 */
public interface FavoriteDao extends JpaRepository<Favorite, Integer>, JpaSpecificationExecutor<Favorite> {

    @Query("select c from Favorite c left join fetch c.commodity where c.customer=?1 order by c.createTime desc")
    public List<Favorite> getAllFavorites(Customer customer);

    @Query("select c from Favorite c where c.customer=?1 and c.commodity=?2")
    public Favorite findByCustomerAndCommodity(Customer customer, Commodity commodity);

    long countByCustomer(Customer customer);

}
