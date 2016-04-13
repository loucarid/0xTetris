
public class Matrix {
	int size;
	private int[][] matrice;

	public Matrix(int size) {
		this.size = size;
		this.matrice = new int[size][size];
		int x = 0;
		while (x < size) {
			int y = 0;
			while (y < size) {
				this.matrice[x][y] = -1;
				++y;
			}
			++x;
		}
	}

	public int getSize() {
		return this.size;
	}

	public int getVal(int x, int y) {
		if (x >= 0 && y >= 0 && x < this.size && y < this.size) {
			return this.matrice[x][y];
		}
		return -1;
	}

	public void rotation(boolean sens) {
		int x;
		int temp;
		int y = 0;
		while (y < this.size / 2) {
			x = 0;
			while (x < this.size) {
				temp = this.matrice[x][y];
				this.matrice[x][y] = this.matrice[x][this.size - 1 - y];
				this.matrice[x][this.size - 1 - y] = temp;
				++x;
			}
			++y;
		}
		if (sens) {
			y = 0;
			while (y < this.size) {
				x = 0;
				while (x < this.size - y) {
					temp = this.matrice[x][y];
					this.matrice[x][y] = this.matrice[this.size - 1 - y][this.size
							- 1 - x];
					this.matrice[this.size - 1 - y][this.size - 1 - x] = temp;
					++x;
				}
				++y;
			}
		} else {
			this.transpose();
		}
	}

	public void setVal(int x, int y, int val) {
		if (x >= 0 && y >= 0 && x < this.size && y < this.size) {
			this.matrice[x][y] = val;
		}
	}

	public void transpose() {
		int y = 0;
		while (y < this.size) {
			int x = 0;
			while (x < y) {
				int temp = this.matrice[x][y];
				this.matrice[x][y] = this.matrice[y][x];
				this.matrice[y][x] = temp;
				++x;
			}
			++y;
		}
	}
}
