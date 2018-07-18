package com.yunxin.cb.system.dao;


import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author tanggangyi
 */
public interface ProfileDao extends JpaRepository<Profile, Integer>, JpaSpecificationExecutor<Profile>, BaseDao<Profile>, ProfilePlusDao {

    @Query("select p from Profile p where p.profileName = ?1")
    Profile getProfileByProfileName(ProfileName profileName);
}

interface ProfilePlusDao {

    List<Profile> generateAllProfile();

    String getValue(ProfileName profileType);

    String updateValue(ProfileName profileType, String value);
}