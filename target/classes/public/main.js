$(document).ready(function(){

  $("#getPerson").on("submit", function(e){
  e.preventDefault()


$.ajax({
  url: 'https://randomuser.me/api/?inc=name, email, phone',
  dataType: 'json',
  method: 'get'
}).done(function(data){
      $("#name").html(data.results[0].name.first)
//      $("#last").html.data.results[0].name.last)
      $("#phone").html(data.results[0].phone)
      $("#email").html(data.results[0].email)

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

