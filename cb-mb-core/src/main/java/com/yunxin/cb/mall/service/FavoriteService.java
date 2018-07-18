package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

/**
 * @title: 收藏夹 服务接口类
 * @auther: eleven
 * @date: 2018/7/18 19:59
 */
public interface FavoriteService {

	public Favorite addFavorite(Favorite favorite);

	public void removeFavorite(int favoriteId);

	public PageFinder<Favorite> pageCustomerFavorites(Query q);

	public Favorite findByCustomerAndCommodity(Favorite favorite);
}
