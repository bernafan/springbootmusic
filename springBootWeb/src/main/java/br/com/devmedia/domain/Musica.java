package br.com.devmedia.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "musicas")
public class Musica {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;
  
      @NotBlank
      @Size(min = 2, max = 50)
      @Column(nullable = false, length = 50)
      private String titulo;
      
      @NotBlank
      @Size(min = 2, max = 50)
      @Column(nullable = false, length = 50)
      private String banda;
  
      @Range(min = 0, max = 10)	// a nota da musica deve ter um valor inteiro entre 0 e 10
      @Column(nullable = false)
      private int nota;
  
      @ManyToOne		// muitas musicas podem pertencer a muitas playlist
      @JoinColumn(name = "id_playlist_fk")	// atributo playlist na tabela de musicas(tipo uma chave estrangeira). esse atributo que é passado como valor para o parametro mappedBy.
      private Playlist playlist;
      
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getBanda() {
		return banda;
	}

	public void setBanda(String banda) {
		this.banda = banda;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}    
}