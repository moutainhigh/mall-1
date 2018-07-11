<template>
  <div>
    <div class="title">告知事项<span @click="changeFalse">全选否</span></div>
    <div class="content" style="height: auto">
      <p>1.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
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
      <textarea class="content-text" v-if="matters[0].insuredResult" v-model="matters[0].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[0].policyholderResult" v-model="matters[0].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p>2. 您是否曾被保险公司加费、拒保、延期、以附加的条件承保？</p>
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
      <textarea class="content-text" v-if="matters[1].insuredResult" v-model="matters[1].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[1].policyholderResult" v-model="matters[1].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p>3. 您是否有飞行、潜水、跳伞、赛车、滑雪等高风险运动嗜好？</p>
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
      <textarea class="content-text" v-if="matters[2].insuredResult" v-model="matters[2].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[2].policyholderResult" v-model="matters[2].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p>4.您的吸烟量是否超过20支/天？您的饮酒量是否超过5个酒精单位/天(相当于啤酒1250ml或葡萄酒500ml或白酒150ml/天)？您是否曾经或正在使用麻醉剂、可成瘾药物、毒品或接受戒毒、戒酒治疗？</p>
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
      <textarea class="content-text" v-if="matters[3].insuredResult" v-model="matters[3].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[3].policyholderResult" v-model="matters[3].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p>5.最近两年内是否有医生建议您服药、住院、手术或其他医疗方案？</p>
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
      <textarea class="content-text" v-if="matters[4].insuredResult" v-model="matters[4].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[4].policyholderResult" v-model="matters[4].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p>6.最近五年内，您是否曾经作下列之一的检查且结果异常：核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查？</p>
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
      <textarea class="content-text" v-if="matters[5].insuredResult" v-model="matters[5].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[5].policyholderResult" v-model="matters[5].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">7.您是否有下列身体残障状况：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0; margin-bottom: 0">a.脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？</p>
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
        <textarea class="content-text" v-if="matters[6].insuredResult" v-model="matters[6].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[6].policyholderResult" v-model="matters[6].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？</p>
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
        <textarea class="content-text" v-if="matters[7].insuredResult" v-model="matters[7].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[7].policyholderResult" v-model="matters[7].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）</p>
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
        <textarea class="content-text" v-if="matters[8].insuredResult" v-model="matters[8].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[8].policyholderResult" v-model="matters[8].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.子宫切除？阴道闭锁？乳房切除？（女性）</p>
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
        <textarea class="content-text" v-if="matters[9].insuredResult" v-model="matters[9].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[9].policyholderResult" v-model="matters[9].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e.视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？</p>
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
        <textarea class="content-text" v-if="matters[10].insuredResult" v-model="matters[10].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[10].policyholderResult" v-model="matters[10].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">f.精神、智能障碍或性格行为异常？</p>
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
        <textarea class="content-text" v-if="matters[11].insuredResult" v-model="matters[11].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[11].policyholderResult" v-model="matters[11].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">g.脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？</p>
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
        <textarea class="content-text" v-if="matters[12].insuredResult" v-model="matters[12].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[12].policyholderResult" v-model="matters[12].policyholderRemark"
                  placeholder="投保人"/>
      </div>
    </div>

    <div class="content">
      <p>8.a 您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？</p>
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
      <textarea class="content-text" v-if="matters[13].insuredResult" v-model="matters[13].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[13].policyholderResult" v-model="matters[13].policyholderRemark"
                placeholder="投保人"/>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？</p>
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
        <textarea class="content-text" v-if="matters[14].insuredResult" v-model="matters[14].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[14].policyholderResult" v-model="matters[14].policyholderRemark"
                  placeholder="投保人"/>
      </div>
    </div>

    <div class="content">
      <p>
        9.最近六个月内，您是否有下列疾患或自觉症状？包块或肿物、反复头晕、头痛、晕厥、抽搐、乏力、反复咳嗽、咯血、胸闷、气短、反复腹痛、黄疸、浮肿、听力下降、耳鸣、视力下降、血尿、关节肿痛、肌肉萎缩、反复发热、明显体重下降(体重在3个月内下降超过5公斤)、原因不明的皮肤或鼻出血、淋巴结持续肿大？</p>
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
      <textarea class="content-text" v-if="matters[15].insuredResult" v-model="matters[15].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[15].policyholderResult" v-model="matters[15].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">10.您是否患有或治疗过以下疾病：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0">a.视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上、听力下降、任何耳鼻喉疾患？</p>
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
        <textarea class="content-text" v-if="matters[16].insuredResult" v-model="matters[16].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[16].policyholderResult" v-model="matters[16].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.脑脊液鼻漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病？</p>
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
        <textarea class="content-text" v-if="matters[17].insuredResult" v-model="matters[17].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[17].policyholderResult" v-model="matters[17].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.脑膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺？</p>
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
        <textarea class="content-text" v-if="matters[18].insuredResult" v-model="matters[18].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[18].policyholderResult" v-model="matters[18].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病？</p>
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
        <textarea class="content-text" v-if="matters[19].insuredResult" v-model="matters[19].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[19].policyholderResult" v-model="matters[19].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e.肾炎、肾病综合症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、前列腺疾病、性病？</p>
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
        <textarea class="content-text" v-if="matters[20].insuredResult" v-model="matters[20].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[20].policyholderResult" v-model="matters[20].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">f 糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病？</p>
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
        <textarea class="content-text" v-if="matters[21].insuredResult" v-model="matters[21].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[21].policyholderResult" v-model="matters[21].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">g 贫血、再生障碍性贫血、白血病、紫癜病、血友病？</p>
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
        <textarea class="content-text" v-if="matters[22].insuredResult" v-model="matters[22].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[22].policyholderResult" v-model="matters[22].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">h 风湿热、关节炎、类风湿性关节炎、痛风、颈椎病、椎间盘突出症、红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、其他结缔组织疾病？</p>
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
        <textarea class="content-text" v-if="matters[23].insuredResult" v-model="matters[23].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[23].policyholderResult" v-model="matters[23].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">i 肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿、结节或增生物？</p>
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
        <textarea class="content-text" v-if="matters[24].insuredResult" v-model="matters[24].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[24].policyholderResult" v-model="matters[24].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">j 先天性疾病、遗传性疾病？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(25)">
            <img v-if="!matters[25].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[25].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(25)">
            <img v-if="!matters[25].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[25].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" v-if="matters[25].insuredResult" v-model="matters[25].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[25].policyholderResult" v-model="matters[25].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">k 身体是否瘢痕？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(26)">
            <img v-if="!matters[26].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[26].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <div class="content-state">
          <p class="recognizee">投保人</p>
          <div style="display: inline" @click="changeHolderState(26)">
            <img v-if="!matters[26].policyholderResult" src="../assets/img/switch-off.png">
            <img v-if="matters[26].policyholderResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" v-if="matters[26].insuredResult" v-model="matters[26].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[26].policyholderResult" v-model="matters[26].policyholderRemark"
                  placeholder="投保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">l 除上述以外的其它疾病、症状或意外受伤史？</p>
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
        <textarea class="content-text" v-if="matters[27].insuredResult" v-model="matters[27].insuredRemark"
                  placeholder="被保人"/>
        <textarea class="content-text" v-if="matters[27].policyholderResult" v-model="matters[27].policyholderRemark"
                  placeholder="投保人"/>
      </div>
    </div>
    <div class="content">
      <p>11.您若为16周岁(含)以上女性，请告知：目前是否怀孕？怀孕<input class="value-input" type="number" v-model="values11"/>周数？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(28)">
          <img v-if="!matters[28].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[28].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div class="content-state">
        <p class="recognizee">投保人</p>
        <div style="display: inline" @click="changeHolderState(28)">
          <img v-if="!matters[28].policyholderResult" src="../assets/img/switch-off.png">
          <img v-if="matters[28].policyholderResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" v-if="matters[28].insuredResult" v-model="matters[28].insuredRemark"
                placeholder="被保人"/>
      <textarea class="content-text" v-if="matters[28].policyholderResult" v-model="matters[28].policyholderRemark"
                placeholder="投保人"/>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">12.您若为2周岁(不含)以下婴儿，请额外告知：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0; margin-bottom: 0">a.出生时身长<input type="number" class="value-input"
                                                                   v-model="values12[0]"/>厘米(cm)，体重<input
          class="value-input" type="number" v-model="values12[1]"/>公斤(kg)，出生时留院天数<input class="value-input"
                                                                                        type="number"
                                                                                        v-model="values12[2]"/>天，如超过7天，请详细说明
        </p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(29)">
            <img v-if="!matters[29].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[29].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" v-if="matters[29].insuredResult" v-model="matters[29].insuredRemark"
                  placeholder="被保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(30)">
            <img v-if="!matters[30].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[30].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" v-if="matters[30].insuredResult" v-model="matters[30].insuredRemark"
                  placeholder="被保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.出生时是否有抢救史？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(31)">
            <img v-if="!matters[31].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[31].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" v-if="matters[31].insuredResult" v-model="matters[31].insuredRemark"
                  placeholder="被保人"/>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.是否曾进行婴幼儿体检且结果异常？是否有发育迟缓、惊厥、抽搐、脑瘫、先天性疾病、遗传性疾病？</p>
        <div class="content-state">
          <p class="recognizee">被保人</p>
          <div style="display: inline" @click="changeInsuredState(32)">
            <img v-if="!matters[32].insuredResult" src="../assets/img/switch-off.png">
            <img v-if="matters[32].insuredResult" src="../assets/img/switch-on.png">
          </div>
        </div>
        <textarea class="content-text" v-if="matters[32].insuredResult" v-model="matters[32].insuredRemark"
                  placeholder="被保人"/>
      </div>
    </div>

    <div class="content">
      <p>13.您是否已参加公费医疗或者社会医疗保险？</p>
      <div class="content-state">
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeInsuredState(33)">
          <img v-if="!matters[33].insuredResult" src="../assets/img/switch-off.png">
          <img v-if="matters[33].insuredResult" src="../assets/img/switch-on.png">
        </div>
      </div>
      <textarea class="content-text" v-if="matters[33].insuredResult" v-model="matters[33].insuredRemark"
                placeholder="被保人"/>
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

  export default {
    name: "infoMatters",
    data() {
      return {
        state: false,
        matters: [],
        values11: '',
        values12: ['', '', ''],
        twoYear: true
      }
    },
    methods: {
      changeInsuredState(index) {
        this.matters[index].insuredResult = !this.matters[index].insuredResult;
        console.log(this.matters[index].insuredResult);
      },
      changeHolderState(index) {
        this.matters[index].policyholderResult = !this.matters[index].policyholderResult;
        console.log(this.matters[index].policyholderResult);
      },
      changeFalse() {
        this.matters.forEach(function (matter) {
          matter.insuredResult = false;
          matter.policyholderResult = false;
        });
      },
      next() {
        if (this.twoYear === false) {
          if (this.values12[0] === '' || this.values12[1] === '' || this.values12[2] === '') {
            alert("被保人小于2周岁，请填写第12项额外信息");
            return false;
          }
        }
        if (this.state) {
          this.$router.push('autograph');
        } else {
          alert("请勾选确认")
        }
      }
    },
    watch: {
      values12: {
        handler(newVal, oldVal) {
          if (this.matters.length != 0) {
            this.matters[30].collectValues = JSON.stringify(newVal);
          }
        },
        immediate: true,
        deep: true
      },
      values11: function (newVal, oldVal) {
        if (this.matters.length != 0) {
          this.matters[29].collectValues = newVal;
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
      for (let i = 1; i <= 34; i++) {
        this.matters.push(
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
      //判断投保人周岁是否大于2周岁
      let insured = storage.fetch("insured");
      let newDate = new Date();
      let birthday = new Date(insured.insuredBirthday.replace(/-/, "/"));
      let time = (newDate.valueOf() - birthday.valueOf());
      if (time < 24 * 60 * 60 * 1000 * 365 * 2) {
        this.twoYear = false;
      }
      console.log(this.twoYear);
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
