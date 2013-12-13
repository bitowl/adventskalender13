package de.bitowl.advent.game2;

import com.badlogic.gdx.utils.ObjectMap;

public class OMTest {
	public static void main(String[] args){
		ObjectMap<String, String> map = new ObjectMap<String, String>();
		map.put("tileset", "kekse");
		map.put("collision", "more cookies");
		
		for(String test:  map.values()){
			System.out.println(test);
		}
	}
}
