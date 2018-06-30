/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Freight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author qulei
 */
public interface FreightDao extends FreightPlusDao, JpaRepository<Freight, Integer>, JpaSpecificationExecutor<Freight> {


    public Freight findByAreaCode(String areaCode);

//	public Brand findByBrandName(String brandName);
//	
//	@Query("from Brand b left join fetch b.categories where b.brandId=?1")
//	public Brand findByBrandId(int brandId);
//	
//	@Query("from Brand b where brandName=?1 and brandId<>?2")
//	public Brand findByBrandNameAndBrandIdNot(String brandName,int brandId);
//	
//	@Modifying
//	@Query("update Brand b set b.del = 1 where b.brandId=?1")
//	public void removeBrandByBrandId(int brandId);

    @Query("select f from Freight f where f.areaCode like '%0000'")
    public List<Freight> listProvinceFreight();
}

interface FreightPlusDao {
    public List<Freight> listFreightByPcode(String pcode);
}
