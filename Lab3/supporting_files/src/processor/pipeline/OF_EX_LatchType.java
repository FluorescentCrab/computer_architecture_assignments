package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean EX_enable;
	int opcode; /*send kartoch phude use hoil :| */
	int rs1;
	int rs2;
	int rd; 
	int immx;
	int branchTarget;

	public OF_EX_LatchType()
	{
		EX_enable = false;
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}

	public int getImmx() {
		return immx;
	}

	public void setImmx(int immx) {
		this.immx = immx;
	}
	
	public int getBranchTarget() {
		return branchTarget;
	}
	
	public void setBranchTarget(int branchTarget) {
		this.branchTarget = branchTarget;
	}
	
	public int getRd() {
		return rd;
	}
	
	public void setRd(int rd) {
		this.rd = rd;
	}
	
	public int getRs1() {
		return rs1;
	}
	
	public void setRs1(int rs1) {
		this.rs1 = rs1;
	}
	
	public int getRs2() {
		return rs2;
	}
	
	public void setRs2(int rs2) {
		this.rs2 = rs2;
	}
	
	public int getOpcode() {
		return opcode;
	}
	
	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

}