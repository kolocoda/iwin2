var basePath = 'http://68.169.52.145:8080/sandbox/ecommerce';
var defaults;
var strVar="";
strVar += "<div class=\"modal fade\" id=\"pay-with-onpos-dialog\">";
strVar += "	<div class=\"modal-dialog\">";
strVar += "		<div class=\"modal-content\">";
strVar += "			<div class=\"modal-header\">";
strVar += "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\"";
strVar += "					aria-label=\"Close\">";
strVar += "					<span aria-hidden=\"true\">&times;<\/span>";
strVar += "				<\/button>";
strVar += "				<h4 class=\"modal-title\">";
strVar += "				<img border=\"0\" src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/header.png\"><\/h4>";
strVar += "			<\/div>";
strVar += "			<div style=\"padding-left:30px; padding-top:20px; padding-right:20px\">";
strVar += "			<p align=\"center\">";
strVar += "			<font color=\"#2187BD\">You will receive an SMS and email notification after you have filled the form below. ";
strVar += "			<br>Your OnPOS Payment Reference (OPR) can be paid via:<\/font><font color=\"#808080\"><br>";
strVar += "			<\/font><br>";
strVar += "			<img class=\"responsive\" border=\"0\" alt=\"Pay with Cards, internet-Banking, Quickteller or at the Bank (NIBSS)\" title=\"Pay OPR with Cards, Internet Banking, Quickteller, Interswitch or at the Bank (NIBSS)\" ";
strVar += "			src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/payment_methods.jpg\" width=\"350\" height=\"92\">";
strVar += "		    <\/div>";
strVar += "		    	<hr>";
strVar += "			<form class=\"form-horizontal\" role=\"form\" name=\"onposPay\"";
strVar += "				action=\"\"  method=\"POST\">";
strVar += "				<div class=\"modal-body\">";
strVar += "					<div class=\"row \">";
strVar += "						<div class=\"form-row clearfix\">";
strVar += "							<div class=\"form-group col-sm-12\">";
strVar += "								<label for=\"text\"";
strVar += "									class=\"col-sm-4 col-xs-12  control-label required-l\">";
strVar += "								<font color=\"#999999\">Enter Your Name:<\/font><span style=\"color:red\">*<\/span> <\/label>";
strVar += "								<div class=\"col-sm-8 col-xs-12\">";
strVar += "									<input autocomplete='off' class='form-control card-cvc'";
strVar += "										placeholder=\"Name\" size='4' name=\"customerName\" id=\"customerName\" onchange=\"clearMsg('name')\"";
strVar += "										type='text' required onkeypress=\"return isText(event)\">";
strVar += "										<span id=\"namespan\" class=\"text-danger\"\/>";
strVar += "								<\/div>";
strVar += "							<\/div>";
strVar += "							<div class=\"form-group col-sm-12\">";
strVar += "								<label for=\"text\"";
strVar += "									class=\"col-sm-4 col-xs-12  control-label required-l\">";
strVar += "								<font color=\"#999999\">Enter Your Phone No:<\/font><span style=\"color:red\">*<\/span> <\/label>";
strVar += "								<div class=\"col-sm-8 col-xs-12\">";
strVar += "									<input autocomplete='off' class='form-control card-cvc'";
strVar += "										placeholder=\"Phone\" size='4' name=\"customerPhone\" onchange=\"clearMsg('phone')\"";
strVar += "										id=\"customerPhone\" type='text' required title=\"enter a valid phone number\"";
strVar += "										pattern=\"[\+0-9]{11,15}\" onkeypress=\"return isNumber(event)\">";
strVar += "										<span id=\"phonespan\" class=\"text-danger\"><\/span>";
strVar += "								<\/div>";
strVar += "							<\/div>";
strVar += "							<div class=\"form-group col-sm-12\">";
strVar += "								<label for=\"text\"";
strVar += "									class=\"col-sm-4 col-xs-12  control-label required\">";
strVar += "								<font color=\"#999999\">Enter Your Email:<\/font><\/label>";
strVar += "								<div class=\"col-sm-8 col-xs-12\">";
strVar += "									<input autocomplete='off' class='form-control card-cvc'";
strVar += "										placeholder=\"Email\" size='4' name=\"customerEmail\" onchange=\"clearMsg('email')\"";
strVar += "										id=\"customerEmail\" type='email' title=\"Please enter a valid email\"";
strVar += "										> ";
strVar += "										<span id=\"emailspan\" class=\"text-danger\"><\/span>";
strVar += "								<\/div>";
strVar += "							<\/div>	";
strVar += "						<\/div>";
strVar += "						<!--  Any other sh** goes here -->";
strVar += "						<div class=\"form-group col-sm-12\">";
strVar += "							<div class=\"col-sm-6 col-xs-12 control-label\">";
strVar += "								<span class=\"verify-img text-center\" id=\"txtCaptchaDiv\" style=\"padding:5px; font-family:cursive; font-size:2.0em; color:#ff6600\"><\/span> &nbsp;&nbsp;";
strVar += "							    <button  style=\"margin-top:3px\" type=\"button\" onclick=\"reloadCaptcha()\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-refresh fa-2x\" id=\"spinnerId\"><\/i><\/button>";
strVar += "							   ";
strVar += "							<\/div>							";
strVar += "							<div class=\"col-sm-6 col-xs-12\">";
strVar += "							<input type=\"hidden\" id=\"txtCaptcha\" \/>";
strVar += "							<input autocomplete='off' class='form-control card-cvc' style=\"margin-top:15px\"";
strVar += "										placeholder=\"Enter verification code\" size='4' name=\"txtInput\" size=\"10\" onchange=\"clearMsg('code')\"";
strVar += "										id=\"txtInput\" type='text' title=\"Enter verification text\" onkeypress=\"return isNumber(event)\">";
strVar += "									<span id=\"verifyspan\" class=\"text-danger\"><\/span>";
strVar += "							<\/div>";
strVar += "					<\/div>					";
strVar += "				<\/div>";
strVar += "			<div class=\"modal-footer\">";
strVar += "			<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel<\/button>&nbsp";
strVar += "			<span class=\"but-wrap\" style=\"margin-right:22px\"><button type=\"button\" onclick=\"validateInputs()\" id=\"sendRequestButton\" class=\"but but-pill but-raised but-primary\">Proceed<\/button><\/form>";
strVar += "			<\/span><hr class=\"\" color=\"#FF6600\">";
strVar += "			<span class=\"pull-left\"><br>&nbsp;<\/span><table border=\"0\" width=\"auto\">";
strVar += "				<tr>";
strVar += "					<td width=\"57\">";
strVar += "					<img border=\"0\" src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/logo_footer.jpg\" width=\"63\" height=\"48\" alt=\"Footer Logo\"><\/td>";
strVar += "					<td width=\"879\">";
strVar += "					<p style=\"margin-left: 7px\"><span class=\"pull-left\"><font color=\"#999999\">Powered by <\/font> <a href=\"http:\/\/www.onpos.com.ng\">";
strVar += "			<font color=\"#999999\">www.onpos.com.ng<\/font><\/a><\/span><br>";
strVar += "					<font color=\"#808080\">";
strVar += "					<a target=\"_blank\" href=\"http:\/\/www.onpos.com.ng\/site\/disclaimer.html\">";
strVar += "					<font color=\"#808080\">disclaimer<\/font><\/a> |";
strVar += "					<a target=\"_blank\" href=\"http:\/\/www.onpos.com.ng\/site\/terms.html\">";
strVar += "					<font color=\"#808080\">terms<\/font><\/a> |";
strVar += "					<a target=\"_blank\" href=\"http:\/\/www.onpos.com.ng\/site\/privacy.html\">";
strVar += "					<font color=\"#808080\">privacy<\/font><\/a><\/font><\/td>";
strVar += "					<span class=\"pull-right\" style=\"margin-right:23px; margin-bottom:6px\">";
strVar += "					<a href=\"http:\/\/facebook.com\/onposng\" target=\"_blank\"> <img border=\"0\" src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/fb.jpg\" width=\"35\" height=\"34\"><\/a>&nbsp;";
strVar += "					<a href=\"http:\/\/twitter.com\/onposng\" target=\"_blank\"> <img border=\"0\" src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/tw.jpg\" width=\"35\" height=\"34\"><\/a>&nbsp;";
strVar += "					<a href=\"http:\/\/onpos.com.ng\/youtube\" target=\"_blank\"><img border=\"0\" src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/yt.jpg\" width=\"35\" height=\"34\"><\/a>&nbsp;";
strVar += "					<a href=\"http:\/\/onpos.com.ng\/instagram\" target=\"_blank\"><img border=\"0\" src=\"https:\/\/beta.onpos.com.ng\/developer\/resources\/images\/ig.jpg\" width=\"35\" height=\"34\"><\/a>&nbsp;";
strVar += "					<\/span> ";
strVar += "				<\/tr>";
strVar += "			<\/table>";
strVar += "		<\/div>";
strVar += "	<\/div>";
strVar += "<\/div>";
strVar += "<\/div>";
strVar += "";


