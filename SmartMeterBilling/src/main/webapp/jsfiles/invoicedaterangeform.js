var invoicedaterangeform=new Ext.form.FormPanel({
	  title:'Print Invoice',
	  items:[{
		   xtype:'fieldset',
        columnWidth: 1,
        collapsible: false,
		  items:[{
		  xtype:'textfield',
		  readOnly:true,
		  fieldLabel:'<b>Smart Meter ID</b>',
		  id:'INVOICE_ID',
		  emptyText:'Select Smart Meter ID from Usage Summary',
		  invalidText:'Select Smart Meter ID from Usage Summary',
		  allowBlank:false
	  }]
	  },{ 
		 xtype:'fieldset',
        columnWidth: 1,
        collapsible: false,
         items:[{
        	xtype:'datefield',
        	id:'FROM_DATE',
        	fieldLabel:'<b>From Date</b>',
        	allowBlank:false
        },{
        	xtype:'datefield',
        	id:'TO_DATE',
        	fieldLabel:'<b>To Date</b>',
        	allowBlank:false
        }]
	  }],
       bbar:[{
    	   text:'<b><i>Get Invoice</i><b>',
    	   handler:function(){
    		   var form=invoicedaterangeform.getForm();
    		   var smartId=Ext.getCmp('INVOICE_ID').getValue();
    		   var fromDate=Ext.util.Format.date(Ext.getCmp('FROM_DATE').getValue(), 'm/d/Y')+" 00:00 AM";
    		   var toDate=Ext.util.Format.date(Ext.getCmp('TO_DATE').getValue(), 'm/d/Y')+" 11:59 PM" ;
    		   
    		   
    		   if(form.isValid()){
    			   var me = this;
    			 
    			   Ext.Ajax.request({
    				   	url: URLCONFIG.invoiceprint,
    				   	method:'GET',
    				   	timeout : 30000,
    				   	params : { 
    				   			'smartMeterID': smartId,
    				   			'fromDate':fromDate,
    				   			'toDate':toDate
    				   		},
					    success: function(response, opts) {
					       var obj = Ext.decode(response.responseText);
					       var formData={};
					       if(obj.invoiceDO){
					    	   var fromDt=obj.invoiceDO.fromDate;
					    	   var toDt=obj.invoiceDO.toDate;
					       Ext.apply(formData, obj.invoiceDO.customer);
					       Ext.apply(formData, obj.invoiceDO.fromDate);
					       Ext.apply(formData, obj.invoiceDO.toDate);
					       Ext.apply(formData, obj.invoiceDO.priceDO);
					       formData['fromDate']=new Date(fromDt);
				    	   formData['toDate']=new Date(toDt);
					       generateInvoiceForm(formData);
					       }else{
					    	   if(obj.errorMessage){
					    		   Ext.Msg.alert('Error',obj.errorMessage);
					    	   }else{					    	   
					    	   Ext.Msg.alert('Error','Unable to fetch the data for Download.');
					    	   }
					       }
					       
					    },
					    failure: function(response, opts) {
					    	 Ext.Msg.alert('Error','Unable to fetch the Invoice Data.');
					    },
					    scope: this
					});
    			 Ext.Ajax.on('beforerequest', Ext.emptyFn, this);
    			 Ext.Ajax.on('requestcomplete', Ext.emptyFn, this);
    			 Ext.Ajax.on('requestexception', Ext.emptyFn, this);
    			 
    		   }
    	   }
       },{xtype:'tbfill'},{
    	   text:'<b><i>Reset</i><b>',
    	   handler:function(){
    		    invoicedaterangeform.getForm().reset();
    	   }
       }]
});