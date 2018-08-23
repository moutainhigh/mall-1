package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Profile;
import com.yunxin.cb.mall.entity.SpecFilter;
import com.yunxin.cb.mall.entity.meta.ProfileState;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.RestfulFactory;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.ProfileService;
import com.yunxin.cb.mall.service.SearchRestService;
import com.yunxin.cb.mall.service.SpecFilterService;
import com.yunxin.cb.mall.vo.SearchResultVo;
import com.yunxin.cb.mall.vo.SearchVo;
import com.yunxin.cb.mall.vo.SpecFilterVO;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/{version}/mall/search")
public class SearchResource extends BaseResource {

    @Resource
    private ProfileService profileService;
    @Resource
    private SpecFilterService specFilterService;

    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "返回行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "lat", value = "地理纬度", paramType = "query", dataType = "Double"),
            @ApiImplicitParam(name = "lon", value = "地理经度", paramType = "query", dataType = "Double")
    })
    @PostMapping(value = "keywordSearch")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult keywordSearch(@RequestParam String keyword, @RequestParam int page, @RequestParam int size,
                                        Double lat, Double lon) {
        try {
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            Call<ResponseResult> call = restService.keywordSearch(keyword, page, size, lat, lon);
            ResponseResult result = call.execute().body();
            return result;
        } catch (Exception e) {
            logger.info("categorySearch failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "分类搜索")
    @ApiImplicitParams({
    })
    @PostMapping(value = "categorySearch")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<SearchResultVo> categorySearch(@RequestBody @Valid SearchVo searchVo) {
        try {
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            Call<ResponseResult> call = restService.categorySearch(searchVo);
            ResponseResult result = call.execute().body();
            return result;
        } catch (Exception e) {
            logger.info("categorySearch failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
    @ApiOperation(value = "查询所有规格属性等")
    @GetMapping(value = "commodity")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult selectAll() {
        try {
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            Call<ResponseResult> call = restService.selectAll();
            ResponseResult result = call.execute().body();
            return result;
        } catch (Exception e) {
            logger.info("categorySearch failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }


    @ApiOperation(value = "热门搜索")
    @GetMapping(value = "hotSearch")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<String []> hotSearch() {
        try {
            Profile profile = profileService.getProfileByName(ProfileState.HOT_SEARCH.name());
            if (profile != null && StringUtils.isNotBlank(profile.getFileValue())) {
                String [] hotSearchArr = profile.getFileValue().split(",");
                return new ResponseResult(Result.SUCCESS, hotSearchArr);
            }
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.info("hotSearch failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "搜索条件-规格配置")
    @GetMapping(value = "specFilter")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult specFilter() {
        try {
            List<SpecFilter> specFilterList = specFilterService.getEnableAll();
            List<SpecFilterVO> listVO = specFilterList.stream()
                    .map(specFilter -> {
                        SpecFilterVO filterVO = new SpecFilterVO();
                        BeanUtils.copyProperties(specFilter, filterVO);
                        return filterVO;
                    }).collect(Collectors.toList());

            return new ResponseResult(listVO);
        } catch (Exception e) {
            logger.info("get specFilter failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

}
