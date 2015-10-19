jsf.ajax.addOnError(function(data) {
	// This shows how to get the information via XPath, but it is not required.
	// The error name can be found using data.errorName
	var errorName = data.responseXML.evaluate('//error/error-name',
			data.responseXML, null, XPathResult.STRING_TYPE, null);
	var message = 'AJAX Exception';
	message += '\nSource: ' + data.source.id;
	message += '\nValue:' + data.source.value;
	message += '\nError: ' + errorName.stringValue;
	message += '\nMessage: ' + data.errorMessage;
	alert(message);
	// TODO Take Additional actions.
});

jsf.ajax.addOnEvent(function(data) {
	alert(data.source.id + " " + data.type + " " + data.status);
});

function handleAjax(data) {
	var status = data.status;
	switch (status) {
	case "begin":
		// This is the start of the AJAX request.
		// TODO Take action here.
		break;
	case "complete":
		// This is invoked right after AJAX response is returned.
		// TODO Take action here.
		break;
	case "success":
		// This is invoked right after successful processing of AJAX response
		// and update of HTML DOM.
		alert("The AJAX responseCode was:  " + data.responseCode);
		// TODO Take Additional actions.
		break;
	}
}
