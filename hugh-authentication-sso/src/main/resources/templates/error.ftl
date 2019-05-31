<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>GlobalError</title>
</head>
<body>
<h2>服务异常，请稍后再试。</h2>
<div>
    <h3>发生时间:${time!}</h3>
    <h3>访问地址:${url!}</h3>
    <h3>问题类型:${error!}</h3>
    <h3>通信状态:${statusCode!}  ${reasonPhrase!}</h3>
    <h3>堆栈信息:<br/>${stackTrace!}</h3>
</div>
</body>
</html>