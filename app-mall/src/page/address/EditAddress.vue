<template>
  <div>
    <div class="headTitle">
      <span class="cancel" @click="cancelEdit">取消</span>
      编辑收货地址
      <span class="complete" @click="save">完成</span>
    </div>

    <group style="margin-top: 3.625rem; background: #ffffff">
      <x-input title="联系人" placeholder="名字" v-model="addressVo.consigneeName"
               v-bind:class="{'errorInput': $v.addressVo.consigneeName.$error}"
               @input="$v.addressVo.consigneeName.$touch()"></x-input>
      <div class="error" v-if="!$v.addressVo.consigneeName.required && $v.addressVo.consigneeName.$dirty">联系人不能为空</div>
      <div class="error" v-if="!$v.addressVo.consigneeName.minLength">联系人最少为
        {{$v.addressVo.consigneeName.$params.minLength.min}}
        个字符
      </div>
      <div class="error" v-if="!$v.addressVo.consigneeName.maxLength">联系人最多为
        {{$v.addressVo.consigneeName.$params.maxLength.max}}
        个字符
      </div>

      <x-input title="手机号码" placeholder="11位手机码" v-model="addressVo.consigneeMobile"
               v-bind:class="{'errorInput': $v.addressVo.consigneeMobile.$error}"
               @input="$v.addressVo.consigneeMobile.$touch()"></x-input>
      <div class="error" v-if="!$v.addressVo.consigneeMobile.required && $v.addressVo.consigneeMobile.$dirty">手机号码不能为空
      </div>
      <div class="error" v-if="!$v.addressVo.consigneeMobile.mobile">请输入正确的手机号码</div>

      <x-address title="选择地区" placeholder="地区信息" :list="addressData" value-text-align="left" v-model="pcd"
                 v-bind:class="{'errorInput': $v.pcd.$error}"></x-address>
      <div class="error" v-if="!$v.pcd.required && $v.pcd.$dirty">地区不能为空</div>

      <x-textarea title="详细地址" placeholder="街道门牌信息" v-model="addressVo.consigneeAddress"
                  v-bind:class="{'errorInput': $v.addressVo.consigneeAddress.$error}"
                  @input="$v.addressVo.consigneeAddress.$touch()"></x-textarea>
      <div class="error"
           v-if="!$v.addressVo.consigneeAddress.required && $v.addressVo.consigneeAddress.$dirty">
        请输入详细地址
      </div>
      <div class="error" v-if="!$v.addressVo.consigneeAddress.maxLength">详细地址最多不超过255位数</div>

      <x-input title="邮政编码" placeholder="邮政编码" v-model="addressVo.postCode"
               v-bind:class="{'errorInput': $v.addressVo.postCode.$error}"
               @input="$v.addressVo.postCode.$touch()"></x-input>
      <div class="error" v-if="!$v.addressVo.postCode.required && $v.addressVo.postCode.$dirty">邮政编码不能为空</div>
      <div class="error" v-if="!$v.addressVo.postCode.minLength">邮政编码最少为 {{$v.addressVo.postCode.$params.minLength.min}}
        个字符
      </div>
      <div class="error" v-if="!$v.addressVo.postCode.maxLength">邮政编码最多为 {{$v.addressVo.postCode.$params.maxLength.max}}
        个字符
      </div>
      <div class="error" v-if="!$v.addressVo.postCode.numeric && $v.addressVo.postCode.$dirty">请输入数字</div>

    </group>
    <p style="line-height: 2.7; background: #ffffff; padding-left: 1rem">
      设置成默认收货地址
      <img v-if="!addressVo.defaultAddress" class="isDefault" src="../../assets/img/common/Checkmark_nor.png"
           @click="addressVo.defaultAddress = !addressVo.defaultAddress">
      <img v-if="addressVo.defaultAddress" class="isDefault" src="../../assets/img/common/Checkmark_sele.png"
           @click="addressVo.defaultAddress = !addressVo.defaultAddress">
    </p>
  </div>
</template>

