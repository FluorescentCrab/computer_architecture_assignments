package processor.pipeline;

public class MA_RW_LatchType {
	
	boolean RW_enable;
		int opcode; /*send kartoch phude use hoil :| */
	int rs1;
	int rs2;
	int rd; 
	int immx;
	int branchTarget;
	int aluResult;
	int ldResult;
	int special;


	public MA_RW_LatchType()
	{
		RW_enable = false;
	}

	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}

	public int getAluResult() {
		return aluResult;
	}

	public void setAluResult(int aluResult) {
		this.aluResult = aluResult;
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

	public int getLdResult() {
		return ldResult;
	}

	public void setLdResult(int ldResult) {
		this.ldResult = ldResult;
	}

	
	public int getSpecial(){
		return special;
	}

	public void setSpecial(int special){
		this.special = special;
	}
}
