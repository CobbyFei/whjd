/**
 * 包含easyui的扩展和常用的方法
 * 
 * @author 周建群
 * 
 * @version 20130429
 */

var sy = $.extend({}, sy);/* 定义全局对象，类似于命名空间或包的作用 */

/**
 * 
 * 取消easyui默认开启的parser
 * 
 * 在页面加载之前，先开启一个进度条
 * 
 * 然后在页面所有easyui组件渲染完毕后，关闭进度条
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
/*
$.parser.auto = false;
$(function() {
	$.messager.progress({
		text : '页面加载中....',
		interval : 100
	});
	$.parser.parse(window.document);
	window.setTimeout(function() {
		$.messager.progress('close');
		if (self != parent) {
			window.setTimeout(function() {
				try {
					parent.$.messager.progress('close');
				} catch (e) {
				}
			}, 500);
		}
	}, 1);
	$.parser.auto = true;
});
*/
/**
 * 使panel和datagrid在加载时提示
 * 
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';


/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val(); // param[0] = '#index_regForm
			// input[name=pwd]' -->
			// $('#index_regForm
			// input[name=pwd]')
		},
		message : '密码不一致！'
	},

	mobile : {// 验证手机号码
	    validator: function (value) {
	        return /^(13|15|18)\d{9}$/i.test(value);
	    },
	    message: '手机号码格式不正确 '
	},

	numberOnly : {

		validator : function(value){
			
			return /^[1-9]+\d*$/i.test(value);
		},
		message: '请输入正确的号码'
	},

	idcard : {
		validator : function(value){
			
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},

		message : '身份证号码格式不正确'
	}
	
});


/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 * 
 * 解析树形扁平的树形结构，转换成EasyUI可以识别的tree的JSON结构
 * @param data
 * @param parent
 */
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};


/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;


/**
 * @author 周建群
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
sy.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};


/**
 * @author 周建群
 * 
 * 获得项目根路径
 * 
 * 使用方法：sy.bp();
 * 
 * @returns 项目根路径
 */
sy.bp = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

/**
 * @author 周建群
 * 
 * 增加formatString功能
 * 
 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
sy.fs = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 * @param options
 */
sy.dialog = function(options) {
	var opts = $.extend({
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	return $('<div/>').dialog(opts);
};

/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 * @param title 标题
 * @param msg 提示信息
 * @param fun 回调方法
 */
sy.messagerConfirm = function(title, msg, fn) {
	return $.messager.confirm(title, msg, fn);
};

/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 */
sy.messagerAlert = function(title, msg, icon, fn) {
	return $.messager.alert(title, msg, icon, fn);
};

/**
 * @author 周建群
 * 
 * @requires jQuery,EasyUI
 */
sy.messagerShow = function(options) {
	return $.messager.show(options);
};


/**
 * @author 周建群
 * 
 * 加载页面的效果
 * 
 * 使用方法：loadDataWithTime(时间间隔, 时间);
 * 
 */
function loadDataWithTime(timeInterval, totalTime) {
	$.messager.progress({
		text : '页面加载中....',
		interval : timeInterval
	});
	window.setTimeout(function() {
		try {
			$.messager.progress('close');
		} catch (e) {
		}
	}, totalTime);
};
