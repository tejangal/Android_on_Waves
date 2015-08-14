
//Copyright (c) 2015 Tejaswini Angal
/* This program is released under the "GNU General Public License(GPL)".
   Please see the file COPYING in this distribution for
   license terms. */

//This program controls the motion of the boat in four directions
//1.Forward
//2.Left
//3.Right
//4.Reverse
#include <MeetAndroid.h>
//Pin setup
int led = 13;                    //lights the led on arduino, used for testing purpose
int command = 0;                //used to capture the recieved data,when no data is received, value remains '0'
int pwmLeftPin = 3;            //this pin 3 on arduino uno switches the left motor on and off
int pwmRightPin = 11;         //this pin 11 on arduino uno switches thge right motor on and off 
int directionLeftPin = 12;   //controls the direction of left motor(forward/reverse)
int directionRightPin = 13;  //controls the direction of the right motor(forward/reverse)

void setup()
{
  Serial.begin(115200);            //set the baud rate
  Serial.flush();                  //flush the pipe 
    
  pinMode(led, OUTPUT);             //declare respective output pins
  pinMode(pwmLeftPin,OUTPUT);
  pinMode(pwmRightPin,OUTPUT);
  pinMode(directionLeftPin,OUTPUT);
  pinMode(directionRightPin,OUTPUT);
 
  analogWrite(pwmRightPin,0);          //set initial values to respective pins
  analogWrite(pwmLeftPin,0);
  digitalWrite(directionRightPin,0);        
  digitalWrite(directionLeftPin,0);
  
}

void loop()
{
  Serial.flush();
// If bluetooth received characters copy it in command
  while (Serial.available()>0)  //while characters are arriving over bluetooth     
  {
    
  int rcvData = 0;  
  rcvData = Serial.read();
  command = rcvData; 
  }
   
       if(command == 65 )//A
      {
       motorA();  //switch on motor1
      }
      else if(command == 66)//B
      {
         motorB();   //switch on motor2
      }  
      else if(command == 67) //C
      {  //switch off both motors    
        analogWrite(pwmRightPin,0);
        analogWrite(pwmLeftPin,0);
        digitalWrite(directionRightPin,0);        
        digitalWrite(directionLeftPin,0);
      }
      else if(command == 68) //D
      {
        motorAll();                  //switch on both the motors
        
      }
      else if(command == 69)//E
      {
	 motor_reverse();
      }
	  
      else
      {
        analogWrite(pwmRightPin,0);            //set respective initial value
        analogWrite(pwmLeftPin,0);
        digitalWrite(directionRightPin,0);        
        digitalWrite(directionLeftPin,0);       
      }
   
}

void motorA()
{ 
  analogWrite(pwmLeftPin,160);      //voltage 1.6V
  analogWrite(pwmRightPin,0);
  digitalWrite(directionLeftPin,0);  //direction= forward
  digitalWrite(directionRightPin,0);
}

void motorB()
{
 analogWrite(pwmRightPin,160);     //voltage 1.6V  
 analogWrite(pwmLeftPin,0);
 digitalWrite(directionRightPin,0);  //direction = forward      
 digitalWrite(directionLeftPin,0);
}

void motorAll()
{
 analogWrite(pwmLeftPin,160);      //voltage 1.6V
 digitalWrite(directionLeftPin,0);  //direction= forward
 analogWrite(pwmRightPin,160);     //voltage 1.6V  
 digitalWrite(directionRightPin,0);  //direction = forward      
}

void motor_reverse()
{
 analogWrite(pwmLeftPin,160);      //voltage 1.6V
 digitalWrite(directionLeftPin,1);  //direction= forward
 analogWrite(pwmRightPin,160);     //voltage 1.6V  
 digitalWrite(directionRightPin,1);  //direction = forward      
}




