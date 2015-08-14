Copyright (c) 2015 Tejaswini Angal
This program is released under the "GNU General Public License(GPL)".
Please see the file COPYING in this distribution for license terms.
This project controls an arduino mounted on a toy boat with a smart phone over bluetooth.The components used in this project are arduino uno board, bluesmirf silver bluetooth module from sparkfun, ardumoto motor sheild from sparkfun , 4.8V battery (which came with the boat) and smartphone with minimum android 4.1 installed. The connections have been made as per the schematic provided on the project webpage.
The android application for controlling the toy boat has been developed without the use of any "off the shelf" libraries.The application will not startup untill the bluetooth on the phone has been switched "on".Also, intuitive messages or toasts will be displayed if the device is not in range or the smartphone does not support bluetooth.
The screen when the app is installed has 5 buttons on it.The buttons in the app make the boat go forward,reverse,left,right and stop.Reverse is included to avoid the boat crashing on the walls of the pool during testing.

Working:
The arduino uno has been programmed as follows:
When it receives character "A" it switches the left motor on.
When it receives character "B" it switches the right motor on.
When it receives character "C" it switches both the motors off.
When it receives character "D" it switches both the motors on in forward direction.
When it receives character "E" it switches both the motors on in reverse direction.

The smartphone application is developed as follows:
When the user clicks on the button forward the character "D" is sent over bluetooth to the bluetooth module.
When the user clicks on the button reverse the character "E" is sent over bluetooth to the bluetooth module.
When the user clicks on the button right the character "A" is sent over bluetooth to the bluetooth module.
When the user clicks on the button left the character "B" is sent over bluetooth to the bluetooth module.
When the user clicks on the button stop the character "C" is sent over bluetooth to the bluetooth module.

Future Enhancement:
1. Using android accelerometer.
2. Communication over wifi
3.Using a camera mounted on boat

For more info refer the schematic,block diagram, user guide,build guide and source code.
References:
https://www.arduino.cc/en/Main/arduinoBoardUno 
http://developer.android.com/guide/topics/connectivity/bluetooth.html
https://learn.sparkfun.com/tutorials/using-the-bluesmirf

Note: please report issues on the following link https://github.com/tejangal/Android_on_Waves/issues

You can contact me on the email address : tejangal@pdx.edu