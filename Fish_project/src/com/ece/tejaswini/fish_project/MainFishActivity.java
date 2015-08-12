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
import android.bluetooth.BluetoothSocket;


public class MainFishActivity extends Activity {

	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
     BluetoothDevice mDevice;
    BluetoothSocket mSocket;
    BluetoothSocket tmp = null;
    OutputStream mmOutStream;
    String Left = "A"; 
    String Right = "B"; 
    String Stop = "C";
    String Fwd = "D";
    final String DEVICE_NAME = "RN42-3C1C"; //Name of the Bluesmirf device
 // well known SPP UUID
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String TAG = "BluetoothActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fish);
		
        //Check if device supports bluetooth 
		if (mBluetoothAdapter == null) {
		    // Device does not support Bluetooth
			Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
		}
		mDevice = findBondedDeviceByName(mBluetoothAdapter,DEVICE_NAME );//Find the specified bonded device
		if (mDevice == null) {
		    // Device does not support Bluetooth
			Toast.makeText(getApplicationContext(), "Device not in range", Toast.LENGTH_SHORT).show();
		}
		try {
			tmp = mDevice.createRfcommSocketToServiceRecord(MY_UUID); //create socket connection
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tmp == null) {
		    // Device does not support Bluetooth
			Toast.makeText(getApplicationContext(), "Device not in range", Toast.LENGTH_SHORT).show();
		}
		else
		{
			mSocket= tmp;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageLeft(View view) {
	    // Do something in response to button click
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
	    // Do something in response to button click
		 byte[] toSendRight = Right.getBytes();
		try {
			mmOutStream = mSocket.getOutputStream();
			mmOutStream.write(toSendRight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageStop(View view) {
	    // Do something in response to button click
		 byte[] toSendStop = Stop.getBytes();
		try {
			mmOutStream = mSocket.getOutputStream();
			mmOutStream.write(toSendStop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void sendMessageFwd(View view) {
	    // Do something in response to button click
		 byte[] toSendFwd = Fwd.getBytes();
		try {
			mmOutStream = mSocket.getOutputStream();
			mmOutStream.write(toSendFwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static BluetoothDevice findBondedDeviceByName (BluetoothAdapter adapter, String name) {
		for (BluetoothDevice device : getBondedDevices(adapter)) {
		if (name.matches(device.getName())) {
		Log.v(TAG, String.format("Found device with name %s and address %s.", device.getName(), device.getAddress()));
		return device;
		}
		}
		Log.w(TAG, String.format("Unable to find device with name %s.", name));
		return null;
		}
	
	private static Set<BluetoothDevice> getBondedDevices (BluetoothAdapter adapter) {
		Set<BluetoothDevice> results = adapter.getBondedDevices();
		if (results == null) {
		results = new HashSet<BluetoothDevice>();
		}
		return results;
		}
	
	
}


