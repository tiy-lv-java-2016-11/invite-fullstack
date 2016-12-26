$(document).ready(function(){

  $("#getPerson").on("submit", function(e){
  e.preventDefault()

  var id = $("#getPerson").val()



$.ajax({
  url: 'https://randomuser.me/api/?inc=name, email, phone' + id,
  dataType: 'json',
  method: 'get'
}).done(function(data){
      $("#name").html(data.name)
      $("#phone").html(data.email)
      $("#email").html(data.phone)

 })





//
//    $.ajax({
//  url: 'https://randomuser.me/api/' + id,
//  dataType: 'json',
//  method: 'get'
//   }).done(function(data){
//    $("#name").html(data.name)
//    $("#phone").html(data.email)
//    $("#email").html(data.phone)
//
//  });
//
//  $.getJSON('https://randomuser.me/api/' + id, function(data){
//      $("#name").html(data.name)
//      $("#phone").html(data.email)
//      $("#email").html(data.phone)
//    })
  })
})

