//Copyright (c) 2015 Tejaswini Angal 
 /* This program is released under the "GNU General Public License(GPL)". 
    Please see the file COPYING in this distribution for 
   license terms. */ 

//This program issues pre decided character over bluetooth to the Bluesmirf (bluetooth module) on the click of the respective buttons.
//The buttons used are forward, reverse , left and right

package com.ece.tejaswini.fish_project;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;


public class MainFishActivity extends Activity {

	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
     BluetoothDevice mDevice;
    BluetoothSocket mSocket;
    BluetoothSocket tmp = null;
    OutputStream mmOutStream;                                       //for bluetooth connection
    String Left = "A";                                              //character sent to arduino for turning the board left
    String Right = "B";                                             //character sent to arduino for turning the boat right 
    String Stop = "C";                                              //character sent to arduino for stopping the boat   
    String Fwd = "D";                                               //character sent to arduino for forward  
    String Rev = "E";                                               //character sent to arduino for reverse
    final String DEVICE_NAME = "RN42-3C1C"; 						//Name of the Bluesmirf device 
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //well known UUID 
    private static final String TAG = "BluetoothActivity";                                       //used for log messages
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fish);		
        //Check if device supports bluetooth 
		if (mBluetoothAdapter == null) {
		    // Device does not support Bluetooth
			Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		//Check if bluetooth is switched ON (for phone)
		if(!mBluetoothAdapter.isEnabled()){
			//bluetooth not switched ON
			Toast.makeText(this, "Please switch the bluetooth ON then restart the application", Toast.LENGTH_LONG).show();
			finish();
        }
		else
		{
			//find the bonded device in bonded device list 
			mDevice = findBondedDeviceByName(mBluetoothAdapter,DEVICE_NAME );//Find the specified bonded device
    		if (mDevice == null  ) {
    		    // Device does not support Bluetooth
    			Toast.makeText(getApplicationContext(), "Device not in range", Toast.LENGTH_LONG).show();
    			finish();
    		}
    		else
    		{
    			try 
    			{    				
    				//create socket
    				tmp = mDevice.createRfcommSocketToServiceRecord(MY_UUID); //create socket connection
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			if (tmp == null) {
    		    // Device does not support Bluetooth
    			Toast.makeText(getApplicationContext(), "Device not in range", Toast.LENGTH_LONG).show();
    			finish();
    			}
    			else
    			{
    				mSocket= tmp; //assign temporary socket to main socket
    			}
    			return;	
    		}
		}	
		
	 }
	
		
	
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_fish, menu);
		return true;
	}
	
	@Override
    protected void onStart() {
		 super.onStart();
		try {
			mSocket.connect();
			Toast.makeText(this, "Now connected", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			//if the bonded device is switched off not not able to connect
			Toast.makeText(this, "Not connected try again", Toast.LENGTH_LONG).show();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageLeft(View view) {
	    //Send the character 'A' to arduino
		//it indicates turning left
		 byte[] toSendLeft = Left.getBytes();        //character to be sent
		try {
			mmOutStream = mSocket.getOutputStream();  
			mmOutStream.write(toSendLeft);           //Write the character to be sent to the output stream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageRight(View view) {
		//Send the character 'B' to arduino
		//it indicates turning right
		 byte[] toSendRight = Right.getBytes();      //character to be sent
		try {
			mmOutStream = mSocket.getOutputStream();
			mmOutStream.write(toSendRight);          //Write the character to be sent to the output stream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageStop(View view) {
		//Send the character 'C' to arduino
		//it indicates stop
		 byte[] toSendStop = Stop.getBytes();       //character to be sent
		try {
			mmOutStream = mSocket.getOutputStream();
			mmOutStream.write(toSendStop);            //Write the character to be sent to the output stream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void sendMessageFwd(View view) {
		//Send the character 'D' to arduino
		//it indicates going forward
		 byte[] toSendFwd = Fwd.getBytes();                //character to be sent
		try {
			mmOutStream = mSocket.getOutputStream();
			mmOutStream.write(toSendFwd);                  //Write the character to be sent to the output stream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageReverse(View view) {
		//Send the character 'E' to arduino
		//it indicates going reverse
		 byte[] toSendRev = Rev.getBytes();            //character to be sent
		try {
			mmOutStream = mSocket.getOutputStream();   //Write the character to be sent to the output stream
			mmOutStream.write(toSendRev);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static BluetoothDevice findBondedDeviceByName (BluetoothAdapter adapter, String name) {
		for (BluetoothDevice device : getBondedDevices(adapter)) {
		if (name.matches(device.getName())   ) {  //if device is bonded and found
		Log.v(TAG, String.format("Found device with name %s and address %s.", device.getName(), device.getAddress()));
		return device;
		}
		}
		Log.w(TAG, String.format("Unable to find device with name %s.", name)); //device not bonded or not found
		return null;
		}
	
	private static Set<BluetoothDevice> getBondedDevices (BluetoothAdapter adapter) {
		Set<BluetoothDevice> results = adapter.getBondedDevices(); //gets a list of bonded devices
		if (results == null) {
		results = new HashSet<BluetoothDevice>();
		}
		return results;
		}
	
	
	
}


