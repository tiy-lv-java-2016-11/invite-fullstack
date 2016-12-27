
function getRandomPerson() {
    $.getJSON("https://randomuser.me/api/?nat=us", function(data){
        var person = data.results[0];
        var name = person.name.first + " " + person.name.last;
        var phone = person.cell;
        var email = person.email;
        var photo = person.picture.large;
        console.log(person);
        $("#spanName").html(name);
        $("#spanPhone").html(phone);
        $("#spanEmail").html(email);
        $("#photo").attr("src", photo);
        $("#name").attr("value", name);
        $("#phone").attr("value", phone);
        $("#email").attr("value", email);
        $("#photoUrl").attr("value", photo);
    })
}

$(document).ready(function () {

	getRandomPerson();
	
});