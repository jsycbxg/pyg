 //控制层 
app.controller('itemCatController' ,function($scope,$controller   ,itemCatService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id,pid){
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);
		itemCatService.findList(pid).success(
			function(response){
				$scope.entity.list= response.message;
			}
		);
	}
	$scope.findList=function(id){
		itemCatService.findList(id).success(
			function(response){
				$scope.entity.list= response.message;
				$scope.entity.lists=$scope.entity.list.splice(" >> ");
			}
		);
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			if($scope.grade==1){
				$scope.entity.parentId=0;
			}else if($scope.grade==2){
                $scope.entity.parentId=$scope.entity_1.id;
            }else if($scope.grade==3){
                $scope.entity.parentId=$scope.entity_2.id;
            }
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}else{
                    alert(response.message);
                    $scope.selectIds=[];
                }
			}		
		);				
	}
	$scope.grade=1;
	$scope.setgrade=function(value){
        $scope.grade=value;
	}
	$scope.searchEntity={};//定义搜索对象
	$scope.searchNext=function(pentity){
		$scope.searchEntity={id:pentity.id};
		itemCatService.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage,$scope.searchEntity).success(
			function(response){
					$scope.list=response.rows;
					$scope.paginationConf.totalItems=response.total;//更新总记录数
					if(pentity.id!=0){

                    }else {
                        $scope.grade=1;
					}
					if($scope.grade==1){
						$scope.entity_1=null;
                        $scope.entity_2=null;

                    }else if($scope.grade==2){
                        $scope.entity_1=pentity;
                        $scope.entity_2=null;
					}else if($scope.grade==3){
                        $scope.entity_2=pentity;
                    }


					$scope.reloadList();//重新加载



			}
		);

	}
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	
