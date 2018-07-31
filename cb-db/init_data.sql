INSERT INTO `insurance_product` VALUES (1, '生命福星高照终身寿险（分红型）', '福相随综合保障计划（2017版）', 'http://pb9sg55i7.bkt.clouddn.com/Fjf83MxmMEf9X2b3q7p2PslcjYDk', './static/img/product1.a28ff7e.png', '爱相伴、心相知、福相随', '终身', '十年', '生命福星高照终身寿险', '2018-07-05 16:28:07');
INSERT INTO `insurance_product` VALUES (2, '富德生命鑫福来年金保险', '富德生命鑫福来年金保险', 'http://pb9sg55i7.bkt.clouddn.com/FlZfNYa_yZ63AUyinW8V0A0-iwNt', './static/img/product2.17cf939.png', '鑫福来，鑫福送福乐开怀', '终身', '十年', '富德生命鑫福来年金保险', '2018-07-07 19:22:10');


INSERT INTO `insurance_product_price` VALUES (1, 1, 20000, '元', '返10万', '2018-07-05 16:44:20', '返10万');
INSERT INTO `insurance_product_price` VALUES (2, 1, 50000, '元', '返10万', '2018-07-07 19:19:48', '返10万');
INSERT INTO `insurance_product_price` VALUES (3, 1, 100000, '元', '返10万', '2018-07-07 19:20:17', '返10万');
INSERT INTO `insurance_product_price` VALUES (4, 2, 20000, '元', '返10万', '2018-07-07 19:20:57', '返10万');
INSERT INTO `insurance_product_price` VALUES (5, 2, 50000, '元', '返10万', '2018-07-07 19:28:21', '返10万');
INSERT INTO `insurance_product_price` VALUES (6, 2, 100000, '元', '返10万', '2018-07-07 19:28:35', '返10万');


INSERT INTO `seller` VALUES (1, 'crystalBall', '', b'0', 'crystalBall', 'crystalBall', 'crystalBall', 'crystalBall', '', 'crystalBall', 1, '2016-02-11 14:53:27', 'crystalBall@qq.com', b'1', '', 'crystalBall', NULL, 'crystalBall', '12341234', '', 'crystalBall', '1234', 'crystalBall', '深圳市福田区', 'ziyin', '自营', 1, '1234123', '1234');
INSERT INTO `role_info` VALUES (1, '2016-02-15 10:18:50', 'SUPER_ROLE', 'SUPER_ROLE', '超级管理员', 1);
INSERT INTO `user_info` VALUES (1, '2016-02-11 14:53:27', NULL, b'1', '2018-07-21 14:13:38', NULL, '123456', NULL, NULL, NULL, b'1', 'admin', 1);
INSERT INTO `user_role` VALUES (1, 1);


