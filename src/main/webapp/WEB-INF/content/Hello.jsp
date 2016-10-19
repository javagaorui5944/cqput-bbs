<!DOCTYPE html>
<html>
<head>
	<title>
		
	</title>
</head>
<body>
<img src="404.png">
<script>

	function funUpload() {
		var strJSON = "{topicId:1,uIds:[2],content:'me'}";//得到的JSON

		var commentValue = JSON.stringify(strJSON);

		var xhr = new XMLHttpRequest();

		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4 && xhr.status == 200) {
				//var dataJson  = eval("("+data+")");
				var data = xhr.responseText;
				data =  eval("("+data+")");
				var res = data.code;
				console.log(data);
				if(res==1){
					alert(data.msg);
				}
				else {
					alert(data.data);
				}


			}
		};

		xhr.open('POST', '../Topic/AddTopicComment.do');
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(commentValue);
	}
</script>
</body>
</html>