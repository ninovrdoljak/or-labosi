--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

-- Started on 2020-10-27 01:54:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2994 (class 1262 OID 16437)
-- Name: or-labos; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "or-labos" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Croatian_Croatia.1250';


ALTER DATABASE "or-labos" OWNER TO postgres;

\connect -reuse-previous=on "dbname='or-labos'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 16453)
-- Name: grad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grad (
    nazivgrada character varying(50) NOT NULL,
    naziv character varying(50) NOT NULL
);


ALTER TABLE public.grad OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16448)
-- Name: zupanija; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zupanija (
    naziv character varying(50) NOT NULL,
    sjediste character varying(50) NOT NULL,
    stanovnistvo integer NOT NULL,
    brojgradova integer NOT NULL,
    brojopcina integer NOT NULL,
    brojnaselja integer NOT NULL,
    povrsina integer NOT NULL,
    zupan character varying(50) NOT NULL,
    wikipoveznica character varying(50) NOT NULL,
    gdppercapita integer NOT NULL
);


ALTER TABLE public.zupanija OWNER TO postgres;

--
-- TOC entry 2988 (class 0 OID 16453)
-- Dependencies: 201
-- Data for Name: grad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Orahovica', 'Virovitičko-podravska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Slatina', 'Virovitičko-podravska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Virovitica', 'Virovitičko-podravska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Ivanić Grad', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Samobor', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Velika Gorica', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Dugo Selo', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Jastrebarsko', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Sveta Nedelja', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Sveti Ivan Zelina', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Vrbovec', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Zaprešić', 'Zagrebačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Karlovac', 'Karlovačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Duga resa', 'Karlovačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Ogulin', 'Karlovačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Slunj', 'Karlovačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Ozalj', 'Karlovačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Đurđevac', 'Koprivničko-križevačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Koprivnica', 'Koprivničko-križevačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Križevci', 'Koprivničko-križevačka županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Nova Gradiška', 'Brodsko-posavska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Slavonski Brod', 'Brodsko-posavska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Čakovec', 'Međimurska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Prelog', 'Međimurska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Mursko Središće', 'Međimurska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Dubrovnik', 'Dubrovačko-neretvanska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Korčula', 'Dubrovačko-neretvanska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Metković', 'Dubrovačko-neretvanska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Opuzen', 'Dubrovačko-neretvanska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Ploče', 'Dubrovačko-neretvanska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Ilok', 'Vukovarsko-srijemska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Otok', 'Vukovarsko-srijemska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Vinkovci', 'Vukovarsko-srijemska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Županja', 'Vukovarsko-srijemska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Vukovar', 'Vukovarsko-srijemska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Gospić', 'Ličko-senjska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Novalja', 'Ličko-senjska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Otočac', 'Ličko-senjska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Senj', 'Ličko-senjska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Kutjevo', 'Požeško-slavonska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Lipik', 'Požeško-slavonska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Pakrac', 'Požeško-slavonska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Požega', 'Požeško-slavonska županija');
INSERT INTO public.grad (nazivgrada, naziv) VALUES ('Pleternica', 'Požeško-slavonska županija');


--
-- TOC entry 2987 (class 0 OID 16448)
-- Dependencies: 200
-- Data for Name: zupanija; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Virovitičko-podravska županija', 'Virovitica', 84836, 3, 13, 188, 2024, 'Igor Andrović', 'Virovitičko-podravska_županija', 7641);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Zagrebačka županija', 'Grad Zagreb', 317606, 9, 25, 694, 3060, 'Stjepan Kožić', 'Zagrebačka_županija', 10358);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Karlovačka županija', 'Karlovac', 128899, 5, 17, 649, 3622, 'Damir Jelić', 'Karlovačka_županija', 10158);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Koprivničko-križevačka županija', 'Koprivnica', 115584, 3, 22, 264, 1748, 'Darko Koren', 'Koprivničko-križevačka_županija', 10583);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Brodsko-posavska županija', 'Slavonski Brod', 158575, 2, 26, 185, 2030, 'Danijel Marušić', 'Brodsko-posavska_županija', 7746);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Međimurska županija', 'Čakovec', 113804, 3, 22, 131, 729, 'Matija Posavec', 'Međimurska_županija', 10847);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Dubrovačko-neretvanska županija', 'Dubrovnik', 122568, 5, 17, 230, 1781, 'Nikola Dobroslavić', 'Dubrovačko-neretvanska_županija', 12415);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Vukovarsko-srijemska županija', 'Vukovar', 179521, 5, 26, 85, 2454, 'Božo Galić', 'Vukovarsko-srijemska_županija', 8003);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Ličko-senjska županija', 'Gospić', 50927, 4, 8, 255, 5353, 'Darko Milinović', 'Ličko-senjska_županija', 9831);
INSERT INTO public.zupanija (naziv, sjediste, stanovnistvo, brojgradova, brojopcina, brojnaselja, povrsina, zupan, wikipoveznica, gdppercapita) VALUES ('Požeško-slavonska županija', 'Požega', 78034, 5, 5, 277, 1823, 'Alojz Tomašević', 'Požeško-slavonska_županija', 7874);


--
-- TOC entry 2855 (class 2606 OID 16457)
-- Name: grad grad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grad
    ADD CONSTRAINT grad_pkey PRIMARY KEY (nazivgrada);


--
-- TOC entry 2853 (class 2606 OID 16452)
-- Name: zupanija zupanija_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zupanija
    ADD CONSTRAINT zupanija_pkey PRIMARY KEY (naziv);


--
-- TOC entry 2856 (class 2606 OID 16458)
-- Name: grad grad_naziv_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grad
    ADD CONSTRAINT grad_naziv_fkey FOREIGN KEY (naziv) REFERENCES public.zupanija(naziv);


-- Completed on 2020-10-27 01:54:09

--
-- PostgreSQL database dump complete
--

