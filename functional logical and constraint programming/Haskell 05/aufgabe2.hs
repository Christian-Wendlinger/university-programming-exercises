data Network = Empty
             | Internet
             | Switch String Network Network Network
             | Router String Network Network
             | Pc String Int
     deriving Show


network = Router "Fritzbox"
    Internet
      (Switch "Switch"
        (Pc "Gamerkiste" 500)
        (Pc "Mediensklave" 4000)
        (Switch "YetAnotherSwitch"
          (Pc "Hex" 5)
          (Pc "HAL9000" 90000)
          Empty
        )
      )



foldNetwork :: a -> a -> (String -> a -> a -> a -> a) -> (String -> a -> a -> a) -> (String -> Int -> a) -> Network -> a
foldNetwork fe fi fs fr fp = m
  where
    m Empty = fe
    m Internet = fi
    m (Switch name n1 n2 n3) = fs name (m n1) (m n2) (m n3)
    m (Router name n1 n2) = fr name (m n1) (m n2)
    m (Pc name wert) = fp name wert


countCables :: Network -> Int
countCables = foldNetwork 0 0 (\_ n1 n2 n3 -> 1 + n1 + n2 + n3) (\_ n1 n2 -> 1 + n1 + n2) (\_ _ -> 1)


maxDistance :: Network -> Int
maxDistance = foldNetwork 0 0 (\_ n1 n2 n3 -> 1 + maximum [n1, n2, n3]) (\_ n1 n2 -> max n1 n2) (\_ _ -> 0)


listPc :: Network -> [Network]
listPc = foldNetwork [] [] (\_ n1 n2 n3 -> n1 ++ n2 ++ n3) (\_ n1 n2 -> n1 ++ n2) (\name wert -> [Pc name wert])


antivir :: Network -> Network
antivir = foldNetwork Empty Internet (\name n1 n2 n3 -> Switch name n1 n2 n3) (\name n1 n2 -> Router name n1 n2) (\_ _ -> Empty)
