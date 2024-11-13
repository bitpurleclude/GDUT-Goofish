<template>
    <div>
        <el-container style="height: 90vh">
            <el-header>chat</el-header>
            <el-container>
                <el-aside width="450px">
                    <div class="el-table-wrapper">
                        <el-table :data="messageTitleData" highlight-current-row @current-change="chooseTitle"
                            style="width: 100%" height="80vh">
                            <el-table-column prop="name" width="450px">
                            </el-table-column>
                        </el-table>
                    </div>
                </el-aside>
                <el-container>
                    <el-main>
                        <div class="el-table-wrapper">
                            <el-table :data="messageData" style="width: 100%" height="400px">
                                <el-table-column prop="context" width="800px">
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-main>
                    <el-footer height="150px">
                        <div>
                            <el-input type="textarea" :rows="3" placeholder="请输入内容" v-model="textarea">
                            </el-input>
                        </div>
                        <div>
                            <el-button type="primary" @click="sendMessage">发送</el-button>
                        </div>
                    </el-footer>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// import axios from 'axios'

Vue.use(ElementUI);

export default {
    data() {
        return {
            textarea: '',
            messageTitleData: [{
                name: '王1虎'
            }, {
                name: '王2虎'
            }, {
                name: '王3虎',
                age: 12
            }, {
                name: '王4虎'
            }, {
                name: '王5虎'
            }
            ],
            messageData: [{
                context: '消息1'
            }, {
                context: '消息2'
            }, {
                context: '消息3'
            }, {
                context: '消息4'
            }, {
                context: '消息5'
            }]
        }
    },
    mounted() {
        // axios({
        //     method: 'GET',
        //     url: 'http://localhost:8080/',
        //     data: {
        //         xx: 'xx',
        //         yy: 'yy'
        //     },
        //     headers: {
        //         token: ''
        //     }
        // }).then(res => {
        //     this.messageTitleData = res.data.data
        //     console.log(res.data.data.token)
        // });
    },
    methods: {
        chooseTitle(val) {
            alert(val.name)
            // axios({
            //     method: 'GET',
            //     url: 'http://localhost:8080/',
            //     data: {
            //         xx: 'xx',
            //         yy: 'yy'
            //     },
            //     headers: {
            //         token: ''
            //     }
            // }).then(res => {
            //     this.messageTitleData = res.data.data
            //     console.log(res.data.data.token)
            // });
        },
        sendMessage() {
            alert(this.textarea)
            this.textarea = ''
            // axios({
            //     method: 'POST',
            //     url: 'http://localhost:8080/',
            //     data: {
            //         xx: 'xx',
            //         yy: 'yy'
            //     },
            //     headers: {
            //         token: ''
            //     }
            // }).then(res => {
            //     this.messageTitleData = res.data.data
            //     console.log(res.data.data.token)
            // });
        }
    }
}

</script>
<style>
.el-table-wrapper {
    padding-top: 0;
    margin-top: 0;
}

.el-header,
.el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 50px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 10px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 10px;
}

body>.el-container {
    margin-bottom: 40px;
    margin-top: 0;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 320px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
</style>
