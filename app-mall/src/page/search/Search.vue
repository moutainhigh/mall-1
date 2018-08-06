<template>
  <div>
    <div style="height: 3rem"></div>
    <div class="search">
      <div style="width: 85%;flex: 1; padding-left: 1rem">
        <div class="search-con">
          <img src="../../assets/img/common/ic_search.png"
               style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <input type="text" class="search-text" :class="{'text': searchContent != ''}" v-model="searchContent" placeholder="输入搜索内容">
          <img style="position: absolute; right: 4.5rem; top: 1rem; width: 16px" v-if="searchContent != ''"
               src="../../assets/img/common/search_ic_eliminate.png" @click="clearInput">
        </div>
      </div>
      <button class="cancel" @click="back">取消</button>
    </div>

    <div class="hotSearch" v-if="searchContent == ''">
      <p>热门搜索</p>
      <div style="padding: 0 9px">
        <button class="hotSearch-button" v-for="hotSearch in hotSearchs" @click="searchContent = hotSearch">{{hotSearch}}</button>
      </div>
    </div>

    <div class="history" v-if="searchContent == ''">
      <p class="history-title">搜索历史<span class="history-clear"
                                         @click="clearHistory">清除历史</span></p>
      <div class="listItem" v-for="history in histories"><p>{{history}}</p></div>
    </div>
    <div class="history" v-if="searchContent != ''">
      <div class="listItem" v-for="result in resultList" @click="toDetail(result)"><p>{{result.title}}</p></div>
    </div>
  </div>
</template>

<script>
  import {keywordSearch} from "../../service/getData";
  import storage from "../../store/storage";

  export default {
    name: "Search",
    data() {
      return {
        hotSearchs: ['昂克赛拉', '卡罗拉', '福克斯', '思域', '凯美瑞', '迈腾', '雷克萨斯CT'],
        histories: storage.fetch("keywordSearch"),
        searchContent: '',
        resultList: [],
        pageQuery: {
          page: 0,
          size: 10,
          keyword: ''
        },
      }
    },
    methods: {
      clearInput() {
        this.searchContent = '';
      },
      clearHistory() {
        this.histories = [];
        storage.save("keywordSearch", []);
      },
      toDetail(result) {
        this.saveSearch(result);
        this.$router.push({
          path: '/car-detail',
          query: {
            productId: result.id,
          }
        })
      },
      saveSearch(result) {
        let keywordSearch = storage.fetch("keywordSearch");
        if (keywordSearch.length == 0) {
          let list = [];
          list.push(result);
          storage.save("keywordSearch", list);
        } else {
          if (keywordSearch.length == 10) {
            keywordSearch.splice(0, 1);
          }
          keywordSearch.push(result);
          storage.save("keywordSearch", keywordSearch);
        }
      },
      back() {
        this.$router.go(-1);
      }
    },
    watch: {
      searchContent: {
        handler(newVal, oldVal) {
          this.pageQuery.keyword = newVal;
          keywordSearch(this.pageQuery).then(res => {
            if (res.result == 'SUCCESS') {
              if (res.data.pageFinder) {
                let pageData = res.data.pageFinder.data;
                this.result = {
                  title: '',
                  id: null
                };
                this.resultList = [];
                for (let i = 0; i < pageData.length; i++) {
                  this.result.title = pageData[i].commodityTitle;
                  this.result.id = pageData[i].defaultProduct;
                  this.resultList.push(this.result);
                }
              }
            }
          })
        }
      }
    },
  }
</script>

<style scoped>

  .search-con {
    height: 2rem;
    width: 100%;
    background-color: #F1F3F5;
    border-radius: 1rem;
    margin-top: 0.5rem;
  }

  .search-text {
    color: #333333;
    font-size: 1rem;
    text-align: left;
    margin: 0;
    padding: 0.5rem 0 0 2.5rem;
  }

  .search {
    height: 52px;
    width: 100%;
    background: #ffffff;
    position: fixed;
    z-index: 100;
    top: 0;
    display: flex;
  }

  .search input {
    background: #F1F3F5;
    border: 0;
    border-radius: 36px;
    width: 69%;
    margin: 0;
    outline: none;
    font-size: 1rem;
  }

  .search input::-webkit-input-placeholder {
    color: #999999 !important;
  }

  .cancel {
    border: 0;
    background: none;
    color: #333333;
    height: 100%;
    padding: 0 1rem;
    outline: none;
    font-size: 1rem;
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
    /*height: 450px;*/
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

  .listItem {
    height: 50px;
    margin: 0 16px;
    border-bottom: 1px solid #ECECEC
  }

  .listItem p {
    padding: 18px 0;
    font-size: 14px;
    color: #666666;
  }

  .text {
    padding-top: 0.4rem!important;
  }
</style>
