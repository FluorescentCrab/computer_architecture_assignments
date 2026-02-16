package processor.pipeline;

public class EX_IF_LatchType {
	boolean EX_IF_enable;
	int branchTarget;

    public EX_IF_LatchType() {
		EX_IF_enable = false;
	}
	
	public boolean isEX_IF_enable() {
		return EX_IF_enable;
	}
	
	public void setEX_IF_enable(boolean eX_IF_enable) {
		EX_IF_enable = eX_IF_enable;
	}

	public int getBranchTarget() {
		return this.branchTarget;
	}
	public void setBranchTarget(int branchTarget) {
		this.branchTarget = branchTarget;
	}

}
