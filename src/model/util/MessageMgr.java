package model.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static constant.MapConstants.MAP_ROWS;
import static constant.RobotConstants.*;




/**
 * Message generator
 */
public class MessageMgr {

    public static String URLAddress = "http://192.168.16.133:8123/end";

    public static String generateFinalDescriptor(String part1, String part2) {
        return "{finaldescriptor:\"" + part1 + "," + part2 + "\"}";
    }

    /**
     * Generate map string for Android communication, note that on Android the coordinate of
     * the robot is the upper right corner.
     * @param descriptor Map descriptor in Android format
     * @param x Robot's x coordinates
     * @param y Robot's y coordinates
     * @param heading Robot's heading
     * @return Message string for sending to Android
     */
    public static String generateMapDescriptorMsg(String descriptor, int x, int y, int heading) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"robot\":\"");
        builder.append(descriptor);
        builder.append(",");
        builder.append(MAP_ROWS - y);
        builder.append(",");
        builder.append(x + 1);
        builder.append(",");
        if (heading == NORTH) {
            builder.append(0);
        } else if (heading == EAST) {
            builder.append(90);
        } else if (heading == SOUTH) {
            builder.append(180);
        } else if (heading == WEST) {
            builder.append(270);
        }
        builder.append("\"}");
        return builder.toString();
    }

    /**
     * Parse waypoint message from Android, the Y coordinate received
     * starts from the bottom, so it's reversed.
     * @param msg
     * @return
     */
    public static List<Integer> parseMessage(String msg) {
    	if(msg.contains("waypoint:")) {
    		String[] splitStringTest = msg.split(":", 2);
    		msg = splitStringTest[1];
    		System.out.println(splitStringTest[0]);
    		System.out.println(splitStringTest[1]);
    		msg = msg.trim();
    		String[] splitString = msg.split(", ", 2);
            List<Integer> waypoint = new ArrayList<>();

            Integer wayPointX, wayPointY;
            try {
                wayPointX = Integer.parseInt(splitString[0]);
                wayPointY = MAP_ROWS - Integer.parseInt(splitString[1]) - 1;
                waypoint.add(wayPointX);
                waypoint.add(wayPointY);
                return waypoint;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    	}
    	return null;
        
    }

    public static String ReceivingImageDataJson(){
        try {
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(URLAddress).openConnection();
        // optional default is GET
        httpClient.setRequestMethod("GET");
        // int responseCode = httpClient.getResponseCode();
        // System.out.println("\nSending 'GET' request to URL : " + url);
        // System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
            //print result
        System.out.println(response.toString());
        return response.toString();
        }catch (Exception e) {e.printStackTrace(); return "";}
    
    }

    public static void sendingPOST(HashMap<Long, List<Integer>> data) {
        try {
        URL url = new URL(URLAddress);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        byte[] output = byteOut.toByteArray();
        out.writeObject(data);
        http.setFixedLengthStreamingMode(output.length);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("charset", "utf-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) { os.write(output); }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendingListPOST(List<Long> data) {
        try {
        URL url = new URL(URLAddress);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        byte[] output = byteOut.toByteArray();
        out.writeObject(data);
        http.setFixedLengthStreamingMode(output.length);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("charset", "utf-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) { os.write(output); }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MatchImageDataFromTimeStamp(HashMap<Integer, List<List<Integer>>> ImageHashMapReceivedFromRPi, HashMap<Long, List<Integer>> TimeStampDatabase){

        for(HashMap.Entry<Integer, List<List<Integer>>> inDataentry : ImageHashMapReceivedFromRPi.entrySet()) {
            Integer inDataKey = inDataentry.getKey();
            List<List<Integer>> inDataValue = inDataentry.getValue();

            for (HashMap.Entry<Long, List<Integer>> entry : TimeStampDatabase.entrySet()){
                Long databaseKey = entry.getKey();
                List<Integer> databaseValue = entry.getValue();

                if (Math.abs(inDataKey - databaseKey) < 200){

                    System.out.println(databaseValue);

                    int x_axis_from_database = databaseValue.get(0);
                    int y_axis_from_database = databaseValue.get(1);
                    for (int k=0;k<inDataValue.size();k++){
                        int index_from_received_data = inDataValue.get(k).get(0);
                        int x_axis_from_received_data = inDataValue.get(k).get(1);
                        int y_axis_from_received_data = inDataValue.get(k).get(2);
                        int weight_from_received_data = inDataValue.get(k).get(3);
                        int height_from_received_data = inDataValue.get(k).get(4);
                    }
                }
                
    
            }
        }
    }


    }

