# Attendance system

# *Applied Project & Minor Dissertation Project 2017/18 - Year 4*

**Name:** [Adrian Sypos](https://github.com/sarlianth), [Adrian Golias](https://github.com/snow246), [Robert Kiliszewski](https://github.com/robertkiliszewski) </br>
**Course:** Software Development </br>
**Module:** Applied Project & Minor Dissertation </br>
**Supervisor:** Kevin O'Brien </br>

Screencast of the application can be found here - [YouTube](https://youtu.be/YrmQMPoCGwI)
![screencast](https://github.com/Sarlianth/fingerprint-auth/blob/master/Screencast.gif)

## Introduction

We have seen over the years that the process of manual attendance has been carried out across almost all educational institutions. The process is not only time consuming but also sometimes inefficient resulting in false attendance marking. These days we do not need to use pen and paper to take attendance. Following this thought, we have proposed an attendance monitoring system based on the concept of web services which are implemented as an Android application that communicates with the database residing on a remote server. 

Our mobile application would require connecting to the database through restful API. Our project is an efficient and user-friendly android application for attendance taking. The application will be installed on the users' smartphone - in this case, teachers' smartphone. It provides very easy to understand user interface to take attendance of class listing names of all students that are registered for a certain class. The application provides strong user authentication and efficient database access via the web API.

The second part of our project is the web application that has been designed for parents and admin. Our web application allows parents to easily give feedback or leave notes for teachers. Parents are also able to check their child attendance. The administrator is able to add teachers to the system, as well as classes and students. Admin can also see if there is any feedback written by parents.

## Features

The system comprises of 3 major modules with their sub-modules as follows:

#### Admin (WebApp):
* Add Student
* Add Teacher
* Create Class
* View/Edit Student Details: Admin can view or update student detail.
* View/Edit Teacher Details: Admin can view or update teacher detail.
* View Attendance: Admin can generate attendance report.

#### Parent (WebApp):
* Login: Parents can use their valid login credentials to access their account.
* View Attendance Report: A Student can view their monthly attendance report.

#### Teacher (Android):
* Login: Teacher can log in to their account using valid login credentials created by the admin.
* Take Attendance: Teacher can easily mark students attendance using android application.

## How to Run

### Web application - [Click to access](http://attendancegroup13.azurewebsites.net/)
 - **WebApp has been deployed to Azure and is accessible [here](http://attendancegroup13.azurewebsites.net/).**
 - However, if you wish to compile the project yourself, please follow the steps below
1. Download the repository
2. Open the solution in Visual Studio 2017
3. Run the project in Visual Studio 2017

### Android - [Download App](https://github.com/Sarlianth/fingerprint-auth/raw/master/attendance.apk)
 - **Download [attendance.apk](https://github.com/Sarlianth/fingerprint-auth/raw/master/attendance.apk) file and install it on your android phone**
 - Optionally, if you wish to build the app yourself and deploy it on an emulator, please follow the steps below
1. Download the repository
2. Open the Android project using Android Studio IDE
3. Setup an emulator or any hardware device that is running the supported android version
4. Run/Deploy to the target device of your choice

## Functionality

This application is designed for implementation in the education system such as school or college, it provides a paperless solution for attendance taking, as well as generating statistics based on attendance for individual classes.

Institute has an admin that handles registrations of authorities and lectures/classes within the system, meaning administrator has to register individual teachers and students within the system and assign them to classes. Credentials are provided for individual teachers upon registration that allows for further authentication into the system. When registering a new student, parent information is also taken to create an account for a parent, so they can later monitor their children's attendance and provide feedback.

Teachers use the credentials to access the attendance system using android application and take attendance for individual class.

Parents receive credentials to registered email upon student registration. They can access the system using credentials provided in the e-mail. After authentication into the system parents can add feedback and check attendance of the related student.

## Database design

This project has been developed with the use of SQL database. Its design can be seen below
![database](https://github.com/Sarlianth/fingerprint-auth/blob/master/database.jpg)

## UML Diagram
UML (Unified Modeling Language) is a way of visualizing a software solution using a collection of diagrams. The UML diagram for this projects android application can be seen below
![uml](https://github.com/Sarlianth/fingerprint-auth/blob/master/uml.jpg)
