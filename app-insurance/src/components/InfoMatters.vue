<template>
  <div>
    <div class="title">告知事项<span @click="changeFalse">全选否</span></div>
    <div class="content" style="height: auto">
      <p>1.您是否正在申请或已经拥有任何保险公司的保险合同？若是，请说明：承包公司、保险品种、保险金额总和、因被保险人死亡给付的保险金总和。住院每日补贴日额及保险合同生效日期。</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(0)">
          <img v-if="!matters[0].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[0].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(0)">
          <img v-if="!matters[0].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[0].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[0].insuredResult" v-model="matters[0].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[0].policyholderResult" v-model="matters[0].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>2.您的人寿保险、人身意外或健康保险的投保申请是否曾被拒保、推迟、加费、或作限制保障权益？是否有解除保险合同？是否曾向任何保险公司提出索赔申请？若“是”，请说明。</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(1)">
          <img v-if="!matters[1].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[1].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(1)">
          <img v-if="!matters[1].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[1].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[1].insuredResult" v-model="matters[1].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[1].policyholderResult" v-model="matters[1].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>3.是否计划出国或改变居住地或工作地点？正在试图参加私人性质飞行，或携带氧气瓶潜水、或登山、或从事危险性的运动？若“是”，请填妥相关问卷，连同此投保单一并交回本公司。</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(2)">
          <img v-if="!matters[2].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[2].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(2)">
          <img v-if="!matters[2].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[2].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[2].insuredResult" v-model="matters[2].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[2].policyholderResult" v-model="matters[2].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>4.是否持有有效摩托车驾照？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(3)">
          <img v-if="!matters[3].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[3].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(3)">
          <img v-if="!matters[3].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[3].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[3].insuredResult" v-model="matters[3].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[3].policyholderResult" v-model="matters[3].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p style="padding: 15px 0">5.a.是否吸烟?若“是”，吸烟<input type="number" class="value-input" v-model="values5[0]"/>年<input
        type="number" class="value-input" v-model="values5[1]"/>支/天；若现在已停止吸烟，停止吸烟原因及时间<input
        type="text" maxlength="24" class="value-input" v-model="values5[2]"/>。</p>
      <p style="padding: 15px 0">b.是否饮酒？若“是”，饮酒<input type="number" class="value-input" v-model="values5[3]"/>年，种类<input
        type="text" class="value-input" maxlength="24" v-model="values5[4]"/>，数量<input type="number" class="value-input"
                                                                                       v-model="values5[5]"/>
        （两/周）；若现在已停止饮酒，停止饮酒原因及时间<input type="text" class="value-input" maxlength="64" style="width: 10rem"
                                       v-model="values5[6]"/>。
      </p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(4)">
          <img v-if="!matters[4].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[4].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(4)">
          <img v-if="!matters[4].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[4].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[4].insuredResult" v-model="matters[4].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[4].policyholderResult" v-model="matters[4].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>6.是否曾经或正在使用镇静安眠剂、可成瘾药物、麻醉剂，或接受戒毒、戒酒治疗？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(5)">
          <img v-if="!matters[5].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[5].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(5)">
          <img v-if="!matters[5].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[5].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[5].insuredResult" v-model="matters[5].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[5].policyholderResult" v-model="matters[5].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>7.最近六个月内是否有医生建议您服药、住院、接受诊疗、手术或其他医疗方案？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(6)">
          <img v-if="!matters[6].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[6].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(6)">
          <img v-if="!matters[6].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[6].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[6].insuredResult" v-model="matters[6].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[6].policyholderResult" v-model="matters[6].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>8.最近五年内，是否曾经作下列之一的检查，有无异常？ 核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查。</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(7)">
          <img v-if="!matters[7].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[7].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(7)">
          <img v-if="!matters[7].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[7].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[7].insuredResult" v-model="matters[7].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[7].policyholderResult" v-model="matters[7].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>9.是否有下列身体残障情况：</p>
      <p style="padding: 15px 0">a.脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？</p>
      <p style="padding: 15px 0">b.眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？</p>
      <p style="padding: 15px 0">c.睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）</p>
      <p style="padding: 15px 0">d.子宫切除？阴道闭锁？乳房切除？（女性）</p>
      <p style="padding: 15px 0">e.视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？</p>
      <p style="padding: 15px 0">f.精神、智能障碍或性格行为异常？</p>
      <p style="padding: 15px 0">g.脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(8)">
          <img v-if="!matters[8].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[8].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(8)">
          <img v-if="!matters[8].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[8].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[8].insuredResult" v-model="matters[8].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[8].policyholderResult" v-model="matters[8].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p style="padding: 15px 0">10.a 您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？</p>
      <p style="padding: 15px 0">b 是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(9)">
          <img v-if="!matters[9].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[9].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(9)">
          <img v-if="!matters[9].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[9].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[9].insuredResult" v-model="matters[9].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[9].policyholderResult" v-model="matters[9].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p style="padding: 15px 0">11.若为16周岁(含)以上女性，请告知：a.目前是否怀孕？若是，已怀孕<input class="value-input" type="number"
                                                                            v-model="values11"/>周？</p>
      <p style="padding: 15px 0">b.（曾）患子宫、卵巢、乳房或其他生殖器官疾病？</p>
      <p style="padding: 15px 0">c.（曾）异常妊娠、阴道异常出血或接受下腹部手术？</p>
      <p style="padding: 15px 0">d.母亲、姐妹中是否有人（曾）患乳腺、子宫、卵巢等生殖器官恶性肿瘤？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(10)">
          <img v-if="!matters[10].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[10].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(10)">
          <img v-if="!matters[10].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[10].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[10].insuredResult" v-model="matters[10].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[10].policyholderResult" v-model="matters[10].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">12.是否患有或曾经患有以下疾病：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0">a.最近六个月内，是否有下列疾患或自觉症状?</p>
        <p style="padding: 15px 0">.不明原因皮肤出血点或瘀斑、鼻衄、反复齿龈出血?</p>
        <p style="padding: 15px 0">.不明原因的声嘶、关节红肿酸痛、难以愈合的舌、皮肤溃疡，持续低热，体重显著减轻(短期内5公斤以上)，痣的形态、大小或颜色改变、黄疸?</p>
        <p style="padding: 15px 0">.咳嗽、痰中有血块或血丝?眼睛胀痛、视力或听力明显下降、视物不清?</p>
        <p style="padding: 15px 0">.持续一周以上的吞咽困难、食欲不振、盗汗、腹泻、腹部不适?</p>
        <p style="padding: 15px 0">.紫绀、胸闷、心慌、气急、心前区疼痛、反复头痛、头晕?</p>
        <p style="padding: 15px 0">.小便困难、蛋白尿、血尿、便血、黑便、粘液便?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(11)">
            <img v-if="!matters[11].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[11].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(11)">
            <img v-if="!matters[11].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[11].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[11].insuredResult" v-model="matters[11].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[11].policyholderResult" v-model="matters[11].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(12)">
            <img v-if="!matters[12].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[12].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(12)">
            <img v-if="!matters[12].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[12].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[12].insuredResult" v-model="matters[12].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[12].policyholderResult" v-model="matters[12].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.脑脊液鼻漏或耳漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(13)">
            <img v-if="!matters[13].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[13].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(13)">
            <img v-if="!matters[13].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[13].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[13].insuredResult" v-model="matters[13].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[13].policyholderResult" v-model="matters[13].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.胸膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(14)">
            <img v-if="!matters[14].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[14].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(14)">
            <img v-if="!matters[14].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[14].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[14].insuredResult" v-model="matters[14].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[14].policyholderResult" v-model="matters[14].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e. 慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(15)">
            <img v-if="!matters[15].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[15].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(15)">
            <img v-if="!matters[15].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[15].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[15].insuredResult" v-model="matters[15].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[15].policyholderResult" v-model="matters[15].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">f.肾炎、肾病综合症、尿毒症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、性病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(16)">
            <img v-if="!matters[16].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[16].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(16)">
            <img v-if="!matters[16].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[16].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[16].insuredResult" v-model="matters[16].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[16].policyholderResult" v-model="matters[16].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">g.糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(17)">
            <img v-if="!matters[17].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[17].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(17)">
            <img v-if="!matters[17].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[17].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[17].insuredResult" v-model="matters[17].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[17].policyholderResult" v-model="matters[17].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">h.贫血、再生障碍性贫血、白血病、紫癜症、血友病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(18)">
            <img v-if="!matters[18].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[18].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(18)">
            <img v-if="!matters[18].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[18].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[18].insuredResult" v-model="matters[18].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[18].policyholderResult" v-model="matters[18].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">i.风湿热、 关节炎、类风湿性关节炎、 痛风、颈椎病、椎间盘突出症、 红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、 其他结缔组织疾病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(19)">
            <img v-if="!matters[19].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[19].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(19)">
            <img v-if="!matters[19].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[19].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[19].insuredResult" v-model="matters[19].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[19].policyholderResult" v-model="matters[19].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">j.肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿或增生物?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(20)">
            <img v-if="!matters[20].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[20].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(20)">
            <img v-if="!matters[20].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[20].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[20].insuredResult" v-model="matters[20].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[20].policyholderResult" v-model="matters[20].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">k.先天性疾病、遗传性疾病?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(21)">
            <img v-if="!matters[21].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[21].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(21)">
            <img v-if="!matters[21].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[21].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[21].insuredResult" v-model="matters[21].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[21].policyholderResult" v-model="matters[21].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">l.身体是否有瘢痕？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(22)">
            <img v-if="!matters[22].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[22].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(22)">
            <img v-if="!matters[22].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[22].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[22].insuredResult" v-model="matters[22].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[22].policyholderResult" v-model="matters[22].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>

      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">m.除上述以外的其它疾病、症状或意外受伤史?</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(23)">
            <img v-if="!matters[23].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[23].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(23)">
            <img v-if="!matters[23].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[23].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[23].insuredResult" v-model="matters[23].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
        <textarea class="content-text" type="text" v-if="matters[23].policyholderResult" v-model="matters[23].policyholderRemark"
                  placeholder="投保人" maxlength="250"/>
      </div>
    </div>

    <div class="content">
      <p>13.直系亲属中，是否患有或曾经患有高血压、肾病、心脏病、肝炎、肝肾囊肿、肝硬化、糖尿病、精神病、癌症或早于60周岁因病身故者？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(24)">
          <img v-if="!matters[24].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[24].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(24)">
          <img v-if="!matters[24].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[24].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[24].insuredResult" v-model="matters[24].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[24].policyholderResult" v-model="matters[24].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">14.若为2周岁(不含)以下婴儿，请告知：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0; margin-bottom: 0">a.被保险人出生时身长<input type="number" class="value-input"
                                                                       v-model="values12[0]"/>厘米，体重<input
          class="value-input" type="number" v-model="values12[1]"/>公斤，出生医院<input type="text" class="value-input"
                                                                                 v-model="values12[2]"/>
          ，出生时留院天数<input class="value-input" type="number" v-model="values12[3]"/>天，如超过7天，请详细说明。</p>
        <p style="padding: 15px 0">b.出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？</p>
        <p style="padding: 15px 0">c.出生时是否有抢救史？</p>
        <p style="padding: 15px 0">d.是否未按要求接受预防接种？</p>
        <p style="padding: 15px 0">e.是否曾进行婴幼儿体检且结果异常？</p>
        <p style="padding: 15px 0">f.是否经常患腹痛、婴幼儿腹泻等消化系统疾病？</p>
        <p style="padding: 15px 0">g.是否曾患哮喘、肺炎、扁桃体炎等呼吸系统疾病？</p>
        <p style="padding: 15px 0">h.是否曾患疝气？i是否曾出现“高热惊厥”</p>
        <p style="padding: 15px 0">i.是否曾出现“高热惊厥”？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(25)">
            <img v-if="!matters[25].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[25].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" type="text" v-if="matters[25].insuredResult" v-model="matters[25].insuredRemark"
                  placeholder="被保人" maxlength="250"/>
      </div>
    </div>

    <div class="content">
      <p>15.是否已参加公费医疗或社会医疗保险。</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(26)">
          <img v-if="!matters[26].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[26].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[26].insuredResult" v-model="matters[26].insuredRemark"
                placeholder="被保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>16.您是否有其他事项告知本公司？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(27)">
          <img v-if="!matters[27].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[27].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(27)">
          <img v-if="!matters[27].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[27].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" type="text" v-if="matters[27].insuredResult" v-model="matters[27].insuredRemark"
                placeholder="被保人" maxlength="250"/>
      <textarea class="content-text" type="text" v-if="matters[27].policyholderResult" v-model="matters[27].policyholderRemark"
                placeholder="投保人" maxlength="250"/>
    </div>

    <div class="content">
      <p>请确认您（用户）已完全阅读并如实告知上述询问内容</p>
      <div class="content-state">
        <div style="display: inline" @click="state = !state">
          <img v-if="!state" class="checkIcon" src="../assets/img/unselect.png">
          <img v-if="state" class="checkIcon" src="../assets/img/selected.png">
        </div>
        <p class="recognizee">确认</p>
      </div>
    </div>
    <toast v-model="showPositionValue" type="text" :time="800" is-show-mask position="middle">{{toastText}}</toast>
    <div style="height: 60px;">
      <div class="i-footer">
        <button @click="next">
          <div>下一步</div>
        </button>
      </div>
    </div>

  </div>
