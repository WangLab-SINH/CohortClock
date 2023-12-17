<img src="./images/cohort_clock_logo.jpg" alt="Cohort Clock" title="Cohort Clock"/>

### CohortClock, a platform which capitalizes on smartphone technology and artificial intelligence algorithms to facilitate the collection and management of large-scale rhythmic behavior data. This platform is cross-compatible, seamlessly integrating with both Android (developed using Java) and iOS systems (developed using Swift), thereby ensuring broad accessibility to collect data and transmit it to the server. The server collects data using a MySQL database, and frontend web-based data management is achieved through the use of Flask and Vue. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<img src="./images/figure1.png" alt="Cohort Clock" title="Cohort Clock"/>
CohortClock has three components containing client side, server side and administration side. (A) Client side, With Android version and Ios version, it can collect the user's eating time and pictures, and collect other physical indicators such as height, weight, sleep time, sleep quality, hunger level, mood change and water intake. (B) Server side, the user data transmitted by the client can be received and the deep learning model can be invoked for food picture classification and time-restricted diet model adaptation. (C) Administration side, can extract user data from the server side, analyze, calculate and modify it, and also carry out experimental design of time-restricted diet.


## Mobile app download and demo address
* Android:   [Android version download site](https://github.com/WangLab-SINH/CohortClock/raw/main/app-release.apk)

* IOS: Due to the restrictions on installing untested software on iOS, and as our application is not currently available on the App Store, we have released our beta version on the official Apple-recommended software testing platform, TestFlight. If you would like to install our iOS version, please send the email associated with your registered Apple ID to chiyuhao2018@sibs.ac.cn. We will add you to the list of testers, and you will then be able to download our application on TestFlight.

* HTML administration page: http://39.100.73.118:3001/

* Linux web server: Server image On Alibaba Cloud, the image id of the server is m-8vbevtc05ettdvrph0pa, you can load this image in Alibaba Cloud server, also, the server image can be downloaded from:

## Introduction
### Android/IOS client side
The client mobile applications for both Android and iOS display dietary time recommendations based on the user's uploaded meal times, calculated to align with the user's dietary habits. Beneath the dietary recommendations in the dietary mode, users can view a probability density distribution graph of their dietary habits within a 24-hour time period. This graphical representation allows users to intuitively observe their own dietary patterns. Additionally, we have designed an image upload interface that enables users to choose photos from their albums or take new pictures by clicking the camera button. The images, compressed for efficiency, are synchronized with the upload timestamp and transmitted to the server for computation and storage. In case users forget to upload pictures during meals, they can click the "select time" button to manually choose the mealtime and upload corresponding images.

<img src="./images/Example1.png" alt="Cohort Clock" title="Cohort Clock"/>


In addition to dietary images and meal times, we can also collect other body metrics related to time-restricted eating, such as weight and sleep duration, for correlation analysis with time-restricted eating patterns. Users can also modify and delete uploaded images, annotated weight information, and sleep duration information directly on the client application.

<img src="./images/Example2.png" alt="Cohort Clock" title="Cohort Clock"/>

### Administration side
This tool provides a streamlined interface for managing user data received from the server. Researchers can design experiments and group users on the administrator page. Administrators have access to each user's submitted dietary times and image information. This functionality empowers researchers to analyze data and gain insights into patterns of dietary intake timing.

By aggregating and analyzing the collected data, researchers can identify common trends and patterns, offering valuable insights for future research and interventions. Basic statistical features within the administrator interface allow for simple data exploration, providing an initial overview of the collected data. Researchers can examine indicators such as meal frequency, time distribution, food group composition, weight changes, sleep quality, and more to discover associations and correlations.


## Requirements
* Android:

<video src="https://github.com/WangLab-SINH/CohortClock/assets/87359159/62377bc0-db6f-4d8c-b62e-ceae2b9f1802"></video>
https://github.com/WangLab-SINH/CohortClock/assets/87359159/62377bc0-db6f-4d8c-b62e-ceae2b9f1802

![LPDS_GIF_20231217_212131](https://github.com/WangLab-SINH/CohortClock/assets/87359159/5739c695-88c2-434f-ba30-70e83409a739)
