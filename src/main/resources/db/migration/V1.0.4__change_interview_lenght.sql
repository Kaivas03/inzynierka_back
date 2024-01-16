-- Zmień maksymalną długość pola tekstowego na 25000
ALTER TABLE public.wywiad
ALTER COLUMN tekst TYPE VARCHAR(25000);
