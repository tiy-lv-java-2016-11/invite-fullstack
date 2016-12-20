# Invite Full Stack

## Description
Welcome to your first full stack homework.  This is going to use both Spark as well as a front end you create. The goal is to create an evite type of program which will display 1 user at a time and then you can select yes or no on whether to invite them.  It will use the random person generator
[api](https://randomuser.me/) to display each person to show.

## Requirements

### Front End
* Layout the pages as shown in the screen shots below.  
* There will be 3 pages invite, going and notgoing.
* Invite Page
	* Use Ajax to display 1 [random user](https://randomuser.me/) as someone to invite or not
	* Show the user buttons to accept or reject the user
	* Show the counts of those going and those not going as provided from the back end
	* When clicked the button should submit the data to the back end via a form with hidden inputs created with the javascript
* Going/Not Going
	* Display all the users who are going or not going depending on the page
	* Layout as shown in the screenshot
* Convert the html to use moustache tags where needed
* Put all assets in the templates directory in your project

#### Invite
![invite](single.png)

#### Going
![going](going.png)

#### Not Going
![notgoing](notgoing.png)

### Back End
* Create a Spark app with Maven
* Create a `Invitee` class that stores all the info for the random user when submitted
* Create a route for "/" that will display the invite page with the user counts
* Create a route for "/going" that will display all those going to the event
* Create a route for "/notgoing" that will display all those not going to the event
* Create a POST route for "/mark-invitee"
	* Take the information POST about the user and store it in a `HashMap<String, User>`
	* Don't forget to mark the user as going or not going
	* When the information is stored redirect to "/"

## Resources
* [Github Repo](https://github.com/tiy-lv-java-2016-11/invite-fullstack)
