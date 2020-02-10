$(document).ready(function() {
	productTypeChangePerform();
	categoryChangePerform();
	itemSearchPerform();
});

function productTypeChangePerform() {
	$("#product_type").change(function() {
		$.ajax({
			url : $(this).attr('uri'),
			data : {
				productTypeId : $(this).val()
			},
			type : 'GET',
			error : function(data, textStatus, jqXHR) {
				if (data.status != "success")
					console.log(data);
			},
			success : function(data) {
				if (data != null) {
					addCategoriesRespectToSerach(data);
				}
				console.log(data);
			}
		});
	});
}

function categoryChangePerform() {
	$("#category").change(function() {
		$.ajax({
			url : $(this).attr('uri'),
			data : {
				categoryId : $(this).val()
			},
			type : 'GET',
			error : function(data, textStatus, jqXHR) {
				if (data.status != "success")
					alert(data);
			},
			success : function(data) {
				if (data != null) {
					addProductsRespectToSerach(data);
				}
				console.log(data);
			}
		});
	});
}

function itemSearchPerform() {
	$("#btn_item_search").click(function() {
		var baseUri = $(this).attr("base_uri");
		var searchUri = $(this).attr("search_uri");
		var productTypeId = $('#product_type').val();
		var categoryId = $('#category').val();
		var productId = $('#product').val();

		$.ajax({
			url : searchUri,
			data : {
				baseUri : baseUri,
				productTypeId : productTypeId,
				categoryId : categoryId,
				productId : productId
			},
			type : 'GET',
			error : function(data, textStatus, jqXHR) {
				if (data.status != "success")
					alert(data);
			},
			success : function(data) {
				if (data != null) {
					var element = $('#div_items_add');
					element.find('div').remove();
					element.append(data);
					$("#tbl_items_add").DataTable();
					priceCalculatePerform();
					addItem();
				}
			}
		});
	});
};

function addCategoriesRespectToSerach(data) {
	var element = $('#category');
	element.find('option').not(':first').remove();

	data.forEach(function(category) {
		optionText = category.name;
		optionValue = category.id;
		element.append(`<option value="${optionValue}">${optionText}</option>`);
	});
}

function addProductsRespectToSerach(data) {
	var element = $('#product');
	element.find('option').not(':first').remove();

	data.forEach(function(product) {
		optionText = product.name;
		optionValue = product.id;
		element.append(`<option value="${optionValue}">${optionText}</option>`);
	});
}

function priceCalculatePerform(){
	var quantityEL = $("#txt_quantity");
	var unitPriceEL = $("#txt_unit_price");
	var quantity, unitPrice;

	quantityEL.change(function() {
		calculateAndSetTotalPrice(quantityEL, unitPriceEL);
	});

	unitPriceEL.change(function() {
		calculateAndSetTotalPrice(quantityEL, unitPriceEL);
	});

}

function calculateAndSetTotalPrice(quantityEL, unitPriceEL) {
	$("#txt_total_price").val(quantityEL.val() * unitPriceEL.val());
}

function addItem() {
	$('.btn-item-add').click(function() {
		var url = $(this).attr('uri');
		var trEl = $(this).closest('tr');
		var base_uri = trEl.closest('tbody').attr('base_uri');

		var categoryId = trEl.find('#category').attr('value');
		var productId = trEl.find('#product').attr('value');
		var sizeId = trEl.find('#size').val();
		var colorId = trEl.find('#color').val();
		var unitId = trEl.find('#unit').val();
		var style = trEl.find('#txt_style').val();
		var quantity = trEl.find('#txt_quantity').val();
		var unitPrice = trEl.find('#txt_unit_price').val();
		var totalPrice = trEl.find('#txt_total_price').val();

		if((style == null || style == "") || (quantity == null || quantity == "") || (unitPrice == null || unitPrice == "")) {
			alert("Style, Quantity and Unit price must be given!");
			return false;
		}
	//	console.log(categoryId + ', ' + productId + ', ' + sizeId + ', ' + colorId + ', ' + unitId + ', ' + style + ', ' + quantity + ', ' + unitPrice + ', ' + totalPrice);

		var data = {
					baseUri : base_uri,
					category_id : categoryId,
					product_id : productId,
					size_id : sizeId,
					color_id : colorId,
					unit_id : unitId,
					style : style,
					quantity : quantity,
					unit_price : unitPrice,
					total_price : totalPrice
				}

		$.ajax({
			url : url,
			data : data,
			type : 'POST',
			error : function(data, textStatus, jqXHR) {
				var jsonValue = JSON.parse(data.responseText);
				console.log(jsonValue);
			},
			success : function(data) {
				if (data != null) {
					$('#form_item_list').find('form').remove();

					$('#form_item_list').append(data);
				}
			}
		});
	});
}