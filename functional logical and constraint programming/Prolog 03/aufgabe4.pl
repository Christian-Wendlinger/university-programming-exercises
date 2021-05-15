builtin(B) :- predicate_property(B, built_in).

fac(N, R) :- N > 0, N1 is N - 1, fac(N1, R1), R is N * R1.
fac(0, 1).

fib(N, R) :- N > 1, N1 is N-1, N2 is N-2, fib(N1, R1), fib(N2, R2), R is R1+R2.
fib(0, 1).
fib(1, 1).

%a)
proveCount(true, 0).

%b)
proveCount((Ziel1, Ziel2), Anzahl) :- proveCount(Ziel1, R1), proveCount(Ziel2, R2), Anzahl is R1 + R2.

%c)
proveCount(B, 1) :- builtin(B) -> B.