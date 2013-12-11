package de.bitowl.advent.game2;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tiledmappacker.TiledMapPacker;
import com.badlogic.gdx.tiledmappacker.TiledMapPacker.TiledMapPackerSettings;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
public class MyMapPacker {
	public static void main(String[] args){
		String[] args2={"/home/bitowl/git/adventskalender13/game2-stuff/tilemap/","/home/bitowl/git/adventskalender13/game2-android/assets/tilemap/"};
		TiledMapPacker.main(args2);
//	       final Settings texturePackerSettings = new Settings();
//           texturePackerSettings.paddingX = 2;
//           texturePackerSettings.paddingY = 2;
//           texturePackerSettings.edgePadding = true;
//           texturePackerSettings.duplicatePadding = true;
//           texturePackerSettings.bleed = true;
//           texturePackerSettings.alias = true;
//           texturePackerSettings.useIndexes = true;
//
//           final TiledMapPackerSettings packerSettings = new TiledMapPackerSettings();
//
//           final File inputDir = new File("/home/bitowl/git/adventskalender13/game2-stuff/tilemap/");
//           final File outputDir = new File("/home/bitowl/git/adventskalender13/game2-android/assets/tilemap/");
//           
//         /*  for(String file : outputDir.list()){
//        	   System.out.println("delete " +outputDir.getAbsolutePath()+"/"+ file);
//        	   System.out.println(new File(outputDir.getAbsolutePath()+"/"+file).delete());
//           }*/
//           
//           for(File file:outputDir.listFiles()){
//        	   file.delete();
//           }
//           
//           packerSettings.stripUnusedTiles = false;
//           packerSettings.atlasOutputName = "tiles";
//           packerSettings.tilesetOutputDirectory =".";
//           
//
//      //     MyTiledMapPacker packer = new MyTiledMapPacker(packerSettings);
//           LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//           config.forceExit = false;
//           config.width = 100;
//           config.height = 50;
//           config.useGL20 = true;
//           config.title = "TiledMapPacker";
//           new LwjglApplication(new ApplicationListener() {
//
//                   @Override
//                   public void resume () {
//                   }
//
//                   @Override
//                   public void resize (int width, int height) {
//                   }
//
//                   @Override
//                   public void render () {
//                   }
//
//                   @Override
//                   public void pause () {
//                   }
//
//                   @Override
//                   public void dispose () {
//                   }
//
//                   @Override
//                   public void create () {
//                          MyTiledMapPacker packer = new MyTiledMapPacker(packerSettings);
//
//                           if (!inputDir.exists()) {
//                                   throw new RuntimeException("Input directory does not exist");
//                           }
//
//                           try {
//                                   packer.processMaps(inputDir, outputDir, texturePackerSettings);
//                           } catch (IOException e) {
//                                   throw new RuntimeException("Error processing map: " + e.getMessage());
//                           }
//
//                           Gdx.app.exit();
//                   }
//           }, config);
	}
}
