<template>
  <div class="hello">
        <!--中间-->
    <view class="box-center">

        <!--v
        listType:列表类型
        listBgc:列表背景色
        -->

        <GoodsList class="goods-components" goodsList="{{goodsList}}" listType="{{listType}}" listBgc="{{listBgc}}" id="goodsList">
        </GoodsList>

         <image class="hiddenImg" src="../../icons/暂无该商品插图.png" mode="widthFix" wx:if="{{goodsList.length==0}}" />
    
        <view class="hiddenWord" wx:if="{{goodsList.length ==0}}" >暂无商品哦~</view>
          
    </view>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld1',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
