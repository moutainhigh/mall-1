package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.service.CategoryService;
import com.yunxin.cb.mall.vo.CategoryVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Api(description = "运营分类接口")
@RestController
@RequestMapping(value = "/{version}/mall/category")
public class CategoryResource extends BaseResource {

    @Resource
    private CategoryService categoryService;
    /**
     * 汽车运营分类编码
     */
    private static final String CAR_CATEGORY_CODE = "NO01";

    @ApiOperation(value = "查询汽车品牌 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "list/carBrand")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CategoryVO>> carBrand() {
        try {
            Category cate=categoryService.selectByParentCategoryNo(CAR_CATEGORY_CODE);
            if(LogicUtils.isNull(cate)){
                return new ResponseResult(Result.FAILURE);
            }
            List<CategoryVO> catagoryVOs = new ArrayList<>();
            List<Category> catagorys=categoryService.selectByParentCategoryId(cate.getCategoryId());
            for(Category category : catagorys){
                CategoryVO cVO = new CategoryVO();
                BeanUtils.copyProperties(cVO, category);
                catagoryVOs.add(cVO);
            }
            //根据分类名称升序排序
            Collections.sort(catagoryVOs, new Comparator<CategoryVO>() {
                @Override
                public int compare(CategoryVO o1, CategoryVO o2) {
                    Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
                    return com.compare(o1.getCategoryName(), o2.getCategoryName());

                }
            });
            return new ResponseResult(catagoryVOs);
        } catch (Exception e) {
            logger.info("carBrand failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据汽车品牌分类查询车系 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true, paramType = "path", dataType = "int")})
    @GetMapping(value = "getCategoryById/{categoryId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CategoryVO>> getCategoryById(@PathVariable("categoryId") int categoryId) {
        try {
            List<CategoryVO> catagoryVOs = new ArrayList<>();
            List<Category> catagorys=categoryService.selectByParentCategoryId(categoryId);
            for(Category category : catagorys){
                CategoryVO cVO = new CategoryVO();
                BeanUtils.copyProperties(cVO, category);
                catagoryVOs.add(cVO);
            }
            return new ResponseResult(catagoryVOs);
        } catch (Exception e) {
            logger.info("carCatena failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

}
