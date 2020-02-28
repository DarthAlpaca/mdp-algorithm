package test;
import model.entity.Grid;

import model.entity.Robot;
import model.util.MessageMgr;
import model.util.SocketMgr;

import static constant.CommConstants.TARGET_ANDROID;
import static constant.CommConstants.TARGET_ARDUINO;
import static constant.CommConstants.TARGET_ANDROID;
import static constant.CommConstants.TARGET_BOTH;

public class CommTester {
	public boolean test_general() {
		
		try {
			testSendingMessage(TARGET_BOTH,"M");
			Thread.sleep(100);

			testSendingMessage(TARGET_BOTH,"R");
			testSendingMessage(TARGET_BOTH,"I");
			String sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"R");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"R");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"L");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"L");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"L");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"R");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"L");
			testSendingMessage(TARGET_BOTH,"L");
			testSendingMessage(TARGET_BOTH,"I");
			sensorData = SocketMgr.getInstance().receiveMessage(true);
            while (sensorData == null) {
                SocketMgr.getInstance().sendMessage(TARGET_ARDUINO, "I");
                sensorData = SocketMgr.getInstance().receiveMessage(true);
            }
			testSendingMessage(TARGET_BOTH,"M");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean testConnection() {
		if (!SocketMgr.getInstance().isConnected()) {
			SocketMgr.getInstance().openConnection();
		}
		return SocketMgr.getInstance().isConnected();
		
	}
	public boolean testSendingMessage(String dest, String msg) {
		if(!testConnection())return false;
		SocketMgr.getInstance().sendMessage(dest, msg);
		return true;
	}
	public boolean testReceivingMessage(String msg) {
		if(!testConnection())return false;
		String m = SocketMgr.getInstance().receiveMessage(false);
		if(msg!="") {
			return m==msg;
		}
		return msg!="";
	}
	

}
