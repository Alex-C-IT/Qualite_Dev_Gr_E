$(document).ready(function() {
	$("input:radio").change(function() {
		if ($("input[type='radio'][name='client']:checked").val()=='true'){
			$(":text[name='numClient']").prop('disabled', false);
		} else {
			$(":text[name='numClient']").prop('disabled', true);
		}
	});
});