ALTER TABLE public.pytanie
ADD COLUMN pozycja_x DECIMAL(15, 5),
ADD COLUMN pozycja_y DECIMAL(15, 5);

ALTER TABLE public.hipoteza
ADD COLUMN pozycja_x DECIMAL(15, 5),
ADD COLUMN pozycja_y DECIMAL(15, 5);
