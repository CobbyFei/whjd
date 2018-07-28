<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" charset="UTF-8">
	var centerTabs;
	var tabsMenu;
	$(function() {
	    
	  
		// 设置页签
		centerTabs = $('#layout_center_tabs').tabs({
			fit : true,
			border : false,
			// Fires when a tab panel is right clicked
			onContextMenu : function(e, title) {
				e.preventDefault();
				// tabsMenu是右击菜单，在下面初始化
				tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title); // 关键是将title设置到menu中去
			}
		});
        
		// 这个是页签右击显示的内容
		tabsMenu = $('#tabsMenu').menu({
			onClick : function(item) {
				// data是jQuery的方法，用于获取键所对应的值
				// 这里的tabTitle是在
				var curTabTitle = $(this).data('tabTitle');

				// 获取目标页面的类型,type是属性
				var type = $(item.target).attr('type');

				if (type === 'refresh') {
					refreshTab(curTabTitle);
					return;
				}

				if (type === 'close') {
					var t = centerTabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						centerTabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = centerTabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					centerTabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
	});

	/**
	 * 增加标签页
	 */
	function addTabs(node) {
		if (centerTabs.tabs('exists', node.text)) {
			centerTabs.tabs('select', node.text);
		} else {
			if (node.attributes.url && node.attributes.url.length > 0) {
				if (node.attributes.url.indexOf('!druid.action') < 0) {/*数据源监控页面不需要开启等待提示*/
					// 这句话是用于显示加载的进度条的
					/* loadDataWithTime(100, 1000); */
				}
				/**
				 * 下面是将页面加载到中央面板上
				 * 1.设置标题，作用是判断该页面是否已经打开了，title的作用非常大
				 * 2.设置该页面是否可以关闭
				 * 3.该方法会用title作为判断的依据，看一个页面是否已经被打开
				 */
				centerTabs.tabs('add', {
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					// href : '${pageContext.request.contextPath}' + node.attributes.url,
					content : '<iframe src="${pageContext.request.contextPath}' + node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
					tools : [ {
						// 加一个迷你的刷新图标
						iconCls : 'icon-mini-refresh',
						handler : function() {
							refreshTab(node.text);
						}
					} ]
				});
			
			} else {
				// 发生错误时处理的页面
			}
		}
	}

	function refreshTab(title) {
		// 加入一定的加载时间
		/* loadDataWithTime(100, 500); */
		var tab = centerTabs.tabs('getTab', title);
		centerTabs.tabs('update', {
			tab : tab,
			options : tab.panel('options')
		});
	}
</script>

<div id="layout_center_tabs" class="easyui-tabs" data-options="border: false" style="overflow: hidden;">
	<div title='首页'></div>
</div>
<div id="tabsMenu" style="width: 120px;display:none;">
	<div type="refresh">刷新</div>
	<div class="menu-sep"></div>
	<div type="close">关闭</div>
	<div type="closeOther">关闭其他</div>
	<div type="closeAll">关闭所有</div>
</div>