<script>
  import {ChinaAddressData, Group, XAddress, XInput, XTextarea, Toast} from 'vux'
  import {getDeliveryAddressByAdderssId, updateDeliveryAddress} from "../../service/getData";
  import {required, minLength, maxLength, numeric} from 'vuelidate/lib/validators'
  import {mobile, emoji, space} from '../../assets/js/validate'

  export default {
    name: "EditAddress",
    components: {
      Group,
      XInput,
      XAddress,
      XTextarea,
      Toast
    },
    data() {
      return {
        headTitle: '编辑收货地址',
        addressData: ChinaAddressData,
        isDefault: false,
        addressVo: '',
        pcd: []
      }
    },
    validations: {
      addressVo: {
        consigneeName: {required, minLength: minLength(2), maxLength: maxLength(30)},
        consigneeMobile: {required, mobile},
        consigneeAddress: {required, maxLength: maxLength(255)},
        postCode: {required, minLength: minLength(6), maxLength: maxLength(6), numeric},
      },
      pcd: {required}
    },
    methods: {
      cancelEdit() {
        this.$router.go(-1);
      },
      save() {
        //判断表情输入
        let input = document.getElementsByTagName("input");
        let textarea = document.getElementsByTagName("textarea");
        let isPass = true;
        for (let i = 0; i < input.length; i++) {
          if (emoji.test(input[i].value)) {
            isPass = false;
          }
          if (space.test(input[i].value)) {
            this.$vux.toast.text("请不要输入空格", 'middle');
            return false;
          }
        }
        for (let i = 0; i < textarea.length; i++) {
          if (emoji.test(textarea[i].value)) {
            isPass = false;
          }
          if (space.test(textarea[i].value)) {
            this.$vux.toast.text("请不要输入空格", 'middle');
            return false;
          }
        }
        if (!isPass) {
          this.$vux.toast.text("输入信息不得带表情", 'middle');
          return;
        }

        this.$v.$touch();
        if (this.$v.$invalid) {
          return false;
        }
        updateDeliveryAddress(this.addressVo).then(res => {
          if (res.result == 'SUCCESS') {
            this.$router.go(-1);
          } else {
            alert(res.message);
          }
        })
      }
    },
    created() {
      this.addressVo = {
        consigneeName: '',
        consigneeMobile: '',
        province: '',
        city: '',
        district: '',
        consigneeAddress: '',
        postCode: '',
        defaultAddress: false,
        customerId: 1 // 调试用，之后需去掉
      }
      getDeliveryAddressByAdderssId(this.$route.query.addressId).then(res => {
        if (res.result == 'SUCCESS') {
          this.addressVo = res.data;
          this.pcd.push(this.addressVo.province);
          this.pcd.push(this.addressVo.city);
          this.pcd.push(this.addressVo.district);
        }
      })
    },
    watch: {
      pcd: {
        handler(newVal, oldVal) {
          if (newVal && newVal.length === 3) {
            this.addressVo.province = newVal[0];
            this.addressVo.city = newVal[1];
            this.addressVo.district = newVal[2];
          }
        },
        immediate: true,
        deep: true
      }
    }
  }
</script>

<style scoped>
  .headTitle {
    position: fixed;
    top: 0;
    background: #ffffff;
    line-height: 3rem;
    width: 100vw;
    text-align: center;
    font-size: 1.2rem
  }

  .cancel {
    float: left;
    font-size: 1rem;
    margin-left: 1rem
  }

  .complete {
    float: right;
    font-size: 1rem;
    margin-right: 1rem
  }

  .isDefault {
    width: 1.12rem;
    float: right;
    margin: 0.812rem
  }

  .error {
    color: #f79483;
    font-size: 12px;
    text-align: right;
    margin-right: 10px;
  }

  .errorInput {
    animation-name: shakeError;
    animation-duration: .6s;
    animation-timing-function: ease-in-out;
    border: 1px solid #f79483;
    border-radius: 5px;
  }

  @keyframes shakeError {
    0% {
      transform: translateX(0);
    }
    15% {
      transform: translateX(0.375rem);
    }
    30% {
      transform: translateX(-0.375rem);
    }
    45% {
      transform: translateX(0.375rem);
    }
    60% {
      transform: translateX(-0.375rem);
    }
    75% {
      transform: translateX(0.375rem);
    }
    90% {
      transform: translateX(-0.375rem);
    }
    100% {
      transform: translateX(0);
    }
  }
</style>
