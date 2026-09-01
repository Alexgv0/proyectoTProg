package datatypes;

public final class DTDatosConsulta {
    private final int nums;
    private final float cost;

    public DTDatosConsulta(int nums, float cost) {
        this.nums = nums;
        this.cost = cost;
    }

	public int getNums() {
		return nums;
	}
	
	public float getCost() {
		return cost;
	}
}
