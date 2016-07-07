# AndroidJSSDKDialog


AndroidJSSDKDialog是基于native和js通讯协议AndroidJSSDKCore实现的Dialog模块。

## 使用方式


* 简单dialog对话框提示

```javascript
ITOMIX.Dialog.normalDialog(JSON.stringify({'title': 'title','content': 'content','positiveText': 'Sure','negativeText': 'Cancel'}), function(params){ITOMIX.Common.toast(JSON.stringify({'content':JSON.stringify(params)}), function(){});});
```
 