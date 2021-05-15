harshad :: Integer -> Bool
harshad a = if mod a (quersumme a) == 0 then True else False


quersumme :: Integer -> Integer
quersumme a | a < 10 = a
            | otherwise = mod a 10 + quersumme (div a 10)
