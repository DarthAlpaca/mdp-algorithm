package test;
import model.entity.Grid;
import model.entity.Robot;
import model.util.MessageMgr;
import model.util.SocketMgr;

import static constant.CommConstants.TARGET_ANDROID;
import static constant.CommConstants.TARGET_ARDUINO;

public class CommTester {
	public boolean test_general() {
		return testConnection()&&testSendingMessage(TARGET_ARDUINO,"M")&&testSendingMessage(TARGET_ANDROID, "test")&&testReceivingMessage("");
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
