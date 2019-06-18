package br.com.devmedia.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity 	// representa uma entidade e no banco existe uma tabela chamada playlist
@Table(name = "playlists")
public class Playlist {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)  	// o banco vai auto incrementar esse atributo no banco
      private long id;
  
      @NotBlank
      @Size(min = 2, max = 60)
      @Column(nullable = false, length = 60)
      private String nome;
  
      @NotBlank
      @Column(nullable = false)
      private String descricao;
  
      @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)  // uma playlist pode ter uma ou muitas musicas 1:N  
      																// MappedBy informa o atributo na outra ponta do relacionamento que representa a chave estrangeira
      																// cascadeType all faz com que toda alteracao sobre uma entendade é propagada a entetidade relacionada.
      private List<Musica> musicas;
  
      public long getId() {
          return id;
      }
   
      public void setId(long id) {
          this.id = id;
      }
   
      public String getNome() {
          return nome;
      }
   
      public void setNome(String nome) {
          this.nome = nome;
      }
   
      public String getDescricao() {
          return descricao;
      }
   
      public void setDescricao(String descricao) {
          this.descricao = descricao;
      }
   
      public List<Musica> getMusicas() {
          return musicas;
      }
   
      public void setMusicas(List<Musica> musicas) {
          this.musicas = musicas;
      }
  
}
