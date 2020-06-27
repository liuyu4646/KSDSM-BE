//测试提交，对接程序删除即可
$("#submit").click(function () {
    var filename = document.getElementById("filename").value;
    if (filename == "") {
        alert("数据集名不能为空!");
        return false;
    } else  {
            var param={
                // 提交时携带的参数
                "name": filename,
            };
            $.ajax({
                type: 'POST',
                url: "http://localhost:20202/add",
                data:JSON.stringify(param) ,
                success: function(){
                    alert("创建成功！");
                },
                false:function(result){
                    alert("创建失败！"+result);
                },
                crossDomain: true,
                xhrFields: {
                    withCredentials: true
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

    }
});


