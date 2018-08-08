package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ReimbursementMapper {

    @Delete({
            "delete from rb_reimbursement",
            "where REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reimbursementId);


    @Insert({
            "insert into rb_reimbursement (REIMBURSEMENT_ID, REIMBURSEMENT_NO, ",
            "CUSTOMER_ID, AMOUNT, ",
            "TAX, ORDER_AMOUNT, ",
            "ORDER_STATE, CREATE_TIME)",
            "values (#{reimbursementId,jdbcType=INTEGER}, #{reimbursementNo,jdbcType=VARCHAR}, ",
            "#{customerId,jdbcType=INTEGER}, #{amount,jdbcType=DECIMAL}, ",
            "#{tax,jdbcType=DECIMAL}, #{orderAmount,jdbcType=DECIMAL}, ",
            "#{orderState,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="reimbursementId", keyColumn="REIMBURSEMENT_ID")
    int insert(Reimbursement record);


    @Select({
            "select",
            "REIMBURSEMENT_ID, REIMBURSEMENT_NO, CUSTOMER_ID, AMOUNT, TAX, ORDER_AMOUNT, ",
            "ORDER_STATE, CREATE_TIME",
            "from rb_reimbursement",
            "where REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_NO", property="reimbursementNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TAX", property="tax", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_AMOUNT", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Reimbursement selectByPrimaryKey(Integer reimbursementId);


    @Select({
            "select",
            "REIMBURSEMENT_ID, REIMBURSEMENT_NO, CUSTOMER_ID, AMOUNT, TAX, ORDER_AMOUNT, ",
            "ORDER_STATE, CREATE_TIME",
            "from rb_reimbursement"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_NO", property="reimbursementNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TAX", property="tax", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_AMOUNT", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Reimbursement> selectAll();


    @Update({
            "update rb_reimbursement",
            "set REIMBURSEMENT_NO = #{reimbursementNo,jdbcType=VARCHAR},",
            "CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
            "AMOUNT = #{amount,jdbcType=DECIMAL},",
            "TAX = #{tax,jdbcType=DECIMAL},",
            "ORDER_AMOUNT = #{orderAmount,jdbcType=DECIMAL},",
            "ORDER_STATE = #{orderState,jdbcType=INTEGER},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
            "where REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Reimbursement record);

    @Select({
            "<script>",
            "select",
            "REIMBURSEMENT_ID, REIMBURSEMENT_NO, CUSTOMER_ID, AMOUNT, TAX, ORDER_AMOUNT, ",
            "ORDER_STATE, CREATE_TIME",
            "from rb_reimbursement",
            "where 1=1",
            "<if test='data.customerId!=null'>",
            "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "<if test='data.orderState!=null'>",
            "and ORDER_STATE = #{data.orderState}",
            "</if>",
            "<if test='data.orderState==null'>",
            "and ORDER_STATE = 0 or ORDER_STATE = 4",
            "</if>",
            "ORDER BY CREATE_TIME DESC",
            "LIMIT #{rowIndex},#{pageSize}",
            "</script>"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_NO", property="reimbursementNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TAX", property="tax", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_AMOUNT", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Reimbursement> selectAllByCustomerId(Query q);

    @Select({
            "select",
            "REIMBURSEMENT_ID, REIMBURSEMENT_NO, CUSTOMER_ID, AMOUNT, TAX, ORDER_AMOUNT, ",
            "ORDER_STATE, CREATE_TIME",
            "from rb_reimbursement",
            "where REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER}",
            "and CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_NO", property="reimbursementNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TAX", property="tax", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_AMOUNT", property="orderAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Reimbursement selectByPrimaryKeyAndCustomerId(@Param("reimbursementId") Integer reimbursementId, @Param("customerId") Integer customerId);
}
