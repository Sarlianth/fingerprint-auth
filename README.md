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

## Requirements
Requirements to run the application:
* __Phone/Tablet__ - Running Android 5.0 (Lollipop) and above
* __PC__ -  No particular hardware specifications (average should be more than enough)
* __Visual Studio (VS)__ - Version 2017
* __Android Studio__

## Technologies
### [Android Studio (Java)](https://developer.android.com/studio/index.html)
We have decided to use android studio to develop our app in Java. Andorid studio is the official integrated development envieroment for googles android operating system. Android application was designed for teachers use only.

### [MsSQL - Microsoft SQL]()
Backend database

### [ASP.NET](https://www.asp.net/)
We decided to use ASP.NET to build our frontend web framework which allowed us to create modern application using .NET and ASP.NET services based on HTML, CSS that provide simple, fast and scalable solution. Web application was developed for admin and parent/student use.

### [WEB.API](https://www.asp.net/web-api)
Middleware RESTful API that allowed us to interconnect pieces of our application together. Provided seemless connections between database and web/android applications anywhere.

## Functionality
This application is designed for implementation in the education system such as College/School. 

The application provides paperless solution for attendance taking and providing real-time statistics which is ready to be implemented. 

Institute has administrator/s that handles registrations of authorities and lectures/classes within the system, meaning administrator has to register individual teachers and students within the system and assigns them to classes. Credentials are provided for individual teachers (& and parents when registering student) upon registration that allow for further authentication into the system. 

Teachers use the credentials to access the attendance system using android application and take attendance for individual class and its students. 

Parents receive credentials to registered email (e-mail address & pass code) upon student registration (provided that their email address was registered into system). Parents can access the system using credentials provided in the e-mail. After authentication into the system parents can add feedback and check attendance of related student.

Statistics are based on individual students attendance, who is registered and whose attendance was marked in by the teacher within the system. Statistics are accessible by teachers and parents, which provide a nice overview of students attendance.

to be continued...


