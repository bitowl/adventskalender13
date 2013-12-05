package de.bitowl.adventskalender13;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "AdventsSpiel";
		cfg.useGL20 = false;
		//cfg.width = 480;
		//cfg.height = 320;
		cfg.width = 800;
		cfg.height = 480;

		new LwjglApplication(new AdventGame(), cfg);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					setHWCursorVisible(true);
				} catch (LWJGLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	static org.lwjgl.input.Cursor emptyCursor;
	
	public static void setHWCursorVisible(boolean visible)
			throws LWJGLException {
		if (Gdx.app.getType() != ApplicationType.Desktop
				&& Gdx.app instanceof LwjglApplication)
			return;
		if (emptyCursor == null) {
			if (Mouse.isCreated()) {
				int min = org.lwjgl.input.Cursor.getMinCursorSize();
				IntBuffer tmp = BufferUtils.createIntBuffer(min * min);
				emptyCursor = new org.lwjgl.input.Cursor(min, min, min / 2,
						min / 2, 1, tmp, null);
			} else {
				throw new LWJGLException(
						"Could not create empty cursor before Mouse object is created");
			}
		}
		if (Mouse.isInsideWindow())
			Mouse.setNativeCursor(visible ? null : emptyCursor);
	}

}
