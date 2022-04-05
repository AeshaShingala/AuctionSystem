
console.log("Hello World");


$(document).ready(function() {
	var i = 1
	 var value=$('#id').val();
	$("#add").click(function(e) {
		i++;
		value++;
		event.preventDefault();
		$('#items').append(
		'<div class="section"><hr><h4 class="d-inline" >New Student '+i+' </h4>'
		+'<button class="deleteBtn d-inline btn-sm btn-danger" type="button" id="delete"><i class="fa fa-close" style="font-size:18px"></i></button>'
		+'<div class="row g-3">'
			+'<div class="col-sm-4">'
//				+'<label class="form-label" for="id'+i+'">ID:</label>'
				+'<input class="form-control form-control-sm" type="text" id="id'+i+'"value='+value+' name="id" readonly>'
			+'</div>'
			+'<div class="col-sm-4">'
//				+'<label class="form-label" for="name'+i+'">Enter Name:</label>'
				+'<input placeholder="Enter Name" class="form-control form-control-sm" type="text" id="name'+i+'" name="name">'
			+'</div>'
			+'<div class="col-sm-4">'
//				+'<label class="form-label" for="tech'+i+'">Enter Tech:</label>'
				+'<input placeholder="Enter Tech" class="form-control form-control-sm" type="text" id="tech'+i+'" name="tech">'
			+'</div>'
		+'</div>'
		+'</div>');
		$("#items").scrollTop($("#items")[0].scrollHeight);
		
	}
	);
	$('body').on('click','#delete',function(e) {
		$(this).parent('div').remove();
		i--;
		value--;
	});
});