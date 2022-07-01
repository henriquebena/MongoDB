/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

/**
 *
 * @author desenvolvimento
 */
public class run {

    public static void main(String[] args) {

        ConexaoBanco con = new ConexaoBanco();
        MongoClient mongoClient = con.conectar();

        MongoDatabase database = mongoClient.getDatabase("alunos");
        //table
        MongoCollection<Document> alunos = database.getCollection("alunos");
        
        insertNovoAluno("Novo Aluno Teste", alunos);
        update("Novo Aluno Teste", alunos);
        delete("Update Aluno", alunos);

        //BUSCA. PASSAR FILTERS COMO WHERE, Não especificar o filter = busca full.
        FindIterable<Document> find = alunos.find(Filters.eq("nome", "Celina"));

        //converte o retorno json em obj
        for (Document doc : find) {
            String json2 = doc.toJson(JsonWriterSettings.builder().build());

            Aluno teste = new Gson().fromJson(json2, Aluno.class);
            System.out.println("parse feito : " + teste);
            System.out.println(json2);
            System.out.println(teste.getNome() + " " + teste.getCurso().getNome());

            mongoClient.close();
        }

        con.fecharConecao(mongoClient);

    }

    /*
    INSERT
     */
    public static void insertNovoAluno(String nomeAluno, MongoCollection<Document> doc) {

        //possivel criar uma list de document e usar insertMany
        //List<Document> docs = new ArrayList<>();
        //doc.insertMany(docs);
        Document novoAluno = new Document("nome", nomeAluno)
                .append("data_nascimento", new Date(2003, 10, 10))
                .append("curso", new Document("nome", "Historia"))
                .append("notas", Arrays.asList(10, 9, 8))
                .append("habilidades", Arrays.asList(
                        new Document().append("nome", "Ingles").append("nível", "Básico"),
                        new Document().append("nome", "Espanhol").append("nível", "Básico")));

        doc.insertOne(novoAluno);

        System.out.println("--------------------------------------");
        System.out.println("Aluno " + nomeAluno + " Inserido com sucesso.");
        FindIterable<Document> find = doc.find(Filters.eq("nome", nomeAluno));
        for (Document d : find) {
            System.out.println(d.toJson());
        }
        System.out.println("--------------------------------------");
    }

    /*
    DELETE
     */
    public static void delete(String param, MongoCollection<Document> doc) {

        //apagar varios
//        Bson or = Filters.or(Filters.eq("nome", id), Filters.eq("nome", "teste1"), Filters.eq("nome", "teste2"));
//        System.out.println(doc.deleteMany(or));
        //apagar um
        Bson filter = Filters.eq("nome", param);
        DeleteResult result = doc.deleteOne(filter);
        System.out.println(result);

        System.out.println("--------------------------------------");
        System.out.println("Aluno " + param + " Deletado com sucesso.");
        FindIterable<Document> find = doc.find();
        for (Document d : find) {
            System.out.println(d.toJson());
        }
        System.out.println("--------------------------------------");
    }

    public static void findOneAndDelete(String param, MongoCollection<Document> doc) {
//        procurar e apagar um
        Document doc1 = doc.findOneAndDelete(Filters.eq("nome", param));
        System.out.println(doc1.toJson(JsonWriterSettings.builder().indent(true).build()));

        System.out.println("--------------------------------------");
        System.out.println("Aluno " + param + " Deletado com sucesso.");
        System.out.println(doc.find());
        System.out.println("--------------------------------------");
    }

    public static void update(String param, MongoCollection<Document> doc) {

        Document novoAluno = new Document("$set", new Document("nome", "Update Aluno"));

        UpdateResult updateOne = doc.updateOne(Filters.eq("nome", param), novoAluno);
        //possui tambem o updateMany, é possivel informar a alteração + where
        
        System.out.println("Resultado do update: " + updateOne);
        FindIterable<Document> find = doc.find(Filters.eq("nome", "Update Aluno"));
        for (Document d : find) {
            System.out.println(d.toJson());
        }

    }

}
