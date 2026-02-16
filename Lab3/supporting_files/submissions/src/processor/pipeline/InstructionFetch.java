package processor.pipeline;

import processor.Processor;

public class InstructionFetch {
	
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performIF()
	{
		if(IF_EnableLatch.isIF_enable())
		{
			// so initially getting the current pc or what ever the first instruction is at
			int currentPC = containingProcessor.getRegisterFile().getProgramCounter();

			// now if the branch instruction is active then we have to set current pc to this BranchTarget value
			if(EX_IF_Latch.isEX_IF_enable()){
				currentPC = EX_IF_Latch.getBranchTarget();
				EX_IF_Latch.setEX_IF_enable(false);
			}

			//now we fetch this instruction at current pc
			int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
			// send this to the next segment through IF_OF Latch
			IF_OF_Latch.setInstruction(newInstruction);

			System.out.println("Current PC :" + currentPC);
			//now updating the pc so that it points to the next instruction
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);
			
			// now disabling the IF stage and also enabling the next stage
			IF_EnableLatch.setIF_enable(false);
			IF_OF_Latch.setOF_enable(true);
		}
	}

}
