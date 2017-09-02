<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script src="bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="bootstrap-theme.min.css">
    <script src="jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<#if numadd??>
上次浏览的页面： <<<<${numadd}>>>>
</#if>
<#if fileurl??>
文件名和文件路径： ${fileurl}
</#if>

<div class="container-fluid">
    <div class="row">

        <div class="col-xs-2">
            <form action="/openfile" method="post">
                <input type="text" name="s">
                <button type="submit" class="btn btn-default btn-primary">打开</button>
            </form>
        </div>

        <div class="col-xs-2">
            <form action="/skipnum" method="post">
                <input type="text" name="skipnum">
                <button type="submit" class="btn btn-default btn-primary">跳转</button>
            </form>
        </div>
        <div class="col-xs-2">
            <a href="/forexit">
                <button type="submit" class="btn btn-default btn-primary">关闭程序</button>
            </a>
        </div>

    </div>

</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-6">
            <p class="text-left">
            <form action="last" method="get">
                <button type="submit" class="btn btn-default btn-block btn-primary">上一页</button>
            </form>

            </p>

        </div>

        <div class="col-xs-6">
            <p class="text-right">
            <form action="/next" method="get">
                <button type="submit" class="btn btn-default btn-block btn-primary">下一页</button>
            </form>
            </p>
        </div>
    </div>

    <div style="text-align: center;">
        <img src="/image" class="img-responsive center-block">
    </div>

    <div class="col-xs-6">
        <p class="text-left">
        <form action="last" method="get">
            <button type="submit" class="btn btn-default btn-block btn-primary">上一页</button>
        </form>

        </p>

    </div>

    <div class="col-xs-6">
        <p class="text-right">
        <form action="/next" method="get">
            <button type="submit" class="btn btn-default btn-block btn-primary">下一页</button>
        </form>
        </p>
    </div>
</div>

</div>
</body>
</html>
