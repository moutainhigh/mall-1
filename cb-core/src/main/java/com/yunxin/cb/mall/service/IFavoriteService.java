/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z001075
 */
public interface IFavoriteService {

    public Favorite addFavorite(Favorite favorite);

    public void removeFavorite(int collectId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countByCustomer(Customer customer);

    public List<Favorite> getAllFavorites(Customer customer);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Favorite> pageFavorites(PageSpecification<Favorite> favoritesQuery, Customer customer);

    public Favorite findByCustomerAndCommodity(Customer customer, Commodity commodity);

    public Page<Favorite> pageCollects(PageSpecification<Favorite> collectQuery);


}
