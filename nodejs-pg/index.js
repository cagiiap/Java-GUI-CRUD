
const express = require('express');
const app = express();
var path=require('path');

//config
        app.set('view engine', 'ejs');
        app.use(express.json());
        app.use(express.static(path.join(__dirname+"/")));
    //Body parser
        app.use(express.urlencoded({extended: true}));

    //Conectando com o banco de dados:
        const Client = require('pg').Client;
        
//Rotas
    app.get('/', function(req, res){
        res.sendFile(__dirname+ "/pages/index.html");
    })  

    app.post('/add', function(req, res){
        const cliente = new Client({
            connectionString: "postgres://kjzevqklgfrvoi:bdb8aa2e6f52392eaef6cb56fae6a93df90ba83cfaf4c26cc51f5c4f284e87ee@ec2-35-170-85-206.compute-1.amazonaws.com:5432/d5mka0s4ikomrq",
            ssl: {
                rejectUnauthorized: false
            }
        })
        res.send("nome: " + req.body.conteudo + "<br>Valor: " + req.body.titulo);
        
        var teste = req.body.conteudo;
        var teste_2 = req.body.titulo;
        cliente.connect();
        cliente.query("insert into login (username, senha) values ('"+ teste_2 + "', '"+ teste +"')").finally(() => {
            cliente.end();
            })
    })

app.listen(8081, function(){
    console.log("servidor rodando");
})

