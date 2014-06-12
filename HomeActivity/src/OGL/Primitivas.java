package OGL;

import android.graphics.Bitmap;
import android.graphics.Color;

import OGL.Valores;

public class Primitivas {

	public Valores GenerateHeightMap(float Height, float Width, int VerteX, int VerteZ, int tile, Bitmap bM)
	 {
		 Valores Vertex = new Valores();
		 float VX[];
		 float Normals[];
		 float UV[];
		 short Index[];
		 
		 VX = new float [VerteZ * VerteX * 3 * 3];
		 Normals = new float [VerteZ * VerteX * 3 * 3];
		 UV = new float [VerteZ * VerteX * 3 * 2];
		 Index = new short [VerteZ * VerteX * 6];
		 
		 float DeltaX = (Height/VerteX-1);
		 float DeltaZ = (Width/VerteZ-1);
		 
		 for(int i = 0; i < VerteZ; i++)
		 {
			 for(int j = 0; j < VerteX; j++)
			 {
				 int Indice = i * VerteX + j;
				 
				 VX[Indice * 3] = (float) (j*DeltaX - Height/2.0);
				 VX[Indice * 3 + 1] = (float) ((Color.red(bM.getPixel(i, j))/(0.7)));
				 VX[Indice * 3 + 2] =  (float) (i*DeltaZ - Width/2.0);
				 
				 Normals [Indice * 3] = 0;
				 Normals [Indice * 3 + 1] = 1;
				 Normals [Indice * 3 + 2] = 0;
				 
				 UV[Indice * 2] = (float)(j*tile)/(VerteX-1);
				 UV[Indice * 2 + 1] = (float)(i*tile)/(VerteZ-1);
			 }
		 }
		 
		 int indice = 0;
		 for(int i = 0; i < VerteZ-1; i++)
		 {
			 for(int j = 0; j < VerteX-1; j++)
			 { 
					Index[indice++]=(short) (i * VerteX + j);
					Index[indice++]=(short) ((i+1) * VerteX + j+1);
					Index[indice++]=(short) (i * VerteX+ j+1);

					Index[indice++]=(short) (i * VerteX + j);
					Index[indice++]=(short) ((i+1) * VerteX + j);
					Index[indice++]=(short) ((i+1) * VerteX + j+1);
			 }
		 }
		 
		 		 
		 Vertex.setIndex(Index);
		 Vertex.setVertices(VX);
		 Vertex.setNorm(Normals);
		 Vertex.setUV(UV);
		 return Vertex;
	 }
	public Valores Sphere(int stacks, int slices, float radio, float Ini, float Fin)
	{
		//Declaramos los arreglos
		Valores Vertex = new Valores();
		float VX[];
		float Normals[];
		float UV[];
		short Index[];
		
		//Cargamos los arreglos con el tamaño que necesitaremos
		Index = new short[stacks*slices*6];
		//cantidad de stacks * cantidad de slices * los puntos * XYZ
		Normals = new float[stacks*slices*3*3];
		VX = new float [stacks*slices*3*3];
		//cantidad de stacks * cantidad de lices * los puntos * UV
		UV = new float[stacks*slices*3*2];
		
		
		for(int i=0; i<slices;i++ )
		{
			for(int j=0; j<stacks; j++)
			{
				int indices = (i * stacks + j);
				VX[indices * 3 ] = (float) (radio*Math.cos(((double)j/(stacks-1))*
											(Math.PI*(Fin-Ini))+ Math.PI*Ini-Math.PI/2.0)*
												Math.cos(2.0*Math.PI*(double)i/(slices-1)));
				VX[indices * 3 + 1] = (float) (radio*Math.sin(((double)j/(stacks-1))*
												(Math.PI*(Fin-Ini))+ Math.PI*Ini-Math.PI/2.0));
				VX[indices * 3 + 2] = (float) (radio*Math.cos(((double)j/(stacks-1))*
												(Math.PI*(Fin-Ini))+ Math.PI*Ini-Math.PI/2.0)*
													Math.sin(2.0*Math.PI*(double)i/(slices-1)));
				Normals[indices * 3] = (float) (radio*Math.cos(((double)j/(stacks-1))*
						(Math.PI*(Fin-Ini))+ Math.PI*Ini-Math.PI/2.0)*
							Math.cos(2.0*Math.PI*(double)i/(slices-1)));
				Normals[indices * 3 + 1] = (float) (radio*Math.sin(((double)j/(stacks-1))*
						(Math.PI*(Fin-Ini))+ Math.PI*Ini-Math.PI/2.0));
				Normals[indices * 3 + 2] = (float) (radio*Math.cos(((double)j/(stacks-1))*
							(Math.PI*(Fin-Ini))+ Math.PI*Ini-Math.PI/2.0)*
								Math.sin(2.0*Math.PI*(double)i/(slices-1)));
				
				UV[indices * 2 ] = (float)1*(1-(float)i/(stacks-1));
				UV[indices * 2 + 1] = (float)1*(1-(float)j/(slices-1));
			}
		}
		
		int indice = 0;
		for(int i = 0;i<slices-1; i++)
		{
			for(int j = 0; j<stacks-1; j++)
			{
				Index[indice++]=(short) (i * stacks + j);
				Index[indice++]=(short) ((i+1) * stacks + j+1);
				Index[indice++]=(short) (i * stacks + j+1);

				Index[indice++]=(short) (i * stacks + j);
				Index[indice++]=(short) ((i+1) * stacks + j);
				Index[indice++]=(short) ((i+1) * stacks + j+1);
			}
		}
		
		Vertex.setIndex(Index);
		Vertex.setVertices(VX);
		Vertex.setNorm(Normals);
		Vertex.setUV(UV);
		return Vertex;
	}
}
