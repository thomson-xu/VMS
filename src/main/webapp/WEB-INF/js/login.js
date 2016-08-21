/**
 * Created by Administrator on 2016/8/13.
 */

function doLogin() {
    if(checkUserName() && checkUserPwd() && checkCode())
    {
        var data = {
            UserName: $('#txtUserName').val(),
            UserPwd: $('#txtUserPwd').val(),
            CheckCode: $('#txtCheckCode').val()
        };
//提交数据给Login.ashx页面处理
        $.post("Ajax/Login.ashx",data,function(result){
            if(result == "1") //登录成功
            {
                alert("登录成功！您可以进行其他操作了！");
// 关闭模拟窗口
                window.parent.window.close();
            }
            else if(result == "2") //验证码错误
            {
                $('#txtCheckCode').next("span").css("color","red").text("*验证码错误");
            }
            else
            {
                alert("登录失败！请重试");
            }
        });
    }
    else
    {
        checkUserName();
        checkUserPwd();
        checkCode();
    }


}
 function refreshImgCode() {
    $("imgCode").attr('src','CheckCode.aspx?'+Math.random());

 }
//check the userName
function checkUserName()
{
    if($("#VUsername").val().length == 0)
    {
        $("#VUsername").next("span").css("color","red").text("*用户名不为空");
        return false;
    }
    else
    {
        var reg = /^\d{9}$/;
        if(!reg.test($('#VUsername').val()))
        {
            $('#txtUserName').next("span").css("color","red").text("*正确的格式如：030602888");
            return false;
        }
        else
        {
            $("#VUsername").next("span").css("color","red").text("");
            return true;
        }
    }
}
//check the pwd
function checkUserPwd()
{
    if($('#VPassword').val().length == 0)
    {
        $('#VPassword').next("span").css("color","red").text("*密码不为空");
        return false;
    }
    else
    {
        $('#VPassword').next("span").css("color","red").text("");
        return true;
    }
}
// check the check code
function checkCode()
{
    if($('#code').val().length == 0)
    {
        $('#code').next("span").css("color","red").text("*验证码不为空");
        return false;
    }
    else
    {
        $('#code').next("span").css("color","red").text("");
        return true;
    }
}
function loginSubmit(){
    var form = $('#LoginForm').serialize();
    $.post('<c:url value="/ajaxLoginProcess" />',form,function(data){
        if(data.error == 1)
            alert(data.messages);
        else if (data.error == 0){
            alert("success")
        }
    });
}

var email_str = "/^(?:[a-z/d]+[_/-/+/.]?)*[a-z/d]+@(?:([a-z/d]+/-?)*[a-z/d]+/.)+([a-z]{2,})+$/i"; //Email regular expression

$(document).ready(function() {
    $("#btnLogin").click(function() {
            var username = $("#txtUser").val();
            var password = $("#txtPassword").val();
            if (username == "" || password == "") {//check if the input is legal
                alert("用户名和密码不可以为空!");
                return false;
            }
            else if (!email_str.test(username)) {//check if email is legal
                alert("邮件地址格式不正确!");
                return false;
            } else {
                $.ajax({
                    type: "POST",
                    url: "/Ajax/LoginHandler.ashx", //event handler url
                    data: "username=" + escape($('#txtUser').val()) + "&password=" + escape($('#txtPassword').val()),//发送ajax请求
                    beforeSend: function() {
                        $("#loading").css("display", "block"); //show loading
                        $("#btnLogin").css("display", "none"); //hide login button
                    },
                    success: function(msg) {
                        $("#loading").hide(); //hide loading
                        if (msg == "unregistered") {
                            alert("对不起，该用户未注册!");//user is unregistered
                        }
                        if (msg == "frozen") {
                            alert("对不起，该用户已被冻结!");//user id frozen
                        }
                        if (msg == "fail") {
                            alert("对不起，用户名或密码错误!"); //login failed
                        }
                        if (msg == "success") {
                            parent.document.location.href = "manage.aspx"; //login successfully
                        }
                    },
                    complete: function(data) {
                        $("#loading").css("display", "none"); //hide loading
                        $("#btnLogin").css("display", "block"); //show login
                    }
                });
            }
            return false; //stop client continue submit
        }
    );
});