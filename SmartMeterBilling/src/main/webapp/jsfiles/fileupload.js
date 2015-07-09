var bulkuploadform= new Ext.form.FormPanel({
                title:'Upload Usage Data',
				fileUpload: true,
                autoHeight: true,
                bodyStyle: 'padding: 10px 10px 10px 5px;',
                labelWidth: 50,
                defaults: {
                    //anchor: '55%',
                    allowBlank: false,
                    msgTarget: 'side'
                },
                items:[
                {
                    xtype: 'fileuploadfield',
                    id: 'filedata',
                    name:'fileUploadPath',
                    emptyText: 'Select a document to upload...',
                    fieldLabel: '<b>File</b>',
                    buttonText: 'Browse'
                },{
                	xtype:'displayfield',
                	value:'<i>Accepts .csv , .xls and .xlsx files.</i>',
                	hideLabel: true
                }],
                bbar: [{
                	ctCls:'x-btn-small x-btn-icon-small-left',
                	text: '<b><i>Upload</i></b>',
                    handler: function(){
                        if(bulkuploadform.getForm().isValid()){
                        	if(Ext.getCmp('filedata')){
                        		var fileName=Ext.getCmp('filedata').getValue();
                        		var validFlag=false;
                        		var resArr = fileName.split(".");
                        		var validExtn=['xls','xlsx','csv','txt'];
                        		var incExtn=resArr[resArr.length-1];
                        		for(var i=0;i<validExtn.length;i++){
                        			if(incExtn==validExtn[i]){
                        				validFlag=true;
                        				break;
                        			}
                        		}
                        		
                        		if(!validFlag){
                        			Ext.Msg.alert('Invalid File Extension', 'Please upload a valid file.');
                        			bulkuploadform.getForm().reset();
                        		return;
                        		}
                        	}
                            bulkuploadform.getForm().submit({
                                url: URLCONFIG.fileupload,
                                waitMsg: 'Uploading file...',
                                success: function(form,action){
                                	try{
                                		var succesStr=action.response.responseText.indexOf('"success":true');
                                		if(succesStr<0){
                                			return;
                                		}
                                	}catch(e){
                                		
                                	}
                                	
                                    Ext.Msg.alert('Success', 'Processed file on the server');
                                    bulkuploadform.getForm().reset();
                                    setTimeout(function(){
                                    	if(Ext.getCmp('myDataGrid')){
                                    		var mygrid=Ext.getCmp('myDataGrid');
                                    		mygrid.getStore().reload();
                                    		mygrid.getView().refresh(true);
                                    	}
                                    },3000);
                                },
                                failure:function(form,action){
                                	try{
                                		var succesStr=action.response.responseText.indexOf('"success":true');
                                		if(succesStr<0){
                                			return;
                                		}
                                			 Ext.Msg.alert('Success', 'Processed file on the server');
                                             bulkuploadform.getForm().reset();
                                             setTimeout(function(){
                                             	if(Ext.getCmp('myDataGrid')){
                                             		var mygrid=Ext.getCmp('myDataGrid');
                                             		mygrid.getStore().reload();
                                             		mygrid.getView().refresh(true);
                                             	}
                                             },3000);
                                	}catch(e){
                                		
                                	}
                                }
                            });
                        }
                    }
                },{xtype:'tbfill'},{
                	text:'<b><i>Reset</i></b>',
                	padding:'0px 30px 0px 0px',
                	ctCls:'x-btn-small x-btn-icon-small-left',
                	handler:function(){
                			 bulkuploadform.getForm().reset();
                	}
                }]
            });
