package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Rank;

import java.util.List;

public interface IRankService {

    public Rank addRank(Rank rank);

    public Rank updateRank(Rank rank);

    public Rank getRankById(int rankId);

    public void removeRankById(int rankId);

    public void updateRankAndIntegral(int customerId, double calculateValue);

    public void setDefaulRankByRankId(int rankId);

    public List<Rank> getAllRanks();

}
