
import Data.Function( fix, on )

--Реализуйте функцию, утраивающую значение своего аргумента.
triple = \x -> x*3


--Реализуйте функцию, возвращащую знак числа (1, 0 или -1).
mySignum  n | n > 0 = 1
            | n < 0 = -1
            | otherwise = n



--Реализуйте следующие двухместные логические операции: стрелку Пирса
--(Peirce’s arrow, NOR) и штрих Шеффера (Sheffer stroke, NAND).

pArrow :: Bool -> Bool -> Bool
pArrow x y | x == False && y == False = True
           | otherwise = False


sStroke :: Bool -> Bool -> Bool
sStroke x y = if (not x || not y) then True
              else False



fib n = 
    let helper one two i n | n == 0 = 0
                           | i < n  = helper two (one + two) (i + 1) n
                           | otherwise = two
    in helper 0 1 1 n
                                
myFlip :: (a -> b -> c) -> b -> a -> c
myFlip f x y = f y x 



fact' f n | n == 0 = 1
          | otherwise = (*) n (f (n-1))

myFact n = (fix fact') n  


x *+* y  = ((+) `on` (^2) ) x y


--еще одно выражение выражение факториала через until

untilFact n = fst $ until (\x -> snd x >= n) ss zz
    where zz   = (1,0)
          ss p = (f*(s+1),s+1)
            where f = fst p
                  s = snd p

--Попробуйте реализовать подобным образом эффективный алгоритм вычисления чисел Фибоначчи 
untilFib 0 = 0
untilFib n = snd $ snd $ until (\x -> fst x >= n) next start
    where start    = (1, (0 , 1))
          next val = ((t1+ 1), (t3, (t3+t2)) )
            where t1 = fst val
                  t2 = fst (snd val)
                  t3 = snd (snd val)  
        
