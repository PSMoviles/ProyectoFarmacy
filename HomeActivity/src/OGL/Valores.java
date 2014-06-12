package OGL;

public class Valores {
private float Vertices[];
	
	private float Norm[];

	private float UV[];
	
	private short Index[];
	
	public float[] getVertices() {
		return Vertices;
	}

	public void setVertices(float vertices[]) {
		Vertices = vertices;
	}

	public float[] getUV() {
		return UV;
	}

	public void setUV(float uV[]) {
		UV = uV;
	}

	public short[] getIndex() {
		return Index;
	}

	public void setIndex(short index[]) {
		Index = index;
	}

	public float[] getNorm() {
		return Norm;
	}

	public void setNorm(float norm[]) {
		Norm = norm;
	}
}
