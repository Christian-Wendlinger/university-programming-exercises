:- use_module(library(chr)).
:- chr_constraint num/1, sum/1, notCountable/0, list/1, counted/1, elem/2.

% a)
r0 @ num([]) <=> true.
r1 @ num([X | Y]) <=> X mod 2 =:= 0 | sum(1), num(Y).
r2 @ num([X | Y]) <=> X mod 2 =:= 1 | notCountable, num(Y).
r3 @ sum(N) \ notCountable <=> N > 0 | true.
r4 @ notCountable \ notCountable <=> true.
r5 @ sum(X), sum(Y) <=> N is X + Y, sum(N).


% b)
r11 @ list([X | Y]) <=> counted([(X, 1)]), list(Y).
r12 @ counted([(X, N1)]), counted([(X, N2)]) <=> N is N1 + N2, counted([(X,N)]).
r13 @ counted(X), counted(Y), list([]) <=> append(X,Y,R), counted(R), list([]).
r14 @ list([]) <=> true.

% c)
r21 @ elem(I1, N1), elem(I2, N2) <=> I1 =< I2, N1 > N2 | I is I1 + 1, elem(I2, N2), elem(I, N1).