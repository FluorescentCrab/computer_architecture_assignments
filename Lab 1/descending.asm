	.data
a:
	70
	80
	40
	20
	10
	30
	50
	60
n:
	8
	.text
main:
	load %x0, $n, %x3	@size of the array in %x3
	add %x0, %x3, %x4	@ i = n in %x4
	subi %x4, 1, %x4	@ now this 'i' points to the final element
	jmp loopINIT
loopINIT:
	blt %x4, %x0, done	@if i is less than 0 then break the loop and go to done
	load %x4, $a, %x5	@load the value of a[i] and store it in %x5 this is our min_elemen
	subi %x4, 1, %x6	@set j = i - 1 in %x6		
	jmp loop		@jump to the loop label
loop:
	blt %x6, %x0, loopEND	@if the loop is done then go to loopEND
	load %x6, $a, %x7	@load a[j] in %x7
	bgt %x5, %x7, swap	@now if a[j] < a[i] replace a[j] with a[i] and also update the min_element with a[j]
	subi %x6, 1, %x6	@else j-- and go to loop again
	jmp loop
swap:
	store %x7, $a, %x4
	store %x5, $a, %x6	@swaped a[i] and a[j] 
	add %x0, %x7, %x5	@setting %x5 as %x7 ,i.e changing the min_element
	subi %x6, 1, %x6	@else j-- and go to loop again
	jmp loop
loopEND:
	subi %x4, 1, %x4	@	i--
	jmp loopINIT		@go to loopINIT
done:
	end