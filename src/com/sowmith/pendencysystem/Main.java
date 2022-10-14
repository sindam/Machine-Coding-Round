package com.sowmith.pendencysystem;

import java.util.Arrays;

import static com.sowmith.pendencysystem.PendencySystemImpl.getPendencySystem;

public class Main {


    public static void main(String[] args) throws Exception {

        PendencySystem pendencySystem = getPendencySystem();
        //P0

        if(pendencySystem==null){
            System.out.println("Server Unavailable");

        }else {

            pendencySystem.startTracking(1112, Arrays.asList("UPI", "Karnataka", "Bangalore"));
            pendencySystem.startTracking(2451, Arrays.asList("UPI", "Karnataka", "Mysore"));
            pendencySystem.startTracking(3421, Arrays.asList("UPI", "Rajasthan", "Jaipur"));
            pendencySystem.startTracking(1221, Arrays.asList("Wallet", "Karnataka", "Bangalore"));

            pendencySystem.stopTracking(1112);

            System.out.println(pendencySystem.getCounts(Arrays.asList("UPI")));
        }

    }
}
