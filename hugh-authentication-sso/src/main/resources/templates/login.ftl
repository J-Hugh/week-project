<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hugh login</title>
    <!-- import Vue.js -->
    <script src="//vuejs.org/js/vue.min.js"></script>
    <!-- import VueI18n.js-->
    <script src="//unpkg.com/vue-i18n/dist/vue-i18n"></script>
    <!-- import stylesheet -->
    <link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">
    <!-- import iView -->
    <script src="//unpkg.com/iview/dist/iview.min.js"></script>
    <script src="//unpkg.com/iview/dist/locale/en-US.js"></script>
    <script src="//unpkg.com/iview/dist/locale/zh-CN.js"></script>
    <script src="/static/i18n/login/zh.js"></script>
    <script src="/static/i18n/login/en.js"></script>
</head>
<body>
    <div id="login">
        <i-form ref="formInline" :model="user" :rules="ruleInline" inline>
            <form-item prop="userAccount">
                <i-input type="text" v-model="user.userAccount" placeholder="userAccount">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
                </i-input>
            </form-item>
            <form-item prop="password">
                <i-input type="password" v-model="user.password" placeholder="password">
                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                </i-input>
            </form-item>
            <form-item>
                <i-button type="primary" @click="handleSubmit('formInline')">登录</i-button>
            </form-item>
            {{info}}
        </i-form>
    </div>
</body>

<script>
    const messages = {
        en: Object.assign({ message: 'hello' }, iview.langs['en-US']),
        zh: Object.assign({ message: '你好' }, iview.langs['zh-CN'])
    };
    const i18n = new VueI18n({
        locale: 'en', // set locale
        messages // set locale messages
    })
    new Vue({
        el: '#login',
        i18n: i18n,
        data(){
          return {
              user:{userAccount:'',password:''},
              ruleInline:{
                  userAccount:[{required : true, message: ""}],
                  password:[]
              },
              info: this.$t("message")
          }
        },
        methods:{
            handleSubmit : function (name) {
                this.$refs[name].validate((valid) => {
                    console.log(valid);
                })
            }
        }
    })
</script>
<style>
    #login {
        font-family: 'Avenir';
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 10%;
    }
</style>
</html>