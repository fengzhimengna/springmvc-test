'use strict'
//1. 定义一个我们自己的模块，
// 第一个参数是：模块名，
// 第二个参数：所依赖的其他的模块的名字的数组
var app2 = angular.module('myApp', []);
// app.run是Angularjs版本的“入口函数”，我们的Angularjs框架加载完成之后，就会来执行这里的函数。
// 其中有一点需要注意：$rootScope是Angularjs提供的“数据对象”，
// 我们操作这个数据对象，就可以把变化同步到DOM上
// 参数名是写死的，不要变。（Angularjs的回调函数的传参，很多地方是“只认名字，不认顺序”的）
app2.run(function ($rootScope) {
    $rootScope['detail'] = function (id) { // 当按钮被点击之后，调用，设置当前的按钮
        alert(id);
    }
});