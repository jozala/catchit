CatchIt
=======

Very simple program to shot a photo with your laptop embedded camera every time computer is turned on, waked up or someone is logging in.

It uses [OpenCV library](http://opencv.org/) and [JavaCV library](https://code.google.com/p/javacv/) to support actions on the camera.

Java code of the application is only responsible for handling a camera and creating files with photos. This means that actual events tracking should be implemented for specific operating systems.
