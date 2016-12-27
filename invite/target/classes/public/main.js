
$(document).ready(function () {
  $('#getperson').on("submit"), function(e){
    e.preventDefault()

    $.ajax({
      url: 'https://randomuser.me/api/?inc=name, email, phone',
      dataType: 'json',
      method: 'get'
    }).done(function(data){
      $("#name").html(data.results[0].name.first)
      // $("#last").html(data.results[0].name.last)
      $("#phone").html(data.results[0].phone)
      $("#email").html(data.results[0].email)
    })
  }
})
