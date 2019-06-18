package br.com.devmedia.service;

import br.com.devmedia.dao.PlaylistDao;
import br.com.devmedia.domain.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service    // representa uma classe de servico par ao spring
@Transactional  //Spring gerencia as transacoes dos metodos dessa classe
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired  // Nossa classe passa a depender de playlist dao. O spring gerencia isso.
                // Uma instancia de playlist dao ￿é injetada nesse atribudo quando essa calsse for criada
                // Assim nao foi precisa escrever new mais um construtor d epplaylist dao.
                // o tipo do atributo ￿é interface mas quando o spring injetar ser￿á impl.
                // O codigo fica menos acoplado, isos facilita a troca do mecanismo de persistencia em outro local.
    private PlaylistDao playlistDao;

    @Override
    public void salvar(Playlist playlist) {
        playlistDao.salvar(playlist);
    }

    @Override
    @Transactional(readOnly = true) // Indica que as transacoes sao apenas para leitura.
                                    // AS ANOTACAOES SAO COLOCADAS NOS SERVICOS PQ SAO ESSAS CLASSES QUE USAM OS DAOS.
                                    // SE O CONTROLE TRANSACIONAL FICASSE NO DAO, DUAS TRANSACOES SERIAM ABERTAS AO EXECUTAR OPERACAO NO SERVICE.
                                    // ISSO AFETARIA A ATOMICIDADE, CONSISTENCIA, ISOLAMENTO E DURABILIDADE DA OPERACAO EXECUTADA.

    public List<Playlist> recuperar() {

        return playlistDao.recuperar();
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist recuperarPorId(long id) {

        return playlistDao.recuperarPorID(id);
    }

    @Override
    public void atualizar(Playlist playlist) {

        playlistDao.atualizar(playlist);
    }

    @Override
    public void excluir(long id) {

        playlistDao.excluir(id);
    }
}

