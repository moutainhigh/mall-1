<template>
  <div>
    <div class="title" v-if="enShow">
      异地投保说明
    </div>
    <div v-if="enShow">
      <div class="content" style="height: auto">
        <p>1、您的户籍所在地是哪里？</p>
        <div class="content-val">
          <p>{{insuranceOrderOffsite.sensue}}</p>
        </div>
        <div class="borderBottom"></div>
      </div>

      <div class="content" style="height: auto">
        <p>2、您目前工作所在城市或地区名？单位名称？工作单位所属行业？您的职务？（例：深圳；深圳市前海水晶球文化传播有限公司；商业服务/内勤人员）</p>
        <div class="content-val">
          <p>{{insuranceOrderOffsite.workplace}}</p>
        </div>
        <div class="borderBottom"></div>
      </div>

      <div class="content" style="height: auto">
        <p>3、请说明您离开投保地的原因？前往何地？出行目的？（如是工作或学习，请提供单位或学校的名称和地址，并详细告知工作内容）公司派驻前往(户籍所在地)；作为异地业务员到xx（填现在的公司）公司负责开展传播工作。</p>
        <div class="content-val">
          <p>{{insuranceOrderOffsite.leaveReason}}</p>
        </div>
        <div class="borderBottom"></div>
      </div>

      <div class="content" style="height: auto">
        <p>4、您一年中平均在投保地逗留的时间多长？每次回投保地的时间间隔多久？您往来投保地和上述异地之间经常乘坐的交通工具是什么？逗留3个月；间隔1个多月；飞机</p>
        <div class="content-val">
          <p>{{insuranceOrderOffsite.stayTime}}</p>
        </div>
        <div class="borderBottom"></div>
      </div>

      <div class="content" style="height: auto">
        <p>5、您在投保地或异地是否已落实居住住所？如已落实请简述居住地址、环境？填异地的住址</p>
        <div class="content-val">
          <p>{{insuranceOrderOffsite.offsiteAddress}}</p>
        </div>
        <div class="borderBottom"></div>
      </div>
    </div>

    <div v-if="!enShow" class="title">
      无异地投保
    </div>
  </div>
</template>

<script>
    export default {
        name: "place",
      props:["insuranceOrderOffsite","enShow"]
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

  .content {
    font-size: 16px;
    margin: 10px 0 0 0;
    background-color: #ffffff;
    padding: 15px;
  }

  .content .content-val {
    font-size: 15px;
    background-color: #ffffff;
    padding: 15px;
    color: #c5c5c5;
  }

</style>
