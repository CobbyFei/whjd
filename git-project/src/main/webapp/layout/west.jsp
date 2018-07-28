<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	/**
	 * tree组件的初始化
	 */
	var ctrlTree;
	$(function() {
		ctrlTree = $('#layout_west_tree').tree({
			// 填写好url后会到后台提取数据
			url : '${pageContext.request.contextPath}/menuAction!doNotNeedSession_treeRecursive.action',//
			parentField : 'pid',
			lines : true, // +、-还是箭头
			onClick : function(node) {
				addTabs(node);
			},
			onDblClick : function(node) {
				if (node.state == 'closed') {
					$(this).tree('expand', node.target);
				} else {
					$(this).tree('collapse', node.target);
				}
			}
		});
	});
</script>

<div class="easyui-accordion" data-options="fit:true, border:false">
	<div title="系统菜单" data-options="iconCls:'icon-save',tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					ctrlTree.tree('reload');
				}
			}, {
				iconCls : 'icon-redo',
				handler : function() {
					var node = ctrlTree.tree('getSelected');
					if (node) {
						ctrlTree.tree('expandAll', node.target);
					} else {
						ctrlTree.tree('expandAll');
					}
				}
			}, {
				iconCls : 'icon-undo',
				handler : function() {
					var node = ctrlTree.tree('getSelected');
					if (node) {
						ctrlTree.tree('collapseAll', node.target);
					} else {
						ctrlTree.tree('collapseAll');
					}
				}
			} ]">
		<ul id="layout_west_tree" style="margin-top:5px;"></ul>
	</div>
	<!-- <div title="Title2" data-options="iconCls:'icon-reload'" style="padding:10px;"></div> -->
</div>
