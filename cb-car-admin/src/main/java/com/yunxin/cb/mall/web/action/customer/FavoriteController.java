package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.IFavoriteService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.IFavoriteService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/collect")

public class FavoriteController {
	
	@Resource
	private IFavoriteService favoriteService;
	
	@RequestMapping(value = "collects")
	public String collects(ModelMap modelMap) {
		return "collect/collectList";
	}
	
	@RequestMapping(value = "pageCollects",method = RequestMethod.POST)
	@ResponseBody
	public Page<Favorite> pageCollects(@RequestBody PageSpecification<Favorite> collectQuery) {
		return favoriteService.pageCollects(collectQuery);
	}
}
