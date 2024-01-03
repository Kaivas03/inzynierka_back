--
-- przykładowe dane
--

-- badanie
insert into public.badanie (nazwa, utworzenie_timestamp, edycja_timestamp)
values
    ('Przykładowe badanie 1', current_timestamp, current_timestamp),
    ('Przykładowe badanie 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- pytanie
insert into public.pytanie (id_hipoteza, id_pytanie, tekst, utworzenie_timestamp, edycja_timestamp)
values
    (1, null, 'Przykładowy tekst pytanie 1', current_timestamp, current_timestamp),
    (2, 1, 'Przykładowy tekst pytanie 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 2, 'Przykładowy tekst pytanie 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- pytanie_kod
insert into public.pytanie_kod (id_pytanie, id_kod)
values
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 3);

-- pytanie_grupa_kodow
insert into public.pytanie_grupa_kodow (id_pytanie, id_grupa_kodow)
values
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 2);

-- cytat
insert into public.cytat (id_kod, id_wywiad, numer_linijki, tekst, utworzenie_timestamp, edycja_timestamp)
values
    (1, 1, 1, 'Przykładowy tekst cytat 1', current_timestamp, current_timestamp),
    (2, 2, 2, 'Przykładowy tekst cytat 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 3, 'Przykładowy tekst cytat 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- wywiad
insert into public.wywiad (id_badanie, nazwa, tekst, utworzenie_timestamp, edycja_timestamp)
values
    (1, 'Wywiad 1', 'Przykładowy tekst wywiad 1', current_timestamp, current_timestamp),
    (2, 'Wywiad 2', 'Przykładowy tekst wywiad 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- hipoteza
insert into public.hipoteza (id_badanie, tekst, utworzenie_timestamp, edycja_timestamp)
values
    (1, 'Przykładowa hipoteza 1', current_timestamp, current_timestamp),
    (2, 'Przykładowa hipoteza 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- grupa_kodow
insert into public.grupa_kodow (id_badanie, nazwa, utworzenie_timestamp, edycja_timestamp)
values
    (1, 'Grupa kodów 1', current_timestamp, current_timestamp),
    (2, 'Grupa kodów 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- kod
insert into public.kod (id_grupa_kodow, id_badanie, nazwa, utworzenie_timestamp, edycja_timestamp)
values
    (1, 1, 'Przykładowy kod 1', current_timestamp, current_timestamp),
    (2, 1, 'Przykładowy kod 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 2, 'Przykładowy kod 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
