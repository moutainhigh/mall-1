package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceEmailDao;
import com.yunxin.cb.insurance.dao.InsuranceOrderCodeDao;
import com.yunxin.cb.insurance.entity.InsuranceEmail;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class InsuranceOrderCodeService implements IInsuranceOrderCodeService {
    public static final String SMTPSERVER = "smtp.163.com";
    public static final String SMTPPORT = "465";
    public static final String ACCOUT = "wangteng1027@163.com";
    public static final String PWD = "wangteng@911027";
    public static final String ACCOUTNAME="Brad Pitt";

    @Resource
    private InsuranceOrderCodeDao insuranceOrderCodeDao;

    @Resource
    private InsuranceEmailDao insuranceEmailDao;
    /**
     *
     * @param codeNo
     * @return
     */
    @Override
    public InsuranceOrderCode getInsuranceOrderCodeByCodeNo(String codeNo){
        return insuranceOrderCodeDao.findInsuranceOrderCodeByCodeNo(codeNo);
    }

    /**
     *
     * @param insuranceOrderCode
     */
    @Override
    public void addInsuranceOrderCode(InsuranceOrderCode insuranceOrderCode){
        insuranceOrderCodeDao.save(insuranceOrderCode);
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceOrderCode> pageInsuranceOrderCode(PageSpecification<InsuranceOrderCode> query){
        query.setCustomSpecification(new CustomSpecification<InsuranceOrderCode>() {
            @Override
            public void buildFetch(Root<InsuranceOrderCode> root) {

            }
            @Override
            public void addConditions(Root<InsuranceOrderCode> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<InsuranceOrderCode> page = insuranceOrderCodeDao.findAll(query, query.getPageRequest());
        return page;
    }

    @Override
    public void mailReminding() {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", SMTPPORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
        // 根据邮件配置创建会话，注意session别导错包
        Session session = Session.getDefaultInstance(props);
        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(true);
        //创建邮件
        MimeMessage message = null;
        try {
            message = createEmail(session);
            //获取传输通道
            Transport transport = session.getTransport();
            transport.connect(SMTPSERVER,ACCOUT, PWD);
            //连接，并发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("成功");

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Transactional
    public MimeMessage createEmail(Session session) throws Exception {
         String  receiveACCOUT="Wangt@999shuijingqiu.com";
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                ACCOUTNAME, "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                receiveACCOUT, "王腾", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(MimeMessage.RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject("测试标题", "utf-8");
        String text="我是个程序员，一天我坐在路边一边喝水一边苦苦检查程序。 这时一个乞丐在我边上坐下了，开始要饭，我觉得可怜，就给了他1块钱。 然后接着调试程序。他可能生意不好，就无聊的看看我在干什么，然后过了一会，他缓缓地指着我的屏幕说，这里少了个分号";
        msg.setText(text);
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        InsuranceEmail insuranceEmail=new InsuranceEmail();
        insuranceEmail.setCreateTime(new Date());
        insuranceEmail.setFromEmail(ACCOUT);
        insuranceEmail.setReceiveEmail(receiveACCOUT);
        insuranceEmail.setContext(text);
        insuranceEmailDao.save(insuranceEmail);
        return msg;
    }
}
