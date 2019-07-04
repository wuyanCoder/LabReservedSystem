<!DOCTYPE html>
<jsp:useBean id="student" scope="page" class="com.sean.model.Student"/>  
<jsp:setProperty property="leadership" name="student" value="${sessionScope.student.leadership}"/>
<jsp:setProperty property="teamwork" name="student" value="${sessionScope.student.teamwork}"/>
<jsp:setProperty property="apprehension" name="student" value="${sessionScope.student.apprehension}"/>
<jsp:setProperty property="practice" name="student" value="${sessionScope.student.practice}"/>
<jsp:setProperty property="analysis" name="student" value="${sessionScope.student.analysis}"/>
<jsp:setProperty property="innovation" name="student" value="${sessionScope.student.innovation}"/>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>个人能力</title>
  <style type="text/css">
    canvas{
    }
  </style>
  
</head>
<body>

<script type="text/javascript">	
	var mW = 400;
	var mH = 400;
	var mData = [['领导力',0],
							['团队力', 0],
							['理解力', 0],
							['实践力', 0],
							['分析力', 0],
							['创新力', 0]];
	
	var mCount = mData.length; //边数	
	var mCenter = mW /2; //中心点
	var mRadius = mCenter - 50; //半径(减去的值用于给绘制的文本留空间)
	var mAngle = Math.PI * 2 / mCount; //角度
	var mCtx = null;
	var mColorPolygon = '#B8B8B8'; //多边形颜色
	var mColorLines = '#B8B8B8'; //顶点连线颜色
	var mColorText = '#000000';
	mData[0][1]=${student.leadership};
	mData[1][1]=${student.teamwork};
	mData[2][1]=${student.apprehension};
	mData[3][1]=${student.practice};
	mData[4][1]=${student.analysis};
	mData[5][1]=${student.innovation};
	//初始化
	(function(){
	  var canvas = document.createElement('canvas');
	  document.body.appendChild(canvas);
	  canvas.height = mH;
	  canvas.width = mW;
	
	  mCtx = canvas.getContext('2d');
	
	  drawPolygon(mCtx);
	  drawLines(mCtx);
	  drawText(mCtx);
	  drawRegion(mCtx);
	  drawCircle(mCtx);

	})();
  
      // 绘制多边形边
      function drawPolygon(ctx){
      	ctx.save();
      	
      	ctx.strokeStyle = mColorPolygon;
      	var r = mRadius/ mCount; //单位半径
      	//画6个圈
      	for(var i = 0; i < mCount; i ++){
      		ctx.beginPath();  		
      		var currR = r * ( i + 1); //当前半径
      		//画6条边
      		for(var j = 0; j < mCount; j ++){
      			var x = mCenter + currR * Math.cos(mAngle * j);
      			var y = mCenter + currR * Math.sin(mAngle * j);
      			
      			ctx.lineTo(x, y);
      		}
      		ctx.closePath()
      		ctx.stroke();
      	}
      	
      	ctx.restore();
      }
	
	//顶点连线
	function drawLines(ctx){
		ctx.save();
		
		ctx.beginPath();
		ctx.strokeStyle = mColorLines;
		
		for(var i = 0; i < mCount; i ++){
			var x = mCenter + mRadius * Math.cos(mAngle * i);
			var y = mCenter + mRadius * Math.sin(mAngle * i);
			
			ctx.moveTo(mCenter, mCenter);
			ctx.lineTo(x, y);
		}
		
		ctx.stroke();
		
		ctx.restore();
	}
	
	//绘制文本
	function drawText(ctx){
		ctx.save();
		
		var fontSize = mCenter / 12;
		ctx.font = fontSize + 'px Microsoft Yahei';
		ctx.fillStyle = mColorText;
		
		for(var i = 0; i < mCount; i ++){
			var x = mCenter + mRadius * Math.cos(mAngle * i);
			var y = mCenter + mRadius * Math.sin(mAngle * i);
			
			if( mAngle * i >= 0 && mAngle * i <= Math.PI / 2 ){
				ctx.fillText(mData[i][0], x, y + fontSize);	
			}else if(mAngle * i > Math.PI / 2 && mAngle * i <= Math.PI){
				ctx.fillText(mData[i][0], x - ctx.measureText(mData[i][0]).width, y + fontSize);	
			}else if(mAngle * i > Math.PI && mAngle * i <= Math.PI * 3 / 2){
				ctx.fillText(mData[i][0], x - ctx.measureText(mData[i][0]).width, y);	
			}else{
				ctx.fillText(mData[i][0], x, y);
			}
			
		}
		
		ctx.restore();
	}
	
	//绘制数据区域
	function drawRegion(ctx){
		ctx.save();
		
		ctx.beginPath();
		for(var i = 0; i < mCount; i ++){
			var x = mCenter + mRadius * Math.cos(mAngle * i) * mData[i][1] / 100;
			var y = mCenter + mRadius * Math.sin(mAngle * i) * mData[i][1] / 100;
			
			ctx.lineTo(x, y);
		}
		ctx.closePath();
		ctx.fillStyle = 'rgba(255, 0, 0, 0.5)';
		ctx.fill();
		
		ctx.restore();
	}
	
	//画点
	function drawCircle(ctx){
		ctx.save();
	
		var r = mCenter / 18;
		for(var i = 0; i < mCount; i ++){
			var x = mCenter + mRadius * Math.cos(mAngle * i) * mData[i][1] / 100;
			var y = mCenter + mRadius * Math.sin(mAngle * i) * mData[i][1] / 100;

			ctx.beginPath();			
			ctx.arc(x, y, r, 0, Math.PI * 2);
			ctx.fillStyle = 'rgba(255, 0, 0, 0.8)';
			ctx.fill();
		}		
		
		ctx.restore();
	}
</script>

</body>
</html>