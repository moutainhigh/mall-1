<template>
  <div>
    <div class="title">告知事项<span>全选否</span></div>
    <div class="content" style="height: auto">
      <p>1.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>2. 您是否曾被保险公司加费、拒保、延期、以附加的条件承保？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>3. 您是否有飞行、潜水、跳伞、赛车、滑雪等高风险运动嗜好？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>4.您的吸烟量是否超过20支/天？您的饮酒量是否超过5个酒精单位/天(相当于啤酒1250ml或葡萄酒500ml或白酒150ml/天)？您是否曾经或正在使用麻醉剂、可成瘾药物、毒品或接受戒毒、戒酒治疗？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>5.最近两年内是否有医生建议您服药、住院、手术或其他医疗方案？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>6.最近五年内，您是否曾经作下列之一的检查且结果异常：核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">7.您是否有下列身体残障状况：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0; margin-bottom: 0">a.脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.子宫切除？阴道闭锁？乳房切除？（女性）</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e.视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">f.精神、智能障碍或性格行为异常？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">g.脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
    </div>

    <div class="content">
      <p>8.a 您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
    </div>

    <div class="content">
      <p>9.最近六个月内，您是否有下列疾患或自觉症状？包块或肿物、反复头晕、头痛、晕厥、抽搐、乏力、反复咳嗽、咯血、胸闷、气短、反复腹痛、黄疸、浮肿、听力下降、耳鸣、视力下降、血尿、关节肿痛、肌肉萎缩、反复发热、明显体重下降(体重在3个月内下降超过5公斤)、原因不明的皮肤或鼻出血、淋巴结持续肿大？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">10.您是否患有或治疗过以下疾病：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0">a.视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上、听力下降、任何耳鼻喉疾患？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.脑脊液鼻漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.脑膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e.肾炎、肾病综合症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、前列腺疾病、性病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">f 糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">g 贫血、再生障碍性贫血、白血病、紫癜病、血友病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">h 风湿热、关节炎、类风湿性关节炎、痛风、颈椎病、椎间盘突出症、红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、其他结缔组织疾病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">i 肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿、结节或增生物？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">j 先天性疾病、遗传性疾病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">k 身体是否瘢痕？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">l 除上述以外的其它疾病、症状或意外受伤史？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
    </div>
    <div class="content">
      <p>11.您若为16周岁(含)以上女性，请告知：目前是否怀孕？怀孕___ 周数？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">12.您若为2周岁(不含)以下婴儿，请额外告知：</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0; margin-bottom: 0">a.出生时身长__厘米(cm)，体重__公斤(kg)，出生时留院天数__天，如超过7天，请详细说明</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.出生时是否有抢救史？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.是否曾进行婴幼儿体检且结果异常？是否有发育迟缓、惊厥、抽搐、脑瘫、先天性疾病、遗传性疾病？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
    </div>

    <div class="content">
      <p>13.您是否已参加公费医疗或者社会医疗保险？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div style="height: 50px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;color: #e1bb3a" @click="comeBack">
        <div>上一步</div>
      </button>
      <button class="i-footer" style="width: 50%;right: 0">
        <router-link to="autograph">
          <div>下一步</div>
        </router-link>
      </button>
    </div>

  </div>
</template>

<script>
  export default {
    name: "infoMatters",
    data() {
      return {
        state: false,
        matters:[]
      }
    },
    methods: {
      comeBack() {
        this.$router.back();
      },
      changeState() {
        this.state = !this.state;
      }
    },
    created:function () {
      for (var i = 0 ; i <= 34 ; i ++){
        this.matters.push(

        )
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
</style>
