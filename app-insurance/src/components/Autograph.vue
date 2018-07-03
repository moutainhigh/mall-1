<template>
    <div>
        <div class="title" style="border-bottom: 10px solid #f3f3f3;"><p>投保单签名</p></div>
        <div class="headPhoto">
            <img src="../assets/img/headPhotograph.png"/>
            <p>请点击此处，拍摄投保人正面头像</p>
        </div>
        <div class="title"><p>投保人签名</p></div>
        <div class="canvas">
            <canvas id="myCanvas"></canvas>
                <span>点击签名</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Autograph',
        props: {
            msg: String
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .title {
        text-align: left;
        width: 100%;
        height: 6vh;
        margin: 0;
        vertical-align: center;
        padding-left: 4vw;
    }

    .headPhoto {
        border-bottom: 2px solid #f3f3f3;
        margin: 0 4vw;
    }

    .headPhoto img {
        width: 23vw;
        margin-top: 5vh;
    }

    .headPhoto p {
        padding-bottom: 2vh;
    }

    .canvas {
        position: relative;
        height: auto;
        padding-bottom: 4vh;
    }

    #myCanvas {
        background: #e5e5e5;
        width: 92%;
        height: 20vh;
        border-radius: 10px;
    }

    span {
        position: absolute;
        left: 0;
        right: 0;
        padding-top: 15%;
        margin: auto;
        color: #999;
    }
</style>
