--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-01-19 02:43:53

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

DROP DATABASE "ORlab3";
--
-- TOC entry 3016 (class 1262 OID 16650)
-- Name: ORlab3; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "ORlab3" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Croatian_Croatia.1250';


ALTER DATABASE "ORlab3" OWNER TO postgres;

\connect "ORlab3"

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
-- TOC entry 201 (class 1259 OID 18051)
-- Name: grad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grad (
    id bigint NOT NULL,
    naziv character varying(255),
    nazivgrada character varying(255),
    stanovnistvograd integer NOT NULL
);


ALTER TABLE public.grad OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 18049)
-- Name: grad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.grad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grad_id_seq OWNER TO postgres;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 200
-- Name: grad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.grad_id_seq OWNED BY public.grad.id;


--
-- TOC entry 203 (class 1259 OID 18062)
-- Name: slika; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.slika (
    id bigint NOT NULL,
    date timestamp without time zone,
    naziv character varying(255)
);


ALTER TABLE public.slika OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 18060)
-- Name: slika_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.slika_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.slika_id_seq OWNER TO postgres;

--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 202
-- Name: slika_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.slika_id_seq OWNED BY public.slika.id;


--
-- TOC entry 205 (class 1259 OID 18070)
-- Name: zupanija; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zupanija (
    id bigint NOT NULL,
    brojgradova integer NOT NULL,
    brojnaselja integer NOT NULL,
    brojopcina integer NOT NULL,
    gdppercapita integer NOT NULL,
    naziv character varying(255),
    povrsina integer NOT NULL,
    sjediste character varying(255),
    stanovnistvo integer NOT NULL,
    wikipoveznica character varying(255),
    zupan character varying(255)
);


ALTER TABLE public.zupanija OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 18068)
-- Name: zupanija_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zupanija_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.zupanija_id_seq OWNER TO postgres;

--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 204
-- Name: zupanija_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zupanija_id_seq OWNED BY public.zupanija.id;


--
-- TOC entry 2864 (class 2604 OID 18054)
-- Name: grad id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grad ALTER COLUMN id SET DEFAULT nextval('public.grad_id_seq'::regclass);


--
-- TOC entry 2865 (class 2604 OID 18065)
-- Name: slika id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.slika ALTER COLUMN id SET DEFAULT nextval('public.slika_id_seq'::regclass);


--
-- TOC entry 2866 (class 2604 OID 18073)
-- Name: zupanija id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zupanija ALTER COLUMN id SET DEFAULT nextval('public.zupanija_id_seq'::regclass);


--
-- TOC entry 3006 (class 0 OID 18051)
-- Dependencies: 201
-- Data for Name: grad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.grad VALUES (1, 'Virovitičko-podravska županija', 'Orahovica', 5304);
INSERT INTO public.grad VALUES (2, 'Virovitičko-podravska županija', 'Slatina', 13686);
INSERT INTO public.grad VALUES (3, 'Virovitičko-podravska županija', 'Virovitica', 21291);
INSERT INTO public.grad VALUES (4, 'Zagrebačka županija', 'Ivanić Grad', 14548);
INSERT INTO public.grad VALUES (5, 'Zagrebačka županija', 'Samobor', 37728);
INSERT INTO public.grad VALUES (6, 'Zagrebačka županija', 'Velika Gorica', 63514);
INSERT INTO public.grad VALUES (7, 'Zagrebačka županija', 'Dugo Selo', 17466);
INSERT INTO public.grad VALUES (8, 'Zagrebačka županija', 'Jastrebarsko', 16689);
INSERT INTO public.grad VALUES (9, 'Zagrebačka županija', 'Sveta Nedelja', 18059);
INSERT INTO public.grad VALUES (10, 'Zagrebačka županija', 'Sveti Ivan Zelina', 15959);
INSERT INTO public.grad VALUES (11, 'Zagrebačka županija', 'Vrbovec', 14797);
INSERT INTO public.grad VALUES (12, 'Zagrebačka županija', 'Zaprešić', 25223);
INSERT INTO public.grad VALUES (13, 'Karlovačka županija', 'Karlovac', 55705);
INSERT INTO public.grad VALUES (14, 'Karlovačka županija', 'Duga resa', 10583);
INSERT INTO public.grad VALUES (15, 'Karlovačka županija', 'Ogulin', 13915);
INSERT INTO public.grad VALUES (16, 'Karlovačka županija', 'Slunj', 5076);
INSERT INTO public.grad VALUES (17, 'Karlovačka županija', 'Ozalj', 7932);
INSERT INTO public.grad VALUES (18, 'Koprivničko-križevačka županija', 'Đurđevac', 8862);
INSERT INTO public.grad VALUES (19, 'Koprivničko-križevačka županija', 'Koprivnica', 30854);
INSERT INTO public.grad VALUES (20, 'Koprivničko-križevačka županija', 'Križevci', 21122);
INSERT INTO public.grad VALUES (21, 'Brodsko-posavska županija', 'Nova Gradiška', 14229);
INSERT INTO public.grad VALUES (22, 'Brodsko-posavska županija', 'Slavonski Brod', 59141);
INSERT INTO public.grad VALUES (23, 'Međimurska županija', 'Čakovec', 27104);
INSERT INTO public.grad VALUES (24, 'Međimurska županija', 'Prelog', 7871);
INSERT INTO public.grad VALUES (25, 'Međimurska županija', 'Mursko Središće', 6307);
INSERT INTO public.grad VALUES (26, 'Dubrovačko-neretvanska županija', 'Dubrovnik', 42615);
INSERT INTO public.grad VALUES (27, 'Dubrovačko-neretvanska županija', 'Korčula', 5663);
INSERT INTO public.grad VALUES (28, 'Dubrovačko-neretvanska županija', 'Metković', 16788);
INSERT INTO public.grad VALUES (29, 'Dubrovačko-neretvanska županija', 'Opuzen', 3254);
INSERT INTO public.grad VALUES (30, 'Dubrovačko-neretvanska županija', 'Ploče', 9415);
INSERT INTO public.grad VALUES (31, 'Vukovarsko-srijemska županija', 'Ilok', 6767);
INSERT INTO public.grad VALUES (32, 'Vukovarsko-srijemska županija', 'Otok', 7755);
INSERT INTO public.grad VALUES (33, 'Vukovarsko-srijemska županija', 'Vinkovci', 32029);
INSERT INTO public.grad VALUES (34, 'Vukovarsko-srijemska županija', 'Županja', 12090);
INSERT INTO public.grad VALUES (35, 'Vukovarsko-srijemska županija', 'Vukovar', 27683);
INSERT INTO public.grad VALUES (36, 'Ličko-senjska županija', 'Gospić', 12980);
INSERT INTO public.grad VALUES (37, 'Ličko-senjska županija', 'Novalja', 3335);
INSERT INTO public.grad VALUES (38, 'Ličko-senjska županija', 'Otočac', 4240);
INSERT INTO public.grad VALUES (39, 'Ličko-senjska županija', 'Senj', 8132);
INSERT INTO public.grad VALUES (40, 'Požeško-slavonska županija', 'Kutjevo', 6247);
INSERT INTO public.grad VALUES (41, 'Požeško-slavonska županija', 'Lipik', 6170);
INSERT INTO public.grad VALUES (42, 'Požeško-slavonska županija', 'Pakrac', 8460);
INSERT INTO public.grad VALUES (43, 'Požeško-slavonska županija', 'Požega', 26248);
INSERT INTO public.grad VALUES (44, 'Požeško-slavonska županija', 'Pleternica', 11323);


