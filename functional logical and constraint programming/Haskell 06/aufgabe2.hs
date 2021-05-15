type Breite = Int
type Höhe = Int
type Farbe = String

data Legu = Stein Breite Höhe Farbe
          | Dach Breite Höhe Farbe
          | Eins Legu Legu
          | Zwei Legu Legu Legu Legu
          | Teilung Breite
    deriving(Show)




bsp = Zwei (Stein 8 3 "blau")
           (Zwei (Stein 3 2 "grün")
                (Dach 1 1 "rot")
                (Teilung 0)
                (Eins (Stein 2 1 "blau")
                     (Dach 2 1 "gelb")))
           (Teilung 0)
           (Zwei (Stein 5 1 "grün")
                (Zwei (Stein 2 2 "gelb")
                     (Dach 1 2 "rot")
                     (Teilung 0)
                     (Dach 1 2 "rot"))
                (Teilung 1)
                (Eins (Stein 2 2 "blau")
                     (Dach 1 1 "grün")))




foldLegu :: (Int -> Int -> String -> a)
            -> (Int -> Int -> String -> a)
            -> (a -> a -> a)
            -> (a -> a -> a -> a -> a)
            -> (Int -> a)
            -> Legu
            -> a

foldLegu fs fd fe fz ft = m
        where
          m (Stein b h f) = fs b h f
          m (Dach b h f) = fd b h f
          m (Eins l1 l2) = fe (m l1) (m l2)
          m (Zwei l1 l2 l3 l4) = fz (m l1) (m l2) (m l3) (m l4)
          m (Teilung b) = ft b


correct :: Legu -> Bool
correct l = w > 0
    where w = foldLegu (\b _ _ -> b) (\b _ _ -> b) (\l1 l2 -> if l1 >= l2 && l2 > 0 then l1 else 0) (\l1 l2 l3 l4 -> if l1 >= l2 + l3 + l4 && l2 > 0 && l4 > 0 then l1 else 0) (\b -> b) l
