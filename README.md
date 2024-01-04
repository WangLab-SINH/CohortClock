# CohortClock:  A deep-learning based platform for biological rhythms research at the cohort level

We have developed CohortClock, a platform that utilizes smartphone technology and artificial intelligence algorithms to streamline the collection and analysis of extensive rhythmic behavior data on a large scale.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<img src="./images/figure1.png" alt="Cohort Clock" title="Cohort Clock"/>
CohortClock consists of three components: the client-side, server-side, and administration-side.


(A) On the client-side, available as both Android and iOS versions, users can input their meal times and provide photos, along with recording other physical indicators such as height, weight, sleep duration, sleep quality, hunger level, mood changes, and water intake.

(B) The server-side is responsible for receiving user data sent by the client and invoking deep learning models for food picture classification and adapting time-restricted eating models.

(C) The administration-side facilitates the extraction, analysis, calculation, and modification of user data from the server-side. Additionally, it supports experimental design for time-restricted eating studies.


## Mobile App Download and Demo Links:
* Android: You can download the Android version of our app from the following site: [Android version download site](https://github.com/WangLab-SINH/CohortClock/blob/main/app-release.apk)

* iOS: Due to iOS restrictions, our app is not available on the App Store. However, we have released a beta version on the official Apple-recommended software testing platform, TestFlight. To install the iOS version, you should have a developer account and please send an email with your registered Apple ID to chiyuhao2018@sibs.ac.cn. We will add you to the list of testers, and you will receive an invitation to download the app via TestFlight.

* Administration Page (HTML): You can access our administration page through the following link: http://39.100.73.118:3001/

* Server Image ID: m-8vbevtc05ettdvrph0pa  To launch the server on Alibaba Cloud, you can use the provided image ID.
Alternatively, you can download the server image from this link: https://198.11.175.199/cohortclockcloud_m-8vbevtc05ettdvrph0pa_system.raw.tar.gz

## Introduction
### Android/iOS Client-Side:
Our mobile applications for both Android and iOS provide users with dietary time recommendations. Users can also access a probability density distribution graph of their dietary habits over a 24-hour period. Additionally, we've incorporated an image upload interface that allows users to select photos from their albums or take new pictures by tapping the camera button. These images are efficiently compressed, synchronized with the upload timestamp, and sent to the server for processing and storage. If users forget to upload pictures during meals, they can use the "select time" button to manually choose the mealtime and upload the corresponding images.

<img src="./images/Example1.png" alt="Cohort Clock" title="Cohort Clock"/>


An example for uploading photos from android app.
<video src="https://github.com/WangLab-SINH/CohortClock/assets/87359159/62377bc0-db6f-4d8c-b62e-ceae2b9f1802"></video>

In addition to dietary images and meal times, we have the capability to gather various other body metrics relevant to time-restricted eating, including weight and sleep duration. These data points can be subjected to correlation analysis alongside time-restricted eating patterns. Furthermore, users are empowered to make direct modifications and deletions to the images they upload, as well as annotated weight and sleep duration information, directly within the client application.

<img src="./images/Example2.png" alt="Cohort Clock" title="Cohort Clock"/>

### Administration side
This tool offers a streamlined interface for efficiently managing user data received from the server. Researchers can design experiments and organize users within the administrator page. Administrators have access to each user's submitted dietary times and image information, enabling them to effectively analyze data and uncover patterns in dietary intake timing.

Through the aggregation and analysis of the gathered data, researchers can pinpoint common trends and patterns, providing valuable insights for future research and interventions. The administrator interface includes basic statistical features that facilitate straightforward data exploration, providing an initial overview of the collected data. Researchers can examine indicators such as disease information, food group composition, weight changes, sleep quality, and more to uncover associations and correlations.

<img src="./images/Example3.png" alt="Cohort Clock" title="Cohort Clock"/>

## Requirements
### Android
* Android Studio version 3.6.3
* SDK version 28
* Java version 1.8
* Detailed package version [versions.gradle](https://github.com/WangLab-SINH/CohortClock/blob/main/Android/versions.gradle)
  
Application Framework Construction: We utilize XUI (https://github.com/xuexiangjys/XUI) as the overall framework for the application. XUI is an open-source UI framework that offers rich templates as references for the interface framework. The advantage of using a unified UI framework is that it ensures consistent design styles across various interfaces within the mobile application. The flexible layout for the overall pages employs the FlexboxLayout framework provided by Google (https://github.com/google/flexbox-layout), allowing the software interface to adapt to screens of different sizes and proportions. Each page in the application belongs to an open-source XPageFragment class constructed based on Fragment (https://github.com/xuexiangjys/XPage), facilitating smooth page sliding, navigation, and data interaction. The data transfer between different interfaces in the application utilizes the open-source MMKV component (https://github.com/Tencent/MMKV), an efficient key-value pair transmission component for cross-page data transfer.

Statistical Analysis of User Dietary Times and Implementation of Dietary Time Recommendation Page: To conveniently display the time points when users upload their dietary information and provide dietary time recommendations, we constructed an HTML page based on ECharts 3.0.0.2 version within the mobile application. ECharts is used to draw cumulative distribution charts and heat maps. The Android application is provided with a web environment and framework using the AgentWeb library (https://github.com/Justson/AgentWeb).

Implementation of Food Image Capture and Selection Page: We employ the user-friendly image selector framework PictureSelector (https://github.com/LuckSiege/PictureSelector) for capturing, operations, and uploads of images. Images are compressed when uploaded to the server to save bandwidth and server resources. We convert images to Bitmap format and compress them.

Implementation of Mobile Database: To allow users to quickly access the historical records of their uploaded data, it is essential to build a local database for storing data. Therefore, we use the SQLite framework on the Android platform for data storage, access, and modification.

### IOS
* Xcode version 15.0.1
* Swift version 5
* IOS version >14.0

Application Framework Construction: We use SnapKit (https://github.com/SnapKit/SnapKit) as the overall framework for the application. SnapKit is an open-source UI framework that provides rich templates as references for the interface framework. The advantage of using a unified UI framework is that it ensures consistent design styles across various interfaces within the mobile application. The data transfer between different interfaces in the application utilizes the open-source MMKV component (https://github.com/Tencent/MMKV), an efficient key-value pair transmission component for cross-page data transfer, which can also be used in the iOS version.

Statistical Analysis of User Dietary Times and Implementation of Dietary Time Recommendation Page: To conveniently display the time points when users upload their dietary information and provide dietary time recommendations, we constructed an HTML page based on ECharts 3.0.0.2 version within the mobile application. ECharts is used to draw cumulative distribution charts and heat maps.

Implementation of Food Image Capture and Selection Page: We use the open-source framework for the iOS system, LXPhotosManagerModule (https://github.com/LIXIANGXLee/LXPhotosManagerModule), for capturing, operations, and uploads of food images.

Implementation of Mobile Database: To allow users to quickly access the historical records of their uploaded data, it is essential to build a local database for storing data. Therefore, we use the FMDB database for the iOS platform (https://github.com/ccgus/fmdb) for data storage.

### Server
* Python version 3.7.4
* Python package version for server and administration side can be obtained from [requirements.txt](./images/requirements.txt)
* MySQL 8.0.0

### Administration html
* Vue 3.3
* Python 3.7.4
* Flask 2.1.1

## Usage
### How to set up time restricted eating times on the administrator side
Go to the "User Info" tab on the administrator interface. Here, you will see information for all users who have uploaded data, including usernames, current dietary patterns, and the number of data uploads.
Select a user for whom you want to modify the eating pattern, and click the "Edit" button. This allows you to edit the user's daily start and end eating times.
On the mobile client, users can choose to accept recommended eating patterns from the management side or the deep learning model, or they can choose not to accept any recommended eating patterns. The default option is to accept the recommended eating patterns.
If a user chooses not to accept the recommended eating patterns from the model, any modifications made by the administrator will have no effect. If a user chooses to accept the recommended eating patterns, the administrator's set eating times will take precedence over the deep learning model's recommendations. The eating pattern recommendations will be based on the administrator-set eating times. If the administrator does not manually set eating times, the recommendations will be generated by the deep learning model.

<video src="https://github.com/WangLab-SINH/CohortClock/assets/87359159/94a70453-c8c6-47a9-bca4-3ee71608b53d"></video>


### How to add entries on the administrator side and display them in the app
Navigate to the "Add Body Metrics" tab on the administrator interface. Here, you will see the various types of Body Metrics data currently collected. To add a new entry, click on the "Add Parameters" button. This action will prompt a window where you can input the necessary information. Fill in the required details on the web page. After providing the information, it will automatically synchronize with the server. The server will then transmit the data to the mobile client. On the Body Metrics page of the mobile app, the newly added parameters will be seamlessly displayed.
The information to be filled in on the web page includes:
1）Body Metrics Name: The name of the metric you want to add.
2) Metrics Query: The type of data for the metric. For instance, if it is continuous data, the mobile app's Body Metrics page will use a Ruler control for data collection. If it is discrete data, a text box will be used for data collection.
3) Metrics Unit: The unit for the parameter, which will be synchronized and displayed in the mobile client. For example, the unit for weight could be kg, and for height, it could be cm. Discrete data types do not require a unit.
4) If the metric involves continuous data, fill in the maximum, minimum, default values, and the interval for the Ruler control to generate the control accurately.
5) If the newly added metric is capable of circadian rhythm calculation, select "True" in the "Is circadian" option. This will determine whether the collected data exhibits circadian rhythm characteristics.

<video src="https://github.com/WangLab-SINH/CohortClock/assets/87359159/507ee1cf-05ed-4875-920c-55ae35051970"></video>


## License

This code is distributed under the terms and conditions of the MIT license.

```
Copyright (C) 2023 

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.






