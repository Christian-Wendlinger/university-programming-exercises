:- use_module(library(chr)).
:- chr_constraint fib/1, summe/1.

r0 @ fib(X) <=> X > 1 | X1 is X - 1, X2 is X - 2, fib(X1), fib(X2).
r1 @ fib(0) <=> summe(1).
r2 @ fib(1) <=> summe(1).
r3 @ summe(X), summe(Y) <=> N is X + Y, summe(N).