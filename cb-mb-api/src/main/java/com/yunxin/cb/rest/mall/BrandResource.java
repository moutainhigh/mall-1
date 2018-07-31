package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.service.BrandService;
import com.yunxin.cb.mall.vo.BrandVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "品牌接口")
@RestController
@RequestMapping(value = "/{version}/mall")
public class BrandResource extends BaseResource {
    @Resource
    private BrandService brandService;

    @ApiOperation(value = "查询所有品牌 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "brand/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<BrandVO>> getBrand() {
        try {
            List<Brand> list = brandService.selectAll();
            List<BrandVO> listVO = new ArrayList<>();
            for(Brand brand : list){
                BrandVO brandVO = new BrandVO();
                BeanUtils.copyProperties(brandVO, brand);
                listVO.add(brandVO);
            }
            return new ResponseResult(listVO);
        } catch (Exception e) {
            logger.info("brand failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
    @ApiOperation(value = "查询热门品牌 V1")
    @ApiImplicitParams({
    })
    @PostMapping(value = "brand/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<BrandVO>> getHotBrand() {
        try {
            List<Brand> list = brandService.selectHotBrand();
            List<BrandVO> listVO = new ArrayList<>();
            for(Brand brand : list){
                BrandVO brandVO = new BrandVO();
                BeanUtils.copyProperties(brandVO, brand);
                listVO.add(brandVO);
            }
            return new ResponseResult(listVO);
        } catch (Exception e) {
            logger.info("brand failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}
