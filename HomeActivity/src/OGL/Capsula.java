package OGL;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import OGL.Primitivas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import OGL.Valores;

public class Capsula extends Primitivas {

	private Valores Datos;
	private FloatBuffer vertBuff, NormBuff, Textbuff;
	private ShortBuffer pBuff;
	private int TextureId[] = new int [1];
	
	public Capsula(InputStream Imagen, GL10 gl, int Sk, int Sl,float radio, float ini, float fin)
	{
		Datos = new Valores();
		Datos = Sphere(Sk,Sl,radio,ini,fin);
		
		InputStream Img = Imagen;
		Bitmap bitmap;
		try
		{
			bitmap = BitmapFactory.decodeStream(Img);
		}
		finally
		{
			try
			{
				Img.close();
			}
			catch(IOException Ex)
			{
				
			}
		}
		
		gl.glGenTextures(1, TextureId, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, TextureId[0]);
			
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
		
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		ByteBuffer bBuff = ByteBuffer.allocateDirect(Datos.getVertices().length *  4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(Datos.getVertices());
		vertBuff.position(0);

		ByteBuffer TBuff = ByteBuffer.allocateDirect(Datos.getUV().length *  4);
		TBuff.order(ByteOrder.nativeOrder());
		Textbuff = TBuff.asFloatBuffer();
		Textbuff.put(Datos.getUV());
		Textbuff.position(0);

		
		ByteBuffer VBuff = ByteBuffer.allocateDirect(Datos.getNorm().length *  4);
		VBuff.order(ByteOrder.nativeOrder());
		NormBuff = VBuff.asFloatBuffer();
		NormBuff.put(Datos.getNorm());
		NormBuff.position(0);
		
		ByteBuffer pbBuff = ByteBuffer.allocateDirect(Datos.getIndex().length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(Datos.getIndex());
		pBuff.position(0);
		
	}
	
	public void draw(GL10 gl)
	{
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glActiveTexture(GL10.GL_TEXTURE0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, TextureId[0]);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		gl.glNormalPointer(3, GL10.GL_FLOAT, NormBuff);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, Textbuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, Datos.getIndex().length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
}
