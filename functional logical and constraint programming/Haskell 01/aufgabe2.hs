fib :: Int -> Int
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2)

even' :: Int -> Bool
even' 0 = True
even' 1 = False
even' z = even' (abs z-2)