// Load css and js
var loadedScripts = loadedScripts || {};
initScriptAndCss();

function initScriptAndCss(){
	var modalCss = {
		btstrp: 'https://beta.onpos.com.ng/developer/resources/css/bootstrap.min.css',
		btstrpthm: 'https://beta.onpos.com.ng/developer/resources/css/bootstrap-theme.min.css',
		fntawsm: 'https://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.css',
		extra: 'https://beta.onpos.com.ng/developer/resources/css/extras.min.css',				
	}
	
	var modalScripts = {
		btstrp: 'https://beta.onpos.com.ng/developer/resources/js/bootstrap.min.js',
		jqry: 'http://code.jquery.com/jquery-2.2.1.min.js',
	}
		
	for(var key in modalCss) {
        if(modalCss.hasOwnProperty(key)) {
			cssLoad(modalCss[key]);
		}
	}
	
	for(var key in modalScripts) {
        if(modalScripts.hasOwnProperty(key)) {
			loadjsfile(modalScripts[key], 'js');
		}
	}
}

var modalPop = (function() {
    function isValid(defaults) {
        if (defaults.appId == undefined) throw new Error("Please provide your App Id via the appid attribute");
		if (defaults.merchantId == undefined) throw new Error("Please provide your Merchant Id via the merchantid attribute");
		if (defaults.description == undefined) throw new Error("Please provide your item description via the description attribute");
		if (defaults.amountInKobo == undefined) throw new Error("Please provide the amountInKobo via the amountInKobo attribute");
		if (defaults.returnUrl == undefined) throw new Error("Please provide your returnUrl via the returnUrl attribute");
        if (defaults.hash == undefined) throw new Error("Please provide hash ref via the hash attribute");
		if (defaults.test == undefined) throw new Error("Please provide test status via the test attribute");
		

        return true
    }

    return {
        setup: function(options) {
            
            defaults = {
                appId: options.appId,
                merchantId: options.merchantId,
                description: options.description,
                amountInKobo: options.amountInKobo,
                returnUrl: options.returnUrl,
                hash: options.hash,
				test: options.test,
                orderId: options.orderId,                
				customerName:options.customerName || '',
				customerPhone:options.customerPhone || '',
				customerEmail:options.customerEmail || '',
				
            }
            if(options.test == 'false'){
             throw new Error("This script is for test purposes only");
            }
            if (isValid(defaults) && options.test == 'true') {
				openOnposModal();
            };
        }
    }
})();
document.addEventListener("DOMContentLoaded", function(event) { 
  
    console.log( "ready!" );
	div = document.createElement('div');
	div.setAttribute('id', 'onpay');
	div.innerHTML = strVar; 
//	$('body').html($('body').html() + strVar);
	document.body.appendChild(div);
	handleOnWindowChange();
	
});



