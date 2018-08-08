<template>
    <div class='configuration'>
      <table>
        <thead>
          <tr >
            <td class='left'>配置参数</td>
            <td class='right'>宝马X1 2018款sDrive18Li 时尚型</td>
          </tr>
        </thead>
        <tbody> 
          <tr v-for="(val,key,index) in TableData">
            <td class='left'>{{key}}</td>
            <td class='right'>{{val}}</td>
          </tr>
        </tbody>
        
      </table>
    </div>
</template>

<script>
  export default {
    name: "CarConfig",
    props: ['TableData'],
  }

</script>

<style lang='scss' scoped>
.configuration{
  margin-top:0.5rem;
  table{
    tr{
       height:3.4rem;
       display: flex;
       td{
          height:3.4rem;
          background:rgba(255,255,255,1);
          border: 0.01rem solid #E5E5E5;
          text-align: center;
          
       }
      .left{
         width:9rem;
         border-right: none;
        line-height: 3.4rem;
        border-left:none;
         
        
      }
      .right{
         flex:1;
         border-right: none;
      }
      
    } 
    thead{
      .left{
          font-size:1.1rem;
          font-family:PingFang-SC-Bold;
          color:rgba(245,202,29,1);
          border-bottom: none;
          line-height: 4rem;
          border-top:none;
      }
      .right{
            font-size:1rem;
            font-family:PingFang-SC-Medium;
            color:rgba(51,51,51,1);
            border-bottom: none;
            word-wrap:break-word;
            padding-top:0.6rem;
            border-top:none;

      }
    }
    tbody{
      .left{
        font-size:0.8rem;              
        font-family:PingFang-SC-Medium;
        color:rgba(51,51,51,1);
      }
      .right{
        font-size:1rem;
        font-family:Roboto-Bold;
        color:rgba(245,78,78,1);
        line-height: 4rem;
      }
    }
  }
}
</style>
