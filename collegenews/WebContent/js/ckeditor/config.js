/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	 config.language = 'zh-cn';//设置语言环境
	 config.skin = 'kama';//设置皮肤 默认三种选项 kama v2 office2003
	 config.uiColor = '#103864';//设置ui颜色(和皮肤有冲突)
	 config.width=590;
	 config.height=180;
	 config.toolbar='Full';
	 //自定义工具栏'-'表示为'|'分割线,每组元素使用[],隔开; '/'换行标记
	 config.toolbar_Full=[
	                      ['Save','-','Copy','Paste','Cut'],
	                      ['JustifyLeft','JustifyCenter','JustifyRight'],
	                      ['Bold','Italic','Underline','Strike'],['TextColor','BGColor'],['Format','Font','FontSize'],['Smiley']
	                      ];
	 //设置表情的文件数组
	 config.smiley_images =[
	                        '1.gif','2.gif','3.gif','4.gif','5.gif',
	                        '6.gif','7.gif','8.gif','9.gif','10.gif',
	                        '11.gif','12.gif','13.gif','14.gif','15.gif',
	                        '16.gif','17.gif','18.gif','19.gif','20.gif',
	                        '21.gif','22.gif','23.gif','24.gif','25.gif',
	                        '26.gif','27.gif','28.gif','29.gif','30.gif',
	                        ];	
	 //设置表情列数
	 config.smiley_columns = 10;
	 config.resize_enabled = false;
};
