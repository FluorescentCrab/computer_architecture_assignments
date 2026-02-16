package processor.pipeline;

import processor.Processor;

public class Execute {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performEX()
	{
		if(OF_EX_Latch.isEX_enable()){

			
			int opcode = OF_EX_Latch.getOpcode();
			int rs1 = OF_EX_Latch.getRs1();
			int rs2 = OF_EX_Latch.getRs2();
			int rd = OF_EX_Latch.getRd();
			int immx = OF_EX_Latch.getImmx();
			int branchTarget = OF_EX_Latch.getBranchTarget();
			
			long aluLong = 0;
			int special = 0;
			int aluResult = 0;
			// ok now arithematic instruction kelyat
			if(opcode < 22){
				// so now loading the values
				int op1 = containingProcessor.getRegisterFile().getValue(rs1);
				int op2 = containingProcessor.getRegisterFile().getValue(rs2);
				// if operation i type wala asla tar 
				if(opcode % 2 == 1) op2 = immx;

				// now apan operations karu
				//add and addi
				if(opcode <= 1) aluLong = op1 + op2;
				// sub and subi
				else if(opcode <= 3) aluLong = op1 - op2;
				// mul and muli
				else if(opcode <= 5) aluLong = op1 * op2;
				// div and divi
				else if(opcode <= 7) aluLong = op1 / op2;
				// and and andi
				else if(opcode <= 9) aluLong = op1 & op2;
				// or and ori
				else if(opcode <= 11) aluLong = op1 | op2;
				// xor and xori
				else if(opcode <= 13) aluLong = op1 ^ op2;
				// slt and slti
				else if(opcode <= 15) aluLong = (op1 < op2) ? 1 : 0;
				// sll and slli
				else if(opcode <= 17) aluLong = op1 << op2;
				// srl and srli
				else if(opcode <= 19) aluLong = op1 >> op2;
				// sra and srai
				else if(opcode <= 21) aluLong = op1 >>> op2;

				aluResult = (int)aluLong;
				special = (int)(aluLong >> 32);

				if(opcode == 6 || opcode == 7){
					special = op1%op2;
				}
				else if(opcode == 16 || opcode == 17){
					special = (op1 >> (32 - op2));
				}
				else if(opcode >= 18){
					special = (op1 << (32 - op2));
					special = (special >> (32 - op2));
				}
			}
			else if(opcode < 24){
				if(opcode == 22) aluResult = containingProcessor.getRegisterFile().getValue(rs1) + immx;
				else aluResult = containingProcessor.getRegisterFile().getValue(rs2)  + immx;
			}
			else{
				int op1 = containingProcessor.getRegisterFile().getValue(rs1);
				int op2 = containingProcessor.getRegisterFile().getValue(rs2);
				System.out.println("op1 : " + op1 + " op2 : " + op2);


				if(opcode == 24) {
					aluResult = containingProcessor.getRegisterFile().getProgramCounter() - 1 + rs1 + branchTarget;
					EX_IF_Latch.setEX_IF_enable(true);
					EX_IF_Latch.setBranchTarget(aluResult);
				}
				else if((opcode == 25) && (op1 == op2)){
					aluResult = containingProcessor.getRegisterFile().getProgramCounter() - 1 + immx;
					EX_IF_Latch.setEX_IF_enable(true);
					EX_IF_Latch.setBranchTarget(aluResult);
				}
				else if((opcode == 26) && (op1 != op2)){
					 aluResult = containingProcessor.getRegisterFile().getProgramCounter() - 1 + immx;
					EX_IF_Latch.setEX_IF_enable(true);
					EX_IF_Latch.setBranchTarget(aluResult);
				}
				else if((opcode == 27) && (op1 < op2)) {
					aluResult = containingProcessor.getRegisterFile().getProgramCounter() - 1 + immx;
					EX_IF_Latch.setEX_IF_enable(true);
					EX_IF_Latch.setBranchTarget(aluResult);
				}
				else if((opcode == 28) && (op1 > op2)) {
					aluResult = containingProcessor.getRegisterFile().getProgramCounter() - 1 + immx;
					EX_IF_Latch.setEX_IF_enable(true);
					EX_IF_Latch.setBranchTarget(aluResult);
				}
			
			}

			EX_MA_Latch.setOpcode(opcode);
			EX_MA_Latch.setRd(rd);
			EX_MA_Latch.setRs1(rs1);	
			EX_MA_Latch.setRs2(rs2);
			EX_MA_Latch.setImmx(immx);
			EX_MA_Latch.setBranchTarget(branchTarget);
			EX_MA_Latch.setAluResult(aluResult);
			EX_MA_Latch.setSpecial(special);

			OF_EX_Latch.setEX_enable(false);
			EX_MA_Latch.setMA_enable(true);
			//==============================================================================

		}
	}

}
