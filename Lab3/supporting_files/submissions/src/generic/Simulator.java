package generic;

import java.io.*;
import processor.Clock;
import processor.Processor;

public class Simulator {
		
	static Processor processor;
	static boolean simulationComplete;
	
	public static void setupSimulation(String assemblyProgramFile, Processor p)
	{
		Simulator.processor = p;
		loadProgram(assemblyProgramFile);
		
		simulationComplete = false;
	}
	
	static void loadProgram(String assemblyProgramFile)
	{
		System.out.println("loading program into memory...");

		try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(assemblyProgramFile)))){
			//-----------------------------------------------------------------------------------------------
			// storing the assembly file in main memory
			int lineNumber = 0;
			int initInstAdd = dis.readInt();
            while (dis.available() > 0) {
                int lineInt = dis.readInt();
				System.out.println("line number : " + lineNumber + " line int : " + lineInt);
                processor.getMainMemory().setWord(lineNumber++, lineInt);
            }
			//-----------------------------------------------------------------------------------------------
			// setting the pc in register file
			processor.getRegisterFile().setProgramCounter(initInstAdd);

			//-----------------------------------------------------------------------------------------------
			// setting the register x1 as 65535 and x2 as 65535
			processor.getRegisterFile().setValue(1, 65535);
			processor.getRegisterFile().setValue(2, 65535);

			
			//-----------------------------------------------------------------------------------------------

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Program loading complete!");
		/*
		 * TODO
		 * 1. load the program into memory according to the program layout described
		 *    in the ISA specification
		 * 2. set PC to the address of the first instruction in the main
		 * 3. set the following registers:
		 *     x0 = 0
		 *     x1 = 65535
		 *     x2 = 65535
		 */
	}
	
	public static void simulate()
	{
		System.out.println("Starting the simulation...");
		int instructionCount = 0;

		while(simulationComplete == false)
		{
			System.out.println("Current Instruction Count : " + instructionCount);
			//System.out.println("Entering Instruction Fetch Stage");
			processor.getIFUnit().performIF();
			//System.out.println("Exiting Instruction Fetch Stage");
			Clock.incrementClock();
			//System.out.println("Entering Operand Fetch Stage");
			processor.getOFUnit().performOF();
			//System.out.println("Exiting Operand Fetch Stage");
			Clock.incrementClock();
			//System.out.println("Entering Execute Stage");
			processor.getEXUnit().performEX();
			//System.out.println("Exiting Execute Stage");
			Clock.incrementClock();
			//System.out.println("Entering Memory Access Stage");
			processor.getMAUnit().performMA();
			//System.out.println("Exiting Memory Access Stage");
			Clock.incrementClock();
			//System.out.println("Entering Register Write Stage");
			processor.getRWUnit().performRW();
			//System.out.println("Exiting Register Write Stage");
			Clock.incrementClock();
			instructionCount++;
			//processor.printState(0, 30); // ((0, 0) refers to the range of main memory addresses we wish to print. this is an empty set.

		}
		

		// TODO
		// set statistics

		Statistics.setNumberOfInstructions(instructionCount);
		Statistics.setNumberOfCycles((int)Clock.getCurrentTime());

	}
	
	public static void setSimulationComplete(boolean value)
	{
		simulationComplete = value;
	}
}
