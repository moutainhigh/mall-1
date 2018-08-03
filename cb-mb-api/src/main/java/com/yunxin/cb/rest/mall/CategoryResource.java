package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.service.BrandService;
import com.yunxin.cb.mall.service.CategoryService;
import com.yunxin.cb.mall.vo.BrandVO;
import com.yunxin.cb.mall.vo.CategoryVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "运营分类接口")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CategoryResource extends BaseResource {

    @Resource
    private CategoryService categoryService;
    /**
     * 汽车运营分类编码
     */
    private static final String CAR_CATEGORY_CODE = "000000";

    @ApiOperation(value = "查询汽车品牌 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "category/list/carBrand")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CategoryVO>> carBrand() {
        try {
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.info("carBrand failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据汽车品牌分类查询车系 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "category/list/carBrand/{categoryId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CategoryVO>> carCatena(@PathVariable("categoryId") int categoryId) {
        try {
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.info("carCatena failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

}
