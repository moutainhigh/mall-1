/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FavoriteDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.IFavoriteService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author z001075
 */
@Service
@Transactional
public class FavoriteService implements IFavoriteService {

    @Resource
    private FavoriteDao favoriteDao;

    @Override
    public Favorite addFavorite(Favorite favorite) {
        Favorite favorite1 = favoriteDao.findByCustomerAndCommodity(favorite.getCustomer(), favorite.getCommodity());
        if (favorite1 == null) {
            favorite.setCreateTime(new Date());
            return favoriteDao.save(favorite);
        }
        return favorite1;
    }

    @Override
    public void removeFavorite(int collectId) {
        favoriteDao.delete(collectId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countByCustomer(Customer customer) {
        return favoriteDao.countByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Favorite> getAllFavorites(Customer customer) {
        return favoriteDao.getAllFavorites(customer);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Favorite> pageCollects(PageSpecification<Favorite> collectQuery) {
        collectQuery
                .setCustomSpecification(new CustomSpecification<Favorite>() {
                    @Override
                    public void buildFetch(Root<Favorite> root) {
                        Fetch<Favorite, Customer> fetchCustomer = root.fetch(
                                Favorite_.customer, JoinType.LEFT);
                        fetchCustomer.fetch(Customer_.rank, JoinType.LEFT);
                        Fetch<Favorite, Commodity> fetchCommodity = root.fetch(
                                Favorite_.commodity, JoinType.LEFT);
                        fetchCommodity.fetch(Commodity_.brand, JoinType.LEFT);
//                        fetchCommodity
//                                .fetch(Commodity_.category, JoinType.LEFT);
                        fetchCommodity
                                .fetch(Commodity_.seller, JoinType.LEFT);
                    }
                });
        Page<Favorite> collects = favoriteDao.findAll(collectQuery,
                collectQuery.getPageRequest());
        return collects;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Favorite> pageFavorites(final PageSpecification<Favorite> favoritesQuery, Customer customer) {
        favoritesQuery.setCustomSpecification(new CustomSpecification<Favorite>() {
            @Override
            public void addConditions(Root<Favorite> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Customer> customerPath = root.get(Favorite_.customer);
                predicates.add(builder.equal(customerPath, customer));
            }

            @Override
            public void buildFetch(Root<Favorite> root) {
                Fetch<Favorite, Commodity> fetchCommodity = root.fetch(
                        Favorite_.commodity, JoinType.LEFT);
                fetchCommodity.fetch(Commodity_.brand, JoinType.LEFT);
            }
        });
        Page<Favorite> page = favoriteDao.findAll(favoritesQuery, favoritesQuery.getPageRequest());
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Favorite findByCustomerAndCommodity(Customer customer, Commodity commodity) {
        return favoriteDao.findByCustomerAndCommodity(customer, commodity);
    }

}
