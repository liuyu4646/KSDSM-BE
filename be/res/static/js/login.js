//测试提交，对接程序删除即可
$("#submit").click(function () {
    var thisUsername = document.getElementById("username").value;
    var thisPassword = document.getElementById("password").value;
    if (thisUsername == "") {
        alert("用户名不能为空!");
        return false;
    } else if (thisPassword == "") {
        alert("密码不能为空!");
        return false;
    } else if (thisUsername == "ST036" || thisUsername == "st036") {
            var param={
                // 提交时携带的参数
                "address": "10.60.43.8:8800",
                    "account": thisUsername,
                    "password": thisPassword,
            };
            $.ajax({
                type: 'POST',
                url: "http://localhost:20202/login",
                data:JSON.stringify(param) ,
                success: function(result){
                    alert("登录成功！"+result);
                    window.location.href = "index.html";
                },
                false:function(result){
                    alert("登录失败！"+result);
                },
                contentType: "application/json;charset=utf-8",
            });
            // 第一个参数是提交的地址
            /*$.post("http://localhost:20202/login",
                {
                    // 提交时携带的参数
                    "address": "10.60.43.8:8800",
                    "account": thisUsername,
                    "password": thisPassword,
                },

            // 回调函数,data是服务器返回的数据
                function (data, status) {
                    //var dataObj = eval("(" + data + ")");//转换为json对象
                    alert("登录成功！");
                    window.location.href = "../index.html";
                }
                );*/

    } else {
        alert("用户名错误！");
        return false;
    }
});


