package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankDao extends JpaRepository<Rank, Integer>, JpaSpecificationExecutor<Rank> {

    @Query("select rank from Rank rank where rank.integral=?1")
    public Rank getRankByIntegral(int integral);

    @Modifying
    @Query("update Rank r set r.defaultRank = false")
    public void updateRank();

    @Query("select rank from Rank rank where rank.defaultRank = true")
    public Rank getRankByDefaultRank();


    /**
     * 获得所有更高的等级的集合（积分阀值大于当前等级积分）
     * 按升序排列
     */
    @Query("select r from Rank r where r.integral>?1 order by r.integral asc")
    public List<Rank> findAllUpperRanks(int integral);

    public Rank findByDefaultRank(boolean defaultRank);

    @Modifying
    @Query("update Rank set defaultRank=?2 where rankId=?1")
    public void setDefaultRankByRankId(int rankId, boolean defaultRank);

}
