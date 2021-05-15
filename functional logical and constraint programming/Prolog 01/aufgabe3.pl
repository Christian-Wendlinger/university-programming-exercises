add(X,Y,R) :- R is X + Y.
sub(X,Y,R) :- R is X - Y.
modulo(X,Y,R) :- R is mod(X,Y).

fac(0,1).
fac(N,R):- N > 0, N1 is N-1, fac(N1, R1), R is N * R1.

fib(0,1).
fib(1,1).
fib(N, R) :- N > 1, N1 is N-1, N2 is N-2, fib(N1, R1), fib(N2, R2), R is R1 + R2.


fib2(N,R) :- N > 0, fibacc(N, 0, 1, R).

fibacc(1,_,R,R).
fibacc(N,A1,A2,R) :- N1 is N-1, A is A1+A2, fibacc(N1,A2,A,R).