function openOnposModal(){	
	
	
	//Generates the captcha function
	var a = Math.ceil(Math.random() * 9)+ ' ';
	var b = Math.ceil(Math.random() * 29)+ ' ';
	var c = Math.ceil(Math.random() * 39)+ ' ';
	var d = Math.ceil(Math.random() * 9)+ ' ';
	var e = Math.ceil(Math.random() * 15)+ ' ';

	var code = a + b + c + d + e;
	document.getElementById('txtCaptchaDiv').innerHTML = code;
	document.getElementById('txtCaptcha').value = code;
	
	$('#pay-with-onpos-dialog').on('hidden.bs.modal', function () {
    document.getElementById('namespan').innerHTML = "";
	document.getElementById('phonespan').innerHTML = "";
	document.getElementById('emailspan').innerHTML = "";
	document.getElementById('verifyspan').innerHTML = "";
})
	$('#pay-with-onpos-dialog').modal('show');
	$('#pay-with-onpos-dialog').on('shown.bs.modal', function () {
    $('#customerName').focus();
})
	
}

//handle window change event
handleOnWindowChange();

function handleOnWindowChange(){

$('#pay-with-onpos-dialog').on('show.bs.modal', function() {
	var modal = this;
	var hash = modal.id;
	window.location.hash = hash;
	window.onhashchange = function() {
		if (!location.hash){
			$(modal).modal('hide');
		}
	}
});

$('#pay-with-onpos-dialog').on('hide', function() {
	var hash = this.id;
	history.pushState('', document.title, window.location.pathname);
});
}

