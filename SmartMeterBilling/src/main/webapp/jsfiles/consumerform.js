var consumerform = new Ext.form.FormPanel({
	title:'Add New TNEB Consumer',
	border: true,
	items:[{
		xtype: 'numberfield',
		name:'sm_identifier',
		fieldLabel: '<b>Smart Meter ID</b>',
		allowBlank:false,
		maxLength: 10,
		minLength: 4
	},{
		xtype: 'textfield',
		name:'firstname',
		fieldLabel: '<b>First Name</b>',
		allowBlank:false,
		maxLength: 15,
		minLength: 4,
		vtype:'alpha'
	},{
		xtype: 'textfield',
		name:'lastname',
		fieldLabel: '<b>Last Name</b>',
		allowBlank:false,
		maxLength: 15,
		minLength: 4,
		vtype:'alpha'
	},{
		xtype: 'textfield',
		name:'address1',
		fieldLabel: '<b>Address Line 1</b>',
		allowBlank:false,
		maxLength: 20,
		minLength: 4,
		vtype:'alphanum'
	},{
		xtype: 'textfield',
		name:'address2',
		fieldLabel: '<b>Address Line 2</b>',
		allowBlank:true,
		maxLength: 20,
		minLength: 4,
		vtype:'alphanum'
	},{
		xtype: 'textfield',
		name:'city',
		fieldLabel: '<b>City</b>',
		allowBlank:false,
		maxLength: 10,
		minLength: 2,
		vtype:'alpha'
	},{
		xtype: 'numberfield',
		name:'pincode',
		fieldLabel: '<b>Pin Code</b>',
		allowBlank:false,
		maxLength: 6,
		minLength: 6
	}],
	 bbar: [{
                text: '<b><i>Submit</i></b>',
                type: 'submit',
                ctCls:'x-btn-small x-btn-icon-small-left',
                handler: function(){
                    var form = consumerform.getForm();
                    if(form.isValid())
                        form.submit({
                            waitMsg:'Loading...',
                            url: URLCONFIG.formsubmit,
                            success: function(fm,action) {
                            	Ext.Msg.alert('Success', 'Profile has been created Successfully');
                            	form.reset();
                            },
                            failure: function(form,action){
                            	console.log('Failure action',action)
                                Ext.MessageBox.alert('Erro',action.result.success);
                            }
                        });
                }
            },{xtype:'tbfill'},{
                	text:'<b><i>Reset</i></b>',
                	ctCls:'x-btn-small x-btn-icon-small-left',
                	handler:function(){
                		consumerform.getForm().reset();
                	}
                }]
});