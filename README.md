# Attendance system

# *Applied Project & Minor Dissertation Project 2017/18 - Year 4*

**Name:** Adrian Sypos, Adrian Golias, Robert Kiliszewski </br>
**Course:** Software Development </br>
**Module:** Applied Project & Minor Dissertation </br>
**Supervisor:** Kevin O'Brien </br>

## Introduction

We have seen over the years that the process of manual attendance has been carried out across almost all educational institutions. The process is not only time consuming but also sometimes inefficient resulting in the false marking of attendance. Today, we need not maintain pen and paper based attendance registers. Following this thought, we have proposed an attendance monitoring system based on the concept of web services which is implemented as an Android mobile application that communicates with the database residing on a remote server. 

The mobile application would require connecting to the database using either General Packet Radio Service(GPRS) or Wi-Fi technology. Our project is an efficient and user friendly Android mobile application for an Attendance Monitoring. The application will be installed on the user’s (in this case teacher’s) smart phone. It intends to provide an interface to the teacher who will require minimal details to input for marking of attendance of a particular class of students. Apart from that, the application would support strong user authentication and quick transmission of data via the web service. 

Lecturers will login to the phone application and get connected to the server. After login, they will take attendance using mobile phone.


## Technology

### [Android Studio(Java)](https://developer.android.com/studio/index.html)
We have decided to use android studio to develop our app in Java. Andorid studio is the official integrated development envieroment for googles android operating system.


### [Couch DB](http://couchdb.apache.org/)
CouchDB is an open source database made by apache. CouchDB is our online database where all our users and data is synched together. If the user is not connected to the internet then they can still use the app and they will be using PouchDB instead.

### [Pouch DB](https://pouchdb.com/)
PouchDB is escpecially developed for Couch DB in order to be used offline and be synchronized with an online database which is what we are going to use pouchDB for so if the user does not have an internet connection can still use the application and when there is a connection then they can synch data with the online database.

### [Neo4j](https://neo4j.com/)
We want to use Neo4j for statistics, since Neo4j is a graphical database we want to display statistics and data for users if they request it.






