# Attendance system

# *Applied Project & Minor Dissertation Project 2017/18 - Year 4*

**Name:** Adrian Sypos, Adrian Golias, Robert Kiliszewski </br>
**Course:** Software Development </br>
**Module:** Applied Project & Minor Dissertation </br>
**Supervisor:** Kevin O'Brien </br>

## Idea
“Student Attendance/Identificator with Fingerprint Reader”. 
Our idea is to use a biometric concept to facilitate the attendance system using the most reliable way of uniquely identifying students through fingerprint readings. Such type of application would be extremely useful in colleges or other organizations requiring daily attendance. Through this application we could keep a systematic track of users (student’s) attendance. 

* Every student could login to the system through fingerprint detection
* Fingerprint comparison for authentication
* Generating a brief report of attendance from the database according to specifications as required.


## Technology

### [Android Studio(Java)](https://developer.android.com/studio/index.html)
We have decided to use android studio to develop our app in Java. Andorid studio is the official integrated development envieroment for googles android operating system.


### [Couch DB](http://couchdb.apache.org/)
CouchDB is an open source database made by apache. CouchDB is our online database where all our users and data is synched together. If the user is not connected to the internet then they can still use the app and they will be using PouchDB instead.

### [Pouch DB](https://pouchdb.com/)
PouchDB is escpecially developed for Couch DB in order to be used offline and be synchronized with an online database which is what we are going to use pouchDB for so if the user does not have an internet connection can still use the application and when there is a connection then they can synch data with the online database.

### [Neo4j](https://neo4j.com/)
We want to use Neo4j for statistics, since Neo4j is a graphical database we want to display statistics and data for users if they request it.






