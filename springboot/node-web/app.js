var express = require('express');
var zookeeper  = require('node-zookeeper-client');  
var httpProxy = require('http-proxy');

var PORT = 1234;
var addr = '192.168.56.134:2181';
var options = {
		sessionTimeout:5000
};
var REGISTRY_ROOT = '/registry';

var zk = zookeeper.createClient(addr,options);
zk.on('connected',function(){
	//console.log(zk);
	//zk.close();
});
zk.connect();

//创建代理服务器对象并监听错误事件
var proxy = httpProxy.createProxyServer();
proxy.on('error',function(err,req,res){
	res.end();//输出空白响应数据
})

//启动web服务器
var app = express();
app.use(express.static('public'));


app.all('*',function(req,res){
	if(req.path=='/favicon.ico'){
		res.end();
		return;
	}
	console.log(req.path);
	var serviceName=req.path;
	console.log('Service-Name: %s',serviceName);
	var servicePath = REGISTRY_ROOT+serviceName;
	zk.getChildren(servicePath,function(error,children,stat){
		if(error){
			console.log(error.stack);
			res.end();
			return;
		}
		var size = children.length;
		if(size==0){
			console.log('address node is not exist');
			res.end();
			return;
		}
		var addressPath = servicePath+'/';
		if(size == 1){
			addressPath+=children[0];
		}else{
			addressPath+=children[parseInt(Math.random()*size)];
		}
		console.log('addressPath: %s',addressPath);
		zk.getData(addressPath,function(error,serviceAddress){
			if(error){
				console.log(error.stack);
				res.end();
				return;
			}
			console.log('serviceAddress: %s',serviceAddress);
			if(!serviceAddress){
				console.log('serviceAddress node is not exist');
				res.end();
				return;
			}
			proxy.web(req,res,{
				target:'http://'+serviceAddress  //目标地址
			});
		});
	});

});
app.listen(PORT,function(){
	console.log("server is running at %d",PORT);
});