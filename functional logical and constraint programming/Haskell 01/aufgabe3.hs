fib :: Int -> Int
fib n = case n of
          0 -> 0
          1 -> 1
          n -> fib (n-1) + fib(n-2)


binom :: Int -> Int -> Int
binom n 0 = 1
binom n k | k == n = 1
          | otherwise = binom (n-1) (k-1) + binom (n-1) k


ggT :: Int -> Int -> Int
ggT a b | b == 0 = a
        | otherwise = ggT b (mod a b)
