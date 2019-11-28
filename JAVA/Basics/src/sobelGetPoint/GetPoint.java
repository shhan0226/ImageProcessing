package sobelGetPoint;

public class GetPoint {
	int x, y, value, weight;

	public GetPoint() {
		value = -1;
		weight = -1;
		x = -1;
		y = -1;
	}

	public GetPoint(int value, int weight) {
		this.value = value;
		this.weight = weight;
		x = -1;
		y = -1;
	}

	public void reSet(int value, int weight) {
		this.value = value;
		this.weight = weight;
		x = -1;
		y = -1;
	}

	public int getX() {
		x = this.value % this.weight;
		return x;
	}

	public int getY() {
		y = this.value / this.weight;
		return y;
	}

}
