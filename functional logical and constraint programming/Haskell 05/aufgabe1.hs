mmap :: (a -> b) -> Maybe a -> Maybe b
mmap _ Nothing = Nothing
mmap f (Just x) = Just (f x)


emap :: (a -> c) -> (b -> d) -> Either a b -> Either c d
emap f _ (Left x) = Left (f x)
emap _ f (Right x) = Right (f x)


mfilter :: Eq a => [Maybe a] -> [Maybe a]
mfilter = filter (\x -> if x == Nothing then False else True)


mfilter' :: [Maybe a] -> [Maybe a]
mfilter' [] = []
mfilter' (Nothing : xs) = mfilter' xs
mfilter' (Just x : xs) = Just x : mfilter' xs
