function generateInvoiceForm(formData){
	
	
	var invoiceGridStore = new Ext.data.JsonStore({
      root: 'rules',
      fields: [ 'maxValue', 'minValue', 'price' ],
      autoLoad: true,
      data: formData
    }); 
	
	
	
	var invoiceGrid = new Ext.grid.GridPanel({
	itemId:'invoiceGrid',
          frame: false,
          autoHeight:true,
          store: invoiceGridStore,
          viewConfig:{
        	  forceFit:true
          },
          columns: [
                  {header: "Usage Tier Start", dataIndex: 'minValue', sortable:true, resizable:true},
                  {header: "Usage Tier End", dataIndex: 'maxValue', sortable:true, resizable:true},
                  {header: "Price", dataIndex: 'price', sortable:true, resizable:true}
              ]
	});
	
	
	var invoiceForm=new Ext.form.FormPanel({
		layout:'form',
		autoHeight:true,
		autoWidth:true,
	  items:[{
		  xtype:'panel',
		  layout:'form',
		  defaults:{
    	  readOnly:true,
    	  labelStyle: 'font-weight:bold;'
      },
			  items: [{
			  xtype:'displayfield',
			  name:'customerFirstName',
			  fieldLabel:'First Name'
		  },{
			  xtype:'displayfield',
			  name:'customerLastName',
			  fieldLabel:'Last Name'
		  },{
			  xtype:'displayfield',
			  name:'smartMeterId',
			  fieldLabel:'Smart Meter ID'
		  },{
			  xtype:'displayfield',
			  name:'addressLine1',
			  fieldLabel:'Address Line 1'
		  },{
			  xtype:'displayfield',
			  name:'addressLine2',
			  fieldLabel:'Address Line 2'
		  },{
			  xtype:'displayfield',
			  name:'zipCode',
			  fieldLabel:'Zip Code'
			 },{
			  xtype:'displayfield',
			  name:'totalUnits',
			  fieldLabel:'Total Units'
		  },{
			  xtype:'displayfield',
			  name:'unitsPrice',
			  fieldLabel:'Units Price'
		  },{
			  xtype:'displayfield',
			  name:'taxAmount',
			  fieldLabel:'Tax Amount'
		  },{
			  xtype:'displayfield',
			  name:'totalPrice',
			  fieldLabel:'Total Price'
		  }]},invoiceGrid]
	  });
	invoiceForm.getForm().setValues(formData);
	var invoiceFormWindow=new Ext.Window({
		modal:true,
		resizable:true,
		stateful:false,
		width:800,
		height:350,
		autoScroll:true,
		title:'Invoice',
		layout:'fit',
		closable:true,
		items:[invoiceForm],
		bbar:[{
			text:'<b><i>Print</i></b>',
			handler:function(){
				setTimeout(function(){
					printMyDivOnly(invoiceForm);
				},200);
			}
		},{text:' | ', disabled:true},{
			text:'<b><i>Download as PDF</i></b>',
			handler:function(){
				var smId = invoiceForm.getForm().getValues()['smartMeterId'];
				
				   var smartId = smId || formData.smartMeterId;
	    		   var fromDate=Ext.util.Format.date(formData.fromDate, 'm/d/Y')+" 00:00 AM";
	    		   var toDate=Ext.util.Format.date(formData.toDate, 'm/d/Y')+" 11:59 PM" ;
	    		   window.open(URLCONFIG.invoiceDownload+"?smartMeterID="+smartId+"&fromDate="+fromDate+"&toDate="+toDate);
				
			}
		},{xtype:'tbfill'},{
			text:'<b><i>Close</i></b>',
			handler:function(){
				invoiceFormWindow.close();
			}
		}]
	});
	invoiceFormWindow.show();
	
	
} 

function printMyDiv(cmp) {
var myDiv=	cmp.getEl().dom.innerHTML;
	
var mywindow = window.open('', 'new div', 'height=400,width=600');
if(!mywindow){
	Ext.Msg.alert('Please enable Pop-up','Browser is blocking Pop-up. Please enable and generate Invoice again.');
	return false;
}

    mywindow.document.write('<html><head><title>Invoice</title>');
    mywindow.document.write('<link rel="stylesheet" href="../../ext/resources/css/ext-all.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(myDiv);
    mywindow.document.write('</body></html>');

    mywindow.print();
    mywindow.close();

    return true;
}