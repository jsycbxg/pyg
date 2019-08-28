 //控制层 
app.controller('sellerController' ,function($scope,$controller   ,sellerService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		sellerService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		sellerService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		if (r != null) return unescape(r[2]); return null; //返回参数值
	}
	var loginname=getUrlParam("loginname");
	//查询实体 
	$scope.findOne=function(){
		sellerService.findOne(loginname).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function() {
		$scope.save = function () {
			var serviceObject;//服务层对象
			if(loginname=""){
				serviceObject = sellerService.add($scope.entity);//增加
				serviceObject.success(
					function (response) {
						if (response.success) {
							//重新查询
							location.href = "/shoplogin";
						} else {
							alert(response.message);
						}
					}
				);
			}else{
				serviceObject = sellerService.update($scope.entity);//增加
				serviceObject.success(
					function (response) {
						if (response.success) {
							//重新查询
							location.href = "/admin/home";
						} else {
							alert(response.message);
						}
					}
				);
			}

		}
	}
	
	 
	//批量删除 
	$scope.password=function(){
		//获取选中的复选框
		var serviceObject;//服务层对象
		if($scope.new.p1==$scope.new.p2){
			sellerService.findOne(loginname).success(

				function(res){
					if(res.password==$scope.entity.password){
						$scope.entity={};
						$scope.entity.password=$scope.new.p1;
						$scope.entity.sellerId=loginname;
						serviceObject = sellerService.update($scope.entity);//增加
						serviceObject.success(
							function (response) {
								if (response.success) {
									//重新查询
									location.href = "/admin/home";
								} else {
									alert(response.message);
								}
							}
						);
					}else {
						alert("原密码不匹配");
					}

				}
			)
		}else {
			alert("两次密码不一致");

		}

		};

	$scope.change=function(){
		//获取选中的复选框
		sellerService.change().success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}
			}
		);
	}
	
	$scope.searchEntity={};//定义搜索对象 
	$scope.new={};//定义搜索对象
	//搜索
	$scope.search=function(page,rows){			
		sellerService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});
