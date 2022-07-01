package mongodb;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Aluno implements Serializable {

    private String nome;
//    private String data_nascimento;
    private Data_nascimento data_nascimento;
    private Curso curso;
    private List<String> notas;
    private List<Habilidades> habilidades;
    private Contato contato;

    public Data_nascimento getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Data_nascimento data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public static class Data_nascimento {

        private String $date;

        public String get$date() {
            return $date;
        }

        public void set$date(String $date) {
            this.$date = $date;
        }
    }

    public List<Habilidades> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidades> habilidades) {
        this.habilidades = habilidades;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

    public List<String> getNotas() {
        return notas;
    }

    public class Curso {

        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

    }

    public class Habilidades {

        private String nome;
        private String nivel;

        public String getNivel() {
            return nivel;
        }

        public void setNivel(String nivel) {
            this.nivel = nivel;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

    }

    public class Contato {

        private String endereco;
        private Collection<String> coordinates;
        private String type;

        public Collection<String> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Collection<String> coordinates) {
            this.coordinates = coordinates;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
