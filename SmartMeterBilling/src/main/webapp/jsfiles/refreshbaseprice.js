function updatedRulesWin(formData){
	
	var invoiceGridStore = new Ext.data.JsonStore({
      root: 'rules',
      fields: [ 'maxValue', 'minValue', 'price' ],
      autoLoad: true,
      data: formData
    }); 
	
	var invoiceGrid = new Ext.grid.GridPanel({
	itemId:'invoiceGrid',
          frame: false,
          /*autoHeight:true,*/
          height:150,
          store: invoiceGridStore,
          viewConfig:{
        	  forceFit:true
          },
          columns: [
                  {header: "<b>Usage Tier Start</b>", dataIndex: 'minValue', sortable:true, resizable:true, renderer:function(val,p,rec){if(val<0){return 0;} return val;}},
                  {header: "<b>Usage Tier End</b>", dataIndex: 'maxValue', sortable:true, resizable:true, renderer:function(val,p,rec){if(val<0){return "And Above";}  return val;}},
                  {header: "<b>Price</b>", dataIndex: 'price', sortable:true, resizable:true, renderer:function(val,p,rec){if(val<0){return 0;}  return val;}}
              ]
	});
	
	var winGrid=new Ext.Window({
		title:'Updated Rules',
		layout:'vbox',
		width: 400,
		modal: true,
		height: 200,
		autoScroll:true,
		items:[{
			xtype:'displayfield',
			hideLabel:true,
			value:'Please find the updated rules applicable from now on.'
		},invoiceGrid]
	});
	winGrid.show();
}

function refreshRules(){
	  Ext.Ajax.request({
    				   	url: URLCONFIG.refreshPricingRules,
    				   	method:'GET',
    				   	timeout : 30000,
    				   	params : { 
    				   		},
					    success: function(response, opts) {
					       var obj = Ext.decode(response.responseText);
					       var formData={};
					       console.log('Refresh Base Rul',obj)
					       if(obj){
					    	   updatedRulesWin(obj);
					       }else{
					    	   Ext.Msg.alert('Sorry.','Unable to fetch the Updated Rules.');
					       }
					    },
					    failure: function(response, opts) {
					    	 Ext.Msg.alert('Sorry.','Unable to fetch the Updated Rules.');
					       console.log('server-side failure with status code ' + response.status);
					    },
					    scope: this
					});
}