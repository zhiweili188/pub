/**
 * 关联多选框，使用于全选多选框
 * @param  allSelectClass
 * @param  otherSelectClass
 */
function selectAllCheckBox(headCheckBox, dataCheckBox) {

    	if($(headCheckBox).attr("checked")){
    		$(dataCheckBox + ":not(:disabled)").attr("checked", "checked");
    	} else {
    		$(dataCheckBox + ":not(:disabled)").attr("checked", "");
    	}
        
    $(dataCheckBox).change(function() {
        if ($(dataCheckBox + ":checked").length == $(dataCheckBox).length) {
            $(headCheckBox).attr("checked", true);
        } else {
            $(headCheckBox).attr("checked", false);
        }
    });
}

/**
 * 获取多选框的值
 * @param  dataCheckBox
 */
function getCheckBoxValues(dataCheckBox) {
    var ids = "";
    $(dataCheckBox + ":checked").each(function() {
        ids += this.value + ",";
    });
    if (ids.length > 0) {
        ids = ids.substring(0, ids.length - 1);
    }
    return ids;
};

function getCheckBoxValuesArray(dataCheckBox) {
    var arrayObj = new Array();
    $(dataCheckBox + ":checked").each(function() {alert(this.value);
    	arrayObj.push(this.value);
    });
 
    return arrayObj;
};

function getCheckedData(gridObj)
{
    var rows = gridObj.getCheckedRows();
    var str = "";
    $(rows).each(function ()
    {
        str += this.id + ",";
    });
    if (str.length > 0) {
    str = str.substring(0, str.length-1);
    }
    return str;
}