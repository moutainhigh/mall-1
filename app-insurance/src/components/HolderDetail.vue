<template>
  <div>
    <group label-width="6em" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="form.name"></x-input>
      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="证件号码" placeholder="请输入证件号" v-model="form.documentNum"></x-input>
      <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="form.documentNum"></x-input>
      <popup-picker title="国籍" placeholder="请选择国籍" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="身高(cm)" placeholder="请输入身高"  v-model="form.documentNum"></x-input>
      <x-input title="体重(kg)" placeholder="请输入体重"  v-model="form.documentNum"></x-input>
      <x-input title="年收入(万元)" placeholder="请输入年收入"  v-model="form.documentNum"></x-input>
      <popup-picker title="婚姻状况" placeholder="请选择婚姻状况" :data="maritalStatus" v-model="form.marital" value-text-align="left"></popup-picker>
      <div>
        <div style="    border-top: 1px solid #D9D9D9;margin: 15px 0 0 15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" v-model="form.documentNum"></x-input>
      <x-input title="移动电话" v-model="form.documentNum"></x-input>
      <x-input title="email" v-model="form.documentNum"></x-input>
    </group>

    <div style="height: 48px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;">
        <div>上一步</div>
      </button>
      <button class="i-footer" style="width: 50%;right: 0">
        <div>下一步</div>
      </button>
    </div>
  </div>

</template>

<script>
  import { GroupTitle, Group, Cell, XInput, Selector, PopupPicker, Datetime, XNumber, ChinaAddressData, XAddress, XTextarea, XSwitch } from 'vux'

  export default {
    components: {
      Group,
      GroupTitle,
      Cell,
      XInput,
      Selector,
      PopupPicker,
      XAddress,
      Datetime,
      XNumber,
      XTextarea,
      XSwitch
    },
    name: "holder-detail",
    data() {
      return {
        form: {
          name: '',
          documentType: ['身份证'],
          documentNum:'',
          marital:['未婚']

        },
        list: [['身份证', '驾驶证', '护照']],
        maritalStatus:[['未婚','已婚']]
      }
    }
  }
</script>

<style scoped>

</style>
