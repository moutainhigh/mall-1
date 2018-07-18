<template>
  <div>
    <div style="padding: 10px;background-color: #ffffff;">
      <button-tab>
        <button-tab-item :selected="tab == 'order'" @on-item-click="tab='order'">基本资料</button-tab-item>
        <button-tab-item :selected="tab == 'matter'" @on-item-click="tab='matter'">告知事项</button-tab-item>
        <button-tab-item :selected="tab == 'place'" @on-item-click="tab='place'">异地投保</button-tab-item>
      </button-tab>
    </div>
    <div v-if="tab == 'order'">
      <div class="title">
        投保资料
      </div>
      <div class="d-card" v-if="order">
        <div class="d-cell">
          <div class="d-cell-item">保单编号</div>
          <div class="d-cell-val">
            {{order.orderCode}}
          </div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">合同编号</div>
          <div class="d-cell-val">{{order.contractNo}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">保险期间</div>
          <div class="d-cell-val">{{order.insuranceProduct.insurePeriod}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">缴费期限</div>
          <div class="d-cell-val">{{order.insuranceProduct.protectionYear}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">基本保额</div>
          <div class="d-cell-val">{{order.insuranceProductPrice.price}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">保单状态</div>
          <div class="d-cell-val">{{orderState(order.orderState)}}</div>
        </div>
      </div>
      <div class="title">
        被保人信息
      </div>
      <div class="d-card" v-if="insured">
        <div class="d-cell">
          <div class="d-cell-item">姓名</div>
          <div class="d-cell-val">{{insured.insuredName}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">性别</div>
          <div class="d-cell-val">{{insured.insuredGender ? '男' : '女'}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">出生日期</div>
          <div class="d-cell-val">{{insured.insuredBirthday}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">年龄</div>
          <div class="d-cell-val">{{ageYear(insured.insuredBirthday)}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件类型</div>
          <div class="d-cell-val">{{insured.insuredCardType}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件号码</div>
          <div class="d-cell-val">{{insured.insuredCardNo}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件有效期</div>
          <div class="d-cell-val">{{insured.insuredCardPeriod}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">国籍</div>
          <div class="d-cell-val">{{insured.insuredCountry}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">身高</div>
          <div class="d-cell-val">{{insured.insuredHeight}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">体重</div>
          <div class="d-cell-val">{{insured.insuredBodyWeight}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">婚姻状况</div>
          <div class="d-cell-val">{{insured.insuredMarriage}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">固定电话</div>
          <div class="d-cell-val">{{insured.insuredTel}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">移动电话</div>
          <div class="d-cell-val">{{insured.insuredMobile}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">住址</div>
          <div class="d-cell-val">{{district(insured.insuredProvince,insured.insuredCity,insured.insuredDistrict)}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">详细住址</div>
          <div class="d-cell-val">{{insured.insuredAddress}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">职业</div>
          <div class="d-cell-val">{{career(insured.insuredCareer)}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">年收入</div>
          <div class="d-cell-val">{{insured.insuredIncome}}</div>
          <div class="d-cell-end">万元</div>
        </div>
      </div>

      <div class="title">
        投保人信息
      </div>
      <div class="d-card" v-if="holder">
        <div class="d-cell">
          <div class="d-cell-item">姓名</div>
          <div class="d-cell-val">{{holder.policyholderName}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">性别</div>
          <div class="d-cell-val">{{holder.policyholderGender ? '男' : '女'}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">出生日期</div>
          <div class="d-cell-val">{{holder.policyholderBirthday}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">年龄</div>
          <div class="d-cell-val">{{ageYear(holder.policyholderBirthday)}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件类型</div>
          <div class="d-cell-val">{{holder.policyholderCardType}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件号码</div>
          <div class="d-cell-val">{{holder.policyholderCardNo}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件有效期</div>
          <div class="d-cell-val">{{holder.policyholderCardPeroid}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">国籍</div>
          <div class="d-cell-val">{{holder.policyholderCountry}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">身高</div>
          <div class="d-cell-val">{{holder.policyholderHeight}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">体重</div>
          <div class="d-cell-val">{{holder.policyholderBodyWeight}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">婚姻状况</div>
          <div class="d-cell-val">{{holder.policyholderMarriage}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">固定电话</div>
          <div class="d-cell-val">{{holder.policyholderTel}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">移动电话</div>
          <div class="d-cell-val">{{holder.policyholderMobile}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">E-mail</div>
          <div class="d-cell-val">{{holder.policyholderEmail}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">住址</div>
          <div class="d-cell-val">{{district(holder.policyholderProvince,holder.policyholderCity,holder.policyholderDistrict)}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">详细住址</div>
          <div class="d-cell-val">{{holder.policyholderAddress}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">职业</div>
          <div class="d-cell-val">{{career(holder.policyholderCareer)}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">年收入</div>
          <div class="d-cell-val">{{holder.policyholderIncome}}</div>
        </div>
      </div>

      <div class="title" v-if="beneficiaries.length == 0">
        收益人为法定收益人
      </div>

      <div class="title" v-if="beneficiaries.length != 0">
        受益人信息
      </div>
      <div class="d-card" v-for="item in beneficiaries">
        <div class="d-cell">
          <div class="d-cell-item">姓名</div>
          <div class="d-cell-val">{{item.beneficiaryName}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">性别</div>
          <div class="d-cell-val">{{item.beneficiaryGender ? '男' : '女'}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">出生日期</div>
          <div class="d-cell-val">{{item.beneficiaryBirthday}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">收益顺序</div>
          <div class="d-cell-val">{{item.beneficiaryOrder}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">收益份额</div>
          <div class="d-cell-val">{{item.beneficiaryProportion}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">是被保险人的</div>
          <div class="d-cell-val">{{item.insuredRelation}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件类型</div>
          <div class="d-cell-val">{{item.beneficiaryCardType}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件号码</div>
          <div class="d-cell-val">{{item.beneficiaryCardNo}}</div>
        </div>
        <div class="d-cell">
          <div class="d-cell-item">证件有效期</div>
          <div class="d-cell-val">{{item.beneficiaryCardPeroid}}</div>
        </div>
      </div>
    </div>

    <div v-if="tab == 'place'">
      <place insuranceOrderOffsite="" :en-show="holder.policyholderCity !== '440300'"></place>
    </div>
  </div>
</template>

<script>
  import {getOrderDetail} from "../../service/getData";
  import {ChinaAddressData} from 'vux'
  import { ButtonTab,ButtonTabItem} from 'vux'
  import {arrayContain,ageYear} from "../../config/mUtils";
  import {careerCode} from "../../admin/career";
  import Place from "./Place";

  export default {
    name: "orderDetail",
    components:{
      Place,
      ButtonTab,
      ButtonTabItem
    },
    data(){
      return {
        tab:'order',
        insured:'',
        holder:'',
        beneficiaries:[],
        order:'',
        addr:ChinaAddressData
      }
    },
    methods:{
      orderState(state) {
        switch (state) {
          case "UN_PAID":
            return "待支付";
          case "ON_PAID":
            return "已支付";
          case "BEEN_COMPLETED":
            return "已完成";
          case "UN_SURRENDER":
            return "退保审核";
          case "ON_SURRENDER":
            return "已退保";
        }
      },
      district(province,city,district){
        let result = '';
        result = result + arrayContain(province,this.addr,"value")['name'] + " ";
        result = result + arrayContain(city,this.addr,"value")['name'] + " ";
        result = result + arrayContain(district,this.addr,"value")['name'] + " ";
        return result;
      },
      career(code){
        return arrayContain(code,careerCode,"key");
      },
      ageYear(birthday){
        return ageYear(birthday);
      }
    },
    created() {
      this.$vux.loading.show({
        text: '加载中...'
      });
      let _this = this;
      let order;
      let orderCode = this.$route.query.orderCode;
      if (orderCode) {
        getOrderDetail(orderCode).then(res => {
          console.log(res);
          _this.order = res.data;
          _this.insured = res.data.insuranceOrderInsured;
          _this.holder = res.data.insuranceOrderPolicyholder;
          _this.beneficiaries = res.data.insuranceOrderBeneficiarys;
          this.$vux.loading.hide();
        })
      }
    }
  }
</script>

<style scoped>
  .title {
    font-weight: bold;
    font-size: 17px;
    margin: 10px 0 0 0;
    background-color: #ffffff;
    padding: 15px;
    color: #f5ca1d;
  }

  .d-card {
    border-top: #ececec solid 1px;
    border-bottom: #ececec solid 1px;
    background-color: #ffffff;
    padding: 0 14px;
  }

  .d-cell {
    border-bottom: #ececec 1px solid;
    padding: 10px 0;
    margin-bottom: -1px;
  }

  .d-cell .d-cell-item {
    display: inline-block;
    width: 7rem;
  }

  .d-cell .d-cell-val {
    display: inline-block;
    color: #c5c5c5;
  }

  .d-cell .d-cell-end {
    float: right;
    right: 10px;
  }

  .vux-button-group > a.vux-button-group-current {
    color: #FFF;
    background: #f5ca1d;
  }

  .vux-button-group > a.vux-button-tab-item-first:after {
    position: absolute;
    left: 0;
    top: 0;
    width: 200%;
    border: 1px solid #f5ca1d;
    color: #f5ca1d;
    height: 200%;
    -webkit-transform-origin: left top;
    transform-origin: left top;
    -webkit-transform: scale(0.5);
    transform: scale(0.5);
    border-top-left-radius: 16px;
    border-bottom-left-radius: 16px;
  }

  .vux-button-group > a.vux-button-tab-item-middle:after {
    position: absolute;
    left: 0;
    top: 0;
    width: 200%;
    border: 1px solid #f5ca1d;
    color: #f5ca1d;
    height: 200%;
    -webkit-transform-origin: left top;
    transform-origin: left top;
    -webkit-transform: scale(0.5);
    transform: scale(0.5);
  }

  .vux-button-group > a.vux-button-tab-item-last:after {
    position: absolute;
    left: 0;
    top: 0;
    width: 200%;
    border: 1px solid #f5ca1d;
    color: #f5ca1d;
    height: 200%;
    -webkit-transform-origin: left top;
    transform-origin: left top;
    -webkit-transform: scale(0.5);
    transform: scale(0.5);
    border-top-right-radius: 16px;
    border-bottom-right-radius: 16px;
  }

  .vux-button-group > a.vux-button-tab-item-last {
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
  }

  .vux-button-group > a.vux-button-tab-item-first {
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
  }

  a {
    text-decoration: unset;
  }

</style>
