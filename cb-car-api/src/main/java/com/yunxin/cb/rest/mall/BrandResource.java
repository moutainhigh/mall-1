package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.service.BrandService;
import com.yunxin.cb.mall.vo.BrandVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
            Brand b=new Brand();
            b.setBrandName("奔驰");
            Query q=new Query();
            q.setData(b);
            List<Brand> list = brandService.getBrandList(q);
            List<BrandVO> listVO = list.stream()
                    .map(brand -> {
                        BrandVO brandVO = new BrandVO();
                        BeanUtils.copyProperties(brand,brandVO);
                        return brandVO;
                    }).collect(Collectors.toList());
            return new ResponseResult(listVO);
        } catch (Exception e) {
            logger.info("brand failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "查询所有品牌 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "brand/list2")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<BrandVO>> getBrand2() {
        try {
            Brand b=new Brand();
//            b.setBrandName("奔驰");
            Query q=new Query();
            q.setData(b);
            List<Brand> list = brandService.getBrandList(q);
            List<BrandVO> listVO = list.stream()
                    .map(brand -> {
                        BrandVO brandVO = new BrandVO();
                        BeanUtils.copyProperties(brand,brandVO);
                        return brandVO;
                    }).collect(Collectors.toList());
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
            List<Brand> list = brandService.getBrandList(null);
            List<BrandVO> listVO = list.stream()
                    .map(brand -> {
                        BrandVO brandVO = new BrandVO();
                        BeanUtils.copyProperties(brand,brandVO);
                        return brandVO;
                    }).collect(Collectors.toList());
            return new ResponseResult(listVO);
        }
        catch (Exception e) {
            logger.info("brand failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}
