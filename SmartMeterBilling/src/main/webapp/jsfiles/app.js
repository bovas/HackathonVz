Ext.onReady(function(){
	    Ext.QuickTips.init();
	var viewport = new Ext.Viewport({
		title:"My Viewport",
		frame:true,
		renderTo: Ext.getBody(),
		layout:'border',
		items:[{
			region:'west',
			/*layout:"fit",*/
			autoScroll:true,
			width: 295,
			height:500,
			autoHeight:false,
			collapsible:true,
			xtype:'panel',
			frame:true,
			title:"<span class='viewport-heading-text'><b>Consumer Profile Creation</b></span>",
			items:[{
				xtype:'panel',
				padding:'5px 0px 10px',
				items:[consumerform]
			},{
				xtype:'panel',
				padding:'20px 0px 0px',
				items:[bulkuploadform]
			}]
		},{
		region:'center',
		xtype:'panel',
		frame:true,
		ctCls:'viewport-header',
		title:"<span class='viewport-heading-text'><b>TNEB Usage</b></span>",
		layout:"fit",
		items:[myDataGrid]
		},{
			region:'east',
			width: 350,
			layout:"fit",
			collapsible:true,
			xtype:'panel',
			frame:true,
			title:"<span class='viewport-heading-text'><b>Invoice and Rules</b></span>",
			items:[{
				xtype:'panel',
				items:[{
					xtype:'panel',
					items:[invoicedaterangeform],
					padding:'10px 0px 50px'
				},{
					title:'To get Updated Rules',
					xtype:'panel',
				bbar:[{
					text:'<b><i>Click here....</i></b>',
					handler:function(){
						refreshRules();
					}
				}]
				}]
			}]
		},{
			region:'north',
			height:100,
			layout:'hbox',
			collapsible:false,
			renderTo:'header-logo',
			frame:false,
			bodyStyle: 'opacity:0;'
	        //bodyStyle: "background-image:url(../../images/verizon-wireless-transparent-logo.png) !important"
		}]
	});
	viewport.show();
	Ext.Msg.alert('Welcome', 'Welcome to Tamil Nadu Electricity Board');
});

Ext.override(Ext.Panel, {
     bodyCfg: {
        tag: 'center',
        cls: 'x-panel-header' 
    }
 });

Ext.override(Ext.form.FormPanel, {
     bodyCfg: {
        tag: 'center',
        cls: 'x-panel-header' 
    }
 });