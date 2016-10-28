
<table class="table table-striped" >
	<tr>
		<th>序号</th>
		<th>名字</th>
		<th>年龄</th>
		<th>性别</th>
		<th>操作</th>
	</tr>
	
	<tr ng-repeat="user in  users">
		<td>{{$index+1}}</td>
		<td>{{user.name}}</td>
		<td>{{user.age}}</td>
		<td>{{user.sex}}</td>
		<td>
			<span title="删除" ng-click="delete(user.id,user.name)" style="cursor: pointer" class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;&nbsp;
			<span title="修改" ng-click="edit(user)" style="cursor: pointer" class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;&nbsp;
			<span title="详情" ng-click="detail(user.id)" style="cursor: pointer" class="glyphicon glyphicon-list"></span>
		</td>
	</tr>
</table>