function validateInputs(){
	var name = document.getElementById("customerName").value;
	var email = document.getElementById("customerEmail").value;
	var phone = document.getElementById("customerPhone").value;
	var verifyCode = document.getElementById("txtInput").value;
	var ok = false;
	
	if (name == ""){
		document.getElementById('namespan').innerHTML = "Please enter your name";
		ok = false;
		return;
	} else{
		document.getElementById('namespan').innerHTML = "";	
		ok = true;
	}
	if (phone == ""){
		document.getElementById('phonespan').innerHTML = "Please enter your phone number";
		ok = false;
	} else if (!validatePhonenumber(phone)){
		document.getElementById('phonespan').innerHTML = "Please enter a valid phone number";
		ok = false;
	}
	else{
		document.getElementById('phonespan').innerHTML = "";
		ok = true;
	}
	if(email != ""){
		if (!validateEmail(email)){
			document.getElementById('emailspan').innerHTML = "Please enter a valid email";
			ok = false;
		} else{
			document.getElementById('emailspan').innerHTML = "";		
		}
	}
	
	if(verifyCode == ''){
		document.getElementById('verifyspan').innerHTML = "Verification code should not be empty";
		ok = false;
	}
	else if(verifyCode != ''){
			if(ValidCaptcha(verifyCode) == false){
			document.getElementById('verifyspan').innerHTML = 'Verification code did not match';
			ok = false;
		}
	}
	else{
			document.getElementById('verifyspan').innerHTML = '';
				ok = true;
	}
	
	if(ok){
		finalize();
	}	
	
}
//Clear error message
function clearMsg(str){
	if(str == 'name'){
		document.getElementById('namespan').innerHTML = "";
	}
	if(str == 'phone'){
		document.getElementById('phonespan').innerHTML = "";
	}
	if(str == 'email'){
		document.getElementById('emailspan').innerHTML = "";
	}
	if(str == 'code'){
		document.getElementById('verifyspan').innerHTML = "";
	}
}

// Validate the Entered input aganist the generated security code function
function ValidCaptcha(){
	var s = document.getElementById('txtCaptcha').value;
	var s2 = document.getElementById('txtCaptcha').value;	
	var str1 = removeSpaces(s);
	var str2 = removeSpaces(s2);
	if (str1 == str2){
		return true;
	}else{
		return false;
	}
}

// Remove the spaces from the entered and generated code
function removeSpaces(string){
	var s = string;
	return s.split(' ').join('');
}

