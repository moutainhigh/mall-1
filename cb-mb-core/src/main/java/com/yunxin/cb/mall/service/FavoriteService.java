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

	/**
	 * @title: 添加收藏夹
	 * @param: [favorite]
	 * @return: com.yunxin.cb.mall.entity.Favorite
	 * @auther: eleven
	 * @date: 2018/7/19 10:41
	 */
	public Favorite addFavorite(Favorite favorite);

	/**
	 * @title: 移除收藏夹
	 * @param: [favoriteId]
	 * @return: void
	 * @auther: eleven
	 * @date: 2018/7/19 10:41
	 */
	public int removeFavorite(int favoriteId);

	/**
	 * @title: 商品移出收藏夹(批量)
	 * @param: [commodityId]
	 * @return: com.yunxin.cb.vo.ResponseResult
	 * @auther: eleven
	 * @date: 2018/7/17 18:27
	 */
	public int removeFavoriteBatch(String[] favoriteIds);

	/**
	 * @title: 分页查询收藏夹
	 * @param: [q]
	 * @return: com.yunxin.cb.util.page.PageFinder<com.yunxin.cb.mall.entity.Favorite>
	 * @auther: eleven
	 * @date: 2018/7/19 10:42
	 */
	public PageFinder<Favorite> pageCustomerFavorites(Query q);

	/**
	 * @title: 通过商品id和客户id查询收藏夹
	 * @param: [favorite]
	 * @return: com.yunxin.cb.mall.entity.Favorite
	 * @auther: eleven
	 * @date: 2018/7/19 10:42
	 */
	public Favorite findByCustomerAndCommodity(Favorite favorite);


}
