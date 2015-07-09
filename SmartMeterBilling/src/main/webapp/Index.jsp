<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to TNEB | Tamil Nadu Electricity Board</title>
<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="ext/ext-all-debug.js"></script>
<script type="text/javascript" src="ext/ux/ImagePanel.js"></script>
<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="ext/resources/css/xtheme-gray.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script type="text/javascript" src="ext/ux/FileUploadField.js"></script>
<script type="text/javascript" src="jsfiles/urlconfig.js"></script>
<script type="text/javascript" src="jsfiles/datagrid.js"></script>
<script type="text/javascript" src="jsfiles/consumerform.js"></script>
<script type="text/javascript" src="jsfiles/fileupload.js"></script>
<script type="text/javascript" src="jsfiles/invoicedaterangeform.js"></script>
<script type="text/javascript" src="jsfiles/invoice/invoiceform.js"></script>
<script type="text/javascript" src="jsfiles/refreshbaseprice.js"></script>
<script type="text/javascript" src="jsfiles/app.js"></script>
<script>
try{
if(!console){
	console={log:Ext.EmptyFn};
}
}catch(e){
	
}


function printMyDivOnly(cmp) {

if(cmp.setTitle){
	cmp.setTitle("Invoice Details");
}	
var myDiv=	cmp.getEl().dom.innerHTML;	
var mywindow = window.open('', 'new div', 'height=600,width=600');
if(!mywindow){
	Ext.Msg.alert('Please enable Pop-up','Browser is blocking Pop-up. Please enable and generate Invoice again.');
	return false;
}
    mywindow.document.write('<html><head><title>Invoice</title>');
    mywindow.document.write('<link rel="stylesheet" href="ext/resources/css/ext-all.css" type="text/css" />');
    mywindow.document.write('<link rel="stylesheet" href="ext/resources/css/xtheme-gray.css" />');
    mywindow.document.write('<link rel="stylesheet" href="css/custom.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(myDiv);
    mywindow.document.write('</body></html>');
    mywindow.print();
    mywindow.close();
    return true;
}
</script>
</head>
<body>
<div id="header-logo" style="background-color: #fffff; background-image: url(images/vzon2.png); height: 103px; width: 220px;"> </div>
</body>
</html>