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
* Create a route for "/going" that will display the going template and pass all those going to the event.
* Create a route for "/notgoing" that will display notgoing template and pass all those not going to the event.
* Create a POST route for "/mark-invitee"
	* Take the information POST about the user and store it in a `HashMap<String, Invitee>` or `ArrayList<Invitee>`
	* Don't forget to mark the user as going or not going
	* When the information is stored redirect to "/"

## Hard Mode
* Store all invitees in a postgres database
* Authentication
	* Add a login page that will log the user in
	* Use the session to store the user (or username if you prefer)
	* Attach the username of the user who accepted/rejected an invitee to the `Invitee` class
* Now that we have a login only allow people who have logged in to approve/reject an invitee.  Simply disable the buttons if there is no logged in user.

## Nightmare Mode
We are going to take this to the next level of program.  We know that similar programs wouldn't just have 1 event nor would they allow everyone to edit it.  Let's fix this.
* A visitor is no longer allowed to use the site so send them directly to the login screen if not logged in
* When a user logs in their home page ("/") will be a list of events they have created.
* A user can add a new event that will take them to a seperate page with a form where they may enter all of the details of the event
	* Name
	* Date and time
	* Location
	* Description
* The form will post to the back end and create the event and assign it to the user.
* Validate the data coming from the form (make sure that it is filled in as well as the date being in the future)
* Create an `com.theironyard.invitator.Event` class as well as a database table to back it
* From the home screen the user may also select an event which should take them to the page from the main assignment where they may add users one at a time

## Epic Mode
Finally, we want the user to see all events they have been invited to as well.  To save friendships we will avoid telling them the events that they were rejected from.  We will also be enhancing the site as a whole.
* Create a navigation bar of some sort on the home page that will give the following options and persisted across all pages:
	* My Events
	* Invited
	* Account
	* Logout
* Invited
	* Create an "/invited" page that will display a list of all events they have been invited to.
	* If they click the event it will show the details as well as everyone that has been invited
* Account
	* This page will give the user the ability to change their password.
	* Make them enter their current password as well as the new password 2x
	* Check to ensure that the current password is correct and the new passwords match
* Tie the whole site together with the navigation.

## Resources
* [Github Repo](https://github.com/tiy-lv-java-2016-11/invite-fullstack)
