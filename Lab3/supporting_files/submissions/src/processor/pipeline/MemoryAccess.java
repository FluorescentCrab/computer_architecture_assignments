package processor.pipeline;

import processor.Processor;

public class MemoryAccess {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	
	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
	}
	
	public void performMA()
	{
		//TODO
		if(EX_MA_Latch.isMA_enable()){

			int opcode = EX_MA_Latch.getOpcode();
			int aluResult = EX_MA_Latch.getAluResult();
			int rs2 = EX_MA_Latch.getRs2();
			int rd = EX_MA_Latch.getRd();	
			int immx = EX_MA_Latch.getImmx();
			int rs1 = EX_MA_Latch.getRs1();
			int branchTarget = EX_MA_Latch.getBranchTarget();
			int special = EX_MA_Latch.getSpecial();
			
			int ldResult = 0;

			if(opcode == 22){
				ldResult = containingProcessor.getMainMemory().getWord(aluResult);
			}
			else if(opcode == 23){
				containingProcessor.getMainMemory().setWord(aluResult, containingProcessor.getRegisterFile().getValue(rs1));
			}

			MA_RW_Latch.setOpcode(opcode);
			MA_RW_Latch.setAluResult(aluResult);
			MA_RW_Latch.setRd(rd);
			MA_RW_Latch.setImmx(immx);
			MA_RW_Latch.setBranchTarget(branchTarget);
			MA_RW_Latch.setRs1(rs1);
			MA_RW_Latch.setRs2(rs2);
			MA_RW_Latch.setLdResult(ldResult);
			MA_RW_Latch.setSpecial(special);


			EX_MA_Latch.setMA_enable(false);
			MA_RW_Latch.setRW_enable(true);
		}
	}

}
