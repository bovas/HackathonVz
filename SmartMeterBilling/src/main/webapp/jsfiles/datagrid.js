
var myGridStore = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    url: URLCONFIG.loadgrid,
    storeId: 'myGridStore',
    // reader configs
    root: 'usageLineDOs',
    autoLoad: true,
   /*sortInfo:{
    	field:'sm_identifier',
    	direction:'ASC'
    },*/
    fields: ['sm_identifier', 'name', {name:'mr_start', type: 'float'}, {name:'mr_end', type:'float'}, 'st_time', 'end_time', {name:"bill_cost", type:"float"}]
});

var myDataGrid = new Ext.grid.GridPanel({
	itemId:'myDataGrid',
	id:'myDataGrid',
          frame: true,
          title: 'Usage Summary',
          store: myGridStore,
          loadMask: true,
          viewConfig:{
        	  forceFit:true
          },
              columns: [
                  {header: "<b>Smart Meter ID</b>", dataIndex: 'sm_identifier', sortable:true, resizable:true},
                  {header: "<b>Name</b>", dataIndex: 'name', sortable:true, resizable:true, hidden:true},
                  {header: "<b>Meter Reading - Start</b>", dataIndex: 'mr_start', sortable:true, resizable:true},
                  {header: "<b>Start Time</b>", dataIndex: 'st_time', sortable:true, resizable:true/*, renderer: function(value, metaData, record, rowIndex, colIndex, store) { return Ext.util.Format.date(value, 'm/d/Y')}*/},
                  {header: "<b>End Time</b>", dataIndex: 'end_time', sortable:true, resizable:true/*, renderer: function(value, metaData, record, rowIndex, colIndex, store) { return Ext.util.Format.date(value, 'm/d/Y')}*/},
                  {header: "<b>Meter Reading - End</b>", dataIndex: 'mr_end', sortable:true, resizable:true},
                  {header: "<b>Cost (in Rs)</b>", dataIndex: 'bill_cost', sortable:true, resizable:true, hidden:true}
              ],
              listeners:{
            	'cellclick':function(iView, iCellEl, iColIdx, iRecord, iRowEl, iRowIdx, iEvent){
            		
            		var record=myGridStore.getAt(iCellEl);
            		var smartId='';
            		if(record){
            			smartId=record.json.sm_identifier;
            		 }
            		console.log(smartId);
            		if(Ext.getCmp('INVOICE_ID')){
            			Ext.getCmp('INVOICE_ID').setValue(smartId);
            		}
            	}  
              },
             tools:[{
            	 id:'refresh',
            	 handler: function(obj){
            		 myDataGrid.getStore().reload();
            		 myDataGrid.getView().refresh(true);
            	 }
             }]
                      });