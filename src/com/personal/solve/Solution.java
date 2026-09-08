package com.personal.solve;

import java.util.*;

public class Solution {
	public int[] solution(String[] genres, int[] plays) {
	        int[] answer = {};
	        
	        // map <장르 , [[재생수, 원본 인덱스], [] ....]>
	        Map<String,List<int[]>> map = new HashMap<>();
	        
	        for(int i=0; i<genres.length; i++) {
	        	
	        	if(!(map.containsKey(genres[i]))) {
	        		List<int[]> playsList = new ArrayList<>();
	        		playsList.add(new int[] {plays[i],i} );
		        	map.put(genres[i], playsList);
	        	}
	        	else {
	        		// 해당 장르가 map에 이미 있으면 리스트에 [재생수, 원본인덱스] 추가
	        		map.get(genres[i]).add(new int[] {plays[i],i} );
	        	}
	        }
	        
	        for(String key : map.keySet()) {
//	        	System.out.println(key+" : "+ map.get(key).get(0)+", "+map.get(key).get(1));
	        	System.out.println(map.values().);
	        }
	        
	        
	        return answer;
	    }
}
