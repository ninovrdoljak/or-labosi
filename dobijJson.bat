docker exec -u postgres mojaBazaSpremnik psql -d ORlabos3 -c "COPY (WITH sq AS ( SELECT zup.*, array_agg(jsonb_build_object('id', gra.id, 'nazivgrada', gra.nazivgrada, 'stanovnistvograd', gra.stanovnistvograd)) as gradovi FROM zupanija zup JOIN grad gra ON zup.naziv = gra.naziv group by gra.naziv, zup.id ) SELECT to_jsonb(array_agg(sq)) FROM sq) TO STDOUT" > noviJson.json
