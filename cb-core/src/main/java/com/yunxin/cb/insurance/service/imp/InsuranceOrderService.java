package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.*;
import com.yunxin.cb.insurance.entity.*;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.util.CodeGenerator;
import com.yunxin.cb.util.ZxingUtils;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CalendarUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InsuranceOrderService implements IInsuranceOrderService {

    @Resource
    private InsuranceOrderDao insuranceOrderDao;
    @Resource
    private InsuranceOrderBeneficiaryDao insuranceOrderBeneficiaryDao;
    @Resource
    private InsuranceOrderInformedMatterDao insuranceOrderInformedMatterDao;
    @Resource
    private InsuranceOrderInsuredDao insuranceOrderInsuredDao;
    @Resource
    private InsuranceOrderPolicyholderBankDao insuranceOrderPolicyholderBankDao;
    @Resource
    private InsuranceOrderPolicyholderDao insuranceOrderPolicyholderDao;
    @Resource
    private InsuranceInformedMatterDao insuranceInformedMatterDao;
    @Resource
    private InsuranceOrderOffsiteDao insuranceOrderOffsiteDao;



    /**
     * 根据用户ID查询保险订单列表
     * @return
     */
    public List<InsuranceOrder> getInsuranceOrderByCustomer() {
        return insuranceOrderDao.findAll();
    }

    /**
     * 添加保险订单
     * @param insuranceOrder
     * @return
     */
    @Override
    @Transactional
    public InsuranceOrder addInsuranceOrder(InsuranceOrder insuranceOrder) {
        insuranceOrder.setOrderCode(CodeGenerator.getInsuranceCode());
        insuranceOrder.setCreateTime(new Date());
        //生成条形码
        String barCode = ZxingUtils.encodeBarCodeToBase64(insuranceOrder.getOrderCode(), 150, 50);
        insuranceOrder.setBarCode(barCode);
        InsuranceOrderInsured insuranceOrderInsured= insuranceOrder.getInsuranceOrderInsured();
        insuranceOrderInsuredDao.save(insuranceOrderInsured);

        InsuranceOrderPolicyholder insuranceOrderPolicyholder =insuranceOrder.getInsuranceOrderPolicyholder();
        insuranceOrderPolicyholderDao.save(insuranceOrderPolicyholder);

        insuranceOrderInsuredDao.save(insuranceOrder.getInsuranceOrderInsured());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        insuranceOrderPolicyholderBankDao.save(insuranceOrder.getInsuranceOrderPolicyholderBank());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        if(insuranceOrder.getInsuranceOrderOffsite() != null){
            insuranceOrderOffsiteDao.save(insuranceOrder.getInsuranceOrderOffsite());
        }
        insuranceOrder = insuranceOrderDao.save(insuranceOrder);
        insuranceOrder.setContractNo(insuranceOrder.getOrderCode());

        Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatters = insuranceOrder.getInsuranceOrderInformedMatters();
        for (InsuranceOrderInformedMatter insuranceOrderInformedMatter : insuranceOrderInformedMatters) {
            insuranceOrderInformedMatter.setInsuranceOrder(insuranceOrder);
            insuranceOrderInformedMatterDao.save(insuranceOrderInformedMatter);
        }


        Set<InsuranceOrderBeneficiary> insuranceOrderBeneficiarys = insuranceOrder.getInsuranceOrderBeneficiarys();
        for (InsuranceOrderBeneficiary insuranceOrderBeneficiary : insuranceOrderBeneficiarys) {
            insuranceOrderBeneficiary.setInsuranceOrder(insuranceOrder);
        }
        insuranceOrderBeneficiaryDao.save(insuranceOrderBeneficiarys);

        return insuranceOrder;

    }

    /**
     * 修改状态
     * @param orderId
     * @param orderState
     * @return
     */
    @Override
    @Transactional
    public boolean updInsuranceOrderState(int orderId,InsuranceOrderState orderState) {
        boolean flag=true;
        try {
            insuranceOrderDao.updInsuranceOrderState(orderState,orderId);
        }catch (Exception e){
            flag=false;
        }
        return flag;
    }

    /**
     * 获取事项
     * @param orderId
     * @return
     */
    @Override
    public List<Map<String, Object>> findMatter(int orderId) {
        final List<InsuranceOrderInformedMatter> insuranceOrderInformedMatterList =  insuranceOrderInformedMatterDao.getInsuranceOrderInformedMatter(orderId);

        return new ArrayList<Map<String,Object>>(){
            {
                int groupId=0;
                for(InsuranceOrderInformedMatter list:insuranceOrderInformedMatterList
                        ) {
                    InsuranceInformedMatter insuranceInformedMatter= insuranceInformedMatterDao.getInsuranceInformedMatter(list.getInsuranceInformedMatter().getMatterId());
                    if(null!=insuranceInformedMatter) {
                        InsuranceInformedMatterGroup matterGroup = insuranceInformedMatter.getMatterGroup();
                        if (null != matterGroup && Hibernate.isInitialized(matterGroup)) {
                            if(groupId!=insuranceInformedMatter.getMatterGroup().getGroupId()){
                                add(new HashMap<String, Object>() {
                                    {
                                        put("matter",matterGroup.getDescription());
                                        put("no","0");
                                    }
                                });
                                 groupId=matterGroup.getGroupId();
                            }
                        }
                        add(new HashMap<String,Object>(){
                            {
                                if(insuranceInformedMatter.getMatterType()==0){
                                    put("matter",insuranceInformedMatter.getMatterDescription());
                                   // put("o_value",list.getCollectValues());
                                    put("insured",list.getInsuredResult());
                                    put("insured_remark",list.getInsuredRemark());
                                    put("policy",list.getPolicyholderResult());
                                    put("policy_remark",list.getPolicyholderRemark());
                                    put("no","2");
                                }else{
                                    String description=insuranceInformedMatter.getMatterDescription();
                                        String[] str={"{0}","{1}","{2}","{3}","{4}","{5}","{6}"};

                                            String[] strValue=list.getCollectValues().replace("[","").replace("]","").replace("\"","") .split(",");
                                            if(strValue.length>0){
                                            for (int j=0;j<strValue.length;j++)
                                                description = description.replace(str[j],"<p style=\"text-decoration:underline;display:inline\">&nbsp;&nbsp;"+strValue[j]+"&nbsp;&nbsp;</p>");
                                            }else{
                                                for (int i=0;i<str.length;i++)
                                                    description = description.replace(str[i],"<p style=\"text-decoration:underline;display:inline\">&nbsp;&nbsp;&nbsp;&nbsp;</p>");
                                            }
                                    put("matter",description);
                                    put("insured",list.getInsuredResult());
                                    put("insured_remark",list.getInsuredRemark());
                                    put("policy",list.getPolicyholderResult());
                                    put("policy_remark",list.getPolicyholderRemark());
                                    put("no","2");
                                }

                            }
                        });
                    }
                }

            }
        };
    }



    /**
     * 保单分页
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query) {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        List<PageSpecification.FilterDescriptor> list=query.getFilter().getFilters();
//        for (PageSpecification.FilterDescriptor filterDescriptor:list
//             ) {
//            if("createTime".equals(filterDescriptor.getField())){
//
//                Date createTime= null;
//                    SimpleDateFormat simpleDateFormats=new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    Date dates=simpleDateFormats.parse(String.valueOf(filterDescriptor.getValue()));
//                    String createTimes=simpleDateFormat.format(dates);
//                    filterDescriptor.setValue(createTimes);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        query.setCustomSpecification(new CustomSpecification<InsuranceOrder>(){
            @Override
            public void buildFetch(Root<InsuranceOrder> root) {
                root.fetch(InsuranceOrder_.insuranceOrderPolicyholder, JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProduct,JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProductPrice,JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceOrderInsured,JoinType.LEFT);

            }

            @Override
            public void addConditions(Root<InsuranceOrder> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(InsuranceOrder_.createTime)));
            }
        });
        return insuranceOrderDao.findAll(query,query.getPageRequest());
    }

    /**
     * 保单详情
     * @param orderId
     * @return
     */
    @Override
    public InsuranceOrder getInsuranceOrderDetailById(int orderId) {
        InsuranceOrder InsuranceOrder = insuranceOrderDao.getInsuranceOrderDetailById(orderId);
        return InsuranceOrder;
    }

    public InsuranceOrder getInsuranceOrderDetailByOrderCode(String orderCode) {
        InsuranceOrder InsuranceOrder = insuranceOrderDao.getInsuranceOrderDetailByOrderCode(orderCode);
        return InsuranceOrder;
    }

    @Override
    public Map<String, Object> insuranceOrder(int orderId) {
        /**
         * 获取保单详情
         */
        final InsuranceOrder insuranceOrder = getInsuranceOrderDetailById(orderId);
        /**
         * 获取事项
         */
        final List<InsuranceOrderInformedMatter> insuranceOrderInformedMatterList =  insuranceOrderInformedMatterDao.getInsuranceOrderInformedMatter(orderId);
        SimpleDateFormat simpleDateFormats=new SimpleDateFormat("yyyy-MM-dd");
        return new HashMap<String,Object>(){
            {
                if(null!=insuranceOrder){

                    put("insuranceOrder",insuranceOrder);
                    /**
                     * 被保人
                     */
                    InsuranceOrderInsured insuranceOrderInsured= insuranceOrder.getInsuranceOrderInsured();
                    if(null!=insuranceOrderInsured&& Hibernate.isInitialized(insuranceOrderInsured)){

                        Date birthday=insuranceOrderInsured.getInsuredBirthday();
                        String insuredBirthday= simpleDateFormats.format(birthday);
                        String insuredCardPeriod= simpleDateFormats.format(insuranceOrderInsured.getInsuredCardPeriod());

                        if( StringUtils.isNotBlank(insuredBirthday)){
                            put("insurance_b_year",insuredBirthday.substring(0,4));
                            put("insurance_b_month",insuredBirthday.substring(5,7));
                            put("insurance_b_day",insuredBirthday.substring(8,insuredBirthday.length()));
                        }

                        String insuredTel=insuranceOrderInsured.getInsuredTel();
                        if(StringUtils.isNotBlank(insuredTel)){
                            String insured=insuredTel.substring(1,2);
                            String insuranceqtel="";
                            String insurancehtel="";
                            if(insured.equals("1")||insured.equals("2")){
                                insuranceqtel=insuredTel.substring(0,3);
                                insurancehtel=insuredTel.substring(3,insuredTel.length());
                            }else{
                                insuranceqtel=insuredTel.substring(0,4);
                                insurancehtel=insuredTel.substring(4,insuredTel.length());
                            }
                            put("insurance_q_tel",insuranceqtel);
                            put("insurance_h_tel",insurancehtel);
                        }

                        try {
                            int age= CalendarUtils.getAge(birthday);
                            put("age",age);
                            if(StringUtils.isNotBlank(insuredCardPeriod)){
                                put("insurance_p_year",insuredCardPeriod.substring(0,4));
                                put("insurance_p_month",insuredCardPeriod.substring(5,7));
                                put("insurance_p_day",insuredCardPeriod.substring(8,insuredCardPeriod.length()));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    /**
                     * 投保人
                     */
                    InsuranceOrderPolicyholder insuranceOrderPolicyholder= insuranceOrder.getInsuranceOrderPolicyholder();
                    if(null!=insuranceOrderPolicyholder&&Hibernate.isInitialized(insuranceOrderPolicyholder)){

                        Date birthday=insuranceOrderPolicyholder.getPolicyholderBirthday();
                        String policyholderBirthday= simpleDateFormats.format(birthday);
                        String policyholderCardPeriod= simpleDateFormats.format(insuranceOrderPolicyholder.getPolicyholderCardPeroid());

                        if(StringUtils.isNotBlank(policyholderBirthday)) {
                            put("policy_b_year", policyholderBirthday.substring(0, 4));
                            put("policy_b_month", policyholderBirthday.substring(5, 7));
                            put("policy_b_day", policyholderBirthday.substring(8, policyholderBirthday.length()));
                        }
                        String policyholderTel=insuranceOrderPolicyholder.getPolicyholderTel();

                        if(StringUtils.isNotBlank(policyholderTel)){

                            String policyholderT=policyholderTel.substring(1,2);
                            String policyqtel="";
                            String policyhtel="";
                            if(policyholderT.equals("1")||policyholderT.equals("2")){
                                policyqtel=policyholderTel.substring(0,3);
                                policyhtel=policyholderTel.substring(3,policyholderTel.length());
                            }else{
                                policyqtel=policyholderTel.substring(0,4);
                                policyhtel=policyholderTel.substring(4,policyholderTel.length());
                            }
                            put("policy_q_tel",policyqtel);
                            put("policy_h_tel",policyhtel);
                        }
                        if(StringUtils.isNotBlank(policyholderCardPeriod)){
                            put("policy_p_year",policyholderCardPeriod.substring(0,4));
                            put("policy_p_month",policyholderCardPeriod.substring(5,7));
                            put("policy_p_day",policyholderCardPeriod.substring(8,policyholderCardPeriod.length()));
                        }

                    }

                /**
                 * 受益人
                 */
                Set<InsuranceOrderBeneficiary> beneficiary=insuranceOrder.getInsuranceOrderBeneficiarys();

                if(null!=beneficiary&&Hibernate.isInitialized(beneficiary)&&beneficiary.size()>0){

                    List<InsuranceOrderBeneficiary> list=new ArrayList<>(beneficiary);
                    List<InsuranceOrderBeneficiary> beneficiaryList= sortIntMethod(list);
                    put("beneficiaryList",beneficiaryList);

                }
                    /**
                     * 告知事项填空
                     */
                    put("insurance_matter_value",new ArrayList<Map<String,Object>>(){{

                        for (InsuranceOrderInformedMatter insuranceOrderInformedMatter:insuranceOrderInformedMatterList
                                ) {
                            InsuranceInformedMatter insuranceInformedMatter= insuranceInformedMatterDao.getInsuranceInformedMatter(insuranceOrderInformedMatter.getInsuranceInformedMatter().getMatterId());

                            if(null!=insuranceInformedMatter) {
                                Integer matterType= insuranceInformedMatter.getMatterType();
                                if(matterType==1){
                                    add(new HashMap<String,Object>(){
                                        {
                                            String collectValues=insuranceOrderInformedMatter.getCollectValues();
//                                                if(StringUtils.isNotBlank(collectValues)){
                                                    String[] strValue=collectValues.replace("[","").replace("]","").replace("\"","") .split(",");
                                                   if(strValue.length>0){
                                                    for (int j=0;j<strValue.length;j++)
                                                        put("m_value"+j,"<p style=\"text-decoration:underline;display:inline\">&nbsp;&nbsp;"+strValue[j]+"&nbsp;&nbsp;</p>");
                                                    }else{
                                                        String[] strCollectValues={"{0}","{1}","{2}","{3}","{4}","{5}","{6}"};
                                                        for (int i=0;i<strCollectValues.length;i++)
                                                            put("m_value"+i,"<p style=\"text-decoration:underline;display:inline\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>");
                                                    }
                                        }
                                    });
                                }
                            }
                        }

                    }});

                    /**
                     * 告知事项补充说明
                     */
                    int pageSize=8;
                    List<InsuranceOrderInformedMatter> listInsuranceoim=new ArrayList<InsuranceOrderInformedMatter>();
                    put("matter_remark",new ArrayList<List<Map<String,Object>>>(){
                        {

                            List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
                            for (InsuranceOrderInformedMatter insuranceOrderInformedMatter:insuranceOrderInformedMatterList
                                    ) {
                                if(insuranceOrderInformedMatter.getInsuredResult()||insuranceOrderInformedMatter.getPolicyholderResult())
                                    listInsuranceoim.add(insuranceOrderInformedMatter);
                            }
                            if(listInsuranceoim!=null&&listInsuranceoim.size()>0){
                                int total=listInsuranceoim.size();

                                for (int i=0;i<listInsuranceoim.size();i++){

                                    InsuranceInformedMatter insuranceInformedMatter= insuranceInformedMatterDao.getInsuranceInformedMatter(listInsuranceoim.get(i).getInsuranceInformedMatter().getMatterId());

                                    String matterDescription=insuranceInformedMatter.getMatterDescription();
                                    if(matterDescription.contains("."))
                                        matterDescription= matterDescription.substring(0,matterDescription.indexOf("."));

                                    if(insuranceInformedMatter.getMatterGroup()!=null&&Hibernate.isInitialized(insuranceInformedMatter.getMatterGroup()))
                                        matterDescription=insuranceInformedMatter.getMatterGroup().getDescription().substring(0,insuranceInformedMatter.getMatterGroup().getDescription().indexOf("."))+matterDescription;
                                    final String matterDescriptions=matterDescription;
                                    final boolean insuredResult=listInsuranceoim.get(i).getInsuredResult();
                                    final boolean policyholderResult=listInsuranceoim.get(i).getPolicyholderResult();
                                    final String insuredRemark=listInsuranceoim.get(i).getInsuredRemark();
                                    final String policyholderRemark=listInsuranceoim.get(i).getPolicyholderRemark();

                                    list.add(new HashMap<String,Object>(){
                                        {
                                            put("title",matterDescriptions);
                                            if(insuredResult)
                                                put("insured_remark",insuredRemark );
                                            if(policyholderResult)
                                            put("policyholder_remark",policyholderRemark);
                                        }
                                    });
                                    if((i+1)%pageSize==0){
                                        add(list);
                                        list=new ArrayList<Map<String,Object>>();
                                    }else if(i==total-1) {
                                        add(list);
                                    }
                                }

                            }
                        }

                    });
                    put("insurance_matterList",insuranceOrderInformedMatterList);


                }

            }

        };
    }

    public static List<InsuranceOrderBeneficiary> sortIntMethod(List list){
        Collections.sort(list, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                InsuranceOrderBeneficiary insuranceOrderBeneficiary=(InsuranceOrderBeneficiary)o1;
                InsuranceOrderBeneficiary insuranceOrderBeneficiary2=(InsuranceOrderBeneficiary)o2;
                if(insuranceOrderBeneficiary.getBeneficiaryOrder()>insuranceOrderBeneficiary2.getBeneficiaryOrder())
                    return 1;
                else if(insuranceOrderBeneficiary.getBeneficiaryOrder()==insuranceOrderBeneficiary2.getBeneficiaryOrder())
                    return 0;
                else
                    return -1;
            }
        });
        return list;
    }

}
