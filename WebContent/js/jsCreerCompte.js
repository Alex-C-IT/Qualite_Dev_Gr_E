$(document).ready(function() {
	$(":text[name='decouvertAutorise']").prop('disabled', true);
	$("input:radio").change(function() {
		if ($("input[type='radio'][name='avecDecouvert']:checked").val()=='true'){
			$(":text[name='decouvertAutorise']").prop('disabled', false);
		} else {
			$(":text[name='decouvertAutorise']").prop('disabled', true);
		}
	});
});