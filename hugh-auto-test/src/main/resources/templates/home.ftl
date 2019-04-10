<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="http://oss.sheetjs.com/js-xlsx/xlsx.full.min.js"></script>
</head>

<body>
<input id="stepFile" type="file" accept=".xls"/>
<button type="button" onclick="send()">执行</button>
<div id="demo"></div>
<script>

    function send() {
        let fileInput = document.getElementById("stepFile");
        if (!fileInput.files[0]) {
            alert("请选择文件");
            return;
        }
        let file = fileInput.files[0];
        let fileName = file.name;
        if (".xls" != fileName.substring(fileName.lastIndexOf("."), fileName.length)) {
            alert("文件类型异常");
            return;
        }
        let data = read(file);
        console.log(data);
    }

    let wb;//读取完成的数据
    let rABS = false; //是否将文件读取为二进制字符串

    function read(f) {//导入
        let reader = new FileReader();
        reader.onload = function (e) {
            let data = e.target.result;
            wb = XLSX.read(data, {
                type: 'binary'
            });
            document.getElementById("demo").innerHTML = JSON.stringify(XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]));
        };
        reader.readAsBinaryString(f);
    }
    function fixdata(data) { //文件流转BinaryString
        let o = "",
            l = 0,
            w = 10240;
        for (; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
        o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
        return o;
    }

</script>
</body>

</html>