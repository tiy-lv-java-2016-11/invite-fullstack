
$(document).ready(function () {

        $.ajax({
            url: 'https://randomuser.me/api/?inc',
            dataType: 'json',
            method: 'get'
        }).done(function(data){
            $("#name").html(data.results[0].name.first)
            $("#last").html(data.results[0].name.last)
            $("#phone").html(data.results[0].phone)
            $("#email").html(data.results[0].email)
            $("#picture").attr("src", data.results[0].picture.large)
            $("#fpicture").attr("value", data.results[0].picture.large)
            $("#fname").attr("value", data.results[0].name.first)
            $("#fphone").attr("value", data.results[0].phone)
            $("#femail").attr("value", data.results[0].email)

        })
})
