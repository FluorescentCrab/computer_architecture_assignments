	.data
n:
	10
	.text
main:
	load %x0, $n, %x3	@loaded the value of 'n'
	addi %x0, 65535, %x4	@pointed to the address we want to store
	addi %x0, 0, %x5	@set the 1st fibo
	addi %x0, 1, %x6	@set the 2nd fibo
	store %x5, 0, %x4	@stored the value of f(0)
	subi %x4, 1, %x4	@dec the pointer
	store %x6, 0, %x4	@stored the value of f(1)
	subi %x4, 1, %x4	@dec the pointer
	subi %x3, 2, %x3	@reduced n by 2
	jmp loop		@jumping to the loop
loop:
	blt %x3, %x0, done	@compare the n and 0 if n < 0 then stop the loop
	add %x5, %x6, %x7	@f3 = f2 + f1
	store %x7, 0, %x4	@store the value of f3 in address
	subi %x4, 1, %x4	@dec the add by 1
	addi %x6, 0, %x5	@f1 = f2
	addi %x7, 0, %x6	@f2 = f3
	subi %x3, 1, %x3	@dec n by 1
	jmp loop		@restart the loop
done:
	end			@end the program