function reloadCaptcha(){
	//Generates the captcha function
	var a = Math.ceil(Math.random() * 9)+ ' ';
	var b = Math.ceil(Math.random() * 9)+ ' ';
	var c = Math.ceil(Math.random() * 9)+ ' ';
	var d = Math.ceil(Math.random() * 9)+ ' ';
	var e = Math.ceil(Math.random() * 9)+ ' ';

	var code = a + b + c + d + e;
	document.getElementById('txtCaptcha').value = code;
	document.getElementById('txtCaptchaDiv').innerHTML = code;

}
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
function validatePhonenumber(inputtxt)  
    {  
      var phoneno = /^\+?[0-9]{11,15}$/;  
      if(inputtxt.match(phoneno))  
         {  
           return true;  
         }  
       else  
         {  
           return false;  
         }  
    }

function isNumber(evt) {
    var e = window.event || evt;
    var charCode = e.which || e.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57 || charCode > 107 || charCode > 219 ||          charCode > 221) && charCode != 40 && charCode != 32 && charCode != 41 && (charCode < 43 || charCode > 46)) {
        if (window.event) //IE
            window.event.returnValue = false;
        else //Firefox
            e.preventDefault();
    }
    return true;

}

function isText(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 32 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)) {
        return false;
    }
    return true;;
}	

function finalize(){
	defaults.customerEmail = document.getElementById("customerEmail").value;
	defaults.customerName = document.getElementById("customerName").value;
	defaults.customerPhone = document.getElementById("customerPhone").value;
	
	post(basePath, defaults);
	
}


function hasDataAttribute(list) {
    var result = false;
    list = Array.prototype.slice.call(list)
    for (key in list) {
        var element = list[key].nodeName;
        if (element.indexOf('param') > -1) result = true;
    }
    return result
}

function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.
    
    var myform = document.createElement("form");
    myform.setAttribute("method", method);
    myform.setAttribute("action", path);
    myform.setAttribute("id", 'paySubmit');
    
    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);
			console.log("name: "+key + " " + "value: " + params[key]);
            myform.appendChild(hiddenField);
         }
    }

    document.body.appendChild(myform);
    myform.submit();
    
}

function cssLoad(url, callback) {
    var promise,
        resolutions = [],
        rejections = [],
        resolved = false,
        rejected = false,
        count, id, urlString;

    this.count = (this.count) ? ++this.count : 1;
    count = this.count;
    urlString = url.split('/');
    id = 'load-css-' + urlString[urlString.length - 1];

    promise = {
        done: function(callback) {
            resolutions.push(callback);
            if (resolved) callback();
            return promise;
        },
        fail: function(callback) {
            rejections.push(callback);
            if (rejected) callback();
            return promise;
        }
    };

    function resolve() {
        resolved = true;
        for (var i = 0, len = resolutions.length; i < len; i++) resolutions[i]();
    }

    function reject() {
        rejected = true;
        for (var i = 0, len = rejections.length; i < len; i++) rejections[i]();
    }

    var link = document.createElement('link');
    link.setAttribute('id', id);
    link.setAttribute('rel', 'stylesheet');
    link.setAttribute('type', 'text/css');
    if (typeof link.addEventListener !== 'undefined') {
        link.addEventListener('load', resolve, false);
        link.addEventListener('error', reject, false);
    } else if (typeof link.attachEvent !== 'undefined') {
        link.attachEvent('onload', function() {
            // IE 8 gives us onload for both success and failure
            // and also readyState is always "completed", even
            // for failure.  The only way to see if a stylesheet
            // load failed from an external domain is to try and
            // access its cssText, and then catch the error
            // ... sweet :/
            var txt, cur, i = document.styleSheets.length;
            try {
                while (i--) {
                    cur = document.styleSheets[i];
                    if (cur.id === id) {
                        txt = cur.cssText;
                        resolve();
                        return;
                    }
                }
            } catch (e) {}
            if (!resolved) {
                reject();
            }
        });
    }
    if (loadedScripts[id] == undefined) {
        loadedScripts[id] = true;
        document.getElementsByTagName('head')[0].appendChild(link);
        link.setAttribute('href', url);
        return promise;
    } else {
        return true
    }
}

function loadjsfile(filename, filetype){
    if (filetype=="js"){ //if filename is a external JavaScript file
        var fileref=document.createElement('script')
        fileref.setAttribute("type","text/javascript")
        fileref.setAttribute("src", filename)
    }
    if (typeof fileref!="undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}