INSERT INTO `insurance_informed_matter` VALUES (1, 'C1.您是否正在申请或已经拥有任何保险公司的保险合同？若是，请说明：承保公司、保险品种、保险金额总和、因被保险人死亡给付的保险金总和、住院每日补贴日额及保险合同生效日期。', '2018-07-06 17:32:19', 1, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (2, 'C2.您的人寿保险、人身意外或健康保险的投保申请是否曾被拒保、推迟、加费或作限制保障权益？是否有解除保险合同？是否曾向任何保险公司提出索赔申请？若“是”，请说明。', '2018-07-06 17:32:57', 2, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (3, 'C3.是否计划出国或改变居住地或工作地点？正在或试图参加私人性质飞行，或携带氧气瓶潜水、或登山、或从事危险性的运动？若“是”，请填妥相关问卷，连同此投保单一并交回本公司。', '2018-07-06 17:33:12', 3, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (4, 'C4.是否持有有效摩托车驾照？', '2018-07-06 17:33:28', 4, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (5, 'C5.a.是否吸烟?若“是”，吸烟{0}年{1}支/天；若现已停止吸烟，停止吸烟原因及时间{2}。b.是否饮酒？若“是”，饮酒{3}年，种类{4}，数量{5}（两/周）；若现已停止饮酒，停止饮酒原因及时间{6}。', '2018-07-06 17:33:44', 5, 1, NULL, 1);
INSERT INTO `insurance_informed_matter` VALUES (6, 'C6.是否曾经或正在使用镇静安眠剂、可成瘾药物、麻醉剂或接受戒毒、戒酒治疗？', '2018-07-06 17:34:04', 6, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (7, 'C7.最近六个月内是否有医生建议您服药、住院、接受诊疗、手术或其他医疗方案？', '2018-07-06 17:34:25', 7, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (8, 'C8.最近五年内，是否曾经作下列之一的检查，有无异常？ 核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查。', '2018-07-06 17:35:35', 8, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (9, '.a 脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？b 眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？c 睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）d 子宫切除？阴道闭锁？乳房切除？（女性）e 视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？f 精神、智能障碍或性格行为异常？g 脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？', '2018-07-06 17:36:08', 9, 1, 1, 0);
INSERT INTO `insurance_informed_matter` VALUES (10, 'C10.a 您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？b 是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？', '2018-07-06 17:36:25', 10, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (11, 'C11.您若为16周岁(含)以上女性，请告知：a.目前是否怀孕？若是，已怀孕{0}周？b.（曾）患子宫、卵巢、乳房或其它生殖器官疾病？c.（曾）异常妊娠、阴道异常出血或接受下腹部手术？d.母亲、姐妹中是否有人（曾）患乳腺、子宫、卵巢等生殖器官恶性肿瘤？', '2018-07-06 17:36:46', 11, 1, NULL, 1);
INSERT INTO `insurance_informed_matter` VALUES (12, 'a.最近六个月内，是否有下列疾患或自觉症状?.不明原因皮肤出血点或瘀斑、鼻衄、反复齿龈出血?.不明原因的声嘶、关节红肿酸痛、难以愈合的舌、皮肤溃疡，持续低热，体重显著减轻(短期内5公斤以上)，痣的形态、大小或颜色改变、黄疸?.咳嗽、痰中有血块或血丝?眼睛胀痛、视力或听力明显下降、视物不清?.持续一周以上的吞咽困难、食欲不振、盗汗、腹泻、腹部不适?.紫绀、胸闷、心慌、气急、心前区疼痛、反复头痛、头晕?.小便困难、蛋白尿、血尿、便血、黑便、粘液便?', '2018-07-06 17:36:59', 12, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (13, 'b.视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上?', '2018-07-06 17:37:19', 13, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (14, 'c.脑脊液鼻漏或耳漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病?', '2018-07-06 17:38:54', 14, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (15, 'd.胸膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺?', '2018-07-06 17:39:09', 15, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (16, 'e. 慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病?', '2018-07-06 17:39:31', 16, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (17, 'f.肾炎、肾病综合症、尿毒症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、性病?', '2018-07-06 17:39:54', 17, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (18, 'g.糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病?', '2018-07-06 17:40:09', 18, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (19, 'h.贫血、再生障碍性贫血、白血病、紫癜症、血友病?', '2018-07-06 17:40:52', 19, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (20, 'i.风湿热、 关节炎、类风湿性关节炎、 痛风、颈椎病、椎间盘突出症、 红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、 其他结缔组织疾病?', '2018-07-06 17:41:08', 20, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (21, 'j.肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿或增生物?', '2018-07-06 17:41:22', 21, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (22, 'k.先天性疾病、遗传性疾病?', '2018-07-06 17:41:34', 22, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (23, 'l.身体是否有瘢痕？', '2018-07-06 17:41:47', 23, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (24, 'm.除上述以外的其它疾病、症状或意外受伤史?', '2018-07-06 17:42:00', 24, 1, 2, 0);
INSERT INTO `insurance_informed_matter` VALUES (25, 'C13.直系亲属中，是否患有或曾经患有高血压、肾病、心脏病、肝炎、肝肾囊肿、肝硬化、糖尿病、精神病、癌症或早于60周岁因病身故者？', '2018-07-16 14:46:41', 25, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (26, '.a 被保险人出生时身长{0}厘米，体重{1}公斤，出生医院{2}，出生时留院天数{3}天，如超过7天，请详细说明。b 出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？c 出生时是否有抢救史？d 是否未按要求接受预防接种？e是否曾进行婴幼儿体检且结果异常？ f是否经常患腹痛、婴幼儿腹泻等消化系统疾病？g是否曾患哮喘、肺炎、扁桃体炎等呼吸系统疾病？h是否曾患疝气，是否曾出现“高热惊厥”', '2018-07-06 17:42:17', 26, 1, 3, 1);
INSERT INTO `insurance_informed_matter` VALUES (27, ' C15.您是否参加公费医疗或社会医疗保险？', '2018-07-06 17:42:34', 27, 1, NULL, 0);
INSERT INTO `insurance_informed_matter` VALUES (28, ' C16.您是否有其他事项告知本公司？', '2018-07-06 17:42:45', 28, 1, NULL, 0);


INSERT INTO `insurance_informed_matter_group` VALUES (1, 'C9.是否有下列身体残障状况：', '2018-07-06 18:04:05', 1, 1);
INSERT INTO `insurance_informed_matter_group` VALUES (2, 'C12.是否患有或曾经患有以下疾病：', '2018-07-16 14:38:49', 2, 1);
INSERT INTO `insurance_informed_matter_group` VALUES (3, 'C14.若为2周岁(不含)以下婴儿，请额外告知', '2018-07-06 18:04:46', 3, 1);

INSERT INTO `rank` VALUES (1, b'1', 0, '普通用户', '1', '1');
INSERT INTO `customer`(`CUSTOMER_ID`, `ACCOUNT_NAME`, `ADDRESS`, `BIRTHDAY`, `CARD_NO`, `CITY`, `CREATE_TIME`, `CUSTOMER_TYPE`, `DISTRICT`, `EMAIL`, `ENABLED`, `EXCHANGE_INTEGRAL`, `INTEGRAL`, `MAIL_CHECKED`, `MOBILE`, `MOBILE_CHECKED`, `PASSWORD`, `POST_CODE`, `PROVINCE`, `QQ_ACCESS_TOKEN`, `QQ_FIGURE_URL`, `QQ_NICK_NAME`, `QQ_OPEN_ID`, `REAL_NAME`, `REMARK`, `SEX`, `TELEPHONE`, `TOTAL_INTEGRAL`, `RANK`, `EMAIL_CHECKED`, `RONG_CLOUD_TOKEN`, `AVATAR_URL`, `RECOMMEND_CUSTOMER_ID`, `NICK_NAME`, `PRAISE`, `PRAISE_NUM`, `CARD_TYPE`, `CUSTOMER_CARD_NO`, `CARD_POSITIVE_IMG`, `CARD_NEGATIVE_IMG`, `BANK_CARD_IMG`, `CUSTOMER_LEVEL`, `LEVEL_CODE`, `INVITATION_CODE`, `POLICY`) VALUES (1, '888888', '1', '2016-03-22 21:58:37', '1', '1', '2016-03-22 21:58:43', 1, '1', '1', b'1', 1, 0, b'1', '888888', b'1', '888888', '1', '1', '1', '1', '1', '1', '888888', '1', b'1', '1', 0, 1, b'0', NULL, NULL, NULL, NULL, 0, 0, '', '', '', '', '', 1, '1001', '888888', b'0');

INSERT INTO `catalog`(`CATALOG_ID`, `CATALOG_CODE`, `CATALOG_NAME`, `CREATE_TIME`, `ENABLED`, `LEAF`, `REMARK`, `SORT_ORDER`, `PARENT_CATALOG_ID`, `SUPPORT_ADDED_TAX`) VALUES (1, '000', '商品根分类', '2016-02-03 12:42:39', b'1', b'0', '', 12, 1, b'0');
INSERT INTO `category`(`CATEGORY_ID`, `CATEGORY_KEY`, `CATEGORY_NAME`, `CATEGORY_NO`, `CREATE_TIME`, `DESCRIPTION`, `ENABLED`, `ICON_PATH`, `LEVEL`, `RECOMMEND`, `REMARK`, `SEO_DESCRIPTION`, `SEO_KEY`, `SEO_TITLE`, `SORT_ORDER`, `PARENT_CATEGORY_ID`, `LOWEST_PRICE`, `HIGHEST_PRICE`) VALUES (1, '根分类', '根分类', '000', '2016-02-03 12:43:18', '根分类', b'1', 'category/000/1478053441093', 0, b'0', '根分类', '根分类', '11', '根分类', 0, 1,0,0);

INSERT INTO `profile` VALUES (3, 'ANDROID_VERSION_CODE', '1');
INSERT INTO `profile` VALUES (4, 'ANDROID_VERSION_NAME', 'v1.0.0.1');
INSERT INTO `profile` VALUES (5, 'ANDROID_APP_NAME', '水晶球');
INSERT INTO `profile` VALUES (6, 'ANDROID_URL', 'http://resource.999shuijingqiu.com/CrystalBall.apk');
INSERT INTO `profile` VALUES (7, 'ANDROID_DESCRIPTION', 'APP更新');