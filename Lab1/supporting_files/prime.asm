	.data
a:
	56
	.text
main:
	load %x0, $a, %x3	@loading the number in x3
	addi %x0, 2, %x4	@set x4 to 1
	subi %x3, 1, %x5	@set the upper bound
	jmp loop
loop:
	blt %x5, %x4, success	@if the number b becomes greater than upper bound we are done
	div %x3, %x4, %x6	@divide the number a by b and store the result in x6
	mul %x4, %x6, %x7	@reconstruct the number in x7
	sub %x3, %x7, %x8	@sub the ori number - reconstructed number and store it in x6
	beq %x8, %x0, fail	@ if the remainder is 0 then a is not a prime number
	addi %x4, 1, %x4		@inc b by 1
	jmp loop
success:
	addi %x0, 1, %x10
	end
fail:
	subi %x0, 1, %x10
	end
