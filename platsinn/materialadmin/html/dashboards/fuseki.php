<?php
include 'header.html';

include 'verificaserver.php';
?>
<br>
<script language="JavaScript">
<!--
function resize_iframe()
{

	var height=window.innerWidth;//Firefox
	if (document.body.clientHeight)
	{
		height=document.body.clientHeight;//IE
	}
	document.getElementById("iframe").style.height=parseInt(height-document.getElementById("iframe").offsetTop-8)+"px";
}


//-->
</script>
<iframe id="iframe" src="http://localhost:3030/index.html" width="100%" onload="resize_iframe()"></iframe>
<?php
include 'footer.html';
?>
