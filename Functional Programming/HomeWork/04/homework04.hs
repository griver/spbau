module HomeWork04 where
--(1)  Реализуйте функцию, описывающую плотность равномерного распределения на промежутке от 3 до 4.

uniformDensity a b x | x <= b && x >= a = 1/(b - a)
                     | otherwise = 0  

densityFunc n = uniformDensity 3 4 n 


--(2) ализуйте функцию, находящую наибольший общий делитель двух целых чисел с помощью алгоритма Евклида:

myGCD :: Int-> Int -> Int

myGCD a b | b == 0 = a
          | otherwise = myGCD b (a `mod` b)                

--(3) Реализуйте функции, находящие сумму и количество цифр заданного целого числа.

byDigit f n = fst $ until ( \x -> (snd x) == 0 ) f (0, n)

digitSum :: Int -> Int

digitSum n  = byDigit sum  (abs n) 
    where sum p = ((fst p)+((snd p) `mod` 10), (snd p) `div` 10) 

digitCount :: Int -> Int

digitCount 0 = 1
digitCount n  = byDigit count (abs n)          
    where count p = ((fst p)+1, (snd p) `div` 10) 


--(4) Реализуйте функцию, находящую элементы следующей рекурентной последовательности
getNth 1 = 2
getNth 0 = 1

getNth n = 
    let helper t0 t1 t2 i n  | i < n  = helper t1 t2 (t2 + t1 - 2*t0) (i+1) n
                             | otherwise = t2
    in helper 1 2 3 2 n


--(5) - Реализуйте функцию, находящую значение определённого интеграла от заданной функции на заданном интервале по методу трапеций.
trapMethod func a b | b < a  = (-(trapMethod func b a))
                    | b == a = 0
                    | otherwise = ((b - a)/1000)*( (func a + func b)/ 2 + methodSum func a b) 

methodSum f a b =snd $ until (\x -> fst x > b ) app (a + h , 0)
    where h = (b - a) / 1000 
          app p = (x + h,  acc + (f x))
            where x = fst p
                  acc = snd p
                  

--(6) Реализуйте функцию, находящую значение квадратного корня методом Ньютона
goodEnouth a b = abs((a^^2) - b) < 0.000001


newtonSqrt x | x < 0 =  print ("This method does not support the calculation with complex numbers") 
             | x == 0 = print (0)
             | otherwise = print (helper 1.0 x)  
                where helper a b | goodEnouth a b = a
                                 | otherwise = helper ( (a + b/a) / 2) b
            

