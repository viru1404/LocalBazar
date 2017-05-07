$(function() {
	$("#add_product").submit(function(e){
		e.preventDefault();
		var name = $('#name').val().trim();
		var category = $('#category').val().trim();
		var cost = $('#cost').val().trim();
		var quantity = $('#quantity').val().trim();
		var image = $('#image').val().trim();
		$.ajax({
			url:'http://d96393cc.ngrok.io/createproduct/',
			type:'POST',
			data:{name:name,cost:cost,pic:image,quantity:quantity,category:category},
			success: function(result) {
				if(result.msg == "success") {
					window.alert("Successfully Added"),
					$(".homeb").trigger("click")}
				else window.alert("Error Occured")
			},
			error:function() {
				window.alert("Some Error Occured");
			}
		})
	});
	$("#remove_product").submit(function(e){
		e.preventDefault();
		var name = $('#namer').val().trim();
		var cost = $('#costr').val().trim();
		var quantity = $('#quantityr').val().trim();
		$.ajax({
			url:'http://d96393cc.ngrok.io/updateproduct/',
			type:'POST',
			data:{name:name,cost:cost,quantity:quantity},
			success: function(result) {
				if(result.msg == "success") {
					window.alert("Successfully Updated"),
					$(".homeb").trigger("click")}
				else window.alert("Error Occured")
			},
			error:function() {
				window.alert("Some Error Occured");
			}
		})
	});
	$(".subremove").click(function(){
		var name;
		var category;
		var cost;
		var quantity;
		$.ajax({
			url:'http://d96393cc.ngrok.io/inventory/',
			type:'GET',
			success: function(result) {
				var i = 1;
				var html = '<table class="myTable" style="width: 100%;background-color: rgba(0,0,0,.5);border:1px solid white; border-radius: 2px 2px 2px 2px" border="1"><tbody><tr><td style="text-align: center;opacity: .8" colspan="5"><h3>Current Inventory</h3></td><tr><td>&nbsp;Serial No.</td><td>&nbsp;Name</td><td>&nbsp;Category</td><td>&nbsp;Cost</td><td>&nbsp;Quantity</td></tr>'
				$.each(result,function(key, value){
				html += '<tr class="table1"><td>' + i +'</td><td>'+value.name+'</td><td>'+value.category+'</td><td>'+value.cost+'</td><td>'+value.quantity+'</td></tr>'
				i+=1;
				});
				html+='</tbody></table>'
				$('#tablex').html(html);
				$("tr.table1").click(function() {
				    var tableData = $(this).children("td").map(function() {
	        		return $(this).text();
	 				}).get();
				$("#namer").val(tableData[1]);
				$("#costr").val(tableData[3]);
				$("#quantityr").val(tableData[4]);
				});
			},
			error:function() { 
				window.alert("Some Error Occured");
			}
			})
		});
})