package processor.pipeline;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	
	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}
	
	public void performOF()
	{
		if(IF_OF_Latch.isOF_enable())
		{
			// loading the instruction from the IF_OF_latch
			int newInstruction = IF_OF_Latch.getInstruction();
			
			int opcode = (newInstruction & 0xF8000000) >>> 27;
			//now calculating the immx and branchTarget
			int immx = (newInstruction & 0x0001FFFF);
			if ((immx & 0x10000) != 0) immx |= 0xFFFE0000;
			int branchTarget = (newInstruction & 0x003FFFFF); // this takes the 22 rightmost bits
			if ((branchTarget & 0x200000) != 0) branchTarget |= 0xFFC00000; // sign extension for branch target
			// now getting the opcode becoz we need this so that we can calculate the signals
			// calculating the rs1,rs2,rd
			// here we are considering the R3 type of format 
			int rs1 = (newInstruction & 0x07C00000) >>> 22;
			int rs2 = (newInstruction & 0x003E0000) >>> 17;
			int rd = (newInstruction & 0x0001F000) >>> 12;
			System.out.println("Instruction : " +newInstruction + " opcode : " + opcode + " rs1 : " + rs1 + " rs2 : " + rs2 + " rd : " + rd + " immx : " + immx + " branchTarget : " + branchTarget);
			
			//so screw that im just gonna send everything
			OF_EX_Latch.setOpcode(opcode);
			OF_EX_Latch.setRs1(rs1);
			OF_EX_Latch.setRs2(rs2);
			OF_EX_Latch.setRd(rd);
			OF_EX_Latch.setImmx(immx);
			OF_EX_Latch.setBranchTarget(branchTarget);

			// so what i did?
			// i took the instruction from the IF-OF latch
			// forwarded these values to the OF-EX latch

			IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

}
