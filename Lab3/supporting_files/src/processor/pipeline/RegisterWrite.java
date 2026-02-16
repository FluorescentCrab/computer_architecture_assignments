package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	
	public RegisterWrite(Processor containingProcessor, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			int opcode = MA_RW_Latch.getOpcode();
			int aluResult = MA_RW_Latch.getAluResult();
			int rd = MA_RW_Latch.getRd();
			int immx = MA_RW_Latch.getImmx();	
			int ldResult = MA_RW_Latch.getLdResult();
			int rs1 = MA_RW_Latch.getRs1();
			int rs2 = MA_RW_Latch.getRs2();
			int branchTarget = MA_RW_Latch.getBranchTarget();	
			int special = MA_RW_Latch.getSpecial();

			if(opcode < 22){
				if(opcode%2 == 0){
					containingProcessor.getRegisterFile().setValue(rd, aluResult);
				}
				else{
					containingProcessor.getRegisterFile().setValue(rs2, aluResult);
				}
				if(special > 0)containingProcessor.getRegisterFile().setValue(31, special);
			}
			else if(opcode == 22){
				containingProcessor.getRegisterFile().setValue(rs2, ldResult);
			}

			if(opcode == 29){
				Simulator.setSimulationComplete(true);
			}

			MA_RW_Latch.setRW_enable(false);
			IF_EnableLatch.setIF_enable(true);
		}
	}

}
