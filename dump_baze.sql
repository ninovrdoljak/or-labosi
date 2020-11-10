--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

-- Started on 2020-11-10 05:37:36

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
-- TOC entry 200 (class 1259 OID 16469)
-- Name: grad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grad (
    nazivgrada character varying(50) NOT NULL,
    naziv character varying(50) NOT NULL,
    stanovnistvograd integer NOT NULL
);


ALTER TABLE public.grad OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16472)
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
-- TOC entry 2987 (class 0 OID 16469)
-- Dependencies: 200
-- Data for Name: grad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Orahovica', 'Virovitičko-podravska županija', 5304);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Slatina', 'Virovitičko-podravska županija', 13686);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Virovitica', 'Virovitičko-podravska županija', 21291);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Ivanić Grad', 'Zagrebačka županija', 14548);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Samobor', 'Zagrebačka županija', 37728);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Velika Gorica', 'Zagrebačka županija', 63514);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Dugo Selo', 'Zagrebačka županija', 17466);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Jastrebarsko', 'Zagrebačka županija', 16689);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Sveta Nedelja', 'Zagrebačka županija', 18059);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Sveti Ivan Zelina', 'Zagrebačka županija', 15959);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Vrbovec', 'Zagrebačka županija', 14797);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Zaprešić', 'Zagrebačka županija', 25223);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Karlovac', 'Karlovačka županija', 55705);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Duga resa', 'Karlovačka županija', 10583);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Ogulin', 'Karlovačka županija', 13915);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Slunj', 'Karlovačka županija', 5076);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Ozalj', 'Karlovačka županija', 7932);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Đurđevac', 'Koprivničko-križevačka županija', 8862);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Koprivnica', 'Koprivničko-križevačka županija', 30854);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Križevci', 'Koprivničko-križevačka županija', 21122);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Nova Gradiška', 'Brodsko-posavska županija', 14229);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Slavonski Brod', 'Brodsko-posavska županija', 59141);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Čakovec', 'Međimurska županija', 27104);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Prelog', 'Međimurska županija', 7871);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Mursko Središće', 'Međimurska županija', 6307);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Dubrovnik', 'Dubrovačko-neretvanska županija', 42615);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Korčula', 'Dubrovačko-neretvanska županija', 5663);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Metković', 'Dubrovačko-neretvanska županija', 16788);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Opuzen', 'Dubrovačko-neretvanska županija', 3254);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Ploče', 'Dubrovačko-neretvanska županija', 9415);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Ilok', 'Vukovarsko-srijemska županija', 6767);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Otok', 'Vukovarsko-srijemska županija', 7755);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Vinkovci', 'Vukovarsko-srijemska županija', 32029);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Županja', 'Vukovarsko-srijemska županija', 12090);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Vukovar', 'Vukovarsko-srijemska županija', 27683);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Gospić', 'Ličko-senjska županija', 12980);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Novalja', 'Ličko-senjska županija', 3335);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Otočac', 'Ličko-senjska županija', 4240);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Senj', 'Ličko-senjska županija', 8132);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Kutjevo', 'Požeško-slavonska županija', 6247);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Lipik', 'Požeško-slavonska županija', 6170);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Pakrac', 'Požeško-slavonska županija', 8460);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Požega', 'Požeško-slavonska županija', 26248);
INSERT INTO public.grad (nazivgrada, naziv, stanovnistvograd) VALUES ('Pleternica', 'Požeško-slavonska županija', 11323);


--
-- TOC entry 2988 (class 0 OID 16472)
-- Dependencies: 201
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
-- TOC entry 2853 (class 2606 OID 16476)
-- Name: grad grad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grad
    ADD CONSTRAINT grad_pkey PRIMARY KEY (nazivgrada);


--
-- TOC entry 2855 (class 2606 OID 16478)
-- Name: zupanija zupanija_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zupanija
    ADD CONSTRAINT zupanija_pkey PRIMARY KEY (naziv);


--
-- TOC entry 2856 (class 2606 OID 16479)
-- Name: grad grad_naziv_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grad
    ADD CONSTRAINT grad_naziv_fkey FOREIGN KEY (naziv) REFERENCES public.zupanija(naziv);


-- Completed on 2020-11-10 05:37:36

--
-- PostgreSQL database dump complete
--

