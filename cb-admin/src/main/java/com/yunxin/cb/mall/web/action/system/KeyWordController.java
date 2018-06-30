package com.yunxin.cb.mall.web.action.system;

import com.yunxin.cb.mall.entity.KeyWord;
import com.yunxin.cb.mall.service.IKeyWordService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.KeyWord;
import com.yunxin.cb.mall.service.IKeyWordService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * 
 * @author x001393
 *
 */
@Controller
@RequestMapping(value = "/system")
public class KeyWordController{
	
	@Resource
	private IKeyWordService keyWordService;

	@RequestMapping(value="keyWords")
	public String keyWords(ModelMap modelMap,HttpServletRequest request){
		return "system/keyWords";
	}

	@RequestMapping(value="pageKeyWords",method= RequestMethod.POST)
	@ResponseBody
	public Page<KeyWord> pageKeyWords(@RequestBody PageSpecification<KeyWord> query, HttpServletRequest request){
        return keyWordService.pageKeyWords(query);
		
	}
	
	@RequestMapping(value = "toAddKeyWord", method = RequestMethod.GET)
	public String toAddKeyWord(@ModelAttribute("keyWord") KeyWord keyWord,HttpServletRequest request) {
		return "system/addKeyWord";
	}

	@RequestMapping(value = "addKeyWord",method = RequestMethod.POST)
	public String addKeyWord(@ModelAttribute("keyWord") KeyWord keyWord,HttpServletRequest request){
		keyWordService.addKeyWord(keyWord);
		return "redirect:../common/success.do?reurl=system/keyWords.do";
	}
	
	@RequestMapping(value="toEditKeyWord", method = RequestMethod.GET)
	public String toEditKeyWord(@RequestParam("keyWordId") int keyWordId,ModelMap modelMap){
		KeyWord keyWord=keyWordService.getKeyWordById(keyWordId);
		modelMap.addAttribute("keyWord",keyWord);
		return "system/editKeyWord";
	}
	
	@RequestMapping(value = "editKeyWord", method = RequestMethod.POST)
	public String editKeyWord(@Valid @ModelAttribute("keyWord") KeyWord keyWord,HttpServletRequest request) {
		keyWordService.updateKeyWord(keyWord);
		return "redirect:../common/success.do?reurl=system/keyWords.do";
	}
	
	@RequestMapping(value = "removeKeyWordById", method = RequestMethod.GET)
	@ResponseBody
	public String removeKeyWordById(@RequestParam("keyWordId") int keyWordId,HttpServletRequest request) {
		keyWordService.removeKeyWord(keyWordId);
		return "success";
	}

}
