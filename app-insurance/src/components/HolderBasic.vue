<template>
  <div>
    <div class="i-card">
      <!--<div class="i-card-tip">-->
      <!--请填写被保人信息，用于保费计算-->
      <!--</div>-->
      <div class="i-input">
        <div class="i-input-item">出生日期：</div>
        <div class="i-input-select" @click="showPlugin">
          <div v-if="birthday === ''">请选择出生日期</div>
          <div v-if="birthday !== ''">{{birthday}}</div>
          <img src="../assets/img/drop-down.png"/>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">性别：</div>
        <div class="i-input-radio">
          <div class="radio-div" @click="changeGender(true)">
            <span>男</span>
            <img v-if="gender" src="../assets/img/case-on.png"/>
            <img v-if="!gender" src="../assets/img/case-off.png"/>
          </div>
          <div class="radio-div" @click="changeGender(false)">
            <span>女</span>
            <img v-if="!gender" src="../assets/img/case-on.png"/>
            <img v-if="gender" src="../assets/img/case-off.png"/>
          </div>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">被保人职业：</div>
        <!--<group>-->
        <!--<popup-picker title="受益顺序" placeholder="" :data="list" v-model="test"  value-text-align="left"></popup-picker>-->
        <!--</group>-->
        <div class="i-input-select" @click="showJob">
          <div>请选择职业</div>
          <img src="../assets/img/drop-down.png"/>
        </div>
      </div>
    </div>
    <div class="i-card">
      <div class="i-card-tip">
        {{title}}
        <div class="i-list-right">
          <img src="../assets/img/risk.png" height="18">
          <span>主险</span>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">保险期间：</div>
        <div class="i-input-val">保障终身</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">保险年限：</div>
        <div class="i-input-val">终身</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">基本保额：</div>
        <div class="i-input-radio" v-if="proId == 1">
          <div class="radio-div" @click="priceId = 1">
            <span>2万</span>
            <img v-if="priceId ===1" src="../assets/img/case-on.png" height="100"/>
            <img v-if="priceId !==1" src="../assets/img/case-off.png" height="100"/>
          </div>
          <div class="radio-div" @click="priceId = 2">
            <span>5万</span>
            <img v-if="priceId ===2" src="../assets/img/case-on.png" height="100"/>
            <img v-if="priceId !==2" src="../assets/img/case-off.png" height="100"/>
          </div>
          <div class="radio-div" @click="priceId = 3">
            <span>10万</span>
            <img v-if="priceId ===3" src="../assets/img/case-on.png" height="100"/>
            <img v-if="priceId !==3" src="../assets/img/case-off.png" height="100"/>
          </div>
        </div>
        <div style="display: inline-block;color: #c01212;" v-if="proId == 2">
          <span>￥20000.00元</span>
        </div>
      </div>
    </div>
    <div class="i-card">
      <div class="i-card-tip">
        ※ 投保须知
      </div>
      <div class="i-message">
        <div>1.就来得及发非常急撒就分手了放假撒覅是你小妹。</div>
        <div>2.就来得及发非常急撒就分手了放假撒覅是你小妹。</div>
        <div>3.就来得及发非常急撒就分手了放假撒覅是你小妹。</div>
        <div>4.就来得及发非常急撒就分手了放假撒覅是你小妹。</div>
        <div>5.就来得及发非常急撒就分手了放假撒覅是你小妹。</div>
      </div>
    </div>
    <div style="height: 48px;">
      <button class="i-footer" @click="submit">
        <div>完善投保信息</div>
      </button>
    </div>
  </div>
</template>

<script>
  import storage from "../store/storage"
  import {PopupPicker} from 'vux'

  export default {
    name: "holder-basic",
    components: {
      PopupPicker
    },
    data() {
      return {
        birthday: '',
        gender: true,
        priceId: 1,
        profession: [],
        list: [['居民身份证', '驾驶证', '护照']],
        title: '',
        proId: ''
      }
    },
    methods: {
      showPlugin() {
        let _this = this;
        this.$vux.datetime.show({
          cancelText: '取消',
          confirmText: '确定',
          format: 'YYYY-MM-DD',
          value: new Date().getVarDate,
          minYear: '1956',
          endDate: new Date(),
          onConfirm(val) {
            _this.birthday = val;
          },
          onShow() {
            console.log('plugin show')
          },
          onHide() {
            console.log('plugin hide')
          }
        })
      },
      changeGender:function(val){
        console.log("test");
        this.gender = val;
        let insured = storage.fetch('insured');
        insured.insuredGender = val;
        storage.save('insured', insured);
      },
      submit() {
        if (storage.fetch('insured').length === 0) {
          storage.save('insured', this.Admin.holder);
        }
        this.$router.push('/holder-detail');
      },
      showJob() {
      }
    },
    watch: {
      birthday: {
        handler(newVal, oldVal) {
          let insured = storage.fetch('insured');
          console.log(newVal);
          if (newVal.length !== 0) {
            insured.insuredBirthday = newVal;
          }
          storage.save('insured', insured);
        },
        immediate: true,
        deep: true
      },
      priceId: function (newVal, oldVal) {
        let order = storage.fetch('order');
        order.insuranceProductPrice.priceId = newVal;
        storage.save('order', order);
      }
    },
    created: function () {
      let query = this.$route.query;
      this.title = query.title;
      this.proId = query.id;

      let order = this.Admin.order;
      order.insuranceProduct.prodId = this.proId;
      if (this.proId === 1) {
        order.insuranceProductPrice.priceId = 1;
      }
      if (this.proId === 2) {
        order.insuranceProductPrice.priceId = 4;
      }
      storage.save('order', order);

      if (storage.fetch('insured').length === 0) {
        storage.save('insured', this.Admin.insured);
      }else {
        let insured = storage.fetch("insured");
        this.birthday = insured.insuredBirthday;
        this.gender = insured.insuredGender;
      }
    }
  }
</script>

<style scoped>

</style>
