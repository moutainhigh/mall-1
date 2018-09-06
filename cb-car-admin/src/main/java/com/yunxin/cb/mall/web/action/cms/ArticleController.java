package com.yunxin.cb.mall.web.action.cms;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.cms.entity.*;
import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


/**
 * @author k001389
 */
@Controller
@RequestMapping(value = "/cms")
public class ArticleController implements ServletContextAware {

    @Resource
    private IArticleService articleService;
    @Resource
    private IProgramaService programaService;
    @Resource
    private MessageSource messageSource;
    @Resource
    private IAttachmentService attachmentService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public String articles(ModelMap modelMap) {
        List<Programa> programas = programaService.getProgramas(true);
        List<ArticleChannel> articleChannels = programaService.getArticleChannels(true);
        List<SpecialSubject> specialSubjects = programaService.getSpecialSubjects(true);
        modelMap.addAttribute("programas", programas);
        modelMap.addAttribute("articleChannels", articleChannels);
        modelMap.addAttribute("specialSubjects", specialSubjects);
        return "cms/articles";
    }

    @RequestMapping(value = "pageArticles", method = RequestMethod.POST)
    @ResponseBody
    public Page<Article> pageArticles(@RequestBody PageSpecification<Article> query) {
        Page<Article> page = articleService.pageArticles(query);
        return page;
    }

    @RequestMapping(value = "toAddArticle", method = RequestMethod.GET)
    public String toAddArticle(@ModelAttribute("article") Article article, ModelMap modelMap) {
        List<Programa> programas = programaService.getProgramas(true);
        List<ArticleChannel> articleChannels = programaService.getArticleChannels(true);
        modelMap.addAttribute("programas", programas);
        modelMap.addAttribute("articleChannels", articleChannels);
        return "cms/addArticle";
    }

    @RequestMapping(value = "addArticle", method = RequestMethod.POST)
    public String addArticle(@Valid @ModelAttribute("article") Article article, HttpServletRequest request, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toAddArticle(article,modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                article.setPicPath(imgurl[0].split(",")[0]);
                Article articleDb =  articleService.addArticle(article);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.ARTICLE,articleDb.getArticleId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.ARTICLE,articleDb.getArticleId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            result.addError(new FieldError("article", "articleTitle", article.getArticleTitle(), true, null, null,
                    messageSource.getMessage("article_articleTitle_repeat", null, locale)));
            return toAddArticle(article, modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/articles.do";
    }

    @RequestMapping(value = "toEditArticle", method = RequestMethod.GET)
    public String toEditArticle(@RequestParam("articleId") int articleId, ModelMap modelMap) {
        Article article = articleService.getArticleById(articleId);
        modelMap.addAttribute("article", article);
        List<ArticleRecipeStep> recipeSteps=articleService.getRecipeStepsByArticleId(articleId);
        modelMap.addAttribute("recipeSteps", recipeSteps);
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.ARTICLE,articleId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return editArticle(article, modelMap);
    }

    private String editArticle(Article article, ModelMap modelMap) {
        List<Programa> programas = programaService.getProgramas(true);
        List<ArticleChannel> articleChannels = programaService.getArticleChannels(true);
        modelMap.addAttribute("programas", programas);
        modelMap.addAttribute("articleChannels", articleChannels);

        return "cms/editArticle";
    }

    @RequestMapping(value = "editArticle", method = RequestMethod.POST)
    public String editArticle(@Valid @ModelAttribute("article") Article article, BindingResult result, ModelMap modelMap, Locale locale,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toEditArticle(article.getArticleId(),modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                article.setPicPath(imgurl[0].split(",")[0]);
                Article articleDb=articleService.updateArticle(article);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.ARTICLE,articleDb.getArticleId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.ARTICLE,articleDb.getArticleId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            result.addError(new FieldError("article", "articleTitle", article.getArticleTitle(), true, null, null,
                    messageSource.getMessage("article_articleTitle_repeat", null, locale)));
            return toEditArticle(article.getArticleId(),modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/articles.do";
    }

    @RequestMapping(value = "removeArticleById", method = RequestMethod.GET)
    @ResponseBody
    public int removeArticleById(@RequestParam("articleId") int articleId) {
        //把图片删除掉
        attachmentService.deleteAttachmentPictures(ObjectType.ARTICLE, articleId);
        return articleService.removeArticleById(articleId);
    }

    @RequestMapping(value = "enableArticleById", method = RequestMethod.GET)
    @ResponseBody
    public int enableArticleById(@RequestParam("articleId") int articleId, @RequestParam("enabled") boolean enabled) {
        articleService.enableArticleById(articleId, enabled);
        return articleId;
    }
}
