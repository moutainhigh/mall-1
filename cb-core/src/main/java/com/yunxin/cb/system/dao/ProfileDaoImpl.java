package com.yunxin.cb.system.dao;


import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.entity.Profile_;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.core.orm.BaseDaoImpl;
import com.yunxin.core.util.Encrypt;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanggangyi
 */
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfilePlusDao {

    private void persistProfileNames(List<ProfileName> profileNames) {
        if (CollectionUtils.isNotEmpty(profileNames)) {
            for (ProfileName profileName : profileNames) {
                Profile profile = new Profile();
                profile.setProfileName(profileName);
                profile.setFileValue(profileName.getDefaultValue());
                entityManager.persist(profile);
            }
        }
    }

    private Profile findOneProfile(ProfileName profileType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> criteriaQuery = criteriaBuilder.createQuery(Profile.class);
        Root<Profile> root = criteriaQuery.from(Profile.class);
        Predicate predicate = criteriaBuilder.equal(root.get(Profile_.profileName), profileType);
        criteriaQuery.where(predicate);
        List<Profile> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return CollectionUtils.isNotEmpty(resultList) ? resultList.get(0) : null;
    }

    private List<Profile> findAllProfile() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> criteriaQuery = criteriaBuilder.createQuery(Profile.class);
        Root<Profile> root = criteriaQuery.from(Profile.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Profile> generateAllProfile() {
        List<Profile> profiles = this.findAllProfile();
        ProfileName[] profileNames = ProfileName.values();
        if (CollectionUtils.isEmpty(profiles)) {
            persistProfileNames(Arrays.asList(profileNames));
            return this.findAllProfile();
        } else {
            List<ProfileName> existProfiles = profiles.stream().map(Profile::getProfileName).collect(Collectors.toList());
            List<ProfileName> newProfileNames = Arrays.stream(profileNames).filter(profileName -> !existProfiles.contains(profileName)).collect(Collectors.toList());
            persistProfileNames(newProfileNames);
            return this.findAllProfile();
        }
    }

    @Override
    public String getValue(ProfileName profileType) {
        Profile profile = this.findOneProfile(profileType);
        if (profile == null) {
            profile = new Profile();
            profile.setProfileName(profileType);
            profile.setFileValue(profileType.getDefaultValue());
            this.entityManager.persist(profile);
        }
        return profile.getFileValue();
    }

    @Override
    public String updateValue(ProfileName profileType, String value) {
        Profile profile = this.findOneProfile(profileType);
        if (profile == null) {
            profile = new Profile();
            profile.setProfileName(profileType);
            profile.setFileValue(value);
            this.entityManager.persist(profile);
        } else {
            profile.setFileValue(value);
        }
        return profile.getFileValue();
    }
}