--
-- TOC entry 3008 (class 0 OID 18062)
-- Dependencies: 203
-- Data for Name: slika; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3010 (class 0 OID 18070)
-- Dependencies: 205
-- Data for Name: zupanija; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.zupanija VALUES (1, 3, 188, 13, 7641, 'Virovitičko-podravska županija', 2024, 'Virovitica', 84836, 'Virovitičko-podravska_županija', 'Igor Andrović');
INSERT INTO public.zupanija VALUES (2, 9, 694, 25, 10358, 'Zagrebačka županija', 3060, 'Grad Zagreb', 317606, 'Zagrebačka_županija', 'Stjepan Kožić');
INSERT INTO public.zupanija VALUES (3, 5, 649, 17, 10158, 'Karlovačka županija', 3622, 'Karlovac', 128899, 'Karlovačka_županija', 'Damir Jelić');
INSERT INTO public.zupanija VALUES (4, 3, 264, 22, 10583, 'Koprivničko-križevačka županija', 1748, 'Koprivnica', 115584, 'Koprivničko-križevačka_županija', 'Darko Koren');
INSERT INTO public.zupanija VALUES (5, 2, 185, 26, 7746, 'Brodsko-posavska županija', 2030, 'Slavonski Brod', 158575, 'Brodsko-posavska_županija', 'Danijel Marušić');
INSERT INTO public.zupanija VALUES (6, 3, 131, 22, 10847, 'Međimurska županija', 729, 'Čakovec', 113804, 'Međimurska_županija', 'Matija Posavec');
INSERT INTO public.zupanija VALUES (7, 5, 230, 17, 12415, 'Dubrovačko-neretvanska županija', 1781, 'Dubrovnik', 122568, 'Dubrovačko-neretvanska_županija', 'Nikola Dobroslavić');
INSERT INTO public.zupanija VALUES (8, 5, 85, 26, 8003, 'Vukovarsko-srijemska županija', 2454, 'Vukovar', 179521, 'Vukovarsko-srijemska_županija', 'Božo Galić');
INSERT INTO public.zupanija VALUES (9, 4, 255, 8, 9831, 'Ličko-senjska županija', 5353, 'Gospić', 50927, 'Ličko-senjska_županija', 'Darko Milinović');
INSERT INTO public.zupanija VALUES (10, 5, 277, 5, 7874, 'Požeško-slavonska županija', 1823, 'Požega', 78034, 'Požeško-slavonska_županija', 'Alojz Tomašević');


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 200
-- Name: grad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.grad_id_seq', 44, true);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 202
-- Name: slika_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.slika_id_seq', 1, false);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 204
-- Name: zupanija_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.zupanija_id_seq', 10, true);


--
-- TOC entry 2868 (class 2606 OID 18059)
-- Name: grad grad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grad
    ADD CONSTRAINT grad_pkey PRIMARY KEY (id);


--
-- TOC entry 2870 (class 2606 OID 18067)
-- Name: slika slika_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.slika
    ADD CONSTRAINT slika_pkey PRIMARY KEY (id);


--
-- TOC entry 2872 (class 2606 OID 18080)
-- Name: zupanija ukhuixt7sp2wx57r0hysmhdf6xe; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zupanija
    ADD CONSTRAINT ukhuixt7sp2wx57r0hysmhdf6xe UNIQUE (naziv);


--
-- TOC entry 2874 (class 2606 OID 18078)
-- Name: zupanija zupanija_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zupanija
    ADD CONSTRAINT zupanija_pkey PRIMARY KEY (id);


-- Completed on 2021-01-19 02:43:53

--
-- PostgreSQL database dump complete
--

