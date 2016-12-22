
function getRandomPerson() {
    $.getJSON("https://randomuser.me/api/?nat=us", function(data){
        var person = data.results[0];
        var name = person.name.first + " " + person.name.last;
        var phone = person.cell;
        var email = person.email;
        var photo = person.picture.large;
        console.log(person)
        $("#name").html(name);
        $("#phone").html(phone);
        $("#email").html(email);
        $("#photo").attr("src", photo);
        $("#submit").attr("action", `/submit?name=${name}&phone=${phone}&email=${email}&photo=${photo}`);

    })
}

$(document).ready(function () {

	getRandomPerson();
	
}


)