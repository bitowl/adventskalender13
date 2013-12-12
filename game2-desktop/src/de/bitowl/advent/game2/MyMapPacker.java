package de.bitowl.advent.game2;

import java.io.File;
import com.badlogic.gdx.tiledmappacker.TiledMapPacker;

public class MyMapPacker {
	public static void main(String[] args) {
		String[] args2 = {
				"/home/bitowl/git/adventskalender13/game2-stuff/maps/",
				"/home/bitowl/git/adventskalender13/game2-android/assets/maps/",
				"--strip-unused"};
		// delete the old map files
		for (File file: new File(args2[1]+"tileset/").listFiles()) {
			file.delete();
		}
		// pack the maps
		TiledMapPacker.main(args2);
	}
}
