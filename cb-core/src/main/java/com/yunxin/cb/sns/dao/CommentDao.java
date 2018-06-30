package com.yunxin.cb.sns.dao;

import com.yunxin.cb.sns.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by gonglei on 16/3/15.
 */
public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
}
