package com.sowmith.pendencysystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendencySystemImpl implements PendencySystem{

    static Map<Integer,List<String>> pendingTxns = null;
    static Map<String,Integer> hierarchialTagWiseCountMap = null;

    private PendencySystemImpl() {
        pendingTxns = new HashMap<>();
        hierarchialTagWiseCountMap = new HashMap<>();
    }

    public static PendencySystem getPendencySystem(){
        if(pendingTxns == null && hierarchialTagWiseCountMap == null ){
            return new PendencySystemImpl();
        }
        return null;
    }

    @Override
    public void startTracking(Integer id, List<String> hierarchicalTags) {
         pendingTxns.put(id,hierarchicalTags);
        String tag = "";
        for (String hierarchicalTag : hierarchicalTags) {
            tag += hierarchicalTag;
            if(hierarchialTagWiseCountMap.containsKey(tag)) {
                hierarchialTagWiseCountMap.put(tag.toString(),hierarchialTagWiseCountMap.get(tag)+1);
            }else {
                hierarchialTagWiseCountMap.put(tag.toString(), 1);
            }
        }
    }

    @Override
    public void stopTracking(Integer id) {

        if(!pendingTxns.containsKey(id)) {
           return;
        }

        List<String> hierarchicalTags = pendingTxns.get(id);
        String tag = "";
        for (String hierarchicalTag : hierarchicalTags) {
            tag += (hierarchicalTag);
            if (hierarchialTagWiseCountMap.containsKey(tag)) {
                hierarchialTagWiseCountMap.put(tag.toString(), hierarchialTagWiseCountMap.get(tag) - 1);
            }
        }

        pendingTxns.remove(id);

    }

    @Override
    public Integer getCounts(List<String> hierarchicalTags) {
        String tag = getCodeFromTags(hierarchicalTags);
        return hierarchialTagWiseCountMap.get(tag);
    }

    private String getCodeFromTags(List<String> hierarchicalTags){
        StringBuilder tag= new StringBuilder();
        for (String hierarchicalTag : hierarchicalTags) {
            tag.append(hierarchicalTag);
        }
        return tag.toString();
    }


}
