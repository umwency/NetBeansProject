package com.tingloy;


import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

class PortChecker extends Thread{
    private int portValue;
    private SerialPort sp;


    public void run() {

        sp = SerialPort.getCommPort("COM3");
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
        boolean portStatus = false;

        if(sp.openPort())
            portStatus =true;

        System.out.println(portStatus);

        if (portStatus) {
            for (; ; ) {
                Scanner sc = new Scanner(sp.getInputStream());
                try {
                    portValue = sc.nextByte();
                   }catch (Exception e){}
            }
        }
    }
    int getPortValue() { return portValue; }
    void setPortClose () {  sp.closePort(); }

}