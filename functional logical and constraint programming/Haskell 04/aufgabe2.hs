alternativeEither :: [Either a b] -> [Either b a]
alternativeEither [] = []
alternativeEither (Left x:xs) = Right x : alternativeEither xs
alternativeEither (Right x:xs) = Left x : alternativeEither xs


countValues :: [Maybe a] -> Int
countValues [] = 0
countValues (Just x:xs) = 1 + countValues xs
countValues (Nothing :xs) = 0 + countValues xs


getValues :: [Maybe a] -> [a]
getValues [] = []
getValues (Just x:xs) = x : getValues xs
getValues (Nothing :xs) = getValues xs


maybeFilter :: (a -> Bool) -> [a] -> [Maybe a]
maybeFilter f x = foldr (\x xs -> if f x then [Just x] ++ xs else [Nothing] ++ xs) [] x
