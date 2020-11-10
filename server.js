const express = require('express');
const bodyParser = require('body-parser')
const app = express();
var path = require('path');
var db = require('./db');
const PORT = 3000;


app.use(express.urlencoded({ extended: true }));
app.use(bodyParser.json());


app.get('/', function(req, res) {
  res.sendFile(__dirname + "/index.html");
});

app.get('/datatable', function(req, res) {
  res.sendFile(__dirname + "/datatable.html");
});

app.get('/datatable/JSON', function(req, res) {
    res.sendFile(__dirname + "/hrvatske_zupanije.json");
  });

app.post('/datatable/DB',async function(req, res, next) {
    try {
        let polje = [req.body.pretraga, req.body.atribut];
        console.log(polje[0]);
        console.log(polje[1]);
        // WHERE $2::text = $1::text;
        let novaNaredba = 'SELECT nazivgrada from grad;'; //, polje
        //let dobijRez1 = await db.pgPoolWrapper();
        let dobijRez = await db.query(novaNaredba);
        for (let row of dobijRez.rows) {
            console.log(row);
        }
        //res.json(dobijRez);
    } catch (err) {
    }
});

app.listen(PORT);