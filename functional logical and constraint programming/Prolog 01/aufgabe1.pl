wohntIn(alex, ulm).
wohntIn(marcel, ulm).
wohntIn(sabrina, ulm).
wohntIn(stefanos, ulm).

wohntIn(sabrina, muenchen).
wohntIn(jonas, muenchen).
wohntIn(stefanos, muenchen).
wohntIn(annika, muenchen).

wohntIn(johannes, stuttgart).
wohntIn(annika, stuttgart).

zweitwohnsitz(X) :- wohntIn(X, Y), wohntIn(X,Z), Y \= Z.