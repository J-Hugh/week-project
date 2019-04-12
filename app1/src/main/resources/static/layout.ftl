<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>iview example</title>
    <!-- import Vue.js -->
    <script src="//vuejs.org/js/vue.min.js"></script>
    <!-- import stylesheet -->
    <link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">
    <!-- import iView -->
    <script src="//unpkg.com/iview/dist/iview.min.js"></script>
</head>
<body>
<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-logo{
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    .layout-nav{
        width: 420px;
        margin: 0 auto;
        margin-right: 20px;
    }
</style>

<div id="app">
    <div class="layout">
        <Layout>
            <Header>
                <i-menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                        <menu-item name="1">
                            <Icon type="ios-navigate"></Icon>
                            Item 1
                        </menu-item>
                        <menu-item name="2">
                            <Icon type="ios-keypad"></Icon>
                            Item 2
                        </menu-item>
                        <menu-item name="3">
                            <Icon type="ios-analytics"></Icon>
                            Item 3
                        </menu-item>
                        <menu-item name="4">
                            <Icon type="ios-paper"></Icon>
                            Item 4
                        </menu-item>
                    </div>
                </i-menu>
            </Header>
            <Layout>
                <Sider hide-trigger :style="{background: '#fff'}">
                    <i-menu active-name="1-2" theme="light" width="auto" theme="dark" accordion :open-names="['1']">
                        <Submenu name="1">
                            <template slot="title">

                                <Icon type="ios-navigate"></Icon>
                                Item 1
                            </template>
                            <menu-item name="1-1">Option 1</menu-item>
                            <menu-item name="1-2">Option 2</menu-item>
                            <menu-item name="1-3">Option 3</menu-item>
                        </Submenu>
                        <Submenu name="2">
                            <template slot="title">
                                <Icon type="ios-keypad"></Icon>
                                Item 2
                            </template>
                            <menu-item name="2-1">Option 1</menu-item>
                            <menu-item name="2-2">Option 2</menu-item>
                        </Submenu>
                        <Submenu name="3">
                            <template slot="title">
                                <Icon type="ios-analytics"></Icon>
                                Item 3
                            </template>
                            <menu-item name="3-1">Option 1</menu-item>
                            <menu-item name="3-2">Option 2</menu-item>
                        </Submenu>
                    </i-menu>
                </Sider>
                <Layout :style="{padding: '12px 24px 24px'}">
                    <Content :style="{padding: '24px',  background: '#fff'}">
                        <#include "${content}">
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    </div>
</div>
<script>
    new Vue({
        el: '#app',
        data: {
            visible: false
        },
        methods: {
            show: function () {
                this.visible = true;
            }
        }
    })
</script>
</body>
</html>