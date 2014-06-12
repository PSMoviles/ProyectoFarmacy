package OGL;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.psm.farmacy.R;
import OGL.Capsula;


//import com.example.graficasandroidver.MainActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Render implements Renderer {


	
	private Context ctx;
	private Capsula cp;
	public Render(Context ctx){
		//tri = new GLTriangleX();
		//Cuad = new GLPrimitives();
		this.ctx = ctx;
		
		
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
		// TODO Auto-generated method stub

		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0f, 0f, .3f, 1f);
		gl.glClearDepthf(1f);
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, -60, 0, 0, 0, 0, 0, 0, 1, 0);
		//gl.glRotatef(rotationVar, 0, 1, 0);
		gl.glPushMatrix();
		cp.draw(gl);
		gl.glPopMatrix();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub

		cp = new Capsula(ctx.getResources().openRawResource(R.drawable.skb6), gl,100,100,20,0,1);
		
		gl.glViewport(0, 0, width, height);
		float ratio = (float)width/height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 100);
	}
}



