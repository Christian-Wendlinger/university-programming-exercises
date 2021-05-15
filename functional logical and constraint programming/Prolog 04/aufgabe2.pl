:- use_module(library(chr)).
:- chr_constraint brett/1, leim/0, regal/1, leim/1, brett/2.

build @ brett(lang), brett(lang), brett(lang), regal(X) <=> Y is X + 1, regal(Y), brett(kurz).
leiming @ brett(kurz), brett(kurz), leim <=> brett(lang).

generate_lime @ leim(1) <=> leim.
generate_lime @ leim(N) <=> N > 1 | N1 is N - 1, leim(N1), leim.

generate_brett @ brett(X,1) <=> brett(X).
generate_brett @ brett(X, N) <=> N > 1 | N1 is N - 1, brett(X, N1), brett(X).
