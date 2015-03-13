function Action(config){
	this.config={
		type: 'POST',
		url: "",
		params: "",
		dataType:"json",
		callback:function(result){}
	};
	if (config) $.extend(this.config, config);
	
	this.setUrl = function(url){
		this.config.url=url;
	};
	
	this.getUrl = function(){
		return this.config.url;
	};
	
	this.setParams = function(params){
		this.config.params=params;
	};
	
	this.getParams = function(){
		return this.config.params;
	};
	
	this.setDataType = function(dataType){
		this.config.dataType=dataType;
	};
	
	this.getDataType = function(){
		return this.config.dataType;
	};
	
	this.setCallback = function(callback){
		this.config.callback=callback;
	};
	
	this.getCallback = function(){
		return this.config.callback;
	};
	
	this.setType = function(type){
		this.config.type=type;
	};
	
	this.getType = function(){
		return this.config.type;
	};
	
	this.ajaxPostData=function(param){
		$.ajax({
			  type: this.getType(),
			  url: this.getUrl(),
			  data: this.getParams(),
			  success: this.getCallback(),
			  dataType: this.getDataType()
			});
	};
	
}