<template>
  <div>
    <div class="i-card">
      <!--<div class="i-card-tip">-->
        <!--请填写被保人信息，用于保费计算-->
      <!--</div>-->
      <div class="i-input">
        <div class="i-input-item">出生日期：</div>
        <div class="i-input-select" @click="showPlugin">
          <div>请选择出生日期</div>
          <img src="../assets/img/drop-down.png"/>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">性别：</div>
        <div class="i-input-radio">
          <div class="radio-div">
            <span>男</span>
            <img src="../assets/img/case-on.png"/>
          </div>
          <div class="radio-div">
            <span>女</span>
            <img src="../assets/img/case-off.png"/>
          </div>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">被保人职业：</div>
        <div class="i-input-select">
          <div>请选择职业</div>
          <img src="../assets/img/drop-down.png"/>
        </div>
      </div>
    </div>
    <div class="i-card">
      <div class="i-card-tip">
        生命尊养无忧老年防癌疾病保险
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
        <div class="i-input-radio">
          <div class="radio-div">
            <span>2万</span>
            <img src="../assets/img/case-on.png" height="100"/>
          </div>
          <div class="radio-div">
            <span>5万</span>
            <img src="../assets/img/case-off.png" height="100"/>
          </div>
          <div class="radio-div">
            <span>10万</span>
            <img src="../assets/img/case-off.png" height="100"/>
          </div>
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

  export default {
    name: "holder-basic",
    methods: {
      showPlugin () {
        console.log('plugin confirm', "test")
        this.$vux.datetime.show({
          cancelText: '取消',
          confirmText: '确定',
          format: 'YYYY-MM-DD',
          value: new Date().getVarDate,
          minYear: '1956',
          endDate: new Date(),
          onConfirm (val) {
            console.log('plugin confirm', val)
          },
          onShow () {
            console.log('plugin show')
          },
          onHide () {
            console.log('plugin hide')
          }
        })
      },
      submit(){
        // this.$router.push('/holder-detail');
        // this.$http.get("127.0.0.1:8080/index").then(response => {
        //   console.log("test")
        // }, response => {
        //
        // });
      }
    }
  }
</script>

<style scoped>

</style>
