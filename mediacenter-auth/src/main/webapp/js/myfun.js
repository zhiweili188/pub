function getCheckedData(grid){
	var rows = grid.getCheckedRows();  
    var str = "";  
    $(rows).each(function ()  
    {  
        str += this.id + ",";  
    });
    str = str.substring(0, str.length-1);
    //$.ligerDialog.alert('Ñ¡ÔñµÄÊÇ' + str); 
    return str;
}