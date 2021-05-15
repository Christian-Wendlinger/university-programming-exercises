:- use_module(library(chr)).
:- chr_constraint edge/2, path/2.

init1 @ edge(A,B) <=> path(A,B).
init2 @ edge(A,B) ==> path(B,A).
init3 @ path(A,B) \ path(A,B) <=> true.

transitivity @ edge(A,B), path(B,C) ==> path(A,C).
