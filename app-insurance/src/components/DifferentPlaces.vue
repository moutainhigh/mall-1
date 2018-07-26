<template>
  <div style="height: 100%">
    <div class="title" style="text-align: left;margin-top: 0;">
      异地投保说明
    </div>
    <div class="content" style="height: auto">
      <p>1、您的户籍所在地是哪里？</p>
      <textarea class="content-text" v-model="insuranceOrderOffsite.sensue" maxlength="60"></textarea>
      <div class="borderBottom"></div>
    </div>

    <!--<div class="content" style="height: auto">-->
    <!--<p>2、您目前工作所在城市或地区名？单位名称？工作单位所属行业？您的职务？（例：深圳；深圳市前海水晶球文化传播有限公司；商业服务/内勤人员）</p>-->
    <!--<textarea class="content-text" v-model="insuranceOrderOffsite.workplace" maxlength="510"></textarea>-->
    <!--<div class="borderBottom"></div>-->
    <!--</div>-->

    <div class="content" style="height: auto">
      <p>2、请说明您离开投保地的原因？前往何地？出行目的？（如是工作或学习，请提供单位或学校的名称和地址，并详细告知工作内容）公司派驻前往(户籍所在地)；作为异地业务员到xx（填现在的公司）公司负责开展传播工作。</p>
      <textarea class="content-text" v-model="insuranceOrderOffsite.leaveReason" maxlength="100"></textarea>
      <div class="borderBottom"></div>
    </div>

    <div class="content" style="height: auto">
      <p>3、您一年中平均在投保地逗留的时间多长？每次回投保地的时间间隔多久？您往来投保地和上述异地之间经常乘坐的交通工具是什么？逗留3个月；间隔1个多月；飞机</p>
      <textarea class="content-text" v-model="insuranceOrderOffsite.stayTime" maxlength="100"></textarea>
      <div class="borderBottom"></div>
    </div>

    <div class="content" style="height: auto">
      <p>4、您在投保地或异地是否已落实居住住所？如已落实请简述居住地址、环境？填异地的住址</p>
      <textarea class="content-text" v-model="insuranceOrderOffsite.offsiteAddress" maxlength="100"></textarea>
      <div class="borderBottom"></div>
    </div>

    <!--<div class="content" style="height: auto;">-->
    <!--<p style="text-align: left">6、是否有其他需要说明事项：-->
    <!--<span style="margin-left: 5vw">是</span>-->
    <!--<img style="height: 3vh; margin-bottom: -5px;" v-if="isOther" src="../assets/img/selected.png"-->
    <!--@click="clickIsOther">-->
    <!--<img style="height: 3vh; margin-bottom: -5px;" v-if="!isOther" src="../assets/img/unselect.png"-->
    <!--@click="clickIsOther">-->
    <!--<span style="margin-left: 5vw">否</span>-->
    <!--<img style="height: 3vh; margin-bottom: -5px;" v-if="isOther" src="../assets/img/unselect.png"-->
    <!--@click="clickIsOther">-->
    <!--<img style="height: 3vh; margin-bottom: -5px;" v-if="!isOther" src="../assets/img/selected.png"-->
    <!--@click="clickIsOther">-->
    <!--</p>-->
    <!--<textarea class="content-text" v-if="isOther" v-model="insuranceOrderOffsite.otherMatter" maxlength="510"></textarea>-->
    <!--<div class="borderBottom" v-if="isOther"></div>-->
    <!--</div>-->
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
    name: "differentPlaces",
    data() {
      return {
        insuranceOrderOffsite: storage.fetch("order").insuranceOrderOffsite,
        // isOther: false,
        showPositionValue: false,
        toastText: '',
      }
    },
    methods: {
      // clickIsOther() {
      //   this.isOther = !this.isOther;
      // },
      next() {
        //判断表情输入
        let textatea = document.getElementsByTagName("textarea");
        for (let i = 0; i < textatea.length; i++) {
          if (emoji.test(textatea[i].value)) {
            this.showPositionValue = true;
            this.toastText = "输入信息不得带表情";
            return false;
          }
        }

        if (this.insuranceOrderOffsite.sensue === '' || this.insuranceOrderOffsite.leaveReason === '' || this.insuranceOrderOffsite.stayTime === '' || this.insuranceOrderOffsite.offsiteAddress === '') {
          this.showPositionValue = true;
          this.toastText = "请完善异地投保信息";
          return false;
        }
        let order = storage.fetch("order");
        order.insuranceOrderOffsite = this.insuranceOrderOffsite;
        storage.save('order', order);
        // if (this.isOther) {
        //   if (this.insuranceOrderOffsite.otherMatter === '') {
        //     this.showPositionValue = true;
        //     this.toastText = "请完善异地投保信息";
        //     return false;
        //   }
        // } else {
        //   this.insuranceOrderOffsite.otherMatter = '';
        //   let order = storage.fetch("order");
        //   order.insuranceOrderOffsite.otherMatter = this.insuranceOrderOffsite.otherMatter;
        //   storage.save('order', order);
        // }
        this.$router.push("payment");
      }
    },
    created: function () {
      if (!storage.fetch("order").insuranceOrderOffsite || storage.fetch("order").insuranceOrderOffsite.length === 0) {
        this.insuranceOrderOffsite = {
          sensue: '',
          workplace: '',
          leaveReason: '',
          stayTime: '',
          offsiteAddress: '',
          otherMatter: null
        };
      }
    },
    watch: {
      insuranceOrderOffsite: {
        handler(newVal, oldVal) {
        },
        immediate: true,
        deep: true
      },
    },
  }
</script>

<style scoped>
  .title {
    margin-top: 10px;
    background-color: #ffffff;
    padding: 15px;
    font-size: 16px;
    font-weight: bold;
    color: #f5ca1d;
    text-align: center;
    border-bottom: #D9D9D9 solid 1px;
  }

  .content {
    background: #ffffff;
    padding: 15px;
  }

  .content p {
    margin: 0 0 15px 0;
    font-size: 13px;
  }

  .content-text {
    border-radius: 8px;
    display: block;
    width: 96%;
    margin-top: 10px;
    height: 60px;
    padding: 5px;
    outline: unset;
    border: 0;
  }

  .borderBottom {
    width: 100%;
    border-bottom: 1px solid #f3f3f3;
    margin-top: 10px
  }
</style>
