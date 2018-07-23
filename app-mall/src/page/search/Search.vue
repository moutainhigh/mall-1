<template>
  <div>
    <div class="search">
      <button class="search-magnifier">
        <img src="../../assets/img/common/ic_search.png">
      </button>
      <input type="text" style="padding-left: 40px" v-model="searchContent" placeholder="输入搜索内容">
      <img style="position: absolute; right: 70px; top: 18px; width: 16px" v-if="searchContent != ''"
           src="../../assets/img/common/search_ic_eliminate.png" @click="clearInput">
      <button class="cancel">取消</button>
    </div>

    <div class="hotSearch" v-if="searchContent == ''">
      <p>热门搜索</p>
      <div style="padding: 0 9px">
        <button class="hotSearch-button" v-for="hotSearch in hotSearchs">{{hotSearch}}</button>
      </div>
    </div>

    <div class="history" style="position: relative">
      <p class="history-title" v-if="searchContent == ''">搜索历史<span class="history-clear"
                                                                    @click="clearHistory">清除历史</span></p>
      <div style="height: 50px; margin: 0 16px; border-bottom: 1px solid #ECECEC" v-for="history in histories"><p
        style="padding: 18px 0; font-size: 14px; color: #666666;">{{history}}</p></div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Search",
    data() {
      return {
        hotSearchs: ['昂克赛拉', '卡罗拉', '福克斯', '思域', '凯美瑞', '迈腾', '雷克萨斯CT'],
        histories: ['凯迪拉克XTS', '奥迪A8L', '保时捷718', '迈巴赫S级'],
        searchContent: ''
      }
    },
    methods: {
      clearInput() {
        this.searchContent = '';
      },
      clearHistory() {
        this.histories = [];
      },
    },
    watch: {
      searchContent: {}
    },
  }
</script>

<style scoped>
  .search {
    height: 52px;
    width: 100%;
    background: #ffffff;
    position: fixed;
    z-index: 100;
    top: 0
  }

  .search input {
    background: #F1F3F5;
    border: 0;
    height: 36px;
    border-radius: 36px;
    width: 69%;
    margin: 8px 12px 8px 16px;
    outline: none;
  }

  .cancel {
    border: 0;
    background: none;
    color: #333333;
    height: 100%;
    padding: 0;
    outline: none;
  }

  .search-magnifier {
    background: none;
    border: 0;
    position: absolute;
    outline: none;
  }

  .search-magnifier img {
    height: 18px;
    width: 18px;
    margin-left: 28px;
    margin-top: 17px
  }

  .hotSearch {
    position: relative;
    background: #ffffff;
    margin-bottom: 12px
  }

  .hotSearch p {
    font-weight: bold;
    padding: 16px 16px 6px 16px
  }

  .hotSearch-button {
    background: #F1F3F5;
    color: #666666;
    font-size: 14px;
    height: 30px;
    border-radius: 30px;
    border: 0;
    padding: 0 14px;
    margin: 6px 6px;
  }

  .history {
    height: 450px;
    padding-top: 7px;
    background: #ffffff
  }

  .history-title {
    padding: 10px 16px;
    font-weight: bold;
    font-size: 16px;
  }

  .history-clear {
    color: #f5ca1d;
    float: right;
    font-size: 15px;
  }
</style>
