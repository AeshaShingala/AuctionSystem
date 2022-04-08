
console.log("Hello World");


$(document).ready(function() {
	
	let number = 1;
	var value=$('#id').val();
	$("#add").click(function(e) {
		number++;
		value++;
		event.preventDefault();
		$('#repeat').append(
				'<div id="lot'+number+'">'+
				'<div class="card-header col-sm-6 text-center">'+
					'Lot '+number+
						'<button id="delete" type="button" class="mb-auto btn btn-danger float-right">Delete</button>'+
				'</div>'+
				'<div class="card-body">'+
				'<div class="form-group row">'+
						'<div class="col-sm-4 mb-3">'+
							'<label for="title'+number+'">Title</label>'+
							'<input required="required" type="text" id="title'+number+'" class="form-control" name="title">'+
							'</div>'+

						'<div class="col-sm-8 mb-3">'+
							'<label for="description'+number+'">Description</label>'+
							'<input required="required" type="text" id="description'+number+'"'+
								'class="form-control" name="description">'+
						'</div>'+
				'</div>'+
				'<div class="form-group row">'+
					'<div class="col-sm-4 mb-3">'+
						'<label for="quantity'+number+'">Quantity</label>'+
						'<input required="required" type="number" id="quantity'+number+'"'+
						'name="quantity" class="form-control">'+
						'</div>'+
					'<div class="col-sm-4 mb-3">'+
						'<label for="basePrice'+number+'">BasePrice</label>'+
						'<input required="required" type="number" id="basePrice'+number+'"'+
						'name="basePrice" class="form-control">'+
					'</div>'+
					'<div class="col-sm-4 mb-3">'+
						'<label>Photo:'+
						'</label> <input required="required" type="file" name="imagee" />'+
					'</div>'+
				'</div>'+
			'</div>'+'</div>');
			$(document).scrollTop($(document).height());
		
	}
	);
	$('body').on('click','#delete',function(e) {
		$("#lot"+number).remove();
		number--;
		value--;
	});
});