</template>

<script>
  import storage from "../store/storage";
  import {Toast} from 'vux'
  import {emoji} from "../admin/validate";

  export default {
    components: {Toast},
    name: "infoMatters",
    data() {
      return {
        state: false,
        matters: storage.fetch("matters"),
        values5: '',
        values11: '',
        values12: '',
        twoYear: true,
        showPositionValue: false,
        toastText: '',
        enableSumit: true
      }
    },
    methods: {
      changeInsuredState(index) {
        this.matters[index].insuredResult = !this.matters[index].insuredResult;
      },
      changeHolderState(index) {
        this.matters[index].policyholderResult = !this.matters[index].policyholderResult;
      },
      changeFalse() {
        this.matters.forEach(function (matter) {
          matter.insuredResult = false;
          matter.policyholderResult = false;
        });
      },
      next() {
        // let test = document.getElementsByTagName("textarea");
        // for (let i = 0; i< test.length; i++ ) {
        //   console.log(test[i].value);
        //   if(emoji.test(test[i].value)){
        //
        //   }
        // }
        // return false;
        //吸烟校验
        if (this.matters[4].insuredResult || this.matters[4].policyholderResult) {
          if (this.values5[0] === '' || this.values5[1] === '' || this.values5[2] === '' || this.values5[3] === '' || this.values5[4] === '' || this.values5[5] === '') {
            alert("请完善吸烟和饮酒的信息");
            return false;
          }
        }
        if (this.values5[0].length > 3 || this.values5[1].length > 3 || this.values5[3].length > 3 || this.values5[5].length > 3) {
          alert("输入长度不得大于3位");
          return false;
        }
        if (parseInt(this.values5[0]) <= 0 || parseInt(this.values5[1]) <= 0 || parseInt(this.values5[3]) <= 0 || parseInt(this.values5[5]) <= 0) {
          alert("请输入大于0的整数");
          return false;
        }

        if (this.twoYear === false) {
          if (this.values12[0] === '' || this.values12[1] === '' || this.values12[2] === '' || this.values12[3] === '') {
            alert("被保人小于2周岁，请填写第12项额外信息");
            return false;
          }
        }
        if (this.values11.length > 3) {
          alert("怀孕周数长度不大于3");
          return false;
        }
        if (parseInt(this.values11) <= 0) {
          alert("怀孕周数不能小于0");
          return false;
        }
        if (this.values12[0].length > 3 || this.values12[1].length > 3 || this.values12[3].length > 3) {
          alert("婴儿信息栏填写长度不大于3");
          return false;
        }
        if (parseInt(this.values12[0]) <= 0 || parseInt(this.values12[1]) <= 0 || parseInt(this.values12[3]) <= 0) {
          alert("婴儿信息栏填写数据需大于0");
          return false;
        }
        this.enableSumit = true;
        for (let i = 0; i < this.matters.length; i++) {
          if (this.matters[i].insuredResult) {
            if (this.matters[i].insuredRemark === '') {
              this.enableSumit = false;
            }
          }
          if (this.matters[i].policyholderResult) {
            if (this.matters[i].policyholderRemark === '') {
              this.enableSumit = false;
            }
          }
        }
        console.log(this.enableSumit);
        if (!this.enableSumit) {
          alert("请完善被保人或投保人告知事项");
          return false;
        }
        if (this.state) {
          this.$router.push('autograph');
        } else {
          alert("请勾选确认")
        }
      }
    },
    watch: {
      values5: {
        handler(newVal, oldVal) {
          if (newVal) {
            if (this.matters.length !== 0) {
              this.matters[4].collectValues = JSON.stringify(newVal);
            }
            if (newVal[0].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (newVal[1].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (newVal[3].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (newVal[5].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (parseInt(newVal[0]) <= 0 || parseInt(newVal[1]) <= 0 || parseInt(newVal[3]) <= 0 || parseInt(newVal[5]) <= 0) {
              this.showPositionValue = true;
              this.toastText = "请输入大于0的整数";
            }
          } else {
            this.values5 = ['', '', '', '', '', ''];
          }
        },
        immediate: true,
        deep: true
      },
      values12: {
        handler(newVal, oldVal) {
          if (newVal) {
            if (this.matters.length !== 0) {
              this.matters[25].collectValues = JSON.stringify(newVal);
            }
            if (newVal[0].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (newVal[1].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (newVal[3].length > 3) {
              this.showPositionValue = true;
              this.toastText = "输入长度不得大于3位";
            }
            if (parseInt(newVal[0]) <= 0 || parseInt(newVal[1]) <= 0 || parseInt(newVal[3]) <= 0) {
              this.showPositionValue = true;
              this.toastText = "请输入大于0的整数";
            }
          } else {
            this.values12 = ['', '', '', ''];
          }
        },
        immediate: true,
        deep: true
      },
      values11: function (newVal, oldVal) {
        if (this.matters.length !== 0) {
          this.matters[10].collectValues = newVal;
        }
        if (newVal.length > 3) {
          this.showPositionValue = true;
          this.toastText = "输入长度不得大于3位";
        }
        if (parseInt(newVal) <= 0) {
          this.showPositionValue = true;
          this.toastText = "请输入大于0的整数";
        }
      },
      matters: {
        handler(newVal, oldVal) {
          storage.save("matters", newVal);
        },
        immediate: true,
        deep: true
      }
    },
    created: function () {
      if (storage.fetch("matters").length !== 0) {
        if (storage.fetch("matters")[4].collectValues) {
          this.values5 = JSON.parse(storage.fetch("matters")[4].collectValues);
        }
        this.values11 = storage.fetch("matters")[10].collectValues;
        if (storage.fetch("matters")[25].collectValues) {
          this.values12 = JSON.parse(storage.fetch("matters")[25].collectValues);
        }
      } else {
        let matters = [];
        for (let i = 1; i <= 28; i++) {
          matters.push(
            {
              insuranceInformedMatter: {
                matterId: i
              },
              insuredResult: false,
              policyholderResult: false,
              collectValues: '',
              insuredRemark: '',
              policyholderRemark: '',
            }
          )
        }
        this.matters = matters;
      }

      //判断被保人周岁是否大于2周岁
      let insured = storage.fetch("insured");
      let newDate = new Date();
      let birthday = new Date(insured.insuredBirthday.replace(/-/, "/"));
      let time = (newDate.valueOf() - birthday.valueOf());
      if (time < 24 * 60 * 60 * 1000 * 365 * 2) {
        this.twoYear = false;
      }
    }
  }
</script>

<style scoped>
  .title {
    margin-bottom: 10px;
    background-color: #ffffff;
    padding: 15px;
    font-size: 13px;
    color: #c01212;
  }

  .title span {
    text-decoration: underline;
    color: #000;
    font-size: 13px;
    float: right;
  }

  .content {
    background: #ffffff;
    padding: 15px;
    margin-bottom: 10px;
  }

  .content p {
    margin: 0 0 15px 0;
    font-size: 13px;
    margin: 0;
  }

  .recognizee {
    padding-top: 15px;
    float: left;
    font-size: 13px;
    padding-left: 15px;
  }

  img {
    width: 15vw;
    padding-top: 10px;
    padding-left: 20px;
  }

  .content-state {
    width: 45%;
    display: inline-block;
  }

  .content-text {
    margin-left: 10px;
    border-radius: 8px;
    display: block;
    width: 80%;
    margin-top: 10px;
    height: 60px;
    padding: 5px;
  }

  .value-input {
    border: unset;
    border-bottom: #666666 dashed 1px;
    text-rendering: unset;
    width: 3rem;
    text-align: center;
    outline: none;
    font-size: 14px;
    cursor: pointer;
  }

  .checkIcon {
    width: 24px;
  }
</style>
