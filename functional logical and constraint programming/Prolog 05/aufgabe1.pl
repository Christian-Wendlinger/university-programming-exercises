:- use_module(library(chr)).
:- chr_constraint init/0, pos/2, obstacle/2, north/0, east/0, south/0, west/0, m/0, t/0, check/2.

% a)
init <=> pos(0,0), north, obstacle(0,2), obstacle(3,1), obstacle(2,4).

% b)
north,t <=> east.
east,t <=> south.
south,t <=> west.
west,t <=> north.

% c)
north, pos(X,Y) \ m <=> Yn is Y + 1, check(X, Yn).
east, pos(X,Y) \ m <=> Xn is X + 1, check(Xn, Y).
south, pos(X,Y) \ m <=> Yn is Y - 1, check(X, Yn).
west, pos(X,Y) \ m <=> Xn is X - 1, check(Xn, Y).

% d)
pos(_,_),check(Xc, Yc),obstacle(Xo, Yo) <=> Xo =\= Xc;Yo =\= Yc | pos(Xc, Yc),obstacle(Xo,Yo).
pos(_,_),obstacle(Xo, Yo) \ check(Xc, Yc) <=> Xo =:= Xc,Yo =:= Yc | true.

% e)
:- chr_constraint ml/1.
ml([]) <=> true.
ml([t | R]) <=> t, ml(R).
ml([m | R]) <=> m, ml(R).