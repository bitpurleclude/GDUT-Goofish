<template>
  <div class="hello">
    <!--中间-->
    <view class="box-center">
      <!--v
      listType:列表类型
      listBgc:列表背景色
      -->
      <GoodsList class="goods-components" :goodsList="goodsList" :listType="listType" :listBgc="listBgc" id="goodsList">
      </GoodsList>

      <image class="hiddenImg" src="../../icons/暂无该商品插图.png" mode="widthFix" v-if="goodsList.length == 0" />

      <view class="hiddenWord" v-if="goodsList.length == 0">暂无商品哦~</view>
    </view>
  </div>
</template>

<script>
export default {
  data () {
    return {
      goodsList: [],
      listType: '',
      listBgc: ''
    }
  }
}
</script>

<style scoped>
/* Add your styles here */
</style>
