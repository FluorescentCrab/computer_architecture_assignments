	.data
a:
	10011
	.text
main:
	load %x0, a, %x3	@the number a in x3
	addi %x0, 65535, %x4	@the starting pointer in x4
	addi %x0, 65536, %x5	@the ending pointer in x5
	addi %x0, 1, %x9	@storing 1 in x9
	jmp loop
loop:
	blt %x3, %x9, comp	@if the number becomes less than 0 then stop and start comparing
	add %x0, %x3, %x6	@store the number in x6
	divi %x3, 10, %x3	@divide the number by 10
	muli %x3, 10, %x7	@mul the number and store it in x7
	sub %6, %x7, %x8	@store the remainder in x8
	subi %x5, 1, %x5	@dec the address pointer
	store %x8, 0, %x5	@store the remainder in mem
	jmp loop		@again to the loop
comp:
	bgt %x5, %x4, success	@ when they pass each other
	load %x4, 0, %x6	@load value at the starting pointer in x6
	load %x5, 0, %x7	@load value at the ending pointer in x7
	bne %x6, %x7, fail
	subi %x4, 1, %x4	@dec the staring pointer
	addi %x5, 1, %x5	@inc the ending pointer
	jmp comp
success:
	addi %x0, 1, %x10
	end
fail:
	subi %x0, 1, %x10
	end