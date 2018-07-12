<template>
  <div>
    <div class="search">
      <img class="ic-search" src="../assets/img/ic_search.png"/>
      <input placeholder="请输入职位" v-model="searchVal"/>
      <img class="eliminate" v-if="searchVal.length != 0" @click="searchVal=''" src="../assets/img/search_ic_eliminate.png"/>
      <div class="cancel" @click="cancel">取消</div>
    </div>
    <div class="search-list">
      <div class="search-item" v-if="defaultCareer.key.length != 0" @click="changeCareer(career)">
        <div>{{defaultCareer.value}}</div>
        <img src="../assets/img/selected.png" class="select">
      </div>
      <div class="search-item" v-for="career in careers" v-if="career.key != defaultCareer.key" @click="changeCareer(career)">
          <div>{{career.value}}</div>
      </div>
    </div>
  </div>
</template>

<script>
  import {careerCode} from "../admin/career"
  import storage from "../store/storage";
  export default {
    name: "career-select",

    data() {
      return {
        searchVal:'',
        careers:careerCode,
        defaultCareer:{
          key:'',
          value:''
        },
        type:'',
        key:''
      }
    },
    methods: {
      cancel(){
        this.$router.back();
      },
      changeCareer(career){
        if (this.type != '') {
          let fetch = storage.fetch(this.type);
          fetch.careerName = career.value;
          fetch[this.key] = career.key;
          storage.save(this.type,fetch);
        }
        this.$router.back();
      }
    },
    watch: {
      searchVal(newVal){
        let value = newVal.replace(" ", "");
        if (value == ''){
          this.careers = careerCode;
        }else {
          let dbCareers = [];
          careerCode.forEach(career=>{
            if (career.value.indexOf(value) > 0) {
              dbCareers.push(career);
            }
          });
          this.careers = dbCareers;
        }
      }
    },
    created(){
      this.key = this.$route.query.key;
      this.type = this.$route.query.type;
      if (this.key && this.type) {
        console.log(storage.fetch(this.type));
        if (storage.fetch(this.type)[this.key].length != 0) {
          this.defaultCareer={
            key:storage.fetch(this.type)[this.key],
            value:storage.fetch(this.type).careerName
          }
        }
      }
    }
  }
</script>

<style scoped>
  .search {
    height: 36px;
    background-color: #ffffff;
    padding: 16px 16px 8px;
  }

  .search img {
    width: 18px;
  }

  .search input {
    padding: 5px 5px 5px 36px;
    width: 83%;
    height: 36px;
    box-sizing: border-box;
    border: unset;
    border-radius: 5px;
    background-color: #f1f3f5;
    outline: unset;
  }

  .search div {
    display: inline-block;
  }

  .ic-search {
    position: absolute;
    margin: 8px 8px 8px 10px;
  }

  .eliminate {
    position: absolute;
    margin-left: -30px;
    margin-top: 9px;
  }

  .cancel {
    margin-left: 10px;
    color: #f5ca1d;
  }

  .search-list {
    background-color: #ffffff;
  }

  .search-item {
    padding: 16px 16px 16px 0;
    border-bottom: #d9d9d9 solid 1px;
    margin-left: 16px;
  }

  .select {
    width: 26px;
    float: right;
    margin-right: 10px;
    margin-top: -22px;
  